package com.example.sbb.service;

import com.example.sbb.entity.Article;
import com.example.sbb.entity.Comment;
import com.example.sbb.entity.Member;
import com.example.sbb.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public void create(Article article, String content, Member author) {
        Comment comment = Comment.builder()
                .content(content)
                .createDate(LocalDateTime.now())
                .article(article)
                .author(author)
                .build();

        this.commentRepository.save(comment);
    }

}
