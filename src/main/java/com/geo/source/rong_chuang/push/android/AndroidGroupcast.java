package com.geo.source.rong_chuang.push.android;

import com.alibaba.fastjson.JSONObject;
import com.geo.source.rong_chuang.push.AndroidNotification;

/**
 * Android 组推送
 *
 * @author YanZhen
 * @date 2019/07/11 11:24
 */
public class AndroidGroupcast extends AndroidNotification {
    public AndroidGroupcast(String appkey, String appMasterSecret) throws Exception {
        setAppMasterSecret(appMasterSecret);
        setPredefinedKeyValue("appkey", appkey);
        this.setPredefinedKeyValue("type", "groupcast");
    }

    public void setFilter(JSONObject filter) throws Exception {
        setPredefinedKeyValue("filter", filter);
    }
}
