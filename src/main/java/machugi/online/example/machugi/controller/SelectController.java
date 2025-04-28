package machugi.online.example.machugi.controller;

import lombok.RequiredArgsConstructor;
import machugi.online.example.machugi.dto.SelectDTO;
import machugi.online.example.machugi.service.SelectService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class SelectController {
    private final SelectService selectService;

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

    @PostMapping("/select")
    public String image_attached(@ModelAttribute SelectDTO selectDTO){
        System.out.println("boardDTO = "+ selectDTO);
        SelectService.save(selectDTO);
        return "select";
    }
}
