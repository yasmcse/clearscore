package com.example.yasirnazir.clearscore.models;

/**
 * Created by yasirnazir on 3/18/18.
 */


/**
 * A representation of an error that occurred while making an API request. Contains the HTTP status
 * code returned from the server (if applicable) and the first error message returned from the server
 * (if applicable).
 */
public class ApiError extends RuntimeException {
    private final int responseCode;
    private final String message;

    public ApiError() {
        this.responseCode = -1;
        this.message = null;
    }

    public ApiError(int responseCode, String errorMessage) {
        this.responseCode = responseCode;
        this.message = errorMessage;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getErrorMessage() {
        return message;
    }

    public boolean hasErrorMessage() {
        return message != null;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApiError apiError = (ApiError) o;

        if (responseCode != apiError.responseCode) return false;
        return message.equals(apiError.message);
    }

    @Override
    public int hashCode() {
        int result = responseCode;
        result = 31 * result + message.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ApiError{" +
                "responseCode=" + responseCode +
                ", errorMessage='" + message + '\'' +
                '}';
    }

    @Override
    public String getMessage() {
        return message;
    }
}