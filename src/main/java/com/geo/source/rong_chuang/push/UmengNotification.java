package com.geo.source.rong_chuang.push;

import com.alibaba.fastjson.JSONObject;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Umeng 通知基类
 *
 * @author YanZhen
 * @date 2019/07/10 17:56
 */
public abstract class UmengNotification {
    /**
     * This JSONObject is used for constructing the whole request string.
     */
    final JSONObject rootJson = new JSONObject();


    /**
     * The app master secret
      */
    private String appMasterSecret;

    /**
     * Keys can be set in the root level
      */
    static final HashSet<String> ROOT_KEYS = new HashSet<>(Arrays.asList("appkey", "timestamp", "type", "device_tokens", "alias", "alias_type", "file_id",
            "filter", "production_mode", "feedback", "description", "thirdparty_id"));

    /**
     * 多个自定义键
     */
    static final HashSet<String> CUSTOM_KEYS = new HashSet<>(Arrays.asList("extra", "jumptype", "url"));

    /**
     * Keys can be set in the policy level
      */
    static final HashSet<String> POLICY_KEYS = new HashSet<>(Arrays.asList("start_time", "expire_time", "max_send_num"));

    /**
     * Set predefined keys in the rootJson, for extra keys(Android) or customized keys(IOS) please
     * refer to corresponding methods in the subclass.
     * @param key key
     * @param value value
     * @return 是否成功
     * @throws Exception 检查
     */
    public abstract boolean setPredefinedKeyValue(String key, Object value) throws Exception;

    String getPostBody() {
        return rootJson.toString();
    }

    public void setAppMasterSecret(String secret) {
        appMasterSecret = secret;
    }

    protected final String getAppMasterSecret() {
        return appMasterSecret;
    }

    /**
     * 定义发送模式
     * @param prod 是否正式 true/是、false/否
     * @throws Exception 需要检查异常
     */
    private void setProductionMode(boolean prod) throws Exception {
        setPredefinedKeyValue("production_mode", prod);
    }

    /**
     * 正式模式
     * @throws Exception 检查
     */
    public void setProductionMode() throws Exception {
        setProductionMode(true);
    }

    /**
     * 测试模式
     * @throws Exception 检查
     */
    public void setTestMode() throws Exception {
        setProductionMode(false);
    }

    /**
     * 发送消息描述，建议填写。
     * @param description 描述
     * @throws Exception 检查
     */
    public void setDescription(String description) throws Exception {
        setPredefinedKeyValue("description", description);
    }

    /**
     * 定时发送时间，若不填写表示立即发送。格式: "YYYY-MM-DD hh:mm:ss"。
     * @param startTime 开始发送时间
     * @throws Exception 检查
     */
    public void setStartTime(String startTime) throws Exception {
        setPredefinedKeyValue("start_time", startTime);
    }

    /**
     * 消息过期时间,格式: "YYYY-MM-DD hh:mm:ss"。
     * @param expireTime 过期时间
     * @throws Exception 检查
     */
    public void setExpireTime(String expireTime) throws Exception {
        setPredefinedKeyValue("expire_time", expireTime);
    }

    /**
     * 发送限速，每秒发送的最大条数。
     * @param num 最大条数每秒
     * @throws Exception 检查
     */
    public void setMaxSendNum(Integer num) throws Exception {
        setPredefinedKeyValue("max_send_num", num);
    }

}
