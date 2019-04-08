package com.revolut.noble.android.task.viewmodel;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import com.revolut.noble.android.task.Config;
import com.revolut.noble.android.task.Model.repository.CurrencyRepository;
import com.revolut.noble.android.task.Model.repository.RepositoryListener;
import com.revolut.noble.android.task.view.CurrenciesActivityView;
import com.revolut.noble.android.task.viewmodel.model.Currency;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


public class CurrenciesActivityViewModel implements RepositoryListener {

    private CurrencyRepository repository;
    private CurrenciesActivityView view;
    private CurrenciesAdapter currenciesAdapter;
    private @NonNull
    List<Currency> listModel = Collections.emptyList();
    private Float currentAmount = Config.BASE_AMOUNT;

    public CurrenciesActivityViewModel(CurrencyRepository repository) {
        this.repository = repository;
    }

    public void attachView(CurrenciesActivityView view) {
        this.view = view;
        repository.setListener(this);
        repository.getDataFromNetworkAndListen();
        createListModelIfNeeded(repository.getDataFromCache());
    }

    private void createListModelIfNeeded(Map<String, Float> ratesFromCache) {
        if (listModel.size() == 0) {
            listModel = mapRepoDataToModel(ratesFromCache);
            view.initCurrencyList(buildAdapter());
        }
    }

    public void detachView() {
        currenciesAdapter = null;
        repository.shutdownGettingDataFromNetwork();
        repository.removeListener();
        this.view = null;
    }

    private RecyclerView.Adapter buildAdapter() {
        if (currenciesAdapter == null)
            currenciesAdapter = CurrenciesAdapter.create(this);
        return currenciesAdapter;
    }

    private List<Currency> mapRepoDataToModel(Map<String, Float> repoData) {
        ArrayList<Currency> returnValue = new ArrayList<>();
        if (repoData != null) {
            for (String currencyName : repoData.keySet()) {
                Float value = repoData.get(currencyName);
                if (currencyName.equals(Config.BASE_CURRENCY))
                    returnValue.add(0, Currency.create(currencyName, value));
                else
                    returnValue.add(Currency.create(currencyName, value));
            }
        }
        return returnValue;
    }

    @Override
    public void currenciesFromNetwork(Map<String, Float> data) {
        if (listModel.size() == 0)
            createListModelIfNeeded(data);
        else {
            updateListModelWithNewData(data);
            if (currenciesAdapter != null)
                currenciesAdapter.updateAllCurrencyValues();
            else
                view.initCurrencyList(buildAdapter());
        }
    }

    private void updateListModelWithNewData(Map<String, Float> data) {
        for (String currencyName : data.keySet()) {
            Float value = data.get(currencyName);

            for (Currency item : listModel) {
                if (item.getName().equals(currencyName)) {
                    item.setKoef(value);
                    break;
                }
            }
        }
    }

    @Override
    public void errorFromRepository(String errorString) {
        view.showError(errorString);
    }

    public final List<Currency> getModelList() {
        return listModel;
    }

    public Float getCurrentAmount() {
        return currentAmount;
    }


    public void modifyCurrentAmountWithItem(float newValue, float koef) {
        if (koef > 0.0f)
            this.currentAmount = newValue / koef;
    }

}
