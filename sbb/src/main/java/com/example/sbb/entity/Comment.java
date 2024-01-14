package com.example.sbb.entity;

import com.example.sbb.dto.CommentDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    @ManyToOne
    private Article article;

    @ManyToOne
    private Member author;

    private LocalDateTime modifyDate;

    public static Comment toEntity(CommentDto dto) {
        return Comment.builder()
                .id(dto.getId())
                .content(dto.getContent())
                .createDate(dto.getCreateDate())
                .article(dto.getArticle())
                .author(dto.getAuthor())
                .modifyDate(dto.getModifyDate())
                .build();
    }
}
