package com.example.andynile.sensor;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by AndyNile on 30/08/16.
 */

public class RegisterRequest2 extends StringRequest {


    //private static final String REGISTER_REQUEST_URL ="http://192.168.0.106/anya/login.php";
    private static final String REGISTER_REQUEST_URL ="http://sensor.esy.es/anya/login.php";
    private Map<String,String> params;

    public RegisterRequest2(String username, String password, Response.Listener<String> listener) {

        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);


    }

        @Override
        public Map<String,String>  getParams(){

            return params;
        }



}
