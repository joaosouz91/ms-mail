package com.letshare.msmail.utils;

import static org.apache.commons.lang3.RandomStringUtils.random;

public class IdSupplier {

    private static final int SIZE = 16;

    public static String generateId() {
        return generateId(SIZE);
    }

    public static String generateId(int size) {
        return random(size, true, true);
    }

}
