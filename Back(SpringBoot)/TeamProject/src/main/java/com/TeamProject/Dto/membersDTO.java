package com.TeamProject.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MembersDTO {

    @NotNull(message = "이름을 입력해 주세요.")
    private String name;

    @NotNull(message = "이메일을 입력해 주세요.")
    @Email(message = "이메일 형식을 지켜서 입력해 주세요.")
    private String loginEmail;
    
    @NotNull(message = "비밀번호를 입력해 주세요.")
    @Size(min = 10, max = 15,
        message = "비밀번호는 10자 이상 15자 이하로 입력해 주세요.") // 비밀번호 길이 제한
    @Pattern(regexp = "([^!@#$%^&*()-_=+\\[\\]{};:'\",.<>/]*[!@#$%^&*()-_=+\\[\\]{};:'\",.<>/]?){0,3}",
            message = "특수문자는 3개 이하로만 사용 가능합니다.") // 특수문자 3개 이상 사용금지
    private String password;

    @NotNull(message = "비밀번호를 확인해 주세요.")
    private String confirmPassword;

    @NotNull(message = "전화번호를 입력해 주세요.")
    private String phoneNumber;

    private String companyName;    
}
 