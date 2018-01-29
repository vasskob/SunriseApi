package com.test.vasskob.sunriseapi.data.repository;

import com.test.vasskob.sunriseapi.data.api.ApiInterface;
import com.test.vasskob.sunriseapi.data.mapper.SunDataMapper;
import com.test.vasskob.sunriseapi.domain.model.SunDataModel;
import com.test.vasskob.sunriseapi.domain.repository.SunDataRepository;

import io.reactivex.Single;
import timber.log.Timber;

public class SunDataRepositoryImpl implements SunDataRepository {

    private ApiInterface mApi;
    private SunDataMapper mSunDataMapper = new SunDataMapper();

    public SunDataRepositoryImpl(ApiInterface mApi) {
        this.mApi = mApi;
    }

    @Override
    public Single<SunDataModel> getSunData(double lat, double lng) {
        Timber.d("getSunData: lat = " + lat + " lng = " + lng);
        return mApi.getSunData(lat,lng)
                .map(mSunDataMapper);
    }
}
