package com.revolut.noble.android.task;

import android.app.Application;
import com.revolut.noble.android.task.Model.network.NetworkManager;
import com.revolut.noble.android.task.di.AppComponent;
import com.revolut.noble.android.task.di.DaggerAppComponent;
import com.revolut.noble.android.task.di.RepositoryModule;


/**
 * Created by Noble on 04.04.2019.
 *
 */


public class CurrencyApp extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = buildComponent();

    }

    public static AppComponent getComponent() {
        return appComponent;
    }

    private AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .repositoryModule(new RepositoryModule(getApplicationContext(), new NetworkManager()))
                .build();
    }
}
