package com.example.restfulwebservice.user.controller;

import com.example.restfulwebservice.exception.UserNotFoundException;
import com.example.restfulwebservice.user.bean.User;
import com.example.restfulwebservice.user.service.UserDaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Tag(name = "User Controller", description = "사용자 관련 컨트롤러")
@RestController
public class UserController {

    private UserDaoService service;

    public UserController(UserDaoService service) {
        this.service = service;
    }

    @GetMapping("/users")
    @Operation(summary = "전체 사용자 조회", description = "전체 사용자를 출력합니다.")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }



    // GET /users/1 or /users/10
    @GetMapping("/users/{id}")
    @Operation(summary = "특정 사용자 조회", description = "특정 사용자를 출력합니다.")
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        User user = service.findOne(id);

        // HATEOAS
        EntityModel<User> model = EntityModel.of(user);

        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        model.add(linkTo.withRel("all-users"));
        return model;
    }

    @PostMapping("/users")
    @Operation(summary = "사용자 생성", description = "특정 사용자를 생성합니다.")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = service.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    @Operation(summary = "사용자 삭제", description = "특정 사용자를 삭제합니다.")
    public void deleteUser(@PathVariable int id) {
        User user = service.deleteById(id);

        if(user == null) {
            throw new UserNotFoundException(String.format("ID[%s] is not found", id));
        }
    }
}