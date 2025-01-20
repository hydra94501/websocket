package com.gallery.websoket.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gallery.websoket.dto.parm.MyWebSocketMessage;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MyMessageConverter implements MessageConverter {

    private final ObjectMapper objectMapper;

    public MyMessageConverter() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public Object fromMessage(Message<?> message, Class<?> targetClass) {
        // 检查消息负载是否为字节数组
        if (message.getPayload() instanceof byte[]) {
            byte[] payload = (byte[]) message.getPayload();
            try {
                // 反序列化字节数组为 MyWebSocketMessage 对象
                return deserialize(payload, MyWebSocketMessage.class);
            } catch (Exception e) {
                log.error("反序列化失败，字节数组内容: {}", new String(payload), e);
                throw new RuntimeException("反序列化消息失败", e);
            }
        }
        // 如果负载不是字节数组，可以添加其他处理逻辑
        return null;
    }

    @Override
    public Message<?> toMessage(Object o, MessageHeaders messageHeaders) {
        if (o instanceof MyWebSocketMessage) {
            try {
                byte[] payload = objectMapper.writeValueAsBytes(o);
                return new org.springframework.messaging.support.GenericMessage<>(payload, messageHeaders);
            } catch (Exception e) {
                log.error("序列化 MyWebSocketMessage 失败", e);
                throw new RuntimeException("序列化消息失败", e);
            }
        }
        // 如果对象类型不是 MyWebSocketMessage，可以返回 null 或者其他逻辑
        return null;
    }

    private MyWebSocketMessage deserialize(byte[] payload, Class<MyWebSocketMessage> targetClass) throws Exception {
        // 使用 ObjectMapper 反序列化
        return objectMapper.readValue(payload, targetClass);
    }
}
