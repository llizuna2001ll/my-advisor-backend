package com.hgsplanet.userservice.model;

import com.hgsplanet.userservice.documents.City;
import com.hgsplanet.userservice.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "username should not be empty")
    @Indexed(unique = true)
    private String username;

    @NotBlank(message = "email should not be empty")
    @Email(message = "Email not valid")
    private String email;

    @NotBlank(message = "password should not be empty")
    @Size(min = 6, max = 30)
    private String password;

    private String profileImgPath;

    @NotBlank(message = "phone number should not be empty")
    @Pattern(regexp = "^[0-9]*$", message = "Invalid phone number")
    private String phoneNum;

    private LocalDateTime creationTime;

}
