package machugi.online.example.machugi.room;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    private String roomId;
    private String name;
    private int maxUsers;
    private String topic;

    @Builder.Default
    private Set<String> member = ConcurrentHashMap.newKeySet();
   //ConcurrentHashMap<String, Boolean> map = new ConcurrentHashMap<>();
   //Set<String> member = map.keySet(Boolean.TRUE);
    
   // value는 사용 안할것이기 때문에 항상 true인 boolen값으로 생략, key값인 userId만 사용
   // 그렇다면 왜 ConcurrentHashMap을 쓰냐? >> 멀티스레드에서 안전해서 

    // builder 라는 것을 써봤음. 
    // > 생성자의 가독성 높여줌 >> 필드가 많아질 수록 유용
    /** Room room = new Room("123", "Chat Room", 10, "Technology");
     >>
     Room room = Room.builder()
                .roomId("123")
                .name("Chat Room")
                .maxUsers(10)
                .topic("Technology")
                .build();  */
    
    // 해당 방의 roomId 반환
    public String getRoomId() {
        return roomId;
    }

    // 해당 방의 멤버목록 반환
    public Set<String> getMember() {
        return member;
    }

    // 멤버 추가
    public void addMember(String userId) {
        member.add(userId);
    }
    
    // 멤버 삭제
    public void removeMember(String userId) {
        member.remove(userId);
    }
    
    // 방에 멤버가 남아있나? > 후에 방 삭제를 위함
    public boolean isEmpty() {
        return member.isEmpty();
    }
}

