package io.filipvde.utils.string;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class StringGeneration {

    public static String generateRandomString(int length) throws Exception {
        return RandomStringUtils.random(length, true, true);
    }

    public static String generateRandomLetterString(int length) throws Exception {
        return RandomStringUtils.randomAlphabetic(length);
    }

    public static String generateRandomNumberString(int length) throws Exception {
        return RandomStringUtils.randomAlphanumeric(length);
    }

    public static String generateOrderNumber(int length) throws Exception {
        if (length < 14) {
            throw new Exception("Min length for order number is 14");
        }

        if (length > 20) {
            throw new Exception("Max length for order number is 20");
        }

        StringBuffer orderNumber = new StringBuffer();

        // Unix epoch time format which returns a 13 digits integer (at least until year 2286 when it will become 14 digits).
        long unixTime = System.currentTimeMillis();
        orderNumber.append(String.valueOf(unixTime));

        // Additional number to prevent collision
        int remainingLength = length - 13;
        Random random = new Random();
        // Obtain a number between [0 - 10^x].
        int addition = random.nextInt((int) Math.pow(10, remainingLength));

        orderNumber.append(String.format("%0" + remainingLength + "d", addition));

        return String.join("-", orderNumber.substring(0, 4), orderNumber.substring(4, 8),
                orderNumber.substring(8, 12), orderNumber.substring(12, orderNumber.length()));
    }

}