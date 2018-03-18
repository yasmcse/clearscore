package com.example.yasirnazir.clearscore.networking;

import com.example.yasirnazir.clearscore.models.ClearScoreApiEndPoints;
import com.example.yasirnazir.clearscore.models.Response;

import retrofit2.http.GET;
import rx.Observable;


/**
 * Created by yasirnazir on 3/14/18.
 */
public interface NetworkApi {

    @GET(ClearScoreApiEndPoints.CREDIT_VALUES)
    Observable<Response> getCreditValues();

}