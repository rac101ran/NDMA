package com.example.disastermanagementfinal;

import androidx.fragment.app.FragmentActivity;

import android.graphics.Bitmap;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap map;
    private GoogleMap map2;
    private  GoogleMap map3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }




    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        LatLng chattarpurstation = new LatLng(28.501526, 77.187155);
        map.addMarker(new MarkerOptions().position(chattarpurstation).title("Chattarpur Disaster station").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(chattarpurstation,10.4f));

        map2=googleMap;
        LatLng latLng=new LatLng(28.609863, 77.207708);
        map2.addMarker(new MarkerOptions().title("DRDO Diasater station").position(latLng).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));
        map2.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,10.4f));

        map3=googleMap;
        LatLng latLng1=new LatLng(28.663175, 77.308121);
        map3.addMarker(new MarkerOptions().title("Yamuna Sports Complex Station").position(latLng1).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));
        map3.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng1,10.4f));
    }
}
