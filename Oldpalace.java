package com.example.disastermanagementfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
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
import java.util.Map;

public class Oldpalace extends AppCompatActivity {
DatabaseReference reference , ref2victim,victimfinal;
RecyclerView recyclerView;
ArrayList<String> details_victims;
Map<String,Object> updatedistance;
ListView list;
    ArrayAdapter<String> arrayAdapter;
    int img[]={R.drawable.image,R.drawable.image,R.drawable.image,R.drawable.image,R.drawable.image,R.drawable.image,R.drawable.image};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oldpalace);
        details_victims=new ArrayList<>();
     //   recyclerView=findViewById(R.id.re);
        list=findViewById(R.id.listid);
        updatedistance=new HashMap<>();
        reference= FirebaseDatabase.getInstance().getReference("Users").child("Stations").child("110003").child("Victim Details");
        ref2victim=FirebaseDatabase.getInstance().getReference().child("Users").child("Victims");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> iterateallvictimsacc=dataSnapshot.getChildren().iterator();
                while(iterateallvictimsacc.hasNext()) {
                        DataSnapshot itemvictim =  iterateallvictimsacc.next();
                        details_victims.add("Name : "+itemvictim.child("Name").getValue().toString()+ "\n"
                        +  "Coordinates :   \n"+ "Lat : "+ itemvictim.child("Latitude").getValue().toString() + "\n"+ "Lon : "+itemvictim.child("Longitude").getValue().toString());
                        Distanceupdate distanceupdate=new Distanceupdate(28.5124,77.2318,Double.parseDouble(itemvictim.child("Latitude").getValue().toString())
                                ,Double.parseDouble(itemvictim.child("Longitude").getValue().toString()));
                        victimfinal=ref2victim.child(itemvictim.getKey().toString());
                        updatedistance.put("Distance updates",distanceupdate.distanceCalculate());
                        victimfinal.updateChildren(updatedistance);
                }
                       arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
  //SoutheastdelhiVictims v=new SoutheastdelhiVictims(this,details_victims);
 // v.notifyDataSetChanged();
//  recyclerView.setAdapter(v);
//  recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,details_victims);

    list.setAdapter(arrayAdapter);
    }
}
