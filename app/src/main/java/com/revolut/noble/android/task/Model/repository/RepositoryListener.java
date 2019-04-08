package com.revolut.noble.android.task.Model.repository;

import java.util.Map;

public interface RepositoryListener {
    void currenciesFromNetwork(Map<String, Float> data);
    void errorFromRepository(String errorString);
}
