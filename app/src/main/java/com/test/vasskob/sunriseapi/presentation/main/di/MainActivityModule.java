package com.test.vasskob.sunriseapi.presentation.main.di;


import com.test.vasskob.sunriseapi.data.api.ApiInterface;
import com.test.vasskob.sunriseapi.domain.repository.SunDataRepository;
import com.test.vasskob.sunriseapi.presentation.main.presenter.MainPresenter;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MainActivityModule {

    @MainActivityScope
    @Provides
    MainPresenter provideMainPresenter(SunDataRepository repository) {
        return new MainPresenter(repository);
    }


    @MainActivityScope
    @Provides
    ApiInterface provideApi(Retrofit retrofit) {
        return retrofit.create(ApiInterface.class);
    }

}
