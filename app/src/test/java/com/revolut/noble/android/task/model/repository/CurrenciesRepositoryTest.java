package com.revolut.noble.android.task.model.repository;

import android.content.Context;

import com.revolut.noble.android.task.Model.entity.RevolutResponse;
import com.revolut.noble.android.task.Model.network.NetworkManager;
import com.revolut.noble.android.task.Model.repository.CurrencyRepositoryImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;


/**
 * Created by Noble on 07.04.2019.
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class CurrenciesRepositoryTest {

    @Mock
    private NetworkManager networkManager;

    @Mock
    private Context context;

    private CurrencyRepositoryImpl repository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        repository = new CurrencyRepositoryImpl(context, networkManager);
    }

    @After
    public void tearDown() {
        repository = null;
    }

    @Test
    public void tryToSendCallbackIfNoListenerWasSet() throws Exception {
        repository.setListener(null);
        repository.getDataFromNetworkAndListen();
        repository.shutdownGettingDataFromNetwork();
        verify(networkManager).shutdownRequestsToServer();
    }

    private NetworkManager.NetworkListener listener = new NetworkManager.NetworkListener() {
        @Override
        public void onSuccess(RevolutResponse currencies) {

        }

        @Override
        public void onFailure(Throwable t) {

        }
    };
}
