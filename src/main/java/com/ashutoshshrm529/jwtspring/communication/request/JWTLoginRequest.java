package com.ashutoshshrm529.jwtspring.communication.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JWTLoginRequest {
    private String email;
    private String password;
}
