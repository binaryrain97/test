package com.example.sbb.api;

import com.example.sbb.dto.ArticleDto;
import com.example.sbb.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/article")
@RestController
public class ArticleApiController {
    private final ArticleService articleService;

    @GetMapping("/list")
    public ResponseEntity<List<ArticleDto>> getList() {
        List<ArticleDto> articleDtos = articleService.getList();
        return ResponseEntity.status(HttpStatus.OK).body(articleDtos);
    }

    @GetMapping("/{articleId}")
    public ResponseEntity<ArticleDto> getDetail(@PathVariable Long articleId) {
        ArticleDto dto = articleService.getArticle(articleId);
        return (dto != null) ?
                ResponseEntity.status(HttpStatus.OK).body(dto) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/create")
    public ResponseEntity<ArticleDto> create(@RequestBody ArticleDto dto) {
        ArticleDto created = articleService.create(dto);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PatchMapping("/modify/{articleId}")
    public ResponseEntity<ArticleDto> modify(@PathVariable Long articleId,
                                             @RequestBody ArticleDto dto) {
        ArticleDto modified = articleService.modify(articleId, dto);
        return (modified != null) ?
                ResponseEntity.status(HttpStatus.OK).body(modified):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/delete/{articleId}")
    public ResponseEntity<ArticleDto> delete(@PathVariable Long articleId) {
        ArticleDto deleted = articleService.delete(articleId);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.OK).body(deleted) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
