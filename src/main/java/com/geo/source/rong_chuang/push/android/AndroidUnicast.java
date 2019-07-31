package com.geo.source.rong_chuang.push.android;

import com.geo.source.rong_chuang.push.AndroidNotification;

/**
 * Android 单一推送
 *
 * @author YanZhen
 * @date 2019/07/11 11:24
 */
public class AndroidUnicast extends AndroidNotification {
    public AndroidUnicast(String appkey, String appMasterSecret) throws Exception {
        setAppMasterSecret(appMasterSecret);
        setPredefinedKeyValue("appkey", appkey);
        this.setPredefinedKeyValue("type", "unicast");
        setPredefinedKeyValue("aps", "");
    }

    public void setDeviceToken(String token) throws Exception {
        setPredefinedKeyValue("device_tokens", token);
    }

}