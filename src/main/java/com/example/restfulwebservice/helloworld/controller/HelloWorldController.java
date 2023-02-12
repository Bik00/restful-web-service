package com.example.restfulwebservice.helloworld.controller;

import com.example.restfulwebservice.helloworld.bean.HelloWorldBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    // GET
    // /hello-World (endpoint)
    // @RequestMapping(method=RequestMethod.GET, path = "/hello-world")
    @GetMapping(path = "/hello-world")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World");
    }

    @GetMapping(path = "/hello-world-bean/path-variables/{name}")
    public HelloWorldBean helloWorldBean(@PathVariable  String name) {
        return new HelloWorldBean(String.format("Hello World, %s", name));
    }
}
