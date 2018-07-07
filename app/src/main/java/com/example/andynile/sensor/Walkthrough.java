package com.example.andynile.sensor;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Walkthrough extends AppCompatActivity {

    private ViewPager viewPager;
    private Intromanager intromanager;
    private TextView[] dots;
    private ViewPageAdapter viewPageAdapter;
    Button sign,login;
    private LinearLayout dotsLayout;
    private int[] layouts;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intromanager = new Intromanager(this);
        if (!intromanager.Check()) {
            intromanager.setFirst(false);
            Intent i = new Intent(Walkthrough.this, LoginActivity.class);
            startActivity(i);
            finish();
        }

        if(Build.VERSION.SDK_INT>=21)
        {

            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_FULLSCREEN);
        }




        setContentView(R.layout.activity_walkthrough);
        viewPager=(ViewPager) findViewById(R.id.view_pager);
        dotsLayout=(LinearLayout)findViewById(R.id.layoutDots);
        login=(Button)findViewById(R.id.btn_login);
        sign=(Button) findViewById(R.id.btn_sign);


        layouts= new int[]{R.layout.activity_screen_1,R.layout.activity_screen_6,R.layout.activity_screen_3,
                R.layout.activity_screen_4,R.layout.activity_screen_5};

        addBottomDots(0);
        changeStatusBarColor();
        viewPageAdapter=new ViewPageAdapter();
        viewPager.setAdapter(viewPageAdapter);
        viewPager.addOnPageChangeListener(viewListener);




        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Walkthrough.this, LoginActivity.class);
                startActivity(i);
                finish();


            }
        });

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Walkthrough.this, RegisterActivity.class);
                startActivity(i);
                finish();

            }
        });



    }

    private void addBottomDots(int position)
    {

        dots= new TextView[layouts.length];
        int[] colorActive =getResources().getIntArray(R.array.dot_active);
        int[] colorInactive =getResources().getIntArray(R.array.dot_inactive);
        dotsLayout.removeAllViews();
        for (int i=0;i<dots.length;i++)
        {
            dots[i]=new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorInactive[position]);
            dotsLayout.addView(dots[i]);

        }
        if(dots.length>0) {
            dots[position].setTextColor(colorActive[position]);
        }
    }

    private int getItem(int i)
    {

        return viewPager.getCurrentItem() +i;
    }




    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            addBottomDots(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private void changeStatusBarColor()
    {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP)
        {

            Window window=getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }


    }



    public class ViewPageAdapter extends PagerAdapter
    {
        private LayoutInflater layoutInflater;

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater=(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v =layoutInflater.inflate(layouts[position],container,false);
            container.addView(v);
            return v;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;

        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View v =(View)object;
            container.removeView(v);
        }
    }

}