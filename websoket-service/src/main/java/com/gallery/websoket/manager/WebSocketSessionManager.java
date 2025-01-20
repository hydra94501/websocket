package com.gallery.websoket.manager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
@Slf4j
public class WebSocketSessionManager {

    // 使用 ConcurrentMap 管理 WebSocket 会话
    private final ConcurrentMap<String, WebSocketSession> sessionMap = new ConcurrentHashMap<>();
    // 管理心跳的最后时间
    private final ConcurrentMap<String, Long> lastHeartbeatMap = new ConcurrentHashMap<>();

    // 添加会话
    public void addSession(String userId, WebSocketSession session) {
        sessionMap.put(userId, session);
        lastHeartbeatMap.put(userId, System.currentTimeMillis());  // 初始化心跳时间
    }

    // 获取会话
    public WebSocketSession getSession(String userId) {
        return sessionMap.get(userId);
    }

    // 移除会话
    public void removeSession(String userId) {
        sessionMap.remove(userId);
        lastHeartbeatMap.remove(userId);
    }

    // 获取所有会话
    public ConcurrentMap<String, WebSocketSession> getAllSessions() {
        return sessionMap;
    }

    // 更新心跳时间
    public void updateHeartbeat(String userId) {
        lastHeartbeatMap.put(userId, System.currentTimeMillis());
    }

    // 检查心跳超时
    public void checkHeartbeatTimeout(long timeout) {
        long currentTime = System.currentTimeMillis();
        for (String userId : lastHeartbeatMap.keySet()) {
            long lastHeartbeat = lastHeartbeatMap.get(userId);
            if (currentTime - lastHeartbeat > timeout) {
                WebSocketSession session = sessionMap.get(userId);
                if (session != null && session.isOpen()) {
                    try {
                        // 处理超时的会话，可以选择关闭该连接
                        session.close();
                        sessionMap.remove(userId);
                        lastHeartbeatMap.remove(userId);
                        System.out.println("用户 " + userId + " 的连接已超时并关闭");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
