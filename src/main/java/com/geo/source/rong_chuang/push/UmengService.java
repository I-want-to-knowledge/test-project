package com.geo.source.rong_chuang.push;

import com.geo.source.rong_chuang.push.android.AndroidCustomizedcast;
import com.geo.source.rong_chuang.push.android.AndroidFilecast;
import com.geo.source.rong_chuang.push.dto.PushMessageConstants;
import com.geo.source.rong_chuang.push.dto.PushMessageResponse;
import com.geo.source.rong_chuang.push.ios.IOSCustomizedcast;
import com.geo.source.rong_chuang.push.ios.IOSFilecast;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息推送服务
 *
 * @author YanZhen
 * @date 2019/07/11 11:24
 */
public final class UmengService {

    private static Logger logger = LoggerFactory.getLogger(UmengService.class);
    private static PushClient client = new PushClient();

    private static String appkey_ios = "5434f700fd98c5364c03acd3";
    private static String appMasterSecret_ios = "fab7k6tedc1w0llqt0yzobtozmcogxsk";
    private static String appkey_android = "52020b5256240ba84e009d22";
    private static String appMasterSecret_android = "sowabcng630hx3swloy1gyhl2gplfzdw";

    /**
     * Android普通推送
     *
     * @param alias 身份证号
     * @param str   消息体
     * @param title 消息标题
     * @return 发送是否成功
     * @throws Exception 检查
     */
    public static boolean sendAndroidCustomizedcast(String alias, String str, String title) throws Exception {
        logger.info("Android的普通推送,参数：[alias={}, str={}]", alias, str);
        AndroidCustomizedcast customizedcast = new AndroidCustomizedcast(appkey_android, appMasterSecret_android);
        customizedcast.setAlias(alias, "IDCARD");
        customizedcast.setTicker(str);
        customizedcast.setTitle(title);
        customizedcast.setText(str);
        customizedcast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
        // customizedcast.setTestMode();
        customizedcast.setProductionMode();
        return client.send(customizedcast);
    }

    /**
     * IOS普通推送
     *
     * @param alias 身份证号或token，用‘,’隔开
     * @param str   消息内容
     * @return 是否成功
     * @throws Exception 检查
     */
    public static boolean sendIOSCustomizedcast(String alias, String str) throws Exception {
        logger.info("IOS的普通推送,参数：[alias={}, str={}]", alias, str);
        IOSCustomizedcast customizedcast = new IOSCustomizedcast(appkey_ios, appMasterSecret_ios);
        customizedcast.setAlias(alias, "IDCARD");
        customizedcast.setAlert(str);
        customizedcast.setBadge(0);
        customizedcast.setSound("default");
        // customizedcast.setTestMode();
        customizedcast.setProductionMode();
        return client.send(customizedcast);
    }

    /**
     * ANDROID带参数的推送
     *
     * @param alias 证件号/token
     * @param str   消息内容
     * @param extra 自定义信息
     * @param id    pushLogId
     * @param title 消息标题
     * @return 是否发送成功
     * @throws Exception 检查
     */
    public static boolean sendAndroidCustomized(String alias, String str, String extra, String id, String title) throws Exception {
        logger.info("Android的带参数推送,参数：[alias={}, str={}, extra={" + extra + "}]", alias, str);
        if (StringUtils.isEmpty(extra)) {
            return sendAndroidCustomizedcast(alias, str, title);
        }

        AndroidCustomizedcast customizedcast = new AndroidCustomizedcast(appkey_android, appMasterSecret_android);
        customizedcast.setAlias(alias, "IDCARD");
        customizedcast.setTicker(str);
        customizedcast.setTitle(title);
        customizedcast.setText(str);
        customizedcast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
        // customizedcast.setTestMode();
        customizedcast.setProductionMode();

        String[] type = extra.split("[?]");
        if (type[0].equals("url")) {
            customizedcast.setUrl(type[1]);
        } else {
            Map<String, Object> map = new HashMap<String, Object>();
            if (type.length > 1) {
                map = getUrlParams(type[1]);
            }
            if (id != null) {
                map.put("pushLogId", id);
            }

            customizedcast.setExtraField(map);
            customizedcast.goActivityAfterOpen(type[0]);
        }

        return client.send(customizedcast);
    }

