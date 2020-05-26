package controller;

import java.util.Map;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

/**
 * 描述:
 * Socket建立连接（握手）和断开
 *
 * @author Msater Zg
 * @create 2018-01-24 10:49
 */
@Component
public class HandShake implements HandshakeInterceptor {
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) throws Exception {
        /**
         * websocket系统启动连接程序，启动的时候就会把他的session值传过来，放入到websocketsession（websocket的一个内置服务器）里面
         */
        ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
        String uid = (String)(servletRequest.getServletRequest().getParameter("uid"));
        String sid = (String)(servletRequest.getServletRequest().getParameter("sid"));
        if (uid != null && sid != null) {
            attributes.put("sid", sid);
            attributes.put("uid", uid);
        } else {
            return false;
        }
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
                               Exception exception) {
    }
}