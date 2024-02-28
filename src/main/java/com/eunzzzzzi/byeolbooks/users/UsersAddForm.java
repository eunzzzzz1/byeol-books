package com.eunzzzzzi.byeolbooks.users;

import jakarta.persistence.Convert;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersAddForm {

    @Size(min = 3, max = 20)
    @NotEmpty(message = "ID를 입력해주세요.")
    private String user_id;

    @Size(min = 8, max = 20, message = "비밀번호는 8~20자로 입력해주세요.")
    @NotEmpty(message = "비밀번호를 입력해주세요.")
    @Pattern(regexp = "[a-zA-Z0-9`~!@#$%^&*()_=+|{};:,.<>/?]*$", message = "영문 대/소문자, 숫자, 특수문자를 조합해 비밀번호를 설정해주세요.")
    private String user_password;

    @Size(min = 2, message = "닉네임은 2자 이상으로 설정해주세요.")
    private String user_nickname;

    @Email(message = "이메일 형식이 맞지 않습니다.")
    private String user_email;

    @NotEmpty(message = "성별을 선택해주세요.")
    private int user_sex;

    @NotEmpty(message = "주소를 입력해주세요.")
    private String postcode;

    @NotEmpty(message = "주소를 입력해주세요.")
    private String user_addr1;
    private String user_addr2;

    @NotEmpty(message = "주소를 입력해주세요.")
    private String user_addr3;

}
