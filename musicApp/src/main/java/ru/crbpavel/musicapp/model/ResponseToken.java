package ru.crbpavel.musicapp.model;

public class ResponseToken {

    private String accessToken;
    private String refreshedToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshedToken() {
        return refreshedToken;
    }

    public void setRefreshedToken(String refreshedToken) {
        this.refreshedToken = refreshedToken;
    }
}
