package com.example.disastermanagementfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class Welcomepage extends AppCompatActivity {
Button signup;
Button login;
Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomepage);
        signup=findViewById(R.id.signup);
        login=findViewById(R.id.loginid);
        animation= AnimationUtils.loadAnimation(this,R.anim.signupanima);
        signup.setAnimation(animation);
        login.setAnimation(AnimationUtils.loadAnimation(this,R.anim.loginanim));
    }
    public void login(View v) {

        Intent i=new Intent(Welcomepage.this,Loginpage.class);
        startActivity(i);

    }

    public void signup(View view ) {

        Intent i2=new Intent(Welcomepage.this ,Signuppage.class);
        startActivity(i2);

    }

    public void adminclick(View view) {

        Intent i3=new Intent(Welcomepage.this,Adminclass.class);
        startActivity(i3);
    }
}
