package com.example.restfulwebservice.user.controller;

import com.example.restfulwebservice.exception.UserNotFoundException;
import com.example.restfulwebservice.user.bean.User;
import com.example.restfulwebservice.user.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RequestMapping("/jpa")
@RestController
@Tag(name = "User JPA Controller", description = "사용자 관련 Java Persistenct API (JPA) 활용 컨트롤러")
public class UserJpaController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    @Operation(summary = "전체 사용자 조회", description = "전체 사용자를 출력합니다.")
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format("ID[%s] is not found", id)));

        // HATEOAS
        EntityModel<User> model = EntityModel.of(user);
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        model.add(linkTo.withRel("/users/{id}"));

        return model;
    }
}
