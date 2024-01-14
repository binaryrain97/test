package com.example.sbb.api;

import com.example.sbb.dto.CommentDto;
import com.example.sbb.service.ArticleService;
import com.example.sbb.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CommmentApiController {

    private final CommentService commentService;
    private final ArticleService articleService;

    @PostMapping("/api/article/{articleId}/comment")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto dto,
                                                    @PathVariable Long articleId) {
        CommentDto result = commentService.create(articleId, dto);
        return (result != null) ?
                ResponseEntity.status(HttpStatus.OK).body(result):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
