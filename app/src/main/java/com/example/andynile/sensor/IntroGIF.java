package com.example.andynile.sensor;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebView;


public class IntroGIF extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_intro_gif);
        WebView view=(WebView) findViewById(R.id.myWebView);
        view.loadUrl("file:///android_asset/name.gif");

        Thread myThread =new Thread(){
            @Override
            public  void run(){

                try {
                    sleep(10000);
                    Intent intent =new Intent(getApplicationContext(),Walkthrough.class);
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
