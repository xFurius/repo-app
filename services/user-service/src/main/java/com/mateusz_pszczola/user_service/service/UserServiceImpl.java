package com.mateusz_pszczola.user_service.service;

import com.mateusz_pszczola.user_service.misc.Misc;
import com.mateusz_pszczola.user_service.model.UserData;
import com.mateusz_pszczola.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminConfirmSignUpRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.SignUpRequest;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private CognitoIdentityProviderClient cognitoClient;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Value("${COGNITO_CLIENT_ID}")
    private String clientID;
    @Value("${COGNITO_USER_POOL_ID}")
    private String userPoolID;

    @Override
    public UserData createUser(String email, String password) {
        var u = new UserData(UUID.randomUUID(), email, "");
        cognitoClient.signUp(SignUpRequest.builder().clientId(clientID).secretHash(Misc.getSecretHash(email)).username(email).password(passwordEncoder.encode(password)).build());
        cognitoClient.adminConfirmSignUp(AdminConfirmSignUpRequest.builder().username(email).userPoolId(userPoolID).build());
        cognitoClient.close();
//        repository.addUser(u);
        return u;
    }
}

