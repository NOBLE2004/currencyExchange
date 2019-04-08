package com.revolut.noble.android.task.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import com.revolut.noble.android.task.CurrencyApp;
import com.revolut.noble.android.task.R;
import com.revolut.noble.android.task.viewmodel.CurrenciesActivityViewModel;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Noble on 07.04.2019.
 *
 */

public class CurrenciesActivity extends AppCompatActivity implements CurrenciesActivityView {

    @Inject
    public CurrenciesActivityViewModel viewModel;

    @BindView(R.id.currencies_list)
    RecyclerView ratesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currencies);
        ButterKnife.bind(this);
        CurrencyApp.getComponent().inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        viewModel.attachView(this);
    }

    @Override
    public void initCurrencyList(RecyclerView.Adapter adapter) {
        ratesList.setLayoutManager(new LinearLayoutManager(this));
        ratesList.setHasFixedSize(true);
        ratesList.setItemViewCacheSize(adapter.getItemCount());
        ratesList.setAdapter(adapter);
    }

    @Override
    public void showError(String errorString) {
        Toast.makeText(this, errorString, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        ratesList.setAdapter(null);
        viewModel.detachView();
        super.onStop();
    }


}
