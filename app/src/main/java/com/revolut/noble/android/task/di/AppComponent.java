package com.revolut.noble.android.task.di;

import com.revolut.noble.android.task.view.CurrenciesActivity;
import javax.inject.Singleton;
import dagger.Component;

/**
 * Created by Noble on 04.04.2019.
 *
 */

@Singleton
@Component(modules = {RepositoryModule.class, CurrenciesViewModelModule.class})
public interface AppComponent {
    void inject(CurrenciesActivity activity);
}
