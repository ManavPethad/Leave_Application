package com.example.leave_application_nmims;

import java.security.SecureRandom;

public class RandomKeyGenerator {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int KEY_LENGTH = 5;
    private static String generatedKey;
    public static String generateRandomKey() {
        if (generatedKey == null) {
            SecureRandom random = new SecureRandom();
            StringBuilder keyBuilder = new StringBuilder(KEY_LENGTH);

            for (int i = 0; i < KEY_LENGTH; i++) {
                int randomIndex = random.nextInt(CHARACTERS.length());
                keyBuilder.append(CHARACTERS.charAt(randomIndex));
            }

            generatedKey = keyBuilder.toString();
        }
        return generatedKey;
    }

    public static String getGeneratedKey() {
        if (generatedKey == null) {
            generateRandomKey();
        }
        return generatedKey;
    }
}
