package com.example.restfulwebservice.helloworld.controller;

import com.example.restfulwebservice.helloworld.bean.HelloWorldBean;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@Tag(name = "Hello World Controller", description = "기본 메세지 출력 관련 컨트롤러")
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;

//    public HelloWorldController(MessageSource messageSource) {
//        this.messageSource = messageSource;
//    }

    // GET
    // /hello-World (endpoint)
    // @RequestMapping(method=RequestMethod.GET, path = "/hello-world")
    @GetMapping(path = "/hello-world")
    @Operation(summary = "Hello World 출력", description = "Hello World를 출력합니다.")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping(path = "/hello-world-bean")
    @Operation(summary = "Hello World 출력 (Bean)", description = "Hello World를 빈 형식으로 출력합니다.")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World");
    }

    @GetMapping(path = "/hello-world-bean/path-variables/{name}")
    @Operation(summary = "Hello World 출력 (PathVariable)", description = "Hello World 뒤에 입력받은 값을 출력합니다.")
    public HelloWorldBean helloWorldBean(@PathVariable  String name) {
        return new HelloWorldBean(String.format("Hello World, %s", name));
    }

    @GetMapping(path = "/hello-world-internationalized")
    @Operation(summary = "Hello World 출력 (Locale)", description = "국가 별로 설정된 Hello World를 출력합니다.")
    public String HelloWorldInternationalized(
            @RequestHeader(name = "Accept-Language", required=false) Locale locale) {
        return messageSource.getMessage("greeting.message", null, locale);
    }
}
