package com.example.victorbruno.karimaprodutor.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.victorbruno.karimaprodutor.R;
import com.example.victorbruno.karimaprodutor.adapter.TabsAdapter;
import com.example.victorbruno.karimaprodutor.util.SlidingTabLayout;
import com.parse.ParseUser;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private SlidingTabLayout mSlidingTabLayout ;
    private ViewPager mViewPager ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);

        if (ParseUser.getCurrentUser() == null) {

            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);


        } else{



        setContentView(R.layout.activity_main);



        mViewPager = (ViewPager) findViewById(R.id.viewpager_tabs);
        mViewPager.setAdapter(new TabsAdapter(getSupportFragmentManager(), this));

        mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.stl_tabs);
        mSlidingTabLayout.setViewPager(mViewPager);
        mSlidingTabLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        mSlidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.colorAccent));
        mSlidingTabLayout.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    }
}
