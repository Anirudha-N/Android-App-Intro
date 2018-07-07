package com.example.andynile.sensor;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);

        final EditText Name =(EditText) findViewById(R.id.Name);
        final EditText Username =(EditText) findViewById(R.id.Username);
        final EditText Password =(EditText) findViewById(R.id.Password);
        final Button signup = (Button) findViewById(R.id.Sign);

       signup.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               final String name=Name.getText().toString();
               final String username=Username.getText().toString();
               final String password=Password.getText().toString();




               Response.Listener<String> responseListener=new Response.Listener<String>(){
                 @Override
                 public void onResponse(String response) {

                  try{

                     JSONObject jsonResponse =new JSONObject(response);

                     boolean success =jsonResponse.getBoolean("success");

                     if(success){
                         Name.getText().clear();
                         Username.getText().clear();
                         Password.getText().clear();
                         Toast.makeText(RegisterActivity.this,"Registration Successful",Toast.LENGTH_LONG).show();
                         RegisterActivity.this.finish();

                     }else{
                         AlertDialog.Builder builder =new AlertDialog.Builder(RegisterActivity.this);
                         Name.getText().clear();
                         Username.getText().clear();
                         Password.getText().clear();
                         builder.setMessage("Register Failed")
                                .setNegativeButton("Ok",null)
                                 .create()
                                 .show();
                     }

                   }catch(JSONException e){
                       e.printStackTrace();
                   }
                 }
               };


               RegisterRequest registerRequest =new RegisterRequest(name,username,password,responseListener);
               RequestQueue queue=Volley.newRequestQueue(RegisterActivity.this);
               queue.add(registerRequest);

           }
       });
    }
}
