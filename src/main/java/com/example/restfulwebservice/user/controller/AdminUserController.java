package com.example.restfulwebservice.user.controller;

import com.example.restfulwebservice.user.bean.User;
import com.example.restfulwebservice.user.bean.UserV2;
import com.example.restfulwebservice.user.service.UserDaoService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/admin")
@RestController
@Tag(name = "Admin User Controller", description = "사용자 관리 관련 컨트롤러")
public class AdminUserController {

    private UserDaoService service;

    public AdminUserController(UserDaoService service) {
        this.service = service;
    }

    @GetMapping("/users")
    @Operation(summary = "전체 사용자 조회", description = "전체 사용자를 출력합니다. *관리자용")
    public MappingJacksonValue retrieveAllUsers() {
        List<User> all = service.findAll();

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "name", "joinDate", "ssn");

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(all);
        mapping.setFilters(filters);

        return mapping;
    }

    // GET /admin/users/1 -> /admin/v1/users/1
//    @GetMapping("/v1/users/{id}")
//    @GetMapping(value = "/users/{id}", params =  "version=1")
//    @GetMapping(value = "/users/{id}", headers = "X-API-VERSION=1")
    @Operation(summary = "특정 사용자 조회 (ver. 1.0)", description = "특정 사용자를 출력합니다. *관리자용 (ver. 1.0)")
    @GetMapping(value = "/users/{id}", produces = "application/vnd.company.appv1+json")
    public MappingJacksonValue retrieveUserV1(@PathVariable int id) {
        User user = service.findOne(id);

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "name", "joinDate", "ssn");

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(user);
        mapping.setFilters(filters);
        return mapping;
    }

//    @GetMapping("/v2/users/{id}")
//@GetMapping(value = "/users/{id}", params =  "version=2")
//    @GetMapping(value = "/users/{id}", headers = "X-API-VERSION=2")
@Operation(summary = "특정 사용자 조회 (ver. 2.0)", description = "특정 사용자를 출력합니다. *관리자용 (ver. 2.0)")
@GetMapping(value = "/users/{id}", produces = "application/vnd.company.appv2+json")
    public MappingJacksonValue retrieveUserV2(@PathVariable int id) {
        User user = service.findOne(id);

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "name", "joinDate", "ssn", "grade");

        // User -> UserV2
        UserV2 userV2 = new UserV2();
        BeanUtils.copyProperties(user, userV2); // id, name, joinDate, password, ssn
        userV2.setGrade("VIP");

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfoV2 ", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(userV2);
        mapping.setFilters(filters);
        return mapping;
    }
}
