package com.geo.source.rong_chuang.push;

import com.alibaba.fastjson.JSONObject;

import java.util.Arrays;
import java.util.HashSet;

/**
 * IOS消息通知
 */
public abstract class IOSNotification extends UmengNotification {
	private final String IOS_PAYLOAD ="payload";
	private final String IOS_POLICY = "policy";
	private final String IOS_APS = "aps";

	/**
	 * Keys can be set in the aps level
	 */
	private static final HashSet<String> APS_KEYS = new HashSet<>(Arrays.asList("alert", "badge", "sound",
			"content-available"));
	
	@Override
	public boolean setPredefinedKeyValue(String key, Object value) throws Exception {
		if (ROOT_KEYS.contains(key)) {
			// This key should be in the root level
			rootJson.put(key, value);
		} else if (APS_KEYS.contains(key)) {
			// This key should be in the aps level
			JSONObject apsJson;
			JSONObject payloadJson;
			if (rootJson.containsKey(IOS_PAYLOAD)) {
				payloadJson = rootJson.getJSONObject(IOS_PAYLOAD);
			} else {
				payloadJson = new JSONObject();
				rootJson.put(IOS_PAYLOAD, payloadJson);
			}
			if (payloadJson.containsKey(IOS_APS)) {
				apsJson = payloadJson.getJSONObject(IOS_APS);
			} else {
				apsJson = new JSONObject();
				payloadJson.put(IOS_APS, apsJson);
			}
			apsJson.put(key, value);
		}  else if (CUSTOM_KEYS.contains(key)) {
			// This key should be in the aps level
			JSONObject payloadJson;
			if (rootJson.containsKey(IOS_PAYLOAD)) {
				payloadJson = rootJson.getJSONObject(IOS_PAYLOAD);
			} else {
				payloadJson = new JSONObject();
				rootJson.put(IOS_PAYLOAD, payloadJson);
			}
			payloadJson.put(key, value);
		} else if (POLICY_KEYS.contains(key)) {
			// This key should be in the body level
			JSONObject policyJson;
			if (rootJson.containsKey(IOS_POLICY)) {
				policyJson = rootJson.getJSONObject(IOS_POLICY);
			} else {
				policyJson = new JSONObject();
				rootJson.put(IOS_POLICY, policyJson);
			}
			policyJson.put(key, value);
		} else {
			if (IOS_PAYLOAD.equals(key) || IOS_APS.equals(key) || IOS_POLICY.equals(key)) {
				throw new Exception("You don't need to set value for " + key + " , just set values for the sub keys in it.");
			} else {
				throw new Exception("Unknown key: " + key);
			}
		}
		
		return true;
	}

	/**
	 * Set customized key/value for IOS notification
	 * @param key key
	 * @param value value
	 * @return 是否成功
	 * @throws Exception 检查
	 */
	public boolean setCustomizedField(String key, String value) throws Exception {
		JSONObject payloadJson = null;
		if (rootJson.containsKey(IOS_PAYLOAD)) {
			payloadJson = rootJson.getJSONObject(IOS_PAYLOAD);
		} else {
			payloadJson = new JSONObject();
			rootJson.put(IOS_PAYLOAD, payloadJson);
		}
		payloadJson.put(key, value);
		return true;
	}

	/**
	 * 报警
	 * @param token token
	 * @throws Exception 检查
	 */
	public void setAlert(String token) throws Exception {
    	setPredefinedKeyValue("alert", token);
    }

	/**
	 * 标记
	 * @param badge 标记
	 * @throws Exception 检查
	 */
	public void setBadge(Integer badge) throws Exception {
    	setPredefinedKeyValue("badge", badge);
    }

	/**
	 * 声音
	 * @param sound 声音
	 * @throws Exception 检查
	 */
	public void setSound(String sound) throws Exception {
    	setPredefinedKeyValue("sound", sound);
    }

	/**
	 * 提供内容
	 * @param contentAvailable 提供的内容
	 * @throws Exception 检查
	 */
	public void setContentAvailable(Integer contentAvailable) throws Exception {
    	setPredefinedKeyValue("content-available", contentAvailable);
    }
}
