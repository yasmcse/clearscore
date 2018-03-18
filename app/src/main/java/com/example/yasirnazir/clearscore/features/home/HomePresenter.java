package com.example.yasirnazir.clearscore.features.home;


import com.example.yasirnazir.clearscore.base_classes.Presenter;
import com.example.yasirnazir.clearscore.features.home.fetchers.HomeFetcher;
import com.example.yasirnazir.clearscore.models.ApiError;
import com.example.yasirnazir.clearscore.networking.NetworkModule;
import com.example.yasirnazir.clearscore.networking.NetworkService;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by yasirnazir on 3/17/18.
 */

public class HomePresenter extends Presenter<HomePresenter.View> {
    @Inject
    NetworkService networkService;

    private HomeFetcher homeFetcher;

    public HomePresenter(NetworkService networkService) {
        this.networkService = networkService;
        homeFetcher = new HomeFetcher(networkService);
    }

    @Override
    protected void onViewAttached(View view) {
        super.onViewAttached(view);
        addSubscription(homeFetcher.observeLoading().subscribe(view::showLoading));
        addSubscription(homeFetcher.observeErrors().subscribe(it -> {
            view.showError(it);
        }));
        addSubscription(homeFetcher.observeData().subscribe(it -> {
            view.displayScore(it.getCreditReportInfo().getScore(),
                    it.getCreditReportInfo().getMinScoreValue(),
                    it.getCreditReportInfo().getMaxScoreValue());
        }));
    }


    @Override
    protected void onViewDetached(View view) {
        super.onViewDetached(view);
    }

    public interface View extends Presenter.View {

        void showLoading(boolean show);

        void showError(ApiError apiError);

        void displayScore(int score, int minScore, int maxValue);

    }

}
