package com.mateusz_pszczola.user_service.model;

import lombok.Data;

@Data
public class SignUpReq {
    private String email;
    private String password;
}
