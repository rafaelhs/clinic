package com.example.clinic.util;

public class EmailUtils {
    public static boolean validateEmail(String email) {
        return email.matches("[\\w\\.\\-\\_]+@[\\w\\.\\-\\_]+");
    }
}
