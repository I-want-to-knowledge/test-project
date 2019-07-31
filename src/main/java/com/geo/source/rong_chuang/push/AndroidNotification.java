package com.geo.source.rong_chuang.push;

import com.alibaba.fastjson.JSONObject;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Android发送信息服务
 * @author YanZhen
 * @date 2019/07/10 18:26
 */
public abstract class AndroidNotification extends UmengNotification {
	private final String ANDROID_PAYLOAD = "payload";
	private final String ANDROID_POLICY = "policy";
	private final String ANDROID_BODY = "body";
	
	/**
	 * Keys can be set in the payload level
 	 */
	private static final HashSet<String> PAYLOAD_KEYS = new HashSet<>(Arrays.asList("display_type","extra","aps"));

	/**
	 * Keys can be set in the body level
 	 */
	private static final HashSet<String> BODY_KEYS = new HashSet<>(Arrays.asList("ticker", "title", "text",
			"builder_id", "icon", "largeIcon", "img", "play_vibrate", "play_lights", "play_sound", "sound",
			"after_open", "url", "activity", "custom"));

	/**
	 * 显示类型
	 */
	public enum DisplayType{
		/**
		 * 通知:消息送达到用户设备后，由友盟SDK接管处理并在通知栏上显示通知内容。
		 */
		NOTIFICATION{
			/**
			 * @return 值 notification
			 */
			@Override
			public String getValue(){return "notification";}},
		/**
		 * 消息:消息送达到用户设备后，消息内容透传给应用自身进行解析处理。
		 */
		MESSAGE{
			/**
			 * @return 值 message
			 */
			@Override
			public String getValue(){return "message";}};

		/**
		 * 自定义通知标识
		 * @return 返回自定义通知标识
		 */
		public abstract String getValue();
	}

	/**
	 * 开放操作之后
	 */
	public enum AfterOpenAction{
		/**
		 * 打开应用
		 */
        go_app,
		/**
		 * 跳转到URL
		 */
        go_url,
		/**
		 * 打开特定的activity
		 */
        go_activity,
		/**
		 * 用户自定义内容。
		 */
        go_custom
	}

	/**
	 * Set key/value in the rootJson, for the keys can be set please see ROOT_KEYS, PAYLOAD_KEYS,
	 * BODY_KEYS and POLICY_KEYS.
	 * @param key key
	 * @param value value
	 * @return 是否成功
	 * @throws Exception 解析json异常
	 */
	@Override
	public boolean setPredefinedKeyValue(String key, Object value) throws Exception {
		if (ROOT_KEYS.contains(key)) {
			// This key should be in the root level
			rootJson.put(key, value);

		} else if (PAYLOAD_KEYS.contains(key)) {
			// This key should be in the payload level
			JSONObject payloadJson;
			if (rootJson.containsKey(ANDROID_PAYLOAD)) {
				payloadJson = rootJson.getJSONObject(ANDROID_PAYLOAD);
			} else {
				payloadJson = new JSONObject();
				rootJson.put(ANDROID_PAYLOAD, payloadJson);
			}
			payloadJson.put(key, value);
		} else if (BODY_KEYS.contains(key)) {
			// This key should be in the body level
			JSONObject bodyJson;
			JSONObject payloadJson;
			// 'body' is under 'payload', so build a payload if it doesn't exist
			if (rootJson.containsKey(ANDROID_PAYLOAD)) {
				payloadJson = rootJson.getJSONObject(ANDROID_PAYLOAD);
			} else {
				payloadJson = new JSONObject();
				rootJson.put(ANDROID_PAYLOAD, payloadJson);
			}
			// Get body JSONObject, generate one if not existed
			if (payloadJson.containsKey(ANDROID_BODY)) {
				bodyJson = payloadJson.getJSONObject(ANDROID_BODY);
			} else {
				bodyJson = new JSONObject();
				payloadJson.put(ANDROID_BODY, bodyJson);
			}
			bodyJson.put(key, value);
		} else if (POLICY_KEYS.contains(key)) {
			// This key should be in the body level
			JSONObject policyJson;
			if (rootJson.containsKey(ANDROID_POLICY)) {
				policyJson = rootJson.getJSONObject(ANDROID_POLICY);
			} else {
				policyJson = new JSONObject();
				rootJson.put(ANDROID_POLICY, policyJson);
			}
			policyJson.put(key, value);
		} else {
			if (ANDROID_PAYLOAD.equals(key) || ANDROID_BODY.equals(key) || ANDROID_POLICY.equals(key)) {
				throw new Exception("You don't need to set value for " + key + " , just set values for the sub keys in it.");
			} else {
				throw new Exception("Unknown key: " + key);
			}
		}
		return true;
	}

