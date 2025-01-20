package com.gallery.websoket.interceptor;

import com.gallery.websoket.dto.parm.MyWebSocketMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WebSocketMessageInterceptor implements ChannelInterceptor {

    /**
     * 在消息发送之前调用，方法中可以对消息进行修改，如果此方法返回值为空，则不会发生实际的消息发送调用
     *
     * @param message 发送的消息
     * @param channel 消息通道
     * @return 经过处理的消息
     */
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        // 获取STOMP头信息
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(message);

        // 打印接收到的STOMP消息，用于调试
        log.info("接收到的消息: {}", message);

        // 使用 simpMessageType 判断消息类型
        SimpMessageType messageType = headerAccessor.getMessageType();
        if (messageType == SimpMessageType.MESSAGE) {
            Object payload = message.getPayload();
            log.info("收到的消息载荷类型: {}", payload.getClass().getName());
            if (payload instanceof MyWebSocketMessage) {
                MyWebSocketMessage webSocketMessage = (MyWebSocketMessage) payload;
                log.info("正在保存消息日志: {}", webSocketMessage);
                saveMessageLog(webSocketMessage, "PENDING", null);
            }

        }
        return message;
    }

    /**
     * 在消息发送后立刻调用，boolean值参数表示该调用的返回值
     *
     * @param message 发送的消息
     * @param channel 消息通道
     * @param sent    消息是否成功发送
     */
    @Override
    public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
        // 获取STOMP头信息
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(message);

        // 打印消息发送结果（成功或失败）
        log.info("消息发送结果: {}, 是否成功发送: {}", message, sent);

        // 使用 simpMessageType 判断消息类型
        SimpMessageType messageType = headerAccessor.getMessageType();
        if (messageType == SimpMessageType.MESSAGE) {
            // 获取消息的载荷（实际数据）
            Object payload = message.getPayload();
            if (payload instanceof MyWebSocketMessage) {
                MyWebSocketMessage webSocketMessage = (MyWebSocketMessage) payload;
                // 根据发送结果更新消息日志状态
                if (sent) {
                    log.info("消息成功发送: {}", webSocketMessage);
                    updateMessageLogStatus(webSocketMessage, "SUCCESS", null);
                } else {
                    log.error("消息发送失败: {}", webSocketMessage);
                    updateMessageLogStatus(webSocketMessage, "FAILURE", "消息发送失败");
                }
            }
        }
    }

    /**
     * 1. 在消息发送完成后调用，而不管消息发送是否产生异常，在次方法中，我们可以做一些资源释放清理的工作
     * 2. 此方法的触发必须是preSend方法执行成功，且返回值不为null,发生了实际的消息推送，才会触发
     */
    @Override
    public void afterSendCompletion(Message<?> message, MessageChannel messageChannel, boolean b, Exception e) {

    }

    /**
     * 1. 在消息被实际检索之前调用，如果返回false,则不会对检索任何消息，只适用于(PollableChannels)，
     * 2. 在websocket的场景中用不到
     */
    @Override
    public boolean preReceive(MessageChannel messageChannel) {
        return true;
    }

    /**
     * 1. 在检索到消息之后，返回调用方之前调用，可以进行信息修改，如果返回null,就不会进行下一步操作
     * 2. 适用于PollableChannels，轮询场景
     */
    @Override
    public Message<?> postReceive(Message<?> message, MessageChannel messageChannel) {
        return message;
    }

    /**
     * 1. 在消息接收完成之后调用，不管发生什么异常，可以用于消息发送后的资源清理
     * 2. 只有当preReceive 执行成功，并返回true才会调用此方法
     * 2. 适用于PollableChannels，轮询场景
     */
    @Override
    public void afterReceiveCompletion(Message<?> message, MessageChannel messageChannel, Exception e) {

    }


    /**
     * 保存消息日志，记录消息发送的初始状态
     *
     * @param message      消息内容
     * @param status       消息状态
     * @param errorMessage 错误信息（如果有）
     */
    private void saveMessageLog(MyWebSocketMessage message, String status, String errorMessage) {
        log.info("消息日志保存: 内容 - {}, 状态 - {}, 错误信息 - {}", message.getContent(), status, errorMessage);
    }

    /**
     * 更新消息日志的状态
     *
     * @param message      消息内容
     * @param status       消息状态
     * @param errorMessage 错误信息（如果有）
     */
    private void updateMessageLogStatus(MyWebSocketMessage message, String status, String errorMessage) {
        log.info("消息日志更新: 内容 - {}, 状态 - {}, 错误信息 - {}", message.getContent(), status, errorMessage);
    }

}
