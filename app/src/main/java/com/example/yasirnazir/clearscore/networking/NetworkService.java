package com.example.yasirnazir.clearscore.networking;

import com.example.yasirnazir.clearscore.models.Response;

import rx.Observable;


/**
 * Created by yasirnazir on 3/14/18.
 */

public class NetworkService {
    private final NetworkApi networkApi;

    public NetworkService(NetworkApi networkApi) {
        this.networkApi = networkApi;
    }

    public Observable<Response> getCreditValues() {
        return networkApi.getCreditValues();

    }
}