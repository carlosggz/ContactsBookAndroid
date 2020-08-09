package com.carlosggz.contactsbook.helpers;

public class Utils {
    public static boolean isEmptyOrNull(String s) {
        return s == null || s.length() == 0;
    }

    public static boolean isNullOrWhiteSpace(String s) {
        return s == null || s.trim().length() == 0;
    }
}
