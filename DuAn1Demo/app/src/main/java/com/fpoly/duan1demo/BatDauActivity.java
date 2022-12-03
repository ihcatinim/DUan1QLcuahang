package com.fpoly.duan1demo;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

public class BatDauActivity extends AppCompatActivity {

    TextView title1, title2, title3, title4;
    ImageView imgLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bat_dau);
        imgLogo = findViewById(R.id.imgLogo);
        title1 = findViewById(R.id.title1);
        title2 = findViewById(R.id.title2);
        title3 = findViewById(R.id.title3);
        title4 = findViewById(R.id.title4);
        AnimatorSet ans = new AnimatorSet();

        //Logo
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(imgLogo, "translationY", 0f, 100f);
        anim1.setDuration(1000);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(imgLogo, "alpha", 0f, 1f);
        anim2.setDuration(1000);
        ans.play(anim1).with(anim2);

        //Title1
        ObjectAnimator anim3 = ObjectAnimator.ofFloat(title1, "translationX", -100f, 0f);
        anim1.setDuration(2500);
        ObjectAnimator anim4 = ObjectAnimator.ofFloat(title1, "alpha", 0f, 1f);
        anim2.setDuration(2500);
        ans.play(anim3).with(anim4);

        //Title2
        ObjectAnimator anim5 = ObjectAnimator.ofFloat(title2, "translationX", 100f, 0f);
        anim1.setDuration(2500);
        ObjectAnimator anim6 = ObjectAnimator.ofFloat(title2, "alpha", 0f, 1f);
        anim2.setDuration(2500);
        ans.play(anim5).with(anim6);

        //Title3
        ObjectAnimator anim7 = ObjectAnimator.ofFloat(title3, "translationX", -100f, 0f);
        anim1.setDuration(2500);
        ObjectAnimator anim8 = ObjectAnimator.ofFloat(title3, "alpha", 0f, 1f);
        anim2.setDuration(2500);
        ans.play(anim7).with(anim8);

        //Title4
        ObjectAnimator anim9 = ObjectAnimator.ofFloat(title4, "translationX", 100f, 0f);
        anim1.setDuration(2500);
        ObjectAnimator anim10 = ObjectAnimator.ofFloat(title4, "alpha", 0f, 1f);
        anim2.setDuration(2500);
        ans.play(anim9).with(anim10);

        //Play
        ans.start();

        //StartActivity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        }, 3500);
    }
}