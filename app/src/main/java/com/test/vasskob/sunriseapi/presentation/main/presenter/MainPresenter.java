package com.test.vasskob.sunriseapi.presentation.main.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.test.vasskob.sunriseapi.domain.repository.SunDataRepository;
import com.test.vasskob.sunriseapi.presentation.main.view.MainView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private final CompositeDisposable mCompositeDisposable;
    private SunDataRepository mDataRepository;

    public MainPresenter(SunDataRepository repository) {
        mDataRepository = repository;
        mCompositeDisposable = new CompositeDisposable();
    }

    public void getSunData(double lat, double lng) {
        mDataRepository.getSunData(lat, lng)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                    addDisposable(disposable);
                    getViewState().showLoading();
                })
                .doAfterTerminate(() -> getViewState().hideLoading())
                .subscribe(response -> getViewState().showSunData(response.getSunrise(), response.getSunset()),
                        throwable -> getViewState().showSunDataLoadingError(throwable.getMessage()));
    }

    private void addDisposable(Disposable disposable) {
        Timber.d("addDisposable: " + disposable);
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void onDestroy() {
        if (!mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.clear();
        }
    }
}
