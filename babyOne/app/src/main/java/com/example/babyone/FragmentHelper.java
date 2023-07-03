package com.example.babyone;

public class FragmentHelper {
    private static String email;

    public static String getEmail() {
        System.out.println(email);
        return email;
    }

    public static void setEmail(String email) {

        FragmentHelper.email = email;
    }
}
