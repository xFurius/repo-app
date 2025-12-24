package com.mateusz_pszczola.user_service.misc;

import org.springframework.beans.factory.annotation.Value;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Misc {
    @Value("${COGNITO_CLIENT_ID}")
    private static String clientID;
    @Value("${COGNITO_CLIENT_SECRET}")
    private static String clientSecret;

    public static String getSecretHash(String email) {
        final String HMAC_SHA256_ALGORITHM = "HmacSHA256";
        SecretKeySpec signingKey = new SecretKeySpec(clientSecret.getBytes(StandardCharsets.UTF_8), HMAC_SHA256_ALGORITHM);
        try {
            Mac mac = Mac.getInstance(HMAC_SHA256_ALGORITHM);
            mac.init(signingKey);
            mac.update(email.getBytes(StandardCharsets.UTF_8));
            byte[] rawHmac = mac.doFinal(clientID.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(rawHmac);
        } catch (Exception e) {
            throw new RuntimeException("Error while calculating hash");
        }
    }
}
