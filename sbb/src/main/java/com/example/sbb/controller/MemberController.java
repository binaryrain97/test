package com.example.sbb.controller;

import com.example.sbb.form.MemberForm;
import com.example.sbb.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
