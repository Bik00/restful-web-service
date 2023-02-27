package com.example.restfulwebservice.post.bean;

import com.example.restfulwebservice.user.bean.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue
    private Integer id;

    private String description;

    // User : Post -> 1:N (0~N) Main : Sub -> Parent : Child
    @ManyToOne(fetch= FetchType.LAZY)
    @JsonIgnore
    private User user;
}
