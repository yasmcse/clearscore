package com.example.yasirnazir.clearscore.base_classes;

/**
 * Created by yasirnazir on 3/18/18.
 */


import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.schedulers.Schedulers;
import rx.android.schedulers.AndroidSchedulers;


/**
 * Ensures that Retrofit requests are made on the io thread and responses
 * are observed on Android's main thread so that the view can be updated.
 */
public class RxThreadCallAdapterFactory extends CallAdapter.Factory {
    RxJavaCallAdapterFactory rxFactory = RxJavaCallAdapterFactory.create();

    @Override
    public CallAdapter<?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        CallAdapter<Observable<?>> callAdapter = (CallAdapter<Observable<?>>) rxFactory.get(returnType, annotations, retrofit);
        return callAdapter != null ? new RxThreadCallAdapter(callAdapter) : null;
    }

    private class RxThreadCallAdapter implements CallAdapter<Observable<?>> {
        CallAdapter<Observable<?>> rxCallAdapter;

        public RxThreadCallAdapter(CallAdapter<Observable<?>> rxCallAdapter) {
            this.rxCallAdapter = rxCallAdapter;
        }

        @Override
        public Type responseType() {
            return rxCallAdapter.responseType();
        }

        @Override
        public <T> Observable<?> adapt(Call<T> call) {
            return rxCallAdapter.adapt(call)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    }
}
