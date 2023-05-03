package com.hgsplanet.userservice.model;

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
public class BusinessRegisterRequest {

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

    @NotBlank(message = "opening time should not be empty")
    private String openingTime;

    @NotBlank(message = "closing time should not be empty")
    private String closingTime;

    @NotBlank(message = "city should not be empty")
    private String city;
    private String businessType;
    private LocalDateTime creationTime;

}
