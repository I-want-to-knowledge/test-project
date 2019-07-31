package com.geo.source.rong_chuang.push;

import com.alibaba.fastjson.JSONObject;
import com.geo.source.rong_chuang.push.dto.PushMessageResponse;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * 消息推送客户端
 *
 * @author YanZhen
 * @date 2019/07/11 11:24
 */
class PushClient {

    private static Logger logger = LoggerFactory.getLogger(PushClient.class);

    /**
     * 发送消息的状态
     */
    protected enum Ret {
        /**
         * 成功
         */
        SUCCESS(200, "SUCCESS"),
        FAIL(0, "FAIL");

        private int code;
        private String value;
        Ret(int code, String value) {
            this.code = code;
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }
    }

    /**
     * The user agent
     */
    private String userAgent = "Mozilla/5.0";

    /**
     * The host
     */
    private String host = "http://msg.umeng.com";

    /**
     * The upload path
     */
    private String uploadPath = "/upload";

    /**
     * The post path
     */
    private String postPath = "/api/send";

    /**
     * The post path
     */
    private String statusPath = "/api/status";

    boolean send(UmengNotification msg) {
        final JSONObject jsonObject = pushSend(msg);
        if (jsonObject == null) {
            return false;
        }
        if (Ret.SUCCESS.getValue().equals(jsonObject.getString("ret"))) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * Upload file with device_tokens to Umeng
     *
     * @param appkey          Umeng appKey
     * @param appMasterSecret Umeng appMasterSecret
     * @param contents        contents
     * @return 在Umeng的文件Id，文件保存两个月
     * @throws Exception 必须处理异常
     */
    String uploadContents(String appkey, String appMasterSecret, String contents) throws Exception {
        CloseableHttpClient client = null;
        HttpPost post = null;
        String fileId;
        try {
            client = HttpClients.createDefault();

            // Construct the json string
            JSONObject uploadJson = new JSONObject();
            uploadJson.put("appkey", appkey);
            String timestamp = Integer.toString((int) (System.currentTimeMillis() / 1000));
            uploadJson.put("timestamp", timestamp);
            uploadJson.put("content", contents);

            // Construct the request
            String postBody = uploadJson.toString();

            // 访问url拼接
            String url = host + uploadPath;
            String sign = DigestUtils.md5Hex(("POST" + url + postBody + appMasterSecret).getBytes(StandardCharsets.UTF_8));
            url = url + "?sign=" + sign;

            // http请求
            post = new HttpPost(url);
            post.setHeader("User-Agent", userAgent);
            StringEntity se = new StringEntity(postBody, "UTF-8");
            post.setEntity(se);

            // Send the post request and get the response
            HttpResponse response = client.execute(post);
            logger.info("Response Code : " + response.getStatusLine().getStatusCode());
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            // 结果解析
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            logger.info(result.toString());

            // Decode response string and get file_id from it
            JSONObject respJson = JSONObject.parseObject(result.toString());
            String ret = respJson.getString("ret");
            if (!Ret.SUCCESS.getValue().equals(ret)) {
                throw new Exception("Failed to upload file");
            }
            JSONObject data = respJson.getJSONObject("data");
            fileId = data.getString("file_id");

            // 谁打开谁关闭
        } finally {
            try {
                if (post != null) {
                    post.releaseConnection();
                }
                if (client != null) {
                    client.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return fileId;
    }

    /**
     *  查询任务的状态
     * @param appkey appkey
     * @param taskId 任务Id
     * @return 任务的状态
     * @throws Exception 检查
     */
    PushMessageResponse status(String appkey, String appMasterSecret, String taskId) throws Exception {
        CloseableHttpClient client = null;
        HttpPost post = null;
        PushMessageResponse status;
        try {
            client = HttpClients.createDefault();

            // Construct the json string
            JSONObject uploadJson = new JSONObject();
            uploadJson.put("appkey", appkey);
            uploadJson.put("timestamp", System.currentTimeMillis());
            uploadJson.put("task_id", taskId);

            // Construct the request
            String postBody = uploadJson.toString();

            // 访问url拼接
            String url = host + statusPath;
            String sign = DigestUtils.md5Hex(("POST" + url + postBody + appMasterSecret).getBytes(StandardCharsets.UTF_8));
            url = url + "?sign=" + sign;

            // http请求
            post = new HttpPost(url);
            post.setHeader("User-Agent", userAgent);
            StringEntity se = new StringEntity(postBody, "UTF-8");
            post.setEntity(se);

            // Send the post request and get the response
            HttpResponse response = client.execute(post);
            logger.info("Response Code : " + response.getStatusLine().getStatusCode());
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            // 结果解析
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            logger.info("获取消息状态返回值：{}", result.toString());

            // Decode response string and get file_id from it
            JSONObject respJson = JSONObject.parseObject(result.toString());
            status = parsePushMessage(respJson);

        } finally {
            try {
                // 谁打开谁关闭
                if (post != null) {
                    post.releaseConnection();
                }
                if (client != null) {
                    client.close();
                }
            } catch (IOException e) {
                logger.error("获取消息状态发生异常！", e);
            }
        }
        return status;
    }

    /**
     * 发送消息
     * @param msg 发送的内容
     * @return 响应信息
     */
    PushMessageResponse sendAndRespond(UmengNotification msg) {
        PushMessageResponse pushMessageResponse = new PushMessageResponse();
        final JSONObject jsonObject = pushSend(msg);
        PushMessageResponse status;
        if (jsonObject != null) {
            status = parsePushMessage(jsonObject);
        } else {
            status = null;
        }

        return status;

    }

    /**
     * 解析Umeng返回信息
     * @param jsonObject 返回信息
     * @return 映射实体对象
     */
    private PushMessageResponse parsePushMessage(JSONObject jsonObject) {
        PushMessageResponse status = new PushMessageResponse();
        // Decode response string and get file_id from it
        String ret = jsonObject.getString("ret");
        status.setRet(ret);
        JSONObject data = jsonObject.getJSONObject("data");
        if (Ret.SUCCESS.getValue().equals(ret)) {
            status.setTaskId(data.getString("task_id"));
            status.setStatus(data.getShort("status"));
            status.setSentCount(data.getInteger("sent_count"));
            status.setOpenCount(data.getInteger("open_count"));
            status.setDismissCount(data.getInteger("dismiss_count"));
            status.setTotalCount(data.getInteger("total_count"));
        } else {
            // throw new Exception("Failed to upload file");
            status.setErrorCode(data.getString("error_code"));
            status.setErrorMsg(data.getString("error_msg"));
        }

        return status;
    }

    /**
     * push发送
     * @param msg 消息体
     * @return 响应信息
     */
    private JSONObject pushSend(UmengNotification msg) {
        CloseableHttpClient client = null;
        HttpPost post = null;
        boolean flag = false;
        try {
            client = HttpClients.createDefault();
            String timestamp = Integer.toString((int) (System.currentTimeMillis() / 1000));
            msg.setPredefinedKeyValue("timestamp", timestamp);
            String url = host + postPath;
            String postBody = msg.getPostBody();
            String sign = DigestUtils.md5Hex(("POST" + url + postBody + msg.getAppMasterSecret()).getBytes(StandardCharsets.UTF_8));
            url = url + "?sign=" + sign;
            post = new HttpPost(url);
            post.setHeader("User-Agent", userAgent);
            StringEntity se = new StringEntity(postBody, "UTF-8");
            post.setEntity(se);
            HttpResponse response = client.execute(post);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            logger.info(result.toString());
            return JSONObject.parseObject(result.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (post != null) {
                    post.releaseConnection();
                }
                if (client != null) {
                    client.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
