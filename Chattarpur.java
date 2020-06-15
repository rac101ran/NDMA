package com.example.disastermanagementfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Chattarpur extends AppCompatActivity {
    DatabaseReference reference, refupdatevictim;
    ArrayList<String> arr;
    Map<String, Object> distance_update;
    DatabaseReference ref2;
    ListView listView;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chattarpur);
        arr = new ArrayList<>();
        listView = findViewById(R.id.listchat);
        reference = FirebaseDatabase.getInstance().getReference("Users").child("Stations").child("110070").child("Victim Details");
        refupdatevictim = FirebaseDatabase.getInstance().getReference("Users").child("Victims");
        distance_update = new HashMap<>();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
                while (iterator.hasNext()) {
                    DataSnapshot item = iterator.next();

                    arr.add("Name :   " + item.child("Name").getValue().toString() + "\n" + "Latitude : " +
                            item.child("Latitude").getValue().toString() + "\n" +
                            "Longitude : " + item.child("Longitude").getValue().toString() + "\n");

                    Distanceupdate update = new Distanceupdate(28.5124, 77.1370, Double.parseDouble(item.child("Latitude").getValue().toString())
                            , Double.parseDouble(item.child("Longitude").getValue().toString()));
                               // distance_update.put("Distance Updates", update.distanceCalculate());

                              ref2 = reference.child(item.getKey().toString());
                              ref2.child("Distance").setValue(update.distanceCalculate());

                }
                adapter.notifyDataSetChanged();

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arr);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

         listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
             @Override
             public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                 if (position == 0) {
                     new AlertDialog.Builder(Chattarpur.this).setIcon(R.drawable.ic_delete_black_24dp).setTitle("Do you want this detail?").
                             setMessage("Are you sure you want t delete this?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialog, int which) {
                             arr.remove(position);
                         }
                     }).setNegativeButton("No", null);
                     return true;
                 }
                 if (position == 1) {
                     new AlertDialog.Builder(Chattarpur.this).setIcon(R.drawable.ic_delete_black_24dp).setTitle("Do you want this detail?").
                             setMessage("Are you sure you want t delete this?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialog, int which) {
                             arr.remove(position);
                         }
                     }).setNegativeButton("No", null);
                     return true;
                 }
                 if (position == 2) {
                     new AlertDialog.Builder(Chattarpur.this).setIcon(R.drawable.ic_delete_black_24dp).setTitle("Do you want this detail?").
                             setMessage("Are you sure you want t delete this?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialog, int which) {
                             arr.remove(position);

                         }
                     }).setNegativeButton("No", null);
                     return true;
                 }

                    return  true;
             }
         });

    }
}
