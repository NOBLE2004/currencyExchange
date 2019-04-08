package com.revolut.noble.android.task.model.network;

import com.revolut.noble.android.task.Model.network.NetworkManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * Created by Noble on 06.04.2019.
 *
 */

@RunWith(MockitoJUnitRunner.class)
public class NetworkManagerTest {

    @Mock
    private NetworkManager networkManager;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() {
        networkManager = null;
    }

    @Test
    public void tryToTestRetrofit() throws Exception {

    }
}
