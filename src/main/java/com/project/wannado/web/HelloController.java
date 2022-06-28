/**
 * 1-1. 테스트를 위한 HelloController
 * 이 프로젝트에서 Controller 와 관련된 클래스는 web 패키지에 담는다.
 *
 * .@RestController
 * 해당 컨트롤러 클래스를 JSON 을 반환하는 컨트롤러로 만들어 준다.
 * 메서드마다 선언했던 .@ResponseBody 를 사용하지 않아도 된다.
 *
 * .@GetMapping
 * Http Method - GET 의 요청을 받을 수 있는 API 로 만들어 준다. (@RequestMapping(method=RequestMethod.GET)의 최신 버전)
 * ex) localhost:8080/hello 를 요청하면 문자열 hello 를 반환한다.
 */

package com.project.wannado.web;

import com.project.wannado.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount) {
        return new HelloResponseDto(name, amount);
    }
}
