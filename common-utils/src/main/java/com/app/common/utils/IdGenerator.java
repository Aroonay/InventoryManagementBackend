package com.app.common.utils;

import java.security.SecureRandom;

public class IdGenerator {

    private static final SecureRandom random = new SecureRandom();

    public static long generateSecureRandom8DigitId() {
        return 10000000L + random.nextInt(90000000);
    }

    public static String generateCategoryId() {
        return "CAT" + generateSecureRandom8DigitId();
    }

}
