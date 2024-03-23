package security.root.dtos;

import lombok.Data;

@Data
public class RegistrationUserDto {
    private String username;
    private String password;
    private String confirmPassword;
    private String email;
}

//как настроить профили для spring boot? подскажи пожалуйста

