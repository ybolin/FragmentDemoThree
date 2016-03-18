package com.ybl.fragmentdemothree;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements View.OnClickListener {
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    ImageView imageView;
    ViewPager mViewPager;
    List<Fragment> fragmentList;
    FragmentOne fragmentOne;
    FragmentTwo fragmentTwo;
    FragmentThree fragmentThree;
    FragmentFour fragmentFour;

    int screenWidth;
    int currenttab = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn1.setOnClickListener(this);
        btn2 .setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        imageView = (ImageView) findViewById(R.id.img);
        mViewPager = (ViewPager) findViewById(R.id.mViewPager);

        screenWidth = getResources().getDisplayMetrics().widthPixels;
        fragmentList = new ArrayList<Fragment>();
        fragmentOne = new FragmentOne();
        fragmentTwo = new FragmentTwo();
        fragmentThree = new FragmentThree();
        fragmentFour = new FragmentFour();
        fragmentList.add(fragmentOne);
        fragmentList.add(fragmentTwo);
        fragmentList.add(fragmentThree);
        fragmentList.add(fragmentFour);

        btn2.measure(0, 0);
        RelativeLayout.LayoutParams imgParams = new RelativeLayout.LayoutParams(screenWidth/4,btn2.getMeasuredHeight());
        imgParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        imageView.setLayoutParams(imgParams);
        mViewPager.setAdapter(new MyFragmentAdapter(getSupportFragmentManager()));



    }
    public class MyFragmentAdapter extends FragmentStatePagerAdapter{

        public MyFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public void finishUpdate(ViewGroup container) {
            super.finishUpdate(container);
            int currentItem = mViewPager.getCurrentItem();
            if (currentItem == currenttab){
                return;
            }
            imageMove(mViewPager.getCurrentItem());
            currenttab = mViewPager.getCurrentItem();
        }
    }

    private void imageMove(int moveToTab){
        int startPosition = 0;
        int moveposition = 0;
        startPosition = currenttab*(screenWidth/4);
        moveposition = currenttab*(screenWidth/4);
        TranslateAnimation translateAnimation = new TranslateAnimation(startPosition,moveposition,0,0);
        translateAnimation.setFillAfter(true);
        translateAnimation.setDuration(200);
        imageView.startAnimation(translateAnimation);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1:
                changeView(1);
                break;
            case R.id.btn2:
                changeView(2);
                break;
            case R.id.btn3:
                changeView(3);
                break;
            case R.id.btn4:
                changeView(4);
                break;

        }

    }
    private void changeView(int destab){
        mViewPager.setCurrentItem(destab,true);
    }
}
