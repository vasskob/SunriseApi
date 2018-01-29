package com.test.vasskob.sunriseapi.domain.model;

public class SunDataModel {

    private String sunrise;

    private String sunset;

    private String solarNoon;

    private String dayLength;

    private String civilTwilightBegin;

    private String civilTwilightEnd;

    private String nauticalTwilightBegin;

    private String nauticalTwilightEnd;

    private String astronomicalTwilightBegin;

    private String astronomicalTwilightEnd;

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public String getSolarNoon() {
        return solarNoon;
    }

    public void setSolarNoon(String solarNoon) {
        this.solarNoon = solarNoon;
    }

    public String getDayLength() {
        return dayLength;
    }

    public void setDayLength(String dayLength) {
        this.dayLength = dayLength;
    }

    public String getCivilTwilightBegin() {
        return civilTwilightBegin;
    }

    public void setCivilTwilightBegin(String civilTwilightBegin) {
        this.civilTwilightBegin = civilTwilightBegin;
    }

    public String getCivilTwilightEnd() {
        return civilTwilightEnd;
    }

    public void setCivilTwilightEnd(String civilTwilightEnd) {
        this.civilTwilightEnd = civilTwilightEnd;
    }

    public String getNauticalTwilightBegin() {
        return nauticalTwilightBegin;
    }

    public void setNauticalTwilightBegin(String nauticalTwilightBegin) {
        this.nauticalTwilightBegin = nauticalTwilightBegin;
    }

    public String getNauticalTwilightEnd() {
        return nauticalTwilightEnd;
    }

    public void setNauticalTwilightEnd(String nauticalTwilightEnd) {
        this.nauticalTwilightEnd = nauticalTwilightEnd;
    }

    public String getAstronomicalTwilightBegin() {
        return astronomicalTwilightBegin;
    }

    public void setAstronomicalTwilightBegin(String astronomicalTwilightBegin) {
        this.astronomicalTwilightBegin = astronomicalTwilightBegin;
    }

    public String getAstronomicalTwilightEnd() {
        return astronomicalTwilightEnd;
    }

    public void setAstronomicalTwilightEnd(String astronomicalTwilightEnd) {
        this.astronomicalTwilightEnd = astronomicalTwilightEnd;
    }

    @Override
    public String toString() {
        return "SunDataModel{" +
                "sunrise='" + sunrise + '\'' +
                ", sunset='" + sunset + '\'' +
                ", solarNoon='" + solarNoon + '\'' +
                ", dayLength='" + dayLength + '\'' +
                ", civilTwilightBegin='" + civilTwilightBegin + '\'' +
                ", civilTwilightEnd='" + civilTwilightEnd + '\'' +
                ", nauticalTwilightBegin='" + nauticalTwilightBegin + '\'' +
                ", nauticalTwilightEnd='" + nauticalTwilightEnd + '\'' +
                ", astronomicalTwilightBegin='" + astronomicalTwilightBegin + '\'' +
                ", astronomicalTwilightEnd='" + astronomicalTwilightEnd + '\'' +
                '}';
    }
}
