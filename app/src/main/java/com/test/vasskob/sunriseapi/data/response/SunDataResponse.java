package com.test.vasskob.sunriseapi.data.response;

import com.google.gson.annotations.SerializedName;

public class SunDataResponse {
    @SerializedName("results")
    private SunDataEntity sunDataEntity;
    @SerializedName("status")
    private String status;

    public SunDataEntity getSunDataEntity() {
        return sunDataEntity;
    }

    public void setSunDataEntity(SunDataEntity sunDataEntity) {
        this.sunDataEntity = sunDataEntity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
