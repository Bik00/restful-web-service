package com.example.restfulwebservice.user.bean;

import com.example.restfulwebservice.post.bean.Post;
import com.fasterxml.jackson.annotation.JsonFilter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;

//@JsonIgnoreProperties(value={"password"})
//@JsonFilter("UserInfo")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "템플릿 관련 VO")
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue
    @Schema(description = "사용자 ID")
    private Integer id;

    @Schema(description = "사용자 이름")
    @Size(min=2, message = "Name은 2글자 이상 입력해주세요.")
    private String name;

    @Schema(description = "등록 일자")
    @Past
    private Date joinDate;

//    @JsonIgnore
    @Schema(description = "패스워드")
    private String password;

//    @JsonIgnore
    @Schema(description = "SSN 번호")
    private String ssn;

    @Schema(description = "포스트")
    @OneToMany(mappedBy = "user")
    private List<Post> postList;

    public User(int id, String name, Date joinDate, String password, String ssn) {
        this.id = id;
        this.name = name;
        this.joinDate = joinDate;
        this.password = password;
        this.ssn = ssn;

    }
}