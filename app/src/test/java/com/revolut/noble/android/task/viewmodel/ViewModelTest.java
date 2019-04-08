package com.revolut.noble.android.task.viewmodel;

import com.revolut.noble.android.task.Model.repository.CurrencyRepository;
import com.revolut.noble.android.task.view.CurrenciesActivity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;


/**
 * Created by Noble on 07.04.2019.
 *
 */

@RunWith(MockitoJUnitRunner.class)
public class ViewModelTest {

    private CurrenciesActivityViewModel viewModel;

    @Mock
    private CurrencyRepository repository;
    @Mock
    private CurrenciesActivity mockActivity;
    @Mock
    private CurrenciesAdapter adapter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        viewModel = new CurrenciesActivityViewModel(repository);
    }

    @After
    public void tearDown() {
        repository.shutdownGettingDataFromNetwork();
        repository = null;
        viewModel = null;
    }

    @Test
    public void tryWithoutAttachedView() throws Exception {
        //call detach in start
        viewModel.detachView();
        assertTrue(viewModel.getModelList().size() == 0);
    }

    @Test
    public void tryBehaviorAttachAndDetachView() throws Exception {
        viewModel.attachView(mockActivity);
        verify(repository).setListener(viewModel);
        verify(repository).getDataFromNetworkAndListen();
        viewModel.detachView();
        verify(repository).removeListener();
        verify(repository).shutdownGettingDataFromNetwork();
    }

}