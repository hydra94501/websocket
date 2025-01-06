package com.gallery.websoket.interceptor;


import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

public class WebSocketInterceptor implements HandshakeInterceptor {


    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                   WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest serverHttpRequest = (ServletServerHttpRequest) request;
            //获取参数
            String userId = serverHttpRequest.getServletRequest().getParameter("userId");
            String name = serverHttpRequest.getServletRequest().getParameter("name");
            System.out.println("拦截入参:" + userId);
            attributes.put("userId", userId);
            if (name == null) {
                // 如果参数为存0 然后判断这个参数是否为0就可以了
                attributes.put("name", "0");
            } else {
                attributes.put("name", name);
            }

            HttpSession httpSession = serverHttpRequest.getServletRequest().getSession(false);
            if (httpSession != null) {
                attributes.put("currHttpSession", httpSession);
            } else {
                System.out.println("httpsession is null");
            }
        }
        // 返回 true 表示允许 WebSocket 握手
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
                               WebSocketHandler wsHandler, Exception ex) {
        // 握手完成后不需要执行任何操作
    }


}