	/**
	 * Set extra key/value for Android notification
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean setExtraField(String key, String value) {
		JSONObject payloadJson;
		JSONObject extraJson;
		if (rootJson.containsKey(ANDROID_PAYLOAD)) {
			payloadJson = rootJson.getJSONObject(ANDROID_PAYLOAD);
		} else {
			payloadJson = new JSONObject();
			rootJson.put(ANDROID_PAYLOAD, payloadJson);
		}
		
		if (payloadJson.containsKey("extra")) {
			extraJson = payloadJson.getJSONObject("extra");
		} else {
			extraJson = new JSONObject();
			payloadJson.put("extra", extraJson);
		}
		extraJson.put(key, value);
		return true;
	}

	/**
	 * 显示类型
	 * @param d 显示类型
	 * @throws Exception 检查
	 */
	public void setDisplayType(DisplayType d) throws Exception {
		setPredefinedKeyValue("display_type", d.getValue());
	}

	/**
	 * 通知栏提示文字
	 * @param ticker 提示
	 * @throws Exception 检查
	 */
	public void setTicker(String ticker) throws Exception {
		setPredefinedKeyValue("ticker", ticker);
	}

	/**
	 * 通知标题
	 * @param title 标题
	 * @throws Exception 检查
	 */
	public void setTitle(String title) throws Exception {
		setPredefinedKeyValue("title", title);
	}

	/**
	 * 通知文字描述
	 * @param text 描述
	 * @throws Exception 检查
	 */
	public void setText(String text) throws Exception {
		setPredefinedKeyValue("text", text);
	}

	/**
	 * 用于标识该通知采用的样式。使用该参数时, 必须在SDK里面实现自定义通知栏样式。
	 * @param builderId 建造者
	 * @throws Exception 检查
	 */
	public void setBuilderId(Integer builderId) throws Exception {
		setPredefinedKeyValue("builder_id", builderId);
	}

	/**
	 * 状态栏图标ID, R.drawable.[smallIcon],如果没有, 默认使用应用图标。
	 * @param icon 图标信息
	 * @throws Exception 检查
	 */
	public void setIcon(String icon) throws Exception {
		setPredefinedKeyValue("icon", icon);
	}

	/**
	 * 通知栏拉开后左侧图标ID
	 * @param largeIcon 图片
	 * @throws Exception 检查
	 */
	public void setLargeIcon(String largeIcon) throws Exception {
		setPredefinedKeyValue("largeIcon", largeIcon);
	}

	/**
	 * 通知栏大图标的URL链接。该字段的优先级大于largeIcon。该字段要求以http或者https开头。
	 * @param img 图片链接
	 * @throws Exception 检查
	 */
	public void setImg(String img) throws Exception {
		setPredefinedKeyValue("img", img);
	}

	/**
	 * 收到通知是否震动,默认为"true"
	 * @param playVibrate 是否震动
	 * @throws Exception 检查
	 */
	public void setPlayVibrate(Boolean playVibrate) throws Exception {
		setPredefinedKeyValue("play_vibrate", playVibrate.toString());
	}

