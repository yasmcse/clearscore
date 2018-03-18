package com.example.yasirnazir.clearscore.features.home;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.yasirnazir.clearscore.BaseActivity;
import com.example.yasirnazir.clearscore.R;
import com.example.yasirnazir.clearscore.models.ApiError;
import com.example.yasirnazir.clearscore.networking.NetworkService;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;


/**
 * Created by yasirnazir on 3/14/18.
 */

public class HomeActivity extends BaseActivity implements HomePresenter.View {
    @BindView(R.id.yourScoreTextView)
    TextView yourScoreTextView;
    @BindView(R.id.score)
    TextView scoreTextView;
    @BindView(R.id.maxScoreTextView)
    TextView maxScoreTextView;
    @BindView(R.id.scoreProgressBar)
    ProgressBar scoreProgressBar;
    @BindView(R.id.no_internet)
    ImageView noInternet;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @Inject
    NetworkService networkService;
    private HomePresenter homePresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.dashboard));
        getDeps().inject(this);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        homePresenter = new HomePresenter(networkService);
        homePresenter.attach(this);

    }


    @Override
    public void showLoading(boolean show) {
        progressBar.setVisibility(show ? VISIBLE : GONE);
    }

    @Override
    public void showError(ApiError apiError) {
        showErrorMessage(apiError.getMessage());
    }

    private void showErrorMessage(String message) {
        updateVisibilityNoConnection();
        View view = this.getWindow().getDecorView().findViewById(android.R.id.content);
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);

        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(view.getContext().getResources().getColor(R.color.red));
        TextView snackbartextView = snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        snackbartextView.setMaxLines(3);

        snackbar.show();
    }


    @Override
    public void displayScore(int score, int minScore, int maxScore) {
        updateVisibilityConnectedMode();
        scoreTextView.setText(Integer.toString(score));
        maxScoreTextView.setText(getString(R.string.out_of) + Integer.toString(maxScore));
        scoreProgressBar.setMax(maxScore);
        ObjectAnimator animation = ObjectAnimator.ofInt(scoreProgressBar, getString(R.string.progress), minScore, score);
        animation.setDuration(3000); //in milliseconds
        animation.setInterpolator(new DecelerateInterpolator());
        animation.start();
    }

    private void updateVisibilityConnectedMode() {
        yourScoreTextView.setVisibility(View.VISIBLE);
        scoreProgressBar.setVisibility(View.VISIBLE);
        maxScoreTextView.setVisibility(View.VISIBLE);
        scoreTextView.setVisibility(View.VISIBLE);
    }

    private void updateVisibilityNoConnection() {
        yourScoreTextView.setVisibility(View.GONE);
        scoreProgressBar.setVisibility(View.GONE);
        maxScoreTextView.setVisibility(View.GONE);
        scoreTextView.setVisibility(View.GONE);
        noInternet.setVisibility(View.VISIBLE);
    }
}
