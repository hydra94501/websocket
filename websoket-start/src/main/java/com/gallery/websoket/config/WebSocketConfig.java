package com.gallery.websoket.config;


import com.gallery.websoket.handler.MyWebSocketHandler;
import com.gallery.websoket.interceptor.WebSocketInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler(), "/websocket")
                .addInterceptors(new WebSocketInterceptor())  // 注册拦截器
                .setAllowedOrigins("*"); // 允许所有来源
//                .withSockJS();           // 启用 SockJS fallback
    }

    @Bean
    public MyWebSocketHandler webSocketHandler() {
        return new MyWebSocketHandler();
    }
}