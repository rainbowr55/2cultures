package com.twoculture.twoculture.tools;

/**
 * Created by songxingchao on 26/09/2016.
 */

public class StringUtils {

    public static boolean isEmailValid(String email) {
        return email.contains("@") && email.contains(".");
    }

    public static boolean isPasswordValid(String password) {
        return password.length() > 4;
    }
}
