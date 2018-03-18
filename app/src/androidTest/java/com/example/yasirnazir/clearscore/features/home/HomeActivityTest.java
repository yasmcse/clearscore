package com.example.yasirnazir.clearscore.features.home;


import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.example.yasirnazir.clearscore.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class HomeActivityTest {

    @Rule
    public ActivityTestRule<HomeActivity> mActivityTestRule = new ActivityTestRule<>(HomeActivity.class);

    @Test
    public void homeActivityTest() {
        mActivityTestRule.launchActivity(new Intent());
    }

    @Test
    public void displayProgressBar() throws InterruptedException {
        onView(withId(R.id.scoreProgressBar)).check(matches(isDisplayed()));
    }

    @Test
    public void displayScore() throws InterruptedException {
        onView(withId(R.id.score)).check(matches(isDisplayed()));
    }



}
