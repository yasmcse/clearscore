package com.example.yasirnazir.clearscore.networking;

import com.example.yasirnazir.clearscore.models.APIErrors;
import com.example.yasirnazir.clearscore.models.ApiError;
import com.google.gson.Gson;

import java.net.UnknownHostException;

import retrofit2.Response;
import retrofit2.adapter.rxjava.HttpException;


/**
 * Created by yasirnazir on 3/14/18.
 * <p>
 * Transforms errors received from network requests into {@link ApiError}s which can be displayed to the user.
 */

public class NetworkErrorMapper {
    public static final String DEFAULT_ERROR_MESSAGE = "Something went wrong! Please try again.";
    public static final String NO_CONNECTION_ERROR = "No Internet Connection!";

    public ApiError mapError(Throwable throwable) {
        if (throwable instanceof HttpException) {
            HttpException httpException = (HttpException) throwable;
            if (httpException.response() != null) {
                APIErrors apiErrors = parseError(httpException.response());
                String firstErrorMessage = null;
                if (containsErrors(apiErrors)) {
                    firstErrorMessage = apiErrors.getErrors().get(0).getMessage();

                }
                return new ApiError(httpException.response().code(), firstErrorMessage);
            } else {
                return new ApiError(httpException.response().code(), null);
            }
        }else if (throwable instanceof UnknownHostException) {
            return new ApiError(1000,NO_CONNECTION_ERROR);
        }
        else {
            return new ApiError(1001,DEFAULT_ERROR_MESSAGE);
        }
    }

    private APIErrors parseError(Response<?> response) {
        APIErrors error;

        try {
            error = new Gson().fromJson(response.errorBody().string(), APIErrors.class);
        } catch (Exception e) {
            return new APIErrors();
        }

        return error != null ? error : new APIErrors();
    }

    private boolean containsErrors(APIErrors apiErrors) {
        return apiErrors.getErrors() != null && !apiErrors.getErrors().isEmpty();
    }
}
