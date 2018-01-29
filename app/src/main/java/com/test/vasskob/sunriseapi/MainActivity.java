package com.test.vasskob.sunriseapi;

import android.Manifest;
import android.annotation.SuppressLint;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.TextView;

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

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rl_container)
    ViewGroup mRootView;

    @BindView(R.id.tv_place_name)
    TextView tvPlaceName;

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
        tvPlaceName.setText(String.format("Lat = %s Lng = %s", mLastLocation.getLatitude(), mLastLocation.getLongitude()));
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

}
