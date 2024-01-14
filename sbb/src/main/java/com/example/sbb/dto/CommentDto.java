package com.example.sbb.dto;

import com.example.sbb.entity.Article;
import com.example.sbb.entity.Comment;
import com.example.sbb.entity.Member;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private Long id;
    private String content;
    private LocalDateTime createDate;
    private Article article;
    private Member author;
    private LocalDateTime modifyDate;

    public static CommentDto toDto(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .createDate(comment.getCreateDate())
                .article(comment.getArticle())
                .author(comment.getAuthor())
                .modifyDate(comment.getModifyDate())
                .build();
    }
}
