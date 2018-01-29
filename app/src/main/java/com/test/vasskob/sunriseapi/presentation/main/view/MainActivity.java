package com.test.vasskob.sunriseapi.presentation.main.view;

import android.Manifest;
import android.annotation.SuppressLint;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.CompositePermissionListener;
import com.karumi.dexter.listener.single.PermissionListener;
import com.karumi.dexter.listener.single.SnackbarOnDeniedPermissionListener;
import com.test.vasskob.sunriseapi.R;
import com.test.vasskob.sunriseapi.presentation.main.presenter.MainPresenter;
import com.test.vasskob.sunriseapi.utils.NetworkUtils;

import javax.inject.Inject;
import javax.inject.Provider;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import timber.log.Timber;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    @BindView(R.id.rl_container)
    ViewGroup mRootView;

    @BindView(R.id.tv_place_name)
    TextView tvPlaceName;

    @BindView(R.id.tv_sunrise_time)
    TextView tvSunriseTime;

    @BindView(R.id.tv_sunset_time)
    TextView tvSunsetTime;

    @Inject
    Provider<MainPresenter> mPresenterProvider;

    @InjectPresenter(type = PresenterType.LOCAL)
    MainPresenter mPresenter;

    @ProvidePresenter(type = PresenterType.LOCAL)
    MainPresenter providePresenter() {
        return mPresenterProvider.get();
    }

    private PermissionListener mLocationPermissionListener;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private PermissionListener mPermissionListener = new PermissionListener() {

        @Override
        public void onPermissionGranted(PermissionGrantedResponse response) {
            initGoogleApiClient();
        }

        @Override
        public void onPermissionDenied(PermissionDeniedResponse response) {
            Timber.d("onUserLocationDenied: ");
        }

        @Override
        public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

        }
    };

    private GoogleApiClient.ConnectionCallbacks mGoogleApiConnectionCallback = new GoogleApiClient.ConnectionCallbacks() {
        @Override
        public void onConnected(@Nullable Bundle bundle) {
            getLastKnownLocation();
        }

        @Override
        public void onConnectionSuspended(int i) {
            mGoogleApiClient.connect();
        }
    };

    private GoogleApiClient.OnConnectionFailedListener mGoogleApiOnConnectionFailedListener =
            connectionResult -> Timber.d("onConnectionFailed() called with: connectionResult = [" + connectionResult + "]");

    @SuppressLint("MissingPermission")
    private void getLastKnownLocation() {
        mLastLocation = LocationServices.FusedLocationApi
                .getLastLocation(mGoogleApiClient);
        Timber.d("getLastKnownLocation: LOCATION = " + mLastLocation);

        if (mLastLocation != null) {
            if (NetworkUtils.isOnline(this)) {
                mPresenter.getSunData(mLastLocation.getLatitude(), mLastLocation.getLongitude());
            } else makeOfflineToast();
        }
    }

    private void makeOfflineToast() {
        Toast.makeText(this, "You device is offLine. Please connect to internet & retry!", Toast.LENGTH_SHORT).show();
    }

    private void initGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(mGoogleApiConnectionCallback)
                .addOnConnectionFailedListener(mGoogleApiOnConnectionFailedListener)
                .addApi(LocationServices.API).build();

        mGoogleApiClient.connect();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initPermissionListener();
        requestLocation();
    }

    private void initPermissionListener() {
        mLocationPermissionListener = new CompositePermissionListener(mPermissionListener,
                SnackbarOnDeniedPermissionListener.Builder.with(mRootView, R.string.permission_message_location)

                        .withOpenSettingsButton(R.string.button_settings_title)
                        .build());
    }

    public void requestLocation() {
        Dexter.withActivity(MainActivity.this)
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(mLocationPermissionListener)
                .check();
    }

    @Override
    public void showSunData(String sunrise, String sunset) {
        tvPlaceName.setText(getString(R.string.current_location));
        tvSunriseTime.setText(sunrise);
        tvSunsetTime.setText(sunset);
    }

    @Override
    public void showSunDataLoadingError(String errorMsg) {
        Timber.d("showSunDataLoadingError: " + errorMsg);
    }
}
