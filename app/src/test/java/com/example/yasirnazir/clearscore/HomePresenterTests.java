package com.example.yasirnazir.clearscore;

import com.example.yasirnazir.clearscore.features.home.HomePresenter;
import com.example.yasirnazir.clearscore.models.ApiError;
import com.example.yasirnazir.clearscore.models.CreditReportInfo;
import com.example.yasirnazir.clearscore.models.Response;
import com.example.yasirnazir.clearscore.networking.NetworkService;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import rx.Observable;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by yasirnazir on 3/18/18.
 */

public class HomePresenterTests {
    public static final String NO_CONNECTION_ERROR = "No Internet Connection!";
    public static final String DEFAULT_ERROR_MESSAGE = "Something went wrong! Please try again.";
    private HomePresenter.View view = mock(HomePresenter.View.class);
    private NetworkService networkService = mock(NetworkService.class);
    private HomePresenter presenter = new HomePresenter(networkService);
    Response response1 = new Response(new CreditReportInfo(514, 0, 700));
    Response response2 = new Response(new CreditReportInfo(250, 0, 700));
    ApiError errorNoConnection = new ApiError(1000, NO_CONNECTION_ERROR);
    ApiError httpError = new ApiError(10001, DEFAULT_ERROR_MESSAGE);

    @Before
    public void setUp() {

    }

    @Test
    public void requestSucceedsToFetchCreditValues() {

        when(networkService.getCreditValues()).thenReturn(Observable.just(response1));
        presenter.attach(view);

        verify(view).showLoading(true);
        verify(view).showLoading(false);
        verify(view).displayScore(514, 0, 700);
    }

    @Ignore
    @Test
    public void requestFailsWhenNoInternetConnection() {
        when(networkService.getCreditValues()).thenReturn(Observable.error(errorNoConnection));
        presenter.attach(view);

        verify(view).showLoading(true);
        verify(view).showLoading(false);
        verify(view).showError(errorNoConnection);
    }

    @Test
    public void showErrorWhenHttpException() {
        when(networkService.getCreditValues()).thenReturn(Observable.error(httpError));
        presenter.attach(view);

        verify(view).showLoading(true);
        verify(view).showLoading(false);
        verify(view).showError(httpError);
    }
}
