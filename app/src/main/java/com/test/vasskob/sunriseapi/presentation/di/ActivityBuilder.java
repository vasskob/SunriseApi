package com.test.vasskob.sunriseapi.presentation.di;


import com.test.vasskob.sunriseapi.presentation.main.di.MainActivityModule;
import com.test.vasskob.sunriseapi.presentation.main.di.MainActivityScope;
import com.test.vasskob.sunriseapi.presentation.main.view.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @MainActivityScope
    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity bindMainActivity();
}
