package com.example.yasirnazir.clearscore.base_classes;

/**
 * Created by yasirnazir on 3/18/18.
 */

import com.example.yasirnazir.clearscore.models.ApiError;
import com.example.yasirnazir.clearscore.models.Error;
import com.example.yasirnazir.clearscore.models.GenericModel;
import com.example.yasirnazir.clearscore.models.InvalidSessionError;
import com.example.yasirnazir.clearscore.networking.NetworkErrorMapper;

import java.util.List;

import rx.Observable;
import rx.functions.Func0;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;

import static rx.Observable.empty;


/**
 * A reusable component for loading data from the server. This is intended for GET requests.
 * The {@link Presenter} of the screen can subscribe to
 * the following three observables in order to update the UI with the progress:
 * {@link #observeLoading()}, {@link #observeData()} and {@link #observeErrors()}.
 * <p>
 * When subscribing, if some data has already been fetched, this cached data will be returned,
 * rather than needing to call the server again. If an explicit refresh is required, the
 * {@link #refresh()} method can be called.
 *
 * @param <T> A model object representing the type of data that we are fetching
 */
public class Fetcher<T extends GenericModel> {
    private final int ERROR_CODE_USER_NOT_FOUND = 2007;
    private final int ERROR_CODE_SOMETHING_GOES_WRONG = 1001;
    private final Func0<Observable<T>> requestFunction;

    private final NetworkErrorMapper networkErrorMapper = new NetworkErrorMapper();
    private final BehaviorSubject<Boolean> loading = BehaviorSubject.create();
    private final PublishSubject<ApiError> errors = PublishSubject.create();
    private final Cache<T> cache;
    private final String requestPath;

    public Fetcher(Func0<Observable<T>> requestFunction, String requestPath) {
        this(requestFunction, new Cache<T>(), requestPath);
    }

    public Fetcher(Func0<Observable<T>> requestFunction, Cache<T> cache, String requestPath) {
        this.requestFunction = requestFunction;
        this.cache = cache;
        this.requestPath = requestPath;
    }

    public void refresh() {
        requestFunction.call()
                .take(1)
                .doOnSubscribe(() -> loading.onNext(true))
                .doOnNext(it -> loading.onNext(false))
                .doOnNext(this::checkIfHasError)
                .doOnError(it -> loading.onNext(false))
                .doOnError(it -> errors.onNext(networkErrorMapper.mapError(it)))
                .onErrorResumeNext(it -> empty())
                .subscribe(cache::write);
    }

    private void checkIfHasError(T model) {
        checkForError(model.getErrors());
        checkForError(model.getException());
    }

    private void checkForError(List<Error> errors) {
        if (null != errors && !errors.isEmpty()) {
            if (errors.get(0).getCode() == ERROR_CODE_USER_NOT_FOUND
                    || errors.get(0).getCode() == ERROR_CODE_SOMETHING_GOES_WRONG) {

                throw new InvalidSessionError();
            } else {
                throw new ApiError(errors.get(0).getCode(), errors.get(0).getMessage());
            }
        }
    }

    public Observable<Boolean> observeLoading() {
        return loading;
    }

    public rx.Observable<T> observeData() {
        if (!cache.hasData() && !isLoading()) {
            refresh();
        }
        return cache.observe();
    }

    public Observable<ApiError> observeErrors() {
        return errors;
    }

    public boolean isLoading() {
        return loading.hasValue() && loading.getValue();
    }
}