	/**
	 * 收到通知是否闪灯,默认为"true"
	 * @param playLights 是否闪灯
	 * @throws Exception 检查
	 */
	public void setPlayLights(Boolean playLights) throws Exception {
		setPredefinedKeyValue("play_lights", playLights.toString());
	}

	/**
	 * 收到通知是否发出声音,默认为"true"
	 * @param playSound 否发出声
	 * @throws Exception 检查
	 */
	public void setPlaySound(Boolean playSound) throws Exception {
		setPredefinedKeyValue("play_sound", playSound.toString());
	}

	/**
	 * 通知声音，R.raw.[sound]. 如果该字段为空，采用SDK默认的声音
	 * @param sound 通知声音
	 * @throws Exception 检查
	 */
	public void setSound(String sound) throws Exception {
		setPredefinedKeyValue("sound", sound);
	}

	/**
	 * 收到通知后播放指定的声音文件
	 * @param sound 声音文件
	 * @throws Exception 检查
	 */
	public void setPlaySound(String sound) throws Exception {
		setPlaySound(true);
		setSound(sound);
	}

	/**
	 * 点击"通知"的后续行为，默认为打开app。
	 * @throws Exception 检查
	 */
	public void goAppAfterOpen() throws Exception {
		setAfterOpenAction(AfterOpenAction.go_app);
	}

	/**
	 * 点击"通知"的后续行为，打开url
	 * @param url 打开的链接
	 * @throws Exception 检查
	 */
	public void goUrlAfterOpen(String url) throws Exception {
		setAfterOpenAction(AfterOpenAction.go_url);
		setUrl(url);
	}

	/**
	 * 点击"通知"的后续行为，打开活动
	 * @param activity 活动信息
	 * @throws Exception 检查
	 */
	public void goActivityAfterOpen(String activity) throws Exception {
		setAfterOpenAction(AfterOpenAction.go_activity);
		setActivity(activity);
	}

	/**
	 * 点击"通知"的后续行为，打开自定义
	 * @param custom 自定义行为
	 * @throws Exception 检查
	 */
	public void goCustomAfterOpen(String custom) throws Exception {
		setAfterOpenAction(AfterOpenAction.go_custom);
		setCustomField(custom);
	}

	/**
	 * 点击"通知"的后续行为，打开自定义
	 * @param custom 自定义行为
	 * @throws Exception 检查
	 */
	public void goCustomAfterOpen(JSONObject custom) throws Exception {
		setAfterOpenAction(AfterOpenAction.go_custom);
		setCustomField(custom);
	}

	/**
	 * 点击"通知"的后续行为，默认为打开app。原始接口
	 * @param action 打开的行为
	 * @throws Exception 检查
	 */
	public void setAfterOpenAction(AfterOpenAction action) throws Exception {
		setPredefinedKeyValue("after_open", action.toString());
	}

	/**
	 * 点击"通知"的后续行为，打开url
	 * @param url 打开的URL
	 * @throws Exception 检查
	 */
	public void setUrl(String url) throws Exception {
		setPredefinedKeyValue("url", url);
	}

	/**
	 * 点击"通知"的后续行为，打开活动
	 * @param activity 活动
	 * @throws Exception 检查
	 */
	public void setActivity(String activity) throws Exception {
		setPredefinedKeyValue("activity", activity);
	}

	/**
	 *  点击"通知"的后续行为，打开自定义
	 * can be a string of json
	 * @param custom 自定义
	 * @throws Exception 检查
	 */
	public void setCustomField(String custom) throws Exception {
		setPredefinedKeyValue("custom", custom);
	}

	/**
	 * 点击"通知"的后续行为，打开自定义
	 * @param custom 自定义
	 * @throws Exception 检查
	 */
	public void setCustomField(JSONObject custom) throws Exception {
		setPredefinedKeyValue("custom", custom);
	}
	
}
