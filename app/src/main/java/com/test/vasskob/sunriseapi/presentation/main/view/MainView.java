package com.test.vasskob.sunriseapi.presentation.main.view;

import com.arellomobile.mvp.MvpView;

public interface MainView extends MvpView {
    void showSunData(String sunrise, String sunset);
    void showSunDataLoadingError(String errorMsg);
}
