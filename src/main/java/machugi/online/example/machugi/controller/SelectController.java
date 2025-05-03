package machugi.online.example.machugi.controller;

import lombok.RequiredArgsConstructor;
import machugi.online.example.machugi.dto.SelectDTO;
import machugi.online.example.machugi.service.SelectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class SelectController {
    private final SelectService selectService;

    @GetMapping("/select")
    String selectForm(Model model){
        model.addAttribute("games", selectService.findAll()); // games를 모델에 담음

        List<SelectDTO> games = selectService.findAll();
        System.out.println("게임 개수: " + games.size());  // 게임의 개수가 제대로 나오는지 확인
        for (SelectDTO dto : games) {
            System.out.println("게임 정보: " + dto);  // 게임 정보가 제대로 출력되는지 확인
        }

        return "/select";
    }

    @GetMapping("/select/newGame")
    String newGameForm(){
        return "/newGame";
    }

    @GetMapping("/select/findGame")
    String findGameForm(){
        return "/findGame";
    }

    @PostMapping("/select/newGame")
    public String saveNewGame(@ModelAttribute SelectDTO selectDTO) throws IOException {
        System.out.println("selectDTO = " + selectDTO);
        selectService.save(selectDTO);
        return "redirect:/select";
    }
}
