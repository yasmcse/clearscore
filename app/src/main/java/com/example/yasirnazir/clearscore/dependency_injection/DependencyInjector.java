package com.example.yasirnazir.clearscore.dependency_injection;

import com.example.yasirnazir.clearscore.features.home.HomeActivity;
import com.example.yasirnazir.clearscore.features.home.HomePresenter;
import com.example.yasirnazir.clearscore.networking.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by yasirnazir on 3/14/18.
 */

@Singleton
@Component(modules = {NetworkModule.class})
public interface DependencyInjector {
    void inject(HomeActivity homeActivity);
}
