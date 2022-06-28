/**
 * 1. 프로젝트의 메인 클래스
 * .@SpringBootApplication -> 스프링 부트 자동 설정. 스프링 Bean 읽기, 생성 자동으로 설정.
 * 프로젝트의 최상단에 위치해야 하며, 이 위치에서부터 설정을 읽어 간다.
 * SpringApplication.run() 메서드로 내장 WAS 를 실행함.
 */

package com.project.wannado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing	// JPA Auditing 활성화
@SpringBootApplication
public class WannaApplication {

	public static void main(String[] args) {
		SpringApplication.run(WannaApplication.class, args);
	}

}
