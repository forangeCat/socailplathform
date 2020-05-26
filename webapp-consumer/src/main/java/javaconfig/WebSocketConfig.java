package javaconfig;

import controller.HandShake;
import controller.MyWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    MyWebSocketHandler handler;
    @Autowired
    HandShake handShake;
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(handler, "/ws")
                .addInterceptors(handShake);
//        registry.addHandler(new MyWebSocketHandler(), "/ws/sockjs")
//                .addInterceptors(new HttpSessionHandshakeInterceptor()).withSockJS().setMessageCodec(new
//                Jackson2SockJsMessageCodec());

    }
}