    /**
     * IOS带参数的推送
     *
     * @param alias And if you have many alias, you can also upload a file containing these alias,
     *              then use file_id to send customized notification.
     * @param str   消息内容
     * @param extra 自定义信息
     * @param id    pushLogId
     * @return 是否推送成功
     * @throws Exception 检查
     */
    public static boolean sendIOSCustomized(String alias, String str, String extra, String id) throws Exception {
        logger.info("IOS的带参数推送,参数：[alias={}, str={}, extra={" + extra + "}]", alias, str);
        if (StringUtils.isEmpty(extra)) {
            return sendIOSCustomizedcast(alias, str);
        }

        IOSCustomizedcast customizedcast = new IOSCustomizedcast(appkey_ios, appMasterSecret_ios);

        customizedcast.setAlias(alias, "IDCARD");
        customizedcast.setAlert(str);
        customizedcast.setBadge(0);
        customizedcast.setSound("default");
        // customizedcast.setTestMode();
        customizedcast.setProductionMode();

        String[] type = extra.split("[?]");
        if (type[0].equals("url")) {
            customizedcast.setUrl(type[1]);
        } else {
            Map<String, Object> map = new HashMap<>();
            if (type.length > 1) {
                map = getUrlParams(type[1]);
            }
            if (id != null) {
                map.put("pushLogId", id);
            }

            customizedcast.setZidingyi(type[0], map);
        }
        return client.send(customizedcast);
    }

    /**
     * URL参数解析，例如：a=1&b=2&c=3
     *
     * @param param 解析参数
     * @return 键值对返回
     */
    private static Map<String, Object> getUrlParams(String param) {
        Map<String, Object> map = new HashMap<String, Object>(0);
        if (StringUtils.isEmpty(param)) {
            return map;
        }
        String[] params = param.split("&");
        for (String s : params) {
            String[] p = s.split("=");
            if (p.length == 2) {
                map.put(p[0], p[1]);
            }
        }
        return map;
    }

    /**
     * Android自定义文件上传
     *
     * @param contents  消息体："aa" + "\n" + "bb" + "\n" + "alias"
     * @param aliasType 身份证/token，自定义类型
     * @param ticker    Android customizedcast ticker
     * @param title     中文的title
     * @param text      Android customizedcast text
     * @throws Exception 抛出异常
     */
    public static boolean sendAndroidCustomizedcastFile(
            String contents, String aliasType, String ticker, String title, String text) throws Exception {
        // And if you have many alias, you can also upload a file containing these alias, then
        // use file_id to send customized notification.
        String fileId = androidUploadContents(contents);

        return androidSend(fileId, aliasType, ticker, title, text);
    }

    /**
     * Android默认格式文件上传
     *
     * @param contents 消息体："aa" + "\n" + "bb"
     * @param ticker   通知提示：Android filecast ticker
     * @param title    标题：中文的title
     * @param text     "Android filecast text"
     * @return 是否推送成果
     * @throws Exception 抛出异常
     */
    public static boolean sendAndroidFilecast(String contents, String ticker, String title, String text) throws Exception {
        AndroidFilecast filecast = new AndroidFilecast(appkey_android, appMasterSecret_android);
        // upload your device tokens, and use '\n' to split them if there are multiple tokens
        String fileId = client.uploadContents(appkey_android, appMasterSecret_android, contents);
        filecast.setFileId(fileId);
        filecast.setTicker(ticker);
        filecast.setTitle(title);
        filecast.setText(text);
        filecast.goAppAfterOpen();
        filecast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
        filecast.setProductionMode();
        return client.send(filecast);
    }

    /**
     * IOS文件上传
     *
     * @param contents 消息体（只包括token）："aa" + "\n" + "bb"
     * @param alert    警告：IOS 文件播测试
     * @return 是否执行成果
     * @throws Exception 抛出异常
     */
    public static boolean sendIOSFilecast(String contents, String alert) throws Exception {
        // upload your device tokens, and use '\n' to split them if there are multiple tokens
        String fileId = client.uploadContents(appkey_ios, appMasterSecret_ios, contents);

        IOSFilecast filecast = new IOSFilecast(appkey_ios, appMasterSecret_ios);
        filecast.setFileId(fileId);
        filecast.setAlert(alert);
        filecast.setBadge(0);
        filecast.setSound("default");
        // set 'production_mode' to 'true' if your app is under production mode
        filecast.setProductionMode();
        return client.send(filecast);
    }

    /**
     * IOS自定义推送
     *
     * @param contents  身份证号或token
     * @param aliasType alias类型，自定义
     * @param alert     消息内容
     * @throws Exception 检查
     */
    public static Boolean sendIOSCustomizedcastFile(String contents, String aliasType, String alert) throws Exception {
        // 推送Umeng平台
        String fileId = iosUploadContents(contents);

        // 发送消息
        return iosSend(fileId, aliasType, alert);
    }

