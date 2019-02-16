package br.com.philippesis.mapboxtest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.MapboxMapOptions;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.style.light.Position;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private MapView mMapView;
    private MapboxMap mMapboxMap;

    private EditText editSearch;
    private Button btnIr;
    private Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, getResources().getString(R.string.access_token));
        setContentView(R.layout.activity_main);

        editSearch = findViewById(R.id.id_edit_search);
        btnIr = findViewById(R.id.id_btn_ir);

        this.setListeners();

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(-23.673133, -46.517018))
                .zoom(16)
                .build();

        mMapView = findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        // Change look
        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull final MapboxMap mapboxMap) {
                mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {

                        // Map is set up and the style has loaded. Now you can add data or make other map adjustments
                        mMapboxMap = mapboxMap;

                        mMapboxMap.setCameraPosition(cameraPosition);

                    }
                });

            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        mMapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mMapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    private void setListeners() {
        this.btnIr.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v == btnIr) {

            if(!editSearch.getText().toString().isEmpty() &&
                    editSearch.getText().toString().equals("cgh estacionamento") ) {
                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(new LatLng(-23.632321, -46.656295))
                        .zoom(16)
                        .build();
                //mMapboxMap.setCameraPosition(cameraPosition);
                mMapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), 1000);
                Toast.makeText(MainActivity.this, "Redirecionado para CGH Estacionamento",
                        Toast.LENGTH_SHORT).show();

            }
        }

    }
}