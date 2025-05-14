package machugi.online.example.machugi.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;


@Service
public class ChatService {
	private final Map<String, Set<String>> rooms = new HashMap<>();



	public void addLog(String log) {
		
	}

}
