package com.mateusz_pszczola.user_service.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserData {
    private UUID ID;
    private String email;
    private String profilePic;
}
