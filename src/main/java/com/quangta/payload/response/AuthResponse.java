package com.quangta.payload.response;

import com.quangta.entity.USER_ROLE;
import lombok.Data;

@Data
public class AuthResponse {
    private String token;
    private String message;
    private USER_ROLE role;
}
