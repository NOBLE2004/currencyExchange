package com.revolut.noble.android.task.Model.network;

import com.revolut.noble.android.task.Model.entity.RevolutResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Noble on 07.09.2018.
 *
 */


public interface RevolutRetrofitService {
    @GET("/latest")
    Observable<RevolutResponse> getCurrencies(@Query("base") String base);
}
