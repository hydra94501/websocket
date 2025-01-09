package com.gallery.websoket.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Slf4j
public class WebSocketInterceptor implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                   WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {

        // 1. 确保是 HTTP 请求
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest serverHttpRequest = (ServletServerHttpRequest) request;

            // 2. 获取 userId 和 tId 参数
            String userId = serverHttpRequest.getServletRequest().getParameter("userId");
            String tId = serverHttpRequest.getServletRequest().getParameter("tId");

            // 3. 参数校验：如果缺少 userId 或 tId，则不允许握手
            if (userId == null || userId.isEmpty()) {
                response.setStatusCode(org.springframework.http.HttpStatus.FORBIDDEN); // 设置 HTTP 错误响应
                log.warn("握手失败: 缺少 userId 参数");
                return false;
            }

            if (tId == null || tId.isEmpty()) {
                response.setStatusCode(org.springframework.http.HttpStatus.FORBIDDEN); // 设置 HTTP 错误响应
                log.warn("握手失败: 缺少 tId 参数");
                return false;
            }

            // 4. 将 userId 和 tId 存入 WebSocket 会话属性
            attributes.put("userId", userId);
            attributes.put("tId", tId);

            // 5. 获取 HttpSession（如果存在）
            HttpSession httpSession = serverHttpRequest.getServletRequest().getSession(false);
            if (httpSession != null) {
                attributes.put("currHttpSession", httpSession);
                log.info("HttpSession 找到: {}", httpSession.getId());
            } else {
                log.warn("HttpSession 为 null，WebSocket 连接可能未认证");
            }

            // 6. 如果需要对 userId 或 tId 进行额外的验证，可以在这里进行（例如，检查是否登录、权限验证等）

            return true;  // 返回 true 表示允许 WebSocket 握手
        }

        // 如果不是 HTTP 请求，则拒绝握手
        response.setStatusCode(org.springframework.http.HttpStatus.BAD_REQUEST);
        log.error("无效请求类型，只允许 HTTP 请求");
        return false;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
                               WebSocketHandler wsHandler, Exception ex) {
        if (ex != null) {
            log.error("握手完成时发生错误: {}", ex.getMessage());
        } else {
            log.info("握手成功完成");
        }
    }
}
