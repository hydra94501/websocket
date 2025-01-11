package com.gallery.websoket.dto.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 消息推送结果 VO 类，用于返回消息推送的状况（成功、失败、部分成功等）
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessagePushRes implements Serializable {


    /**
     * 消息ID或推送任务ID，用于追踪具体消息推送情况
     */
    private String messageId;

    /**
     * 推送状态：成功、失败、部分成功
     * 可以定义一些枚举：SUCCESS, FAIL, PARTIAL_SUCCESS
     */
    private String status;

    /**
     * 错误信息（如果有推送失败的情况，包含具体的错误信息）
     */
    private String errorMessage;

    /**
     * 总的推送目标数量（用于给第三方推送接口提供完整的推送情况）
     */
    private Integer totalTargets;

    /**
     * 发送时间（可选）
     */
    private String sendTime;
}
