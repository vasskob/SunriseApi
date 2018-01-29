package com.test.vasskob.sunriseapi.data.api;

import com.test.vasskob.sunriseapi.data.response.SunDataResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    String GET_SUNRISE_SUNSET = "json";

    @GET(GET_SUNRISE_SUNSET)
    Single<SunDataResponse> getSunData(@Query("lat") double lat, @Query("lng") double lng);
}
