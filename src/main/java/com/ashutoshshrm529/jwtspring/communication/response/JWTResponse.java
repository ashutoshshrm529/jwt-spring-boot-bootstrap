package com.ashutoshshrm529.jwtspring.communication.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JWTResponse {
    private String accessToken;
    private String refreshToken;
}
