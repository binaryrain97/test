package com.example.sbb.service;

import com.example.sbb.entity.Article;
import com.example.sbb.entity.Member;
import com.example.sbb.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public List<Article> getList() {
        return articleRepository.findAll();
    }

    public Article getArticle(Long articleId) {
        Article article = articleRepository.findById(articleId).orElse(null);
        return article;
    }
    public void create(String title, String content, Member author) {
        Article article = Article.builder()
                .title(title)
                .content(content)
                .createDate(LocalDateTime.now())
                .author(author)
                .build();

        Article created = this.articleRepository.save(article);
    }

    public Page<Article> getList(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return this.articleRepository.findAll(pageable);
    }
}
