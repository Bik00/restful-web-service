package com.example.restfulwebservice.user.bean;

import com.fasterxml.jackson.annotation.JsonFilter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

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
}