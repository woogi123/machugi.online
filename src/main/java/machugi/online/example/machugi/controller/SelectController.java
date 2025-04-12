package machugi.online.example.machugi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class SelectController {
    @GetMapping("/select")
    String selectForm(){
        return "select";
    }

    @GetMapping("/select/newGame")
    String newGameForm(){
        return "/newGame";
    }

    @GetMapping("/select/findGame")
    String findGameForm(){
        return "findGame";
    }
}
