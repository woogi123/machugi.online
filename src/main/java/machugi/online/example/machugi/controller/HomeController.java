package machugi.online.example.machugi.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index(HttpSession session){

        String loginEmail = (String) session.getAttribute("loginEmail");

        // 로그인 체크
        if (loginEmail != null)
            //로그인 되어있으면 바로 게임페이지로
            return "redirect:/select";
        else
            // 아니면 회원가입,로그인페이지
            return "index";

    }



}
