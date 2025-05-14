package machugi.online.example.machugi.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import machugi.online.example.machugi.dto.ChatDTO;
import machugi.online.example.machugi.dto.ChatDTO.Type;
import machugi.online.example.machugi.room.Room;
import machugi.online.example.machugi.room.RoomManager;
import machugi.online.example.machugi.service.ChatService;

@Controller
@RequiredArgsConstructor
public class ChatController {
	//private final SimpMessagingTemplate messagingTemplate;
	private final ChatService chatService;
	private final RoomManager roomManager;

	// @SubscribeMapping 이 제대로 작동 안 함
	/*
	 * // 구독 시 room에 멤버 추가
	 * 
	 * @SubscribeMapping("/") public void SubscribeRoom(HttpSession
	 * session, @Header("roomId") String roomId) { String userId = (String)
	 * session.getAttribute("loginEmail"); System.out.println("test1");
	 * if(session.getAttribute("roomId") == null) session.setAttribute("roomId",
	 * roomId); // 세션에 roomId 추가 Room room = roomManager.getRoom(roomId);
	 * room.addMember(userId); System.out.println(room.getMember()); }
	 */

	// 메세지 수신, 전달
	@MessageMapping("/{roomId}")
	@SendTo("/room/{roomId}")
	public ChatDTO sendMessage(ChatDTO message) {
		
		// 입장 시 room에 추가
		if (message.getType() == Type.ENTER) {
			String roomId = message.getRoomId();
			String userId = message.getUserId();
			System.out.println("test1");

			Room room = roomManager.getRoom(roomId);
			room.addMember(userId);
			System.out.println(room.getMember());
		}
		return message;
	}

	// 방 리스트 출력
	@GetMapping("/RoomList")
	public ResponseEntity<List<Room>> RoomList() {
		Map<String, Room> rooms = roomManager.getAllRooms();
		return ResponseEntity.ok(new ArrayList<>(rooms.values()));
	}

	// 방 생성
	@PostMapping("/CreateRoom")
	public ResponseEntity<String> CreateRoom(@RequestBody Room room, HttpSession session) {
		if (session.getAttribute("userId") != "") {
			String roomId = generateShortRoomId();
			room.setRoomId(roomId);
			roomManager.createRoom(roomId, room);

			System.out.println(roomManager.getAllRooms());
			return ResponseEntity.ok(roomId);
		} else
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("noLogin");

	}

	// 로그 저장 (나중에 구현할거임)
	@PostMapping("/addLog")
	public void addLog(String Log) {
		chatService.addLog(Log);
	}

	// html 연결
	@GetMapping("/room/{roomId}")
	public String enterRoom(@PathVariable("roomId") String roomId, HttpSession session, Model model) {

		// 존재하는 방 인가?
		if (roomManager.isRoom(roomId)) {
			String userId = (String) session.getAttribute("loginEmail");
			
			// 세션, 모델에 정보 저장해서 넘겨주기
			session.setAttribute("roomId", roomId);
			model.addAttribute("roomId", roomId);
			model.addAttribute("userId", userId);
			
			return "test";
		} else
			return "roomEnterError";

	}

	@GetMapping("/roomList")
	public String roomList() {
		return "roomList";
	}

	// 방 ID 랜덤 생성기
	public static String generateShortRoomId() {
		UUID uuid = UUID.randomUUID();
		BigInteger bigInt = new BigInteger(uuid.toString().replace("-", ""), 16);
		return bigInt.toString(36); // base36 or base62로 줄이기
	}

}
