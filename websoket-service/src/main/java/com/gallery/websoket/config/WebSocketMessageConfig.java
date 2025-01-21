package com.gallery.websoket.config;

import com.gallery.websoket.converter.MyMessageConverter;
import com.gallery.websoket.interceptor.WebSocketInterceptor;
import com.gallery.websoket.interceptor.WebSocketMessageInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

import java.util.List;


@Slf4j
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketMessageConfig implements WebSocketMessageBrokerConfigurer {

    private final WebSocketInterceptor webSocketInterceptor;
    private final WebSocketMessageInterceptor webSocketMessageInterceptor;
    private final MyMessageConverter myMessageConverter;

    @Autowired
    public WebSocketMessageConfig(WebSocketInterceptor webSocketInterceptor, WebSocketMessageInterceptor webSocketMessageInterceptor, MyMessageConverter myMessageConverter) {
        this.webSocketInterceptor = webSocketInterceptor;
        this.webSocketMessageInterceptor = webSocketMessageInterceptor;
        this.myMessageConverter = myMessageConverter;
    }

    /**
     * 添加这个Endpoint，这样在网页中就可以通过websocket连接上服务,
     * 也就是我们配置websocket的服务地址,并且可以指定是否使用socketjs
     *
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        /*
         * 1. 将 /ws/ep路径注册为STOMP的端点，
         *    用户连接了这个端点后就可以进行websocket通讯，支持socketJs
         * 2. setAllowedOrigins("*")表示可以跨域
         * 3. withSockJS()表示支持socktJS访问
         * 4. addInterceptors 添加自定义拦截器，这个拦截器是上一个demo自己定义的获取httpsession的拦截器
         * 5. addInterceptors 添加拦截处理，这里MyPrincipalHandshakeHandler 封装的认证用户信息
         */
        //  这个问题的sendToUser 解决了
        registry.addEndpoint("/message").addInterceptors(webSocketInterceptor).setAllowedOrigins("*").withSockJS();

    }


    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        /*
         *  enableStompBrokerRelay 配置外部的STOMP服务，需要安装额外的支持 比如rabbitmq或activemq
         * 1. 配置代理域，可以配置多个，此段代码配置代理目的地的前缀为 /topic 或者 /group 或者 /user
         *    我们就可以在配置的域上向客户端推送消息
         * 3. 可以通过 setRelayHost 配置代理监听的host,默认为localhost
         * 4. 可以通过 setRelayPort 配置代理监听的端口，默认为61613
         * 5. 可以通过 setClientLogin 和 setClientPasscode 配置账号和密码
         * 6. setxxx这种设置方法是可选的，根据业务需要自行配置，也可以使用默认配置
         */
        //registry.enableStompBrokerRelay("/topic","/group","/user")
        //.setRelayHost("rabbit.someotherserver")
        //.setRelayPort(62623);
        //.setClientLogin("userName")
        //.setClientPasscode("password")
        //;

        // 自定义调度器，用于控制心跳线程
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        // 线程池线程数，心跳连接开线程
        taskScheduler.setPoolSize(1);
        // 线程名前缀
        taskScheduler.setThreadNamePrefix("websocket-heartbeat-thread-");
        // 初始化
        taskScheduler.initialize();

        /*
         * spring 内置broker对象
         * 1. 配置代理域，可以配置多个，此段代码配置代理目的地的前缀为 /topicTest 或者 /userTest
         *    我们就可以在配置的域上向客户端推送消息
         * 2，进行心跳设置，第一值表示server最小能保证发的心跳间隔毫秒数, 第二个值代码server希望client发的心跳间隔毫秒数
         * 服务器每 10 秒发送一次心跳包（ping），并期望客户端也能在 10 秒内响应一个心跳包
         * 如果客户端在 3次心跳超时后仍未响应，服务端可能会认为连接断开并关闭该连接。
         * 3. 可以配置心跳线程调度器 setHeartbeatValue这个不能单独设置，不然不起作用，要配合setTaskScheduler才可以生效
         *    调度器我们可以自己写一个，也可以自己使用默认的调度器 new DefaultManagedTaskScheduler()
         */
        registry.enableSimpleBroker("/topic", "/group", "/user")
                .setHeartbeatValue(new long[]{10000, 10000})
                .setTaskScheduler(taskScheduler);
        /*
         *  "/app" 为配置应用服务器的地址前缀，表示所有以/app 开头的客户端消息或请求
         *  都会路由到带有@MessageMapping 注解的方法中
         */
        registry.setApplicationDestinationPrefixes("/api/message");

        /*
         *  1. 配置一对一消息前缀， 客户端接收一对一消息需要配置的前缀 如“'/user/'+userid + '/message'”，
         *     是客户端订阅一对一消息的地址 stompClient.subscribe js方法调用的地址
         *  2. 使用@SendToUser发送私信的规则不是这个参数设定，在框架内部是用UserDestinationMessageHandler处理，
         *     而不是而不是 AnnotationMethodMessageHandler 或  SimpleBrokerMessageHandler
         *     or StompBrokerRelayMessageHandler，是在@SendToUser的URL前加“user+sessionId"组成
         */
        registry.setUserDestinationPrefix("/user");

        /*
         * 自定义路径分割符
         * 注释掉的这段代码添加的分割符为. 分割是类级别的@messageMapping和方法级别的@messageMapping的路径
         * 例如类注解路径为 “topic”,方法注解路径为“hello”，那么客户端JS stompClient.send 方法调用的路径为“/app/topic.hello”
         * 注释掉此段代码后，类注解路径“/topic”,方法注解路径“/hello”,JS调用的路径为“/app/topic/hello”
         */
        //registry.setPathMatcher(new AntPathMatcher("."));

    }


    /**
     * 添加自定义的消息转换器，spring 提供多种默认的消息转换器，
     * 返回false,不会添加消息转换器，返回true，会添加默认的消息转换器，当然也可以把自己写的消息转换器添加到转换链中
     *
     * @param
     * @return
     */
    @Override
    public boolean configureMessageConverters(List<MessageConverter> converters) {
        // 清空默认的消息转换器（可选）
        converters.clear();
        // 添加自定义消息转换器
        converters.add(0, myMessageConverter);
        // 如果需要 Jackson 支持，可以添加 MappingJackson2MessageConverter
        //converters.add(new MappingJackson2MessageConverter());
        return true;
    }


    /**
     * 配置消息线程池
     * 1. corePoolSize 配置核心线程池，当线程数小于此配置时，不管线程中有无空闲的线程，都会产生新线程处理任务
     * 2. maxPoolSize 配置线程池最大数，当线程池数等于此配置时，不会产生新线程
     * 3. keepAliveSeconds 线程池维护线程所允许的空闲时间，单位秒
     */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.taskExecutor().corePoolSize(10)
                .maxPoolSize(20)
                .keepAliveSeconds(60);
        registration.interceptors(webSocketMessageInterceptor);  // 注册入站消息拦截器(消息并是由 STOMP 客户端发送)
    }


    /**
     * 设置输出消息通道的线程数，默认线程为1，可以自己自定义线程数，最大线程数，线程存活时间
     *
     * @param registration
     */
    @Override
    public void configureClientOutboundChannel(ChannelRegistration registration) {

        registration.taskExecutor().corePoolSize(10)
                .maxPoolSize(20)
                .keepAliveSeconds(60);

        registration.interceptors(webSocketMessageInterceptor);  // 注册出站消息拦截器(消息并是由 STOMP 客户端发送)
    }


    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registry) {

        // 配置 WebSocket 传输
        registry.setMessageSizeLimit(8192);  // 增加消息大小限制
        registry.setSendBufferSizeLimit(8192); // 增加缓冲区大小
//        registry.setSendTimeLimit(20 * 1000);  // 增加超时设置
    }


    /**
     * registerStompEndpoints(StompEndpointRegistry registry)
     * 这个方法用于注册 WebSocket 的 STOMP 端点。在这个方法中，开发者可以定义 WebSocket 连接的路径和支持的跨域设置。
     * 默认实现为空方法，开发者需要重写它来注册端点。例如：registry.addEndpoint("/ws").setAllowedOrigins("*").withSockJS();。
     *
     * configureWebSocketTransport(WebSocketTransportRegistration registry)
     * 这个方法用于配置 WebSocket 传输的相关设置，例如设置 WebSocket 的传输消息的缓冲区大小、最大消息大小、心跳时间等。
     * 默认实现为空方法，开发者可以根据需求进行自定义配置。
     *
     * configureClientInboundChannel(ChannelRegistration registration)
     * 该方法用于配置 WebSocket 客户端的入站消息通道，控制消息的接收。通过 ChannelRegistration 可以设置消息的处理机制，比如添加拦截器、消息过滤器等。
     * 默认实现为空方法，开发者可以重写该方法来对入站消息通道进行定制化配置。
     *
     * configureClientOutboundChannel(ChannelRegistration registration)
     * 该方法用于配置 WebSocket 客户端的出站消息通道，控制消息的发送。通过 ChannelRegistration，开发者可以配置发送消息时的处理机制，例如设置消息的编码、添加拦截器等。
     * 默认实现为空方法，开发者可以重写该方法来定制出站消息通道的行为。
     *
     * addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers)
     * 该方法允许开发者向 WebSocket 消息的处理方法中添加自定义参数解析器。可以在这里注册新的参数解析器，将 WebSocket 消息体转换成特定对象，供处理方法使用。
     * 默认实现为空方法，开发者可以通过实现该方法来自定义解析器的注册。
     *
     * addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers)
     * 该方法允许开发者向 WebSocket 消息的处理方法中添加自定义的返回值处理器。返回值处理器可以用于将处理方法返回的对象转换成 WebSocket 消息体。
     * 默认实现为空方法，开发者可以实现该方法来处理 WebSocket 处理方法的返回值。
     *
     * configureMessageConverters(List<MessageConverter> messageConverters)
     * 该方法用于配置消息转换器，用于 WebSocket 消息的编码和解码。通过 messageConverters，开发者可以定义消息如何转换为适当的格式（例如 JSON、XML 或自定义格式）。
     * 默认实现返回 true，表示使用默认的消息转换器，开发者可以重写该方法并返回 false 来禁用默认配置，或者返回 true 并提供自定义的转换器。
     *
     * configureMessageBroker(MessageBrokerRegistry registry)
     * 该方法用于配置 WebSocket 消息代理的相关设置，例如启用消息代理、定义消息代理的目标前缀（例如 /topic、/queue）等。
     * 默认实现为空方法，开发者可以通过重写该方法来自定义消息代理的配置。
     */
}