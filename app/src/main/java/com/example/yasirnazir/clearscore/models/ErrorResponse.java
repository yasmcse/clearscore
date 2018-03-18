package com.example.yasirnazir.clearscore.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yasirnazir on 3/14/18.
 */

public class ErrorResponse {
    @SerializedName("error")
    public String error;


    @SuppressWarnings({"unused", "used by Retrofit"})
    public ErrorResponse() {
    }


    public ErrorResponse(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