    /**
     * IOS使用者，证件/token组上传
     *
     * @param contents 证件/token组
     * @return 上传Umeng后返回fileId，该fileId两个月过期
     * @throws Exception 检查
     */
    public static String iosUploadContents(String contents) throws Exception {
        return client.uploadContents(appkey_ios, appMasterSecret_ios, contents);
    }

    /**
     * IOS根据fileId给用户发送消息
     *
     * @param fileId    Umeng返回的文件Id
     * @param aliasType 自定义文件中内容类型（身份证号/token）
     * @param alert     消息体
     * @return 发送是否成功
     * @throws Exception 检查
     */
    public static boolean iosSend(String fileId, String aliasType, String alert) throws Exception {

        // 初始化IOS自定义实体
        IOSCustomizedcast customizedcast = new IOSCustomizedcast(appkey_ios, appMasterSecret_ios);

        // Umeng返回的文件Id
        customizedcast.setFileId(fileId, aliasType);
        customizedcast.setAlert(alert);
        customizedcast.setBadge(0);
        customizedcast.setSound("default");
        customizedcast.setProductionMode();
        return client.send(customizedcast);
    }

    /**
     * IOS根据fileId给用户发送消息
     *
     * @param fileId    Umeng返回的文件Id
     * @param aliasType 自定义文件中内容类型（身份证号/token）
     * @param alert     消息体
     * @return 响应信息
     * @throws Exception 检查
     */
    public static PushMessageResponse iosSendFileId(String fileId, String aliasType, String alert) throws Exception {

        // 初始化IOS自定义实体
        IOSCustomizedcast customizedcast = new IOSCustomizedcast(appkey_ios, appMasterSecret_ios);

        // Umeng返回的文件Id
        customizedcast.setFileId(fileId, aliasType);
        customizedcast.setAlert(alert);
        customizedcast.setBadge(0);
        customizedcast.setSound("default");
        customizedcast.setProductionMode();
        return client.sendAndRespond(customizedcast);
    }

    /**
     * IOS根据fileId给用户发送消息
     *
     * @param alias     用户上传的证件号
     * @param aliasType 自定义文件中内容类型（身份证号/token）
     * @param alert     消息体
     * @return 发送是否成功
     * @throws Exception 检查
     */
    public static PushMessageResponse iosSendAlias(String alias, String aliasType, String alert) throws Exception {

        // 初始化IOS自定义实体
        IOSCustomizedcast customizedcast = new IOSCustomizedcast(appkey_ios, appMasterSecret_ios);

        // Umeng返回的文件Id
        customizedcast.setAlias(alias, aliasType);
        customizedcast.setAlert(alert);
        customizedcast.setBadge(0);
        customizedcast.setSound("default");
        customizedcast.setProductionMode();
        return client.sendAndRespond(customizedcast);
    }

    /**
     * Android使用者，证件/token组上传
     *
     * @param contents 证件/token组
     * @return 上传Umeng后返回fileId，该fileId两个月过期
     * @throws Exception 检查
     */
    public static String androidUploadContents(String contents) throws Exception {
        return client.uploadContents(appkey_android, appMasterSecret_android, contents);
    }

    /**
     * android根据fileId给用户发送消息
     *
     * @param fileId    Umeng返回的文件Id
     * @param aliasType 自定义文件中内容类型（身份证号/token）
     * @param ticker    消息体
     * @param title     标题头
     * @param text      消息体
     * @return 是否成功
     * @throws Exception 检查
     */
    public static boolean androidSend(String fileId, String aliasType,
                                      String ticker, String title, String text) throws Exception {
        return client.send(getAndroidCustomizedcast(fileId, aliasType, ticker, title, text));
    }

    /**
     * android根据fileId给用户发送消息
     *
     * @param fileId    Umeng返回的文件Id
     * @param aliasType 自定义文件中内容类型（身份证号/token）
     * @param ticker    消息体
     * @param title     标题头
     * @param text      消息体
     * @return 是否成功
     * @throws Exception 检查
     */
    public static PushMessageResponse androidSendFileId(String fileId, String aliasType,
                                                        String ticker, String title, String text) throws Exception {
        return client.sendAndRespond(getAndroidCustomizedcast(fileId, aliasType, ticker, title, text));
    }

    /**
     * Android构建参数
     *
     * @param fileId    Umeng文件Id
     * @param aliasType 设备类型
     * @param ticker    标贴
     * @param title     标题
     * @param text      消息体
     * @return 响应信息
     * @throws Exception 检查
     */
    private static AndroidCustomizedcast getAndroidCustomizedcast(String fileId, String aliasType, String ticker, String title, String text) throws Exception {
        AndroidCustomizedcast customizedcast = new AndroidCustomizedcast(appkey_android, appMasterSecret_android);
        customizedcast.setFileId(fileId, aliasType);
        customizedcast.setTicker(ticker);
        customizedcast.setTitle(title);
        customizedcast.setText(text);
        customizedcast.goAppAfterOpen();
        customizedcast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
        // Set 'production_mode' to 'false' if it's a test device.
        // For how to register a test device, please see the developer doc.
        customizedcast.setProductionMode();
        return customizedcast;
    }

