package com.example.sbb.service;

import com.example.sbb.dto.CommentDto;
import com.example.sbb.entity.Article;
import com.example.sbb.entity.Comment;
import com.example.sbb.entity.Member;
import com.example.sbb.repository.ArticleRepository;
import com.example.sbb.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    public void create(Article article, String content, Member author) {
        Comment comment = Comment.builder()
                .content(content)
                .createDate(LocalDateTime.now())
                .article(article)
                .author(author)
                .build();

        this.commentRepository.save(comment);
    }
    public CommentDto create(Long articleId, CommentDto dto) {
        Article article = articleRepository.findById(articleId).orElse(null);
        if(article == null) return null;
        dto.setArticle(article);
        Comment comment = Comment.toEntity(dto);
        Comment created = this.commentRepository.save(comment);
        return CommentDto.toDto(created);
    }

    public CommentDto findById(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if(comment == null) return null;
        return CommentDto.toDto(comment);
    }
    public void delete(Long commentId) {
        Comment comment = this.commentRepository.findById(commentId).orElse(null);
        if(comment == null) return;
        this.commentRepository.delete(comment);
    }
}
