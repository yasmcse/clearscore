package com.example.yasirnazir.clearscore.models;

/**
 * Created by yasirnazir on 3/18/18.
 */

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class APIErrors {

    @SerializedName("errors")
    private List<Error> mErrors;

    @SerializedName("exception")
    private List<Error> mException;

    public List<Error> getErrors() {
        return mErrors;
    }

    public List<Error> getException() {
        return mException;
    }
}
