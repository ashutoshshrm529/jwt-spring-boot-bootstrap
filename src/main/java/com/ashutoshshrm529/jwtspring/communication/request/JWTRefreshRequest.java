package com.ashutoshshrm529.jwtspring.communication.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JWTRefreshRequest {
    @NotBlank(message = "refreshToken must not be Null")
    private String refreshToken;
}
