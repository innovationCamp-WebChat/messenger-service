package com.innovationcamp.messenger.domain.user.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class UserRequestDto {
    @NotNull(message = "이메일은 필수 입력 항목입니다.")
    @Pattern(regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,3}$", message = "이메일 형식에 맞춰주세요")
    private String email;
    @NotNull(message = "이름은 필수 입력 항목입니다.")
    private String username;
    @NotNull(message = "비밀번호는 필수 입력 항목입니다.")
    private String password;
}
