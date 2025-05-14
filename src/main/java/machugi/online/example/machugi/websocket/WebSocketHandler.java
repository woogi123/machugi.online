/*
 * package kkomentle.member.websocket;
 * 
 * import java.util.Map;
 * 
 * import org.springframework.stereotype.Component; import
 * org.springframework.web.socket.CloseStatus; import
 * org.springframework.web.socket.WebSocketSession; import
 * org.springframework.web.socket.handler.TextWebSocketHandler;
 * 
 * import jakarta.servlet.http.HttpSession; import kkomentle.member.room.Room;
 * import kkomentle.member.room.RoomManager;
 * 
 * @Component public class WebSocketHandler extends TextWebSocketHandler {
 *//**
	 * TextWebSocketHandler 메서드 목록
	 * 
	 * void afterConnectionEstablished(WebSocketSession session) 클라이언트가 웹소켓 연결을 맺을 때
	 * 
	 * void handleTextMessage(WebSocketSession session, TextMessage message) 클라이언트가
	 * 텍스트 메시지를 보낼 때 호출 (handleMessage() 대신 사용됨)
	 * 
	 * void handleBinaryMessage(WebSocketSession session, BinaryMessage message)
	 * 클라이언트가 바이너리 메시지를 보낼 때 호출
	 * 
	 * void handlePongMessage(WebSocketSession session, PongMessage message) Pong
	 * 메시지(WebSocket Ping-Pong) 처리
	 * 
	 * void handleTransportError(WebSocketSession session, Throwable exception) 웹소켓
	 * 오류 발생 시 호출
	 * 
	 * void afterConnectionClosed(WebSocketSession session, CloseStatus status) 웹소켓
	 * 연결이 닫혔을 때 호출
	 *//*
		 * 
		 * private final RoomManager roomManager;
		 * 
		 * public WebSocketHandler(RoomManager roomManager) { this.roomManager =
		 * roomManager; }
		 * 
		 * @Override public void afterConnectionEstablished(WebSocketSession session)
		 * throws Exception { System.out.println("TEST1"); Map<String, Object>
		 * attributes = session.getAttributes(); System.out.println("TEST2");
		 * 
		 * String userId = (String) attributes.get("loginEmail"); String roomId =
		 * (String) attributes.get("roomId"); System.out.println("TEST3");
		 * 
		 * System.out.println("세션에서 가져온 userId: " + userId);
		 * System.out.println("세션에서 가져온 roomId: " + roomId); Room room =
		 * roomManager.getRoom(roomId); room.addMember(userId);
		 * 
		 * 
		 * }
		 * 
		 * @Override public void afterConnectionClosed(WebSocketSession session,
		 * CloseStatus status) throws Exception { HttpSession httpSession =
		 * (HttpSession) session.getAttributes().get("HTTP.SESSION");
		 * 
		 * String userId = (String) httpSession.getAttribute("userId"); String roomId =
		 * (String) httpSession.getAttribute("roomId");
		 * 
		 * Room room = roomManager.getRoom(roomId); if (room != null) {
		 * room.removeMember(userId); if (room.isEmpty()) {
		 * roomManager.removeRoom(roomId); } } }
		 * 
		 * 
		 * }
		 */

// 놀랍게도 이건 전혀 쓸모가 없었따..... 내 시간...



