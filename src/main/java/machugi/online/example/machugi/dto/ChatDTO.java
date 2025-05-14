package machugi.online.example.machugi.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class ChatDTO {
    public enum Type {
        ENTER, CHAT, LEAVE
    }
    private Type type; // 메시지 타입 (입장, 채팅, 퇴장)
	private String userId;
	private String roomId;
	private String message;
	

}
