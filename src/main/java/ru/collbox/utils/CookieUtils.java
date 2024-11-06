package ru.collbox.utils;


import org.springframework.http.ResponseCookie;
import org.springframework.http.HttpCookie;

public class CookieUtils {

    public static HttpCookie setJwtCookie(String token) {
        return ResponseCookie.from("jwt", token)
                .httpOnly(true)
                //.secure(true)
                .path("/")
                .maxAge(24 * 60 * 60)
                .sameSite("Strict")
                .build();
    }

}
