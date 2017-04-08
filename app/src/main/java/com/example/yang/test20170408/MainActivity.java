package com.example.yang.test20170408;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final static String TAG="TAG";

    private float displayW;//屏幕宽度
    private TextView tvLine;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化屏幕宽度   displayW
        iniDisplayWitch();
        //找到动画横线
        tvLine=(TextView)findViewById(R.id.textView4);
        //初始化viewPager
        iniViewPager();
        //为ViewPager增加监听
        addListense();
    }

    private void addListense() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            private int preIndex=0;

            /**
             * 这里一个小算法，从上一次的位置平移到这次的位置，应该不难，就省略了
             */
            @Override
            public void onPageSelected(int position) {
                Animation animation=new TranslateAnimation(preIndex*(displayW/3),position*(displayW/3),0,0);//平移动画，每次平移屏幕宽度的三分之一
                animation.setDuration(200);//设置动画平移时间
                animation.setFillAfter(true);//动画完成后保持在动画最后位置
                preIndex=position;
                tvLine.startAnimation(animation);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void iniViewPager() {
        viewPager=(ViewPager)findViewById(R.id.viewPager);
        viewPager.setAdapter(new MyFragmentAdapter(getSupportFragmentManager()));
    }

    private void iniDisplayWitch() {
        DisplayMetrics metrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        displayW=metrics.widthPixels;//屏幕看度像素
        Log.i(TAG,displayW+"屏幕宽度");
    }

    class MyFragmentAdapter extends FragmentPagerAdapter{

        private List<Fragment> mData;

        public MyFragmentAdapter(FragmentManager fm) {
            super(fm);
            if(mData==null){
                mData=new ArrayList<>();
                mData.add(new BlankFragment1());
                mData.add(new BlankFragment2());
                mData.add(new BlankFragment3());
            }
        }

        @Override
        public Fragment getItem(int position) {
            return mData.get(position);
        }

        @Override
        public int getCount() {
            return mData.size();
        }
    }
}
