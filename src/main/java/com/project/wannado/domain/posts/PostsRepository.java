package com.project.wannado.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * MyBatis - Dao 에 해당하는 DB Layer 접근자
 * JPA 에서는 Repository 라고 부르며 인터페이스로 생성한다.
 * 단순히 인터페이스를 생성 후, JpaRepository<Entity 클래스, PK 타입>를 상속하면 기본적인 CRUD 메서드가 자동으로 생성된다.
 *
 * `@Repository 를 추가할 필요가 없으며, 주의할 점은 Entity 클래스와 기본 Entity Repository 는 함께 위치해야 한다.
 * Entity 클래스(Posts.java)는 기본 Repository 없이는 역할을 할 수 없다.
 */
public interface PostsRepository extends JpaRepository<Posts, Long> {
}