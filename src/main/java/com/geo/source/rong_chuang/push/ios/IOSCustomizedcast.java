package com.geo.source.rong_chuang.push.ios;

import com.alibaba.fastjson.JSONObject;
import com.geo.source.rong_chuang.push.IOSNotification;

/**
 * IOS customized cast
 *
 * @author YanZhen
 * @date 2019/07/11 11:24
 */
public class IOSCustomizedcast extends IOSNotification {
    public IOSCustomizedcast(String appkey, String appMasterSecret) throws Exception {
        setAppMasterSecret(appMasterSecret);
        setPredefinedKeyValue("appkey", appkey);
        this.setPredefinedKeyValue("type", "customizedcast");
    }

    public void setAlias(String alias, String aliasType) throws Exception {
        setPredefinedKeyValue("alias", alias);
        setPredefinedKeyValue("alias_type", aliasType);
    }

    public void setFileId(String fileId, String aliasType) throws Exception {
        setPredefinedKeyValue("file_id", fileId);
        setPredefinedKeyValue("alias_type", aliasType);
    }

    public void setZidingyi(String type, Object extra) throws Exception {
        setPredefinedKeyValue("jumptype", type);
        setPredefinedKeyValue("extra", JSONObject.toJSONString(extra));
    }

    public void setUrl(String url) throws Exception {
        setPredefinedKeyValue("url", url);
    }

}
