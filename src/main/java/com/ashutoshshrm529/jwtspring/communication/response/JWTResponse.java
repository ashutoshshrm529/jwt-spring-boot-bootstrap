package com.ashutoshshrm529.jwtspring.communication.response;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JWTResponse {

    @NotBlank
    private String accessToken;

    @NotBlank
    private String refreshToken;
    
}
