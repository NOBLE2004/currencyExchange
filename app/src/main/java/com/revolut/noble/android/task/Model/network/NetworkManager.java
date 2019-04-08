package com.revolut.noble.android.task.Model.network;

import android.support.annotation.NonNull;
import android.util.Log;
import java.util.concurrent.TimeUnit;
import com.revolut.noble.android.task.Model.entity.RevolutResponse;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import static com.revolut.noble.android.task.Config.*;



/**
 * Created by Noble on 07.09.2018.
 *
 */

public class NetworkManager {

    private RevolutRetrofitService revolutRetrofitService;
    private Disposable networkObservable;

    public NetworkManager() {
        //Init retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        revolutRetrofitService = retrofit.create(RevolutRetrofitService.class);
    }

    public void requestDataAndListen(@NonNull NetworkListener callback) {

        networkObservable = Observable.interval(1, TimeUnit.SECONDS)
                .flatMap(n -> revolutRetrofitService.getCurrencies(BASE_CURRENCY)
                                .flatMap(Observable::just)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .onErrorResumeNext(observer -> { })
                                .doOnNext(currencies -> {
                                    Log.d("server", currencies.getBase() + "   " + currencies.getDate());
                                    callback.onSuccess(currencies);
                                })
                                .doOnError(throwable ->  {
                                    Log.e("server", "Error: " + throwable.getMessage());
                                    callback.onFailure(throwable);
                                })
                        ).subscribe();
    }

    public void shutdownRequestsToServer() {
        if (null != networkObservable) {
            networkObservable.dispose();
            networkObservable = null;
        }
    }

    public interface NetworkListener {
        void onSuccess(RevolutResponse currencies);
        void onFailure(Throwable t);
    }
}
