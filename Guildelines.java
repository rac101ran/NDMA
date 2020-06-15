package com.example.disastermanagementfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class Guildelines extends AppCompatActivity {
Animation anim;
TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guildelines);
         textView=findViewById(R.id.textView4);
        textView.setText("National Guidelines for Preparation of Action Plan - Prevention and Management of Heat Wave"+
                "Compendium of Task Force Sub Group Reports on National Landslide Risk Management Strategy\n" +
                "        National Disaster Management Guidelines on Disability Inclusive Disaster Risk Reduction\n" +
                "        National Guidelines on Temporary Shelters for Disaster - Affected Families\n" +
                "        Guidelines for Preparation of Action plan - Prevention and Management of Thunderstorm & Lightning/Squall/Dust/Hailstorm and Strong Winds 2018\n" +
                "        Guidelines on Museums\n" +
                "        Guidelines on Cultural Heritage Sites and Precincts\n" +
                "        Guidelines on Boat Safety\n" +
                "        Guidelines for Preparation of Action Plan - Prevention and Management of Heat-Wave-2017\n" +
                "        Guidelines on School Safety Policy\n" +
                "        Guidelines on Hospital Safety\n" +
                "        Guidelines on Minimum Standards of Relief\n" +
                "        राहत के न्यूनतम स्तर पर दिशानिर्देश\n" +
                "        Guidelines on Management of Earthquakes\n" +
                "        Guidelines on Management of Tsunamis\n" +
                "        Guidelines on Management of Cyclones\n" +
                "        Guidelines on Management of Flood\n" +
                "        Guidelines on Management of Urban Flooding\n" +
                "        Guidelines On Drought Management\n" +
                "        Guidelines On Landslide and snow avalanches\n" +
                "        Guidelines for Nuclear and Radiological Emergencies\n" +
                "        Guidelines on Chemical Disaster (Industrial)\n" +
                "                Guidelines for Chemical (Terrorism) Disaster\n" +
                "        Guidelines on Medical Preparedness and Mass Casualty Management\n" +
                "        Guidelines for Biological Disaster\n" +
                "        Guidelines for Psycho-Social Support\n" +
                "        Guidelines on Formulation of State DM Plans\n" +
                "        Guidelines for Incident Response System\n" +
                "        Guidelines for National Disaster Management Information and Communication System\n" +
                "        Guidelines for Scaling, Type of Equipment and Training of Fire Services\n" +
                "        Guidelines for Seismic Retrofitting of Deficient Buildings and Structures");
        anim= AnimationUtils.loadAnimation(Guildelines.this,R.anim.animae2);
        textView.setAnimation(anim);

    }
}
