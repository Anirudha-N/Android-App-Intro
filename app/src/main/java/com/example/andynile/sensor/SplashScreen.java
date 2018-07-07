package com.example.andynile.sensor;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash_screen);


        Thread myThread =new Thread(){
            @Override
            public  void run(){

                  try {
                      sleep(4000);
                      Intent intent =new Intent(getApplicationContext(),IntroGIF.class);
                      startActivity(intent);
                      finish();

                  }catch (InterruptedException e){
                      e.printStackTrace();
                  }
            }
        };
        myThread.start();
    }
}



