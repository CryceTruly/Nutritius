package com.happy.nutritius.model;

import com.google.gson.annotations.SerializedName;

public class Result {
    @SerializedName("token")
    private String token;

    @SerializedName("user")
    private User user;
    @SerializedName("message")
    private String message;

    public Result(String token, User user,String message) {
        this.token = token;
        this.user = user;
        this.message=message;
    }

    public Result() {
    }

    public String getToken() {
        return token;
    }
    public String getMessage(){
        return message;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Result{" +
                "token='" + token + '\'' +
                ", user=" + user +
                ", message='" + message + '\'' +
                '}';
    }
}