    /**
     * android根据fileId给用户发送消息
     *
     * @param alias     用户上传的证件号
     * @param aliasType 自定义文件中内容类型（身份证号/token）
     * @param ticker    消息体
     * @param title     标题头
     * @param text      消息体
     * @return 是否成功
     * @throws Exception 检查
     */
    public static PushMessageResponse androidSendAlias(String alias, String aliasType,
                                                       String ticker, String title, String text) throws Exception {
        AndroidCustomizedcast customizedcast = new AndroidCustomizedcast(appkey_android, appMasterSecret_android);
        customizedcast.setAlias(alias, aliasType);
        customizedcast.setTicker(ticker);
        customizedcast.setTitle(title);
        customizedcast.setText(text);
        customizedcast.goAppAfterOpen();
        customizedcast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
        // Set 'production_mode' to 'false' if it's a test device.
        // For how to register a test device, please see the developer doc.
        customizedcast.setProductionMode();
        return client.sendAndRespond(customizedcast);
    }

    /**
     * 查询状态
     *
     * @param androidFileId Umeng发送的Android设备的FileId
     * @param iosFileId     Umeng发送IOS设备的FileId
     * @return 发送消息的状态
     */
    public static PushMessageResponse pushMessageStatus(String androidFileId, String iosFileId) throws Exception {
        PushMessageResponse iosStatus;
        PushMessageResponse androidStatus;
        short statusNum1 = 0;
        short statusNum2 = 0;
        // IOS的状态
        if (iosFileId != null) {
            iosStatus = client.status(appkey_ios, appMasterSecret_ios, iosFileId);
            if (PushClient.Ret.SUCCESS.getValue().equals(iosStatus.getRet())) {
                statusNum1 = getStatus(iosStatus.getStatus());
            }
        }
        // Android的状态
        if (androidFileId != null) {
            androidStatus = client.status(appkey_android, appMasterSecret_android, androidFileId);
            if (PushClient.Ret.SUCCESS.getValue().equals(androidStatus.getRet())) {
                statusNum2 = getStatus(androidStatus.getStatus());
            }
        }

        // 状态合并
        short statusNum = (statusNum1 > statusNum2 ? statusNum1 : statusNum2);
        PushMessageResponse status = new PushMessageResponse();
        status.setStatus(statusNum == 0 ? null : statusNum);
        return status;
    }

    /**
     * 1.待发送 2.已发送 3.发送失败
     *
     * @param status 消息状态: 0-排队中, 1-发送中，2-发送完成，3-发送失败，4-消息被撤销，
     *               5-消息过期, 6-筛选结果为空，7-定时任务尚未开始处理
     * @return 1.待发送 2.已发送 3.发送失败
     */
    private static short getStatus(Short status) {
        short statusNum = 0;
        if (status != null) {
            if (status == 2) {
                statusNum = PushMessageConstants.Sent.SENT;
            } else if (status < 2 || status == 7) {
                statusNum = PushMessageConstants.Sent.TO_BE_SENT;
            } else {
                statusNum = PushMessageConstants.Sent.FAIL_TO_SEND;
            }
        }
        return statusNum;
    }

    public static void main(String[] args) {
        try {
            // sendIOSCustomizedcastFile("412726199305191655\n412726199305191655", "IDCARD", "嗨嗨你好");
//            if (iosSend("ucmqqdc156352348147201", "IDCARD", "有一个消息")) {
//                logger.info("调用成功");
//            }
            // logger.info(pushMessageStatus("ucmqqdc156352348147201", "ucmqqdc156352348147201").toString());
//            JSONObject jsonObject = JSONObject.parseObject("{\n" +
//                    "\t\"androidTaskId\": \"ucmqqdc156352348147201\",\n" +
//                    "\t\"iosTaskId\": \"ucmqqdc156352348147201\"\n" +
//                    "}");
//            logger.info(jsonObject.getString(PushMessageConstants.ANDROID_TASK_ID) + "==="
//                    + jsonObject.getString(PushMessageConstants.IOS_TASK_ID));
            // final boolean b = sendAndroidCustomizedcast("66993322,412726199305191655", "你好", "这是一个标题");
            final PushMessageResponse response = androidSendAlias("412726199305191655", "IDCARD", "标题1", "标题2", "内容");
            logger.info("是否成功：{}", response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
