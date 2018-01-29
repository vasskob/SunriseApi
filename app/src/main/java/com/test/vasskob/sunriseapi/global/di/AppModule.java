package com.test.vasskob.sunriseapi.global.di;

import android.content.Context;

import com.test.vasskob.sunriseapi.SunriseApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(SunriseApp application) {
        return application.getApplicationContext();
    }
}
