package machugi.online.example.machugi.room;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public class RoomManager {
	private final Map<String, Room> rooms = new ConcurrentHashMap<>();
	// key는 roomId, value는 Room클래스 객체
	
	
	 // 방 생성
    public Room createRoom(String roomId,Room room) {
    	//putIfAbsent : 해당 key가 존재하지 않을 경우 추가 / 일반 put은 그냥 덮어씀
        rooms.putIfAbsent(roomId, room);
        
        return rooms.get(roomId); // roomId를 key로 가진 Room 반환 
    }
    
    // 방 존재 유무
    public boolean isRoom(String roomId) {
    	return rooms.containsKey(roomId);
    }
    
    // 방 가져오기
    public Room getRoom(String roomId) {
        return rooms.get(roomId);
    }

    // 방 삭제
    public void removeRoom(String roomId) {
        rooms.remove(roomId);
    }

    // 모든 방 조회
    public Map<String, Room> getAllRooms() {
        return rooms;
    }
}
