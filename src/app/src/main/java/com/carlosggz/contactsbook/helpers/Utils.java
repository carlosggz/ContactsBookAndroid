package com.carlosggz.contactsbook.helpers;

import android.util.Patterns;

import java.util.regex.Pattern;

public class Utils {
    public static boolean isEmptyOrNull(String s) {
        return s == null || s.length() == 0;
    }

    public static boolean isNullOrWhiteSpace(String s) {
        return s == null || s.trim().length() == 0;
    }

    public static boolean isValidEmail(String email) {
        return !isNullOrWhiteSpace(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isValidPhone(String phone) {
        return !isNullOrWhiteSpace(phone) && Patterns.PHONE.matcher(phone).matches();
    }
}
