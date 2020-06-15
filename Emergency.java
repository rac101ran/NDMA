package com.example.disastermanagementfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static android.location.LocationManager.GPS_PROVIDER;

public class Emergency extends AppCompatActivity {
    DatabaseReference ref;
    FirebaseAuth auth;
    FirebaseUser user;
    LocationManager loc;
    LocationListener listener;
    Map<String,String> map;
    DatabaseReference ref2;
    String postalcode="";
    DatabaseReference stationref,sf2;
    Map<String,Object> sendlocinfo;
    String lats;
    String longs;
    Button b;
    SharedPreferences sharedPreferences;
    private int pressed =1;
    Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);
        auth=FirebaseAuth.getInstance();
        b=findViewById(R.id.button);
        animation=AnimationUtils.loadAnimation(this,R.anim.animguildelines);
        b.setAnimation(animation);
        user=FirebaseAuth.getInstance().getCurrentUser();
        ref= FirebaseDatabase.getInstance().getReference().child("Users").child("Victims");
        stationref=FirebaseDatabase.getInstance().getReference("Users").child("Stations");


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==1 && grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED) {
           if( ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED) {
             loc.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,listener);

            }

        }
    }

    public void sendloc(View view) {

            loc = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
            listener = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    getlocation(location);
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }

            };

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            } else {

                loc.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener);

                Location locationupdates = loc.getLastKnownLocation(GPS_PROVIDER);


                if (locationupdates != null) {
                    getlocation(locationupdates);
                }
            }

        }



     public void getlocation(Location location){

            map = new HashMap<>();
            sendlocinfo = new HashMap<>();
            ref2 = ref.child(user.getUid().toString());
            sharedPreferences = this.getSharedPreferences("com.example.disastermanagementfinal", Context.MODE_PRIVATE);
            lats = Double.toString(location.getLatitude());
            longs = Double.toString(location.getLongitude());
            map.put("Name", sharedPreferences.getString("Name", "").toString());
            map.put("Latitude", lats);
            map.put("Longitute", longs);
            map.put("Distance","0");
            String addr = "";
            try {
                Geocoder geo = new Geocoder(this, Locale.getDefault());
                List<Address> listofaddress = geo.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                if (listofaddress != null && listofaddress.size() > 0) {
                    if (listofaddress.get(0).getPostalCode() != null) {

                        postalcode = listofaddress.get(0).getPostalCode().toString();

                    }
                    if (listofaddress.get(0).getLocality() != null) {
                        addr += listofaddress.get(0).getLocality().toString();
                    }

                }

            } catch (Exception e) {

                e.printStackTrace();

            }
            map.put("Postal code", postalcode);
            map.put("Address", addr);
            ref2.setValue(map);
            Toast.makeText(this, postalcode, Toast.LENGTH_SHORT).show();
            stationref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Iterator<DataSnapshot> iteratestationpostalcodes = dataSnapshot.getChildren().iterator();

                    while (iteratestationpostalcodes.hasNext()) {
                        DataSnapshot postalcodeitem = iteratestationpostalcodes.next();
                        if (postalcodeitem.equals(postalcode)) {

                            sf2 = stationref.child(postalcodeitem.getKey().toString()).child("Victim Details").child(user.getUid());
                            sendlocinfo.put("Postal code", postalcode);
                            sendlocinfo.put("Latitude", lats);
                            sendlocinfo.put("Longitude", longs);
                            sendlocinfo.put("Name", sharedPreferences.getString("Name", "").toString());
                            sf2.updateChildren(sendlocinfo);
                            Toast.makeText(Emergency.this, postalcodeitem.getKey().toString(), Toast.LENGTH_SHORT).show();
                            break;

                        }

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

    ref2.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if (!TextUtils.isEmpty(dataSnapshot.child("Distance").getValue().toString())) {
                if (!dataSnapshot.child("Distance").getValue().toString().equals("0")) {

                    new AlertDialog.Builder(Emergency.this).setIcon(R.drawable.ic_directions_run_black_24dp).setTitle("Up for your Rescue").
                            setMessage("We are " + dataSnapshot.child("Distance").getValue().toString() + " Km Away from your Location.").show();

                }
            }
        }


        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

           }
         });


        }
     }
