package com.example.andynile.sensor;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class SensorActivity extends Activity {

    Button btn;
    int year_x,month_x,day_x;
    static final int DIALOG_ID =0;
    TextView textView;
    String info;
    String data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_sensor);

        TextView Name =(TextView) findViewById(R.id.Name);

        Intent intent =getIntent();
        String name= intent.getStringExtra("name");
        Name.setText(name);

        final Calendar cal = Calendar.getInstance();
        year_x = cal.get(Calendar.YEAR);
        month_x= cal.get(Calendar.MONTH);
        day_x=cal.get(Calendar.DAY_OF_MONTH);
        textView = (TextView) findViewById(R.id.info);
        btn =(Button) findViewById(R.id.select);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                showDialog(DIALOG_ID );

            }}
        );

        final Button out = (Button) findViewById(R.id.out);

        out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                Intent signout = new Intent(SensorActivity.this,LoginActivity.class);
                SensorActivity.this.startActivity(signout);


            }
        });


    }

    @Override
    protected Dialog onCreateDialog(int id){
        if (id ==DIALOG_ID)
            return  new DatePickerDialog(this,dpickerListner,year_x,month_x,day_x);
        return null;
    }

    private DatePickerDialog.OnDateSetListener dpickerListner
            = new DatePickerDialog.OnDateSetListener() {


        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
            year_x =year;
            month_x=monthOfYear+1;
            day_x=dayOfMonth;


            Toast.makeText(SensorActivity.this,"Selected Date is: "+ year_x+ "/" +month_x+"/"+day_x,Toast.LENGTH_LONG).show();

            String Date1=year_x+"-"+month_x+"-"+day_x;

            Response.Listener<String> responseListener=new Response.Listener<String>(){
                @Override
                public void onResponse(String response) {
                    try{
                        JSONObject jsonResponse =new JSONObject(response);
                        boolean success =jsonResponse.getBoolean("success");
                        if(success){
                            info=jsonResponse.getString("info");
                            data=jsonResponse.getString("data");
                        }else{

                            info="Data for this date is not available";
                            data="Data for this date is not available";

                        }

                    }catch(JSONException e){
                        e.printStackTrace();
                    }
                }
            };

            date date2 =new date(Date1,responseListener);
            RequestQueue queue= Volley.newRequestQueue(SensorActivity.this);
            queue.add(date2);

        }
    };

    public void Temperature(View view)
    {
        textView.setText(info);
    }

    public void Accelerometer(View view)
    {
        textView.setText(data);
    }

}
