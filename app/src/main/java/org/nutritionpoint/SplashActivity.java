package org.nutritionpoint;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import org.tensorflow.lite.examples.detection.R;


public class SplashActivity extends AppCompatActivity {
    Handler splashHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splashHandler.postDelayed(splashRunnable, 2000);
    }

    //Create Runnable object r
    Runnable splashRunnable = new Runnable() {
        @Override
        public void run() {
            try{
                //start NavActivity
                Intent i = new Intent(SplashActivity.this, LoginPage.class);
                startActivity(i);
                //close SplashActivity
                finish();
            }catch (Exception e){}
        }
    };
}
