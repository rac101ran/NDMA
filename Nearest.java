package com.example.disastermanagementfinal;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

public class Nearest extends FragmentActivity implements OnMapReadyCallback {
    DatabaseReference ref,ref2;
    FirebaseAuth auth;
    FirebaseUser user;
    private GoogleMap mMap;
    String id;
    double lat,lon;
    String postal;
    DataSnapshot item;
    String stationname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearest);
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();

        ref= FirebaseDatabase.getInstance().getReference("Users").child("Victims").child(user.getUid());
        ref2=FirebaseDatabase.getInstance().getReference("Users").child("Stations");
        id=user.getUid();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              postal= dataSnapshot.child("Postal code").getValue().toString();
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });

       ref2.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               Iterator<DataSnapshot> iterator=dataSnapshot.getChildren().iterator();
               while(iterator.hasNext()) {
                          DataSnapshot iterate=iterator.next();
                          if(iterate.getKey().toString().equals(postal)) {
                                lat=Double.parseDouble(iterate.child("Station Details").child("Latitude").getValue().toString());
                                lon=Double.parseDouble(iterate.child("Station Details").child("Longitude").getValue().toString());
                                stationname=item.child("Station Details").child("Name").getValue().toString();
                                break;
                          }

               }
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Toast.makeText(this, postal, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        LatLng sydney = new LatLng(lat, lon);
        mMap.addMarker(new MarkerOptions().position(sydney).title(stationname).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,11.2f));

    }
}
