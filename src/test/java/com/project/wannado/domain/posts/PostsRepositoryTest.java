package com.project.wannado.domain.posts;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * save, finaAll 기능을 테스트 한다.
 *
 * 1) @AfterEach
 * 단위 테스트가 끝날 때마다 수행되는 메서드를 지정.
 * 전체 테스트 수행 시 테스트간의 데이터 침범을 막기 위해 사용한다.
 *
 * 2) postsRepository.save
 * 테이블 posts 에 insert/update 쿼리를 실행한다.
 * id 값이 있다면 update, 없다면 insert 쿼리 실행.
 *
 * 3) postsRepository.findAll
 * 테이블 posts 에 있는 모든 데이터를 조회하는 메서드.
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @AfterEach
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("nortiz1349@gmail.com")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);

    }
}

 /*
  @ 실행된 쿼리 로그로 확인하기
  src/main/resource -> application.properties

  1) 아래 설정 추가
  spring.jpa.show-sql=true

  2) MySQL 버전으로 출력하기 위해 아래 설정 추가
  spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL57Dialect
  spring.jpa.properties.hibernate.dialect.storage_engine=innodb
  spring.datasource.hikari.jdbc-url=jdbc:h2:mem://localhost/~/testdb;MODE=MYSQL
 */