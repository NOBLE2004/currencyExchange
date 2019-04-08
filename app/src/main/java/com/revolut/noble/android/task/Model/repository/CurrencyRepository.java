package com.revolut.noble.android.task.Model.repository;

import java.util.Map;

/**
 * Created by Noble on 04.04.2019.
 *
 */

public interface CurrencyRepository {

    Map<String, Float> getDataFromCache();
    void getDataFromNetworkAndListen();
    void shutdownGettingDataFromNetwork();

    void setListener(RepositoryListener listener);
    void removeListener();
}
