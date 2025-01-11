package com.gallery.websoket.factory;

import com.gallery.websoket.enums.MessageTargetType;
import com.gallery.websoket.service.MessagePushStrategy;
import com.gallery.websoket.service.impl.message.AllPushStrategy;
import com.gallery.websoket.service.impl.message.GroupPushStrategy;
import com.gallery.websoket.service.impl.message.TopicPushStrategy;
import com.gallery.websoket.service.impl.message.UserPushStrategy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class MessagePushStrategyFactory {

    private final Map<MessageTargetType, MessagePushStrategy> strategies;

    public MessagePushStrategyFactory(List<MessagePushStrategy> strategyList) {
        strategies = strategyList.stream()
                .collect(Collectors.toMap(strategy -> {
                    if (strategy instanceof UserPushStrategy) {
                        return MessageTargetType.USER;
                    } else if (strategy instanceof GroupPushStrategy) {
                        return MessageTargetType.GROUP;
                    } else if (strategy instanceof AllPushStrategy) {
                        return MessageTargetType.ALL;
                    } else if (strategy instanceof TopicPushStrategy) {
                        return MessageTargetType.TOPIC;
                    }
                    throw new IllegalArgumentException("Unknown strategy: " + strategy.getClass().getSimpleName());
                }, strategy -> strategy));
    }

    public MessagePushStrategy getStrategy(MessageTargetType type) {
        return strategies.get(type);
    }
}
