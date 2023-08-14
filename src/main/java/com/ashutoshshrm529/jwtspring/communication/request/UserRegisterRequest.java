package com.ashutoshshrm529.jwtspring.communication.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequest {
    
    @Email(message = "Email is invalid")
    @NotNull(message = "Email must not be Null")
    private String email;

    // one number, one lowercase character, one uppercase character, one symbol, and minlength 8
    @Pattern(
        regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,}$", 
        message = "Password must have atleast one lowercase character, one uppercase character, one number, one symbol and minimum length of 8"
    )
    private String password;

}
