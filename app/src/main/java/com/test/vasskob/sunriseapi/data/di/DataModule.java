package com.test.vasskob.sunriseapi.data.di;


import com.test.vasskob.sunriseapi.data.api.ApiInterface;
import com.test.vasskob.sunriseapi.data.repository.SunDataRepositoryImpl;
import com.test.vasskob.sunriseapi.domain.repository.SunDataRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {

    @Provides
    @Singleton
    SunDataRepository provideSunDataRepository(ApiInterface api) {
        return new SunDataRepositoryImpl(api);
    }
}