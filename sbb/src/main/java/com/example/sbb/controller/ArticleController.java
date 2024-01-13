package com.example.sbb.controller;

import com.example.sbb.entity.Article;
import com.example.sbb.entity.Member;
import com.example.sbb.service.ArticleService;
import com.example.sbb.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequiredArgsConstructor
@RequestMapping("/article")
@Controller
public class ArticleController {

    private final ArticleService articleService;
    private final MemberService memberService;

    @GetMapping("/list")
    public String list(Model model,
                       @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<Article> paging = this.articleService.getList(page);
        model.addAttribute("articleList", paging);
        return "article_list";
    }
    @GetMapping("/{articleId}")
    public String detail(@PathVariable Long articleId,
                         Model model) {
        Article article = articleService.getArticle(articleId);
        model.addAttribute("article", article);
        return "article_detail";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String articleCreate() {
        return "article_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String articleCreate(@RequestParam(value = "title") String title,
                                @RequestParam(value = "content") String content,
                                Principal principal) {
        Member member = this.memberService.getMember(principal.getName());
        this.articleService.create(title, content, member);
        return "redirect:/article/list";
    }
}
