package com.example.sbb.service;

import com.example.sbb.dto.ArticleDto;
import com.example.sbb.entity.Article;
import com.example.sbb.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public List<ArticleDto> getList() {
        List<Article> articleList = articleRepository.findAll();
        List<ArticleDto> articleDtos = new ArrayList<>();
        for(int i=0; i<articleList.size(); i++) {
            ArticleDto dto = ArticleDto.toDto(articleList.get(i));
            articleDtos.add(dto);
        }
        return articleDtos;
    }

    public ArticleDto getArticle(Long articleId) {
        Article article = articleRepository.findById(articleId).orElse(null);
        if(article != null) return ArticleDto.toDto(article);
        else return null;
    }
    public ArticleDto create(ArticleDto dto) {
        Article article = Article.toEntity(dto);
        Article created = this.articleRepository.save(article);
        return ArticleDto.toDto(created);
    }

    public Page<Article> getList(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return this.articleRepository.findAll(pageable);
    }
    public ArticleDto delete(Long articleId) {
        Article article = articleRepository.findById(articleId).orElse(null);
        if(article == null) return null;
        articleRepository.delete(article);
        return ArticleDto.toDto(article);
    }

    public ArticleDto modify(Long articleId, ArticleDto dto) {
        Article article = articleRepository.findById(articleId).orElse(null);
        if(article == null) return null;
        article.patch(Article.toEntity(dto));
        Article result = articleRepository.save(article);
        return ArticleDto.toDto(result);
    }
}
