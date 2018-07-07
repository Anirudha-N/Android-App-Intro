package com.example.andynile.sensor;

/**
 * Created by AndyNile on 22/09/16.
 */

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class date extends StringRequest {

    private static final String URL="http://sensor.esy.es/anya/ret1.php";
    private Map<String,String> params;

    public date(String date1, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        params =new HashMap<>();
        params.put("date",date1);

    }

    @Override
    public Map<String,String>  getParams(){

        return params;
    }

}

