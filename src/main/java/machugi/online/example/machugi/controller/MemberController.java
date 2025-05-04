package machugi.online.example.machugi.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import machugi.online.example.machugi.dto.MemberDTO;
import machugi.online.example.machugi.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/machugi/save")
    public String saveForm(){
        return "save";
    }

    @PostMapping("/machugi/save")
    public String save(@ModelAttribute MemberDTO memberDTO){
        System.out.println("MemberController.save");
        memberService.save(memberDTO);
        return "login";
    }

    @GetMapping("/machugi/login")
    public String loginForm() {
        return "login";
    }


    @PostMapping("/machugi/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session, RedirectAttributes redirectAttributes){
        MemberDTO loginResult = memberService.login(memberDTO);
        if(loginResult != null){
            // login success
            session.setAttribute("loginEmail", loginResult.getMemberEmail());
            // 로그인 성공시, loginEmail이라는 이름으로 사용자 이메일을 저장
            // 페이지 이동이나 새로고침을 해도 사용자 값 유지
            // HttpSession: 로그인 후 세션 유지를 도와줌
            return "redirect:/select";
        }else{
            // login false 일 때 새로 추가
            redirectAttributes.addFlashAttribute("loginError", "아이디를 다시 입력해주세요!");
            return "redirect:/machugi/login";
        }
    }

    @GetMapping("machugi/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";  // 루트 경로로 리다이렉트
    }


    @PostMapping("/machugi/email-check")
    public @ResponseBody String emailCheck(@RequestParam("memberEmail") String memberEmail) {

        String checkResult = memberService.emailCheck(memberEmail);
        return checkResult;
    }


}