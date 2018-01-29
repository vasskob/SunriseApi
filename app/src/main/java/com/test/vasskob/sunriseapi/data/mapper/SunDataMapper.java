package com.test.vasskob.sunriseapi.data.mapper;

import com.test.vasskob.sunriseapi.data.response.SunDataResponse;
import com.test.vasskob.sunriseapi.domain.model.SunDataModel;

import io.reactivex.functions.Function;

public class SunDataMapper implements Function<SunDataResponse, SunDataModel> {

    @Override
    public SunDataModel apply(SunDataResponse target) {
        SunDataModel result = new SunDataModel();
        result.setSunrise(target.getSunDataEntity().getSunrise());
        result.setSunset(target.getSunDataEntity().getSunset());
        result.setDayLength(target.getSunDataEntity().getDayLength());
        result.setAstronomicalTwilightBegin(target.getSunDataEntity().getAstronomicalTwilightBegin());
        result.setAstronomicalTwilightEnd(target.getSunDataEntity().getAstronomicalTwilightEnd());
        result.setSolarNoon(target.getSunDataEntity().getSolarNoon());
        result.setCivilTwilightBegin(target.getSunDataEntity().getCivilTwilightBegin());
        result.setCivilTwilightEnd(target.getSunDataEntity().getCivilTwilightEnd());
        result.setNauticalTwilightBegin(target.getSunDataEntity().getNauticalTwilightBegin());
        result.setNauticalTwilightEnd(target.getSunDataEntity().getNauticalTwilightEnd());
        return result;
    }
}