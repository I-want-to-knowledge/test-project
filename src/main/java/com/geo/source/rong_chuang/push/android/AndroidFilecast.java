package com.geo.source.rong_chuang.push.android;

import com.geo.source.rong_chuang.push.AndroidNotification;

/**
 * Android 文件推送
 *
 * @author YanZhen
 * @date 2019/07/11 11:24
 */
public class AndroidFilecast extends AndroidNotification {
    public AndroidFilecast(String appkey, String appMasterSecret) throws Exception {
        setAppMasterSecret(appMasterSecret);
        setPredefinedKeyValue("appkey", appkey);
        this.setPredefinedKeyValue("type", "filecast");
    }

    public void setFileId(String fileId) throws Exception {
        setPredefinedKeyValue("file_id", fileId);
    }
}