package com.geo.source.rong_chuang.push.dto;

/**
 * @author YanZhen
 * @date 2019/07/31 18:58
 **/
public interface PushMessageConstants {
    /**
     * 发送状态
     */
    interface Sent {
        /**
         * 1.待发送
         */
        short TO_BE_SENT = 1;
        /**
         * 2.已发送
         */
        short SENT = 2;
        /**
         * 3.发送失败
         */
        short FAIL_TO_SEND = 3;
    }

    /**
     * android设备信息发送时, 从返回消息中获取的task_id
     */
    String ANDROID_TASK_ID = "androidTaskId";
    /**
     * IOS设备信息发送时, 从返回消息中获取的task_id
     */
    String IOS_TASK_ID = "iosTaskId";
}
