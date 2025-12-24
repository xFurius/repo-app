package com.mateusz_pszczola.user_service.service;

import com.mateusz_pszczola.user_service.model.UserData;

public interface UserService {
    UserData createUser(String email, String password);
}

