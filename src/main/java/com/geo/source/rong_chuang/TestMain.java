package com.geo.source.rong_chuang;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.netty.handler.codec.http2.HttpUtil;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang3.StringUtils;
import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuer;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author YanZhen
 * @date 2019/07/30 16:08
 **/
public class TestMain {
    private static Logger logger = LoggerFactory.getLogger(TestMain.class);

    public static void main(String[] args) {
        m1();
    }

    /** 测试接口 */
    private static String getUserUrl = "http://rongchuang.natapp4.cc/YYGH_12580_SERVICE/service.htm";

    private static void m1() {
        OAuthIssuer oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
        String code = null;
        String appId = "p9gbs1lwjzt1u8hstio2sjbivl19wgkx";
        String scope = "snsapi_userinfo";
        String token = "cbfcf270-716a-447b-95df-02da62c5e8a4";

        String userId = "0";
        GetUserAppRequest getUserAppRequest = new GetUserAppRequest();
        ArgsBo argsBo = new ArgsBo();
        // token 测试值 a031f2a1-e265-4ed6-94de-b063144a26c0
        argsBo.setToken(token);
        getUserAppRequest.setArgs(argsBo);
        getUserAppRequest.setClientUUID("E1E34948-C772-4ED2-AF7D-FBBCF56A0388");
        getUserAppRequest.setHttpCode("QjfjcNUhCSM=");
        getUserAppRequest.setMethod("PatientInfo");
        getUserAppRequest.setService("patientService");
        getUserAppRequest.setVersion("7.3.1");
        getUserAppRequest.setType("pc");
        getUserAppRequest.setToken(token);
        String requestJson = JSON.toJSONString(getUserAppRequest);
        try {
            //String key = DES.encryptDES(requestJson).replace("+","%2B");
            String key = encryptDES(requestJson).replace("+", "%2B").replace("\r", "").replace("\n", "");
            String url = getUserUrl + "?key=" + key + "&apk=yygh";
            logger.info("请求getUserUrl==========================" + url);
            logger.info("请求参数==========================" + requestJson);
            logger.info("请求token==========================" + token);
            String result = javahttpGet_old(url);
            logger.info("请求结果==========================" + result);
            if (!StringUtils.isEmpty(result)) {
                JSONObject obj = JSONObject.parseObject(result);
                if (null != obj) {
                    JSONObject data = (JSONObject) obj.get("data");
                    if (null != data) {
                        JSONObject patient = (JSONObject) data.get("patient");
                        if (null != patient) {
                            userId = String.valueOf(patient.get("patientId"));

                            logger.info("patientCard : {}",patient.getString("patientCard"));
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("调用异常", e);
        }

    }

    private static final byte[] KEY = "L5IRTNDW".getBytes();
    private static byte[] iv = { 1, 2, 3, 4, 5, 6, 7, 8 };
    private static String encryptDES(String encryptString) throws Exception {
        //      IvParameterSpec zeroIv = new IvParameterSpec(new byte[8]);
        IvParameterSpec zeroIv = new IvParameterSpec(iv);
        SecretKeySpec key = new SecretKeySpec(KEY, "DES");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
        byte[] encryptedData = cipher.doFinal(encryptString.getBytes());

        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(encryptedData);
    }

    public static String javahttpGet_old(String url) {

        try {
            HttpClient client = new HttpClient();
            // 设置连接超时时间为2秒（连接初始化时间）
            client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
            GetMethod getMethod = new GetMethod(url);
            client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
            // 执行并返回状态
            int status = client.executeMethod(getMethod);
            String getStr = "";
            if (status == HttpStatus.SC_OK) {
                getStr = getMethod.getResponseBodyAsString();
            } else {
                logger.debug(getMethod.getResponseBodyAsString());
            }
            client.getHttpConnectionManager().closeIdleConnections(1);
            return getStr;
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "";
        }
        finally {
        }
    }
}
