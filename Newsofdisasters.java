package com.example.disastermanagementfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
public class Newsofdisasters extends AppCompatActivity {
List<Alldetailsinfo> mainlist;
ViewPager viewpager;
Integer[] colors;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsofdisasters);
        final ArgbEvaluator argbEvaluator=new ArgbEvaluator();
        mainlist=new ArrayList<>();

        mainlist.add(new Alldetailsinfo("Cyclones","\nGet all updates on Cyclones near you and around the world",R.drawable.cyclone));
        mainlist.add(new Alldetailsinfo("Earthquake","\nGet all updates on Earthquake near you and around the world",R.drawable.ear));
        mainlist.add(new Alldetailsinfo("Floods","\nGet all updates on Floods near you and around the world",R.drawable.download));
        mainlist.add(new Alldetailsinfo("Forest fires","\nGet all updates on Forest fires near you and around the world",R.drawable.signuppagewallpaperxx));
        mainlist.add(new Alldetailsinfo("Volcanoes","\nGet all updates on Volcanoes near you and around the world",R.drawable.welcomepagewallpapewwwrxx));

        final NewsAdapter news=new NewsAdapter(mainlist,this);
        viewpager=findViewById(R.id.pages);
        Integer[]colorbackground={

                getResources().getColor(R.color.seven),
                getResources().getColor(R.color.five),
                getResources().getColor(R.color.fourth),
                getResources().getColor(R.color.second),
                getResources().getColor(R.color.six),

        };

        colors=colorbackground;
        viewpager.setAdapter(news);
        viewpager.setPadding(103,0,103,0);


        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    if(position<news.getCount()-1 && position<colors.length-1) {
                        viewpager.setBackgroundColor((Integer)argbEvaluator.evaluate(positionOffset,colors[position],colors[position+1]));
                    }
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {

                    case 1:
                        Intent i = new Intent(Newsofdisasters.this, Earthquake.class);
                        startActivity(i);

                    case 2:

                        Intent i2 = new Intent(Newsofdisasters.this, Floods.class);
                        startActivity(i2);


                    case 3:

                        Intent i3 = new Intent(Newsofdisasters.this, Forestfires.class);
                        startActivity(i3);
                }

               }
            @Override
            public void onPageScrollStateChanged(int state) {


            }
        });

    }
}
