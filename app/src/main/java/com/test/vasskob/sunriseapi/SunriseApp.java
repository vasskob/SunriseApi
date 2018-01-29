package com.test.vasskob.sunriseapi;

import android.app.Activity;
import android.app.Application;

import com.test.vasskob.sunriseapi.data.di.DaggerDataComponent;
import com.test.vasskob.sunriseapi.data.di.DataComponent;
import com.test.vasskob.sunriseapi.global.di.AppComponent;
import com.test.vasskob.sunriseapi.global.di.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import timber.log.Timber;

public class SunriseApp extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> activityInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        initTimber();
        initDagger();
    }

    private void initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    private void initDagger() {
        DataComponent mDataComponent = DaggerDataComponent.builder()
                .application(this)
                .build();

        AppComponent mAppComponent = DaggerAppComponent.builder()
                .dataComponent(mDataComponent)
                .build();

        mAppComponent.inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityInjector;
    }
}
