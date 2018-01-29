package com.test.vasskob.sunriseapi.global.di;

import com.test.vasskob.sunriseapi.SunriseApp;
import com.test.vasskob.sunriseapi.data.di.DataComponent;
import com.test.vasskob.sunriseapi.data.di.DataScope;
import com.test.vasskob.sunriseapi.presentation.di.ActivityBuilder;

import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@DataScope
@Component(
        dependencies = DataComponent.class,

        modules = {
                AppModule.class,
                AndroidSupportInjectionModule.class,

                ActivityBuilder.class,
        })

public interface AppComponent {
    void inject(SunriseApp application);
}
