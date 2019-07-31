package com.geo.source.rong_chuang.push.ios;

import com.geo.source.rong_chuang.push.IOSNotification;

/**
 * IOS 单一推送
 * @author YanZhen
 * @date 2019/07/11 11:24
 */
public class IOSUnicast extends IOSNotification {
	public IOSUnicast(String appkey,String appMasterSecret) throws Exception{
			setAppMasterSecret(appMasterSecret);
			setPredefinedKeyValue("appkey", appkey);
			this.setPredefinedKeyValue("type", "unicast");	
	}
	
	public void setDeviceToken(String token) throws Exception {
    	setPredefinedKeyValue("device_tokens", token);
    }
}
