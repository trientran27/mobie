package com.example.quanlysukiendat;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.quanlysukiendat.Model.diadiemModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.quanlysukiendat.databinding.ActivityMapsBinding;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap myMap;
    private ImageView btnback;
    private List<diadiemModel> listDD ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(MapsActivity.this);
        init();
        btnback.setOnClickListener(view -> {
            finish();
        });
    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        Intent intent = getIntent();
        listDD = (List<diadiemModel>) intent.getSerializableExtra("listdiadiem");
        myMap = googleMap;
        for (diadiemModel diadiem : listDD) {
            double latitude = Double.parseDouble(diadiem.getLatitude());
            double longitude = Double.parseDouble(diadiem.getLongitude());
            LatLng location = new LatLng(latitude, longitude);
            myMap.addMarker(new MarkerOptions().position(location).title(diadiem.getTendiadiem()));
        }

        if (!listDD.isEmpty()) {
            double firstLatitude = Double.parseDouble(listDD.get(0).getLatitude());
            double firstLongitude = Double.parseDouble(listDD.get(0).getLongitude());
            LatLng firstLocation = new LatLng(firstLatitude, firstLongitude);
            myMap.moveCamera(CameraUpdateFactory.newLatLngZoom(firstLocation, 10));
        }

        // Set up marker click listener
        myMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                // Open detail activity for the clicked marker
                Intent intent = new Intent(MapsActivity.this, ChitietdiadiemActivity.class);
                intent.putExtra("diadiem", findDiadiemByMarker(marker,listDD));
                startActivity(intent);
                return true;
            }
        });
//        LatLng sydney = new LatLng(-34, 151);
//        myMap.addMarker(new MarkerOptions().position(sydney).title("Sydney"));
//        myMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }
    private diadiemModel findDiadiemByMarker(Marker marker, List<diadiemModel> listdd) {
        for (diadiemModel diadiem : listdd) {
            if (diadiem.getTendiadiem().equals(marker.getTitle())) {
                return diadiem;
            }
        }
        return null;
    }

    public void init(){
        btnback = findViewById(R.id.iv_back);
    }
}