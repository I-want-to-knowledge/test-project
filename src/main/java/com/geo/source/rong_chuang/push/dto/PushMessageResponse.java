package com.geo.source.rong_chuang.push.dto;

import lombok.Data;
import lombok.ToString;

/**
 * @author YanZhen
 * @date 2019/07/19 11:39
 **/
@Data
@ToString
public class PushMessageResponse {
    /**
     * "ret":"SUCCESS/FAIL"
     */
    private String ret;
    /**
     * 当"ret"为"SUCCESS"时
     */
    private String taskId;
    /**
     * 消息状态: 0-排队中, 1-发送中，2-发送完成，
     * 3-发送失败，4-消息被撤销，5-消息过期,
     * 6-筛选结果为空，7-定时任务尚未开始处理
     */
    private Short status;
    /**
     * 消息收到数(Android消息, iOS消息)
     */
    private Integer sentCount;
    /**
     * 打开数(Android消息, iOS消息)
     */
    private Integer openCount;
    /**
     * 忽略数(Android消息)
     */
    private Integer dismissCount;
    /**
     * 投递APNs设备数(iOS消息)
     */
    private Integer totalCount;
    /**
     * 当"ret"为"FAIL"时
     * 错误码
     */
    private String errorCode;
    /**
     * 当"ret"为"FAIL"时
     * 错误信息
     */
    private String errorMsg;

}
