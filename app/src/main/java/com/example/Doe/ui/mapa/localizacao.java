package com.example.Doe.ui.mapa;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.example.Doe.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class localizacao extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String latitude;
    String longitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localizacao);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        if(getIntent().getExtras() != null) {

            latitude = getIntent().getExtras().get("latitude").toString();
            longitude = getIntent().getExtras().get("longitude").toString();


        }

        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        Double lat = Double.parseDouble(latitude);
        Double lon = Double.parseDouble(longitude);

        LatLng local = new LatLng( lat, lon );
        mMap.addMarker(new MarkerOptions().position(local).title("Local do Doador"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(local, 10));
    }
}