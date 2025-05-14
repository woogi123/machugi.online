package machugi.online.example.machugi.websocket;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionUnsubscribeEvent;

import machugi.online.example.machugi.room.Room;
import machugi.online.example.machugi.room.RoomManager;

@Component
public class StompEventListener {
	@Autowired
	private RoomManager roomManager;

	@EventListener
	public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

		// WebSocket 세션의 HttpSession 정보 가져오기
		Map<String, Object> sessionAttributes = headerAccessor.getSessionAttributes();

		if (sessionAttributes != null) {
			String userId = (String) sessionAttributes.get("loginEmail");
			String roomId = (String) sessionAttributes.get("roomId");

			System.out.println("사용자 " + userId + "이(가) 방에서 나감: " + roomId);

			// 방에서 사용자 제거
			Room room = roomManager.getRoom(roomId);
			if (room != null) {
				room.removeMember(userId);
				if (room.isEmpty()) {
					roomManager.removeRoom(roomId);
				}

			}
			// 세션에서 roomId 삭제
			sessionAttributes.remove("roomId");
			System.out.println("세션에서 roomId 제거 완료.");
		}
	}

	@EventListener
	public void handleUnsubscribeEvent(SessionUnsubscribeEvent event) {
		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

		// WebSocket 세션의 HttpSession 정보 가져오기
		Map<String, Object> sessionAttributes = headerAccessor.getSessionAttributes();

		if (sessionAttributes != null) {
			String userId = (String) sessionAttributes.get("loginEmail");
			String roomId = (String) sessionAttributes.get("roomId");

			System.out.println("사용자 " + userId + "이(가) 방에서 나감: " + roomId);
			// 방에서 사용자 제거
			Room room = roomManager.getRoom(roomId);
			if (room != null) {
				room.removeMember(userId);
				if (room.isEmpty()) {
					roomManager.removeRoom(roomId);
				}

			}
			// 세션에서 roomId 삭제
			sessionAttributes.remove("roomId");
			System.out.println("세션에서 roomId 제거 완료.");
		}
	}
}
