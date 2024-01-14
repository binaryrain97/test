package com.example.sbb.controller;

import com.example.sbb.dto.ArticleDto;
import com.example.sbb.entity.Article;
import com.example.sbb.entity.Member;
import com.example.sbb.service.ArticleService;
import com.example.sbb.service.CommentService;
import com.example.sbb.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;
    private final ArticleService articleService;
    private final MemberService memberService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/{articleId}")
    public String create(Model model,
                         @PathVariable Long articleId,
                         @RequestParam String content,
                         Principal principal) {
        ArticleDto dto = articleService.getArticle(articleId);
        Member member = memberService.getMember(principal.getName());
        this.commentService.create(Article.toEntity(dto), content, member);
        return "redirect:/article/" + articleId;
    }
}
