package com.geo.source.rong_chuang.push.android;

import com.geo.source.rong_chuang.push.AndroidNotification;

/**
 * Android 广播
 *
 * @author YanZhen
 * @date 2017/07/11 11:52
 */
public class AndroidBroadcast extends AndroidNotification {
    public AndroidBroadcast(String appkey, String appMasterSecret) throws Exception {
        setAppMasterSecret(appMasterSecret);
        setPredefinedKeyValue("appkey", appkey);
        this.setPredefinedKeyValue("type", "broadcast");
    }
}
