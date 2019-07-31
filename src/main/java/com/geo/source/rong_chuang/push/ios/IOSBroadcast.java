package com.geo.source.rong_chuang.push.ios;

import com.geo.source.rong_chuang.push.IOSNotification;

/**
 * IOS 广播推送
 * @author YanZhen
 * @date 2019/07/11 11:24
 */
public class IOSBroadcast extends IOSNotification {
	public IOSBroadcast(String appkey,String appMasterSecret) throws Exception {
			setAppMasterSecret(appMasterSecret);
			setPredefinedKeyValue("appkey", appkey);
			this.setPredefinedKeyValue("type", "broadcast");	
		
	}
}
