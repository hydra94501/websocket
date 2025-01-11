package com.gallery.websoket.service;

import com.gallery.websoket.dto.res.MessagePushRes;
import com.gallery.websoket.enums.MessageTargetType;
import com.gallery.websoket.exception.BizException;
import com.gallery.websoket.factory.MessagePushStrategyFactory;
import com.gallery.websoket.dto.parm.MyWebSocketMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MessageWebSocketService {


    private final MessagePushStrategyFactory strategyFactory;


    public MessageWebSocketService(MessagePushStrategyFactory strategyFactory) {
        this.strategyFactory = strategyFactory;
    }

    /**
     * 前置处理：保存消息记录和日志
     *
     * @param message 消息内容
     */
    private void processBeforeSending(MyWebSocketMessage message) {
        // 记录消息到数据库（例如保存消息记录表）
//        MessageRecord messageRecord = new MessageRecord();
//        messageRecord.setContent(message.getContent());
//        messageRecord.setSendUserId(message.getSendUserId());
//        messageRecord.setReceiveUserId(message.getReceiveUserId());
//        messageRecord.setCreateTime(message.getCreateTime());
//        messageRecord.setStatus("PENDING"); // 初始状态
//        messageRecordService.saveMessageRecord(messageRecord);

        // 记录日志
//        MessageLog messageLog = new MessageLog();
//        messageLog.setMessageContent(message.getContent());
//        messageLog.setMessageStatus("STARTED");
//        messageLogService.saveMessageLog(messageLog);
    }

    /**
     * 后置处理：更新消息记录状态和日志
     *
     * @param message 消息内容
     */
    private void processAfterSending(MyWebSocketMessage message) {
//        // 更新消息发送状态或其他后置处理
//        messageRecordService.updateMessageStatus(message, "SENT");  // 状态更新为 SENT
//
//        // 记录日志
//        messageLogService.updateMessageLog(message.getContent(), "COMPLETED");
    }

    @Transactional
    public MessagePushRes sendMessage(MessageTargetType targetType, MyWebSocketMessage message) {
        // 根据类型选择推送发送消息
        MessagePushStrategy strategy = strategyFactory.getStrategy(targetType);
        if (strategy != null) {
            processBeforeSending(message);
            //处理
            MessagePushRes messagePushRes = strategy.sendMessage(message);
            processAfterSending(message);
            return messagePushRes;
        } else {
            throw new BizException("推送类型错误: " + targetType);
        }
    }


}
