package com.example.sbb;

import com.example.sbb.entity.Article;
import com.example.sbb.repository.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class SbbApplicationTests {

	@Autowired
	private ArticleRepository articleRepository;

	@Test
	void contextLoads() {
		Article a1 = new Article(null,
				"sbb가 무엇인가요?",
				"sbb에 대해서 알고 싶습니다.",
				LocalDateTime.now(), null);
		this.articleRepository.save(a1);  // 첫번째 질문 저장

		Article a2 = new Article(null,
				"스프링부트 모델 질문입니다.",
				"id는 자동으로 생성되나요?",
				LocalDateTime.now(), null);
		this.articleRepository.save(a2);  // 두번째 질문 저장
	}
}
