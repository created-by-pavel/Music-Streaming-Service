package ru.crbpavel.musicapp.model;

public class SecurityModel {

    private static String login;
    private static String password;

    private static String username;
    private static String accessToken;
    private static String refreshedToken;

    public static String getLogin() {
        return login;
    }

    public static void setLogin(String login) {
        SecurityModel.login = login;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        SecurityModel.password = password;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        SecurityModel.username = username;
    }

    public static String getAccessToken() {
        return accessToken;
    }

    public static void setAccessToken(String accessToken) {
        SecurityModel.accessToken = accessToken;
    }

    public static String getRefreshedToken() {
        return refreshedToken;
    }

    public static void setRefreshedToken(String refreshedToken) {
        SecurityModel.refreshedToken = refreshedToken;
    }

    public static String toStr() {
        return "SecurityToken{" +
                "login='" + login + '\'' +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", refreshedToken='" + refreshedToken + '\'' +
                '}';
    }
}
