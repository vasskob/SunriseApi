package com.test.vasskob.sunriseapi.data.di;

import com.test.vasskob.sunriseapi.SunriseApp;
import com.test.vasskob.sunriseapi.domain.repository.SunDataRepository;
import com.test.vasskob.sunriseapi.global.di.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class,

        NetworkModule.class,
        DataModule.class
})
public interface DataComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(SunriseApp application);

        DataComponent build();
    }

    SunDataRepository getSunDataRepository();
}
