package com.geo.source.rong_chuang.push.android;

import com.geo.source.rong_chuang.push.AndroidNotification;

/**
 * Android customized cast
 *
 * @author YanZhen
 * @date 2019/07/11 11:24
 */
public class AndroidCustomizedcast extends AndroidNotification {
    public AndroidCustomizedcast(String appkey, String appMasterSecret) throws Exception {
        setAppMasterSecret(appMasterSecret);
        setPredefinedKeyValue("appkey", appkey);
        this.setPredefinedKeyValue("type", "customizedcast");
        setPredefinedKeyValue("aps", "");
    }

    public void setAlias(String alias, String aliasType) throws Exception {
        setPredefinedKeyValue("alias", alias);
        setPredefinedKeyValue("alias_type", aliasType);
    }

    public void setFileId(String fileId, String aliasType) throws Exception {
        setPredefinedKeyValue("file_id", fileId);
        setPredefinedKeyValue("alias_type", aliasType);
    }

    public void setExtraField(Object extra) throws Exception {
        setPredefinedKeyValue("extra", extra);
    }

}
