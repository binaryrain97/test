package com.example.sbb.entity;

import com.example.sbb.dto.ArticleDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    @OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE)
    private List<Comment> commentList;

    @ManyToOne
    private Member author;

    private LocalDateTime modifyDate;

    public static Article toEntity(ArticleDto dto) {
        return Article.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .content(dto.getContent())
                .createDate(dto.getCreateDate())
                .commentList(dto.getCommentList())
                .author(dto.getAuthor())
                .build();
    }
    public void patch(Article entity) {
        if(entity.getTitle() != null) this.title = entity.getTitle();
        if(entity.getContent() != null) this.content = entity.getContent();
        if(entity.getCreateDate() != null) this.createDate = entity.getCreateDate();
        if(entity.getCommentList() != null) this.commentList = entity.getCommentList();
        if(entity.getAuthor() != null) this.author = entity.getAuthor();
    }
}
