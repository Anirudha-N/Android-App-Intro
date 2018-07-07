package com.example.andynile.sensor;

import android.app.Activity;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

public class LoginActivity extends Activity {

    EditText e_username,e_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        e_username =(EditText) findViewById(R.id.Username);
        e_password =(EditText) findViewById(R.id.Password);

        final Button login;
        login = (Button) findViewById(R.id.Login);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username=e_username.getText().toString();
                String password=e_password.getText().toString();

                Response.Listener<String> responseListener=new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {

                        try{

                            JSONObject jsonResponse =new JSONObject(response);

                            boolean success =jsonResponse.getBoolean("success");

                            if(success){
                                e_username.getText().clear();
                                e_password.getText().clear();
                                String name= jsonResponse.getString("name");
                                Intent intent = new Intent(LoginActivity.this,MainApp.class);
                                intent.putExtra("name",name);
                                startActivity(intent);
                                Toast.makeText(LoginActivity.this,"Username and Password Correct! Login Successful",Toast.LENGTH_LONG).show();

                            }else{
                                e_username.getText().clear();
                                e_password.getText().clear();
                                AlertDialog.Builder builder =new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("Username and Password are invalid").setTitle("Login Failed")
                                        .setNegativeButton("Ok",null)
                                        .create()
                                        .show();
                            }

                        }catch(JSONException e){
                            e.printStackTrace();
                        }
                    }
                };


                RegisterRequest2 registerRequest =new RegisterRequest2(username,password,responseListener);
                RequestQueue queue= Volley.newRequestQueue(LoginActivity.this);
                queue.add(registerRequest);
            }
        });

        final TextView register= (TextView) findViewById(R.id.Goto);

        register.setOnClickListener(new View.OnClickListener() {

         @Override
         public void onClick (View v){
             Intent registerintent = new Intent(LoginActivity.this,RegisterActivity.class);
             LoginActivity.this.startActivity(registerintent);


         }
        });

        File f=new File(Environment.getExternalStorageDirectory()+ File.separator+"Sensor"); //Creating an internal dir;
        if (!f.exists())
        {
            f.mkdirs();
        }
        File ff=new File(f,"andy.txt");
        try {
            ff.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
