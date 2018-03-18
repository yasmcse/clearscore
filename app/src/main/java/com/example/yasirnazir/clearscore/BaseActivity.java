package com.example.yasirnazir.clearscore;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.yasirnazir.clearscore.dependency_injection.DaggerDependencyInjector;
import com.example.yasirnazir.clearscore.dependency_injection.DependencyInjector;
import com.example.yasirnazir.clearscore.networking.NetworkModule;

import java.io.File;

/**
 * Created by yasirnazir on 3/14/18.
 */


public class BaseActivity extends AppCompatActivity {
    DependencyInjector deps;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        File cacheFile = new File(getCacheDir(), getString(R.string.responses));
        deps = DaggerDependencyInjector.builder().networkModule(new NetworkModule(cacheFile)).build();

    }

    public DependencyInjector getDeps() {
        return deps;
    }
}
