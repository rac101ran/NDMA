package com.example.disastermanagementfinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SoutheastdelhiVictims extends RecyclerView.Adapter<SoutheastdelhiVictims.childclass> {
ArrayList<String> des;
  Context context;

    public SoutheastdelhiVictims(Context c, ArrayList<String> desc) {
           des=desc;
           context=c;

    }

    @NonNull
    @Override
    public SoutheastdelhiVictims.childclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View v= inflater.inflate(R.layout.row,parent,false);
        return new childclass(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SoutheastdelhiVictims.childclass holder, int position) {
               holder.desc.setText(des.get(position));
    }

    @Override
    public int getItemCount() {
        return des.size();
    }

    public class childclass extends RecyclerView.ViewHolder {

      TextView desc;
      ImageView img;

        public childclass(@NonNull View itemView) {
            super(itemView);
            desc=itemView.findViewById(R.id.descritext);
            img=itemView.findViewById(R.id.victimimage);
        }
    }
}
