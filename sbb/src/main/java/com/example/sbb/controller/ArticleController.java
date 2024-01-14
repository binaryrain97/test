package com.example.sbb.controller;

import com.example.sbb.dto.ArticleDto;
import com.example.sbb.entity.Article;
import com.example.sbb.entity.Member;
import com.example.sbb.service.ArticleService;
import com.example.sbb.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
        ArticleDto dto = articleService.getArticle(articleId);
        model.addAttribute("article", dto);
        return "article_detail";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String articleCreate() {
        return "article_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String articleCreate(ArticleDto dto,
                                Principal principal) {
        Member member = this.memberService.getMember(principal.getName());
        dto.setAuthor(member);
        this.articleService.create(dto);
        return "redirect:/article/list";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{articleId}")
    public String delete(@PathVariable Long articleId,
                         Principal principal) {
        ArticleDto dto = articleService.getArticle(articleId);
        if(dto == null) return "redirect:/article/" + articleId;
        if(!dto.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        articleService.delete(articleId);
        return "redirect:/article/list";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{articleId}")
    public String modify(@PathVariable Long articleId,
                         Model model,
                         Principal principal) {
        ArticleDto dto = this.articleService.getArticle(articleId);
        if(!dto.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        model.addAttribute("article", dto);
        return "modify_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{articleId}")
    public String modifyPost(@PathVariable Long articleId,
                         @ModelAttribute ArticleDto articleDto,
                         Principal principal) {
        ArticleDto dto = this.articleService.getArticle(articleId);
        if(!dto.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        articleService.modify(articleId, articleDto);
        return "redirect:/article/" + articleId;
    }
}
