package com.example.sbb.controller;

import com.example.sbb.dto.MemberDto;
import com.example.sbb.form.MemberForm;
import com.example.sbb.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/signup")
    public String signup() {
        return "signup_form";
    }

    @PostMapping("/signup")
    public String signupPost(MemberForm memberForm) {

        memberService.create(
                memberForm.getUsername(),
                memberForm.getPassword(),
                memberForm.getEmail());
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "login_form";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/mypage")
    public String mypage(Principal principal,
                         Model model) {
        if(principal == null) return null;
        MemberDto dto = memberService.findByUsername(principal.getName());
        if(dto == null) return null;
        model.addAttribute("member", dto);
        return "mypage";
    }
}
