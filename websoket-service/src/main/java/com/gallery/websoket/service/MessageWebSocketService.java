package com.gallery.websoket.service;

import com.gallery.websoket.dto.res.MessagePushRes;
import com.gallery.websoket.enums.MessageTargetStatus;
import com.gallery.websoket.enums.MessageTargetType;
import com.gallery.websoket.exception.BizException;
import com.gallery.websoket.factory.MessagePushStrategyFactory;
import com.gallery.websoket.dto.parm.MyWebSocketMessage;
import com.gallery.websoket.model.MsgRecord;
import com.gallery.websoket.model.WsUser;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MessageWebSocketService {


    private final MessagePushStrategyFactory strategyFactory;
    private final MsgRecordService msgRecordService;
    private final WsUserService wsUserService;


    public MessageWebSocketService(MessagePushStrategyFactory strategyFactory, MsgRecordService msgRecordService, WsUserService wsUserService) {
        this.strategyFactory = strategyFactory;
        this.msgRecordService = msgRecordService;
        this.wsUserService = wsUserService;
    }

    /**
     * 前置处理：保存消息记录和日志
     *
     * @param message 消息内容
     */
    private void processBeforeSending(MyWebSocketMessage message) {
        // 记录消息到数据库（例如保存消息记录表）
        MsgRecord msgRecord = new MsgRecord();
        //存发送人用户信息
        WsUser wsUser = wsUserService.getUserBySystemNameAndExternalUserId(message.getSystemName(), message.getSendUserId());
        if (wsUser == null) {
            wsUser = new WsUser();
            wsUser.setSystemName(message.getReceiveUserId());
            wsUser.setExternalUserId(message.getReceiveUserId());
            wsUserService.save(wsUser);
            msgRecord.setSendUserId(wsUser.getId());
        }
        //判断推送类型
        if (message.getMessageTargetType().equals(MessageTargetType.USER.getType())) {
            //用户推送
            //存接收人用户信息
            WsUser reWsUser = wsUserService.getUserBySystemNameAndExternalUserId(message.getSystemName(), message.getReceiveUserId());
            if (reWsUser == null) {
                reWsUser = new WsUser();
                reWsUser.setSystemName(message.getReceiveUserId());
                reWsUser.setExternalUserId(message.getReceiveUserId());
                wsUserService.save(reWsUser);
                msgRecord.setReceiveUserId(reWsUser.getId());
            }
        } else if (message.getMessageTargetType().equals(MessageTargetType.GROUP.getType())) {
            //群组推送
        } else if (message.getMessageTargetType().equals(MessageTargetType.TOPIC.getType())) {
            //主题推送
        } else if (message.getMessageTargetType().equals(MessageTargetType.ALL.getType())) {
            //全部推送
        } else {
            throw new BizException("推送类型不存在！");
        }



        BeanUtils.copyProperties(message, msgRecord);
        msgRecord.setStatus(MessageTargetStatus.PENDING.getType());
        msgRecordService.save(msgRecord);

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
