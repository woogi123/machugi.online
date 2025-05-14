package machugi.online.example.machugi.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/room"); // 메시지를 전달할 경로 (구독 경로)
		config.setApplicationDestinationPrefixes("/send"); // 클라이언트가 메시지를 보낼 경로
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/ws") // 웹소켓 연결 엔드포인트
				.addInterceptors(new HttpSessionHandshakeInterceptor())
				.setAllowedOrigins("http://localhost:8080")
				.withSockJS(); // SockJS 지원
	}

}
