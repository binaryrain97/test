package com.example.sbb.dto;

import com.example.sbb.entity.Article;
import com.example.sbb.entity.Comment;
import com.example.sbb.entity.Member;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private List<Comment> commentList;
    private Member author;

    public static ArticleDto toDto(Article article) {
        return ArticleDto.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .createDate(article.getCreateDate())
                .commentList(article.getCommentList())
                .author(article.getAuthor())
                .build();
    }
}
