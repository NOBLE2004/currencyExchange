package com.revolut.noble.android.task.di;

import android.content.Context;

import com.revolut.noble.android.task.Model.network.NetworkManager;
import com.revolut.noble.android.task.Model.repository.CurrencyRepository;
import com.revolut.noble.android.task.Model.repository.CurrencyRepositoryImpl;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Noble on 04.04.2019.
 *
 */


@Module
public class RepositoryModule {
    private CurrencyRepository repository;

    public RepositoryModule(Context context, NetworkManager networkManager) {
        repository = new CurrencyRepositoryImpl(context, networkManager);
    }

    @Provides
    @Singleton
    CurrencyRepository provideRepository() {
        return repository;
    }
}
