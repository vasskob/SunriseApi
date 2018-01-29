package com.test.vasskob.sunriseapi.domain.repository;

import com.test.vasskob.sunriseapi.domain.model.SunDataModel;

import io.reactivex.Single;

public interface SunDataRepository {
    Single<SunDataModel> getSunData(double lat, double lng);
}
