package com.revolut.noble.android.task.view;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Noble on 04.04.2019.
 */

public interface CurrenciesActivityView {

    void initCurrencyList(RecyclerView.Adapter adapter);
    void showError(String errorString);
}
