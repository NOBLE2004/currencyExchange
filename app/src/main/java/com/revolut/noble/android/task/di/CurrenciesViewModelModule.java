package com.revolut.noble.android.task.di;

import com.revolut.noble.android.task.Model.repository.CurrencyRepository;
import com.revolut.noble.android.task.viewmodel.CurrenciesActivityViewModel;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Noble on 04.04.2019.
 *
 */


@Module
public class CurrenciesViewModelModule {

    @Provides
    @Singleton
    CurrenciesActivityViewModel provideViewModel(CurrencyRepository repository) {
            return new CurrenciesActivityViewModel(repository);
    }

}
