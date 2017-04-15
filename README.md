# Test20170408
一个简单平移动画

http://blog.csdn.net/qq_34557284/article/details/69831516
 一. 先上效果图

![这里写图片描述](http://img.blog.csdn.net/20170409163805766?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvcXFfMzQ1NTcyODQ=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

![这里写图片描述](http://img.blog.csdn.net/20170409163819141?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvcXFfMzQ1NTcyODQ=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

主要就是小横线的一个平滑滑动，比较简单。下面我说下实现步骤。

二. 实现步骤
1. 先写好布局（布局请看下面源码，这里我就不贴了。）
2. 得到当前屏幕宽度：
        DisplayMetrics metrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        displayW=metrics.widthPixels;//屏幕看度像素
 3.  找到布局中的线，我这里用一个TextView实现的。
		tvLine=(TextView)findViewById(R.id.textView4);
 4. 初始化viewPager
		  viewPager=(ViewPager)findViewById(R.id.viewPager);
        viewPager.setAdapter(new MyFragmentAdapter(getSupportFragmentManager()));
        这里自定义了Adaptr，请看下面代码
 5. 主要动画实现在这里，为viewPager加监听
		 

```
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
```


三.源码

 1. 新建项目，下面为主布局代码：
 

```
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.example.yang.test20170408.MainActivity">

    <TextView
        android:id="@+id/textView3"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:text="米老鼠"
        android:padding="10dp"
        android:textSize="18sp"
        android:gravity="center"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toLeftOf="@+id/guideline2"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintHorizontal_bias="0.0" />

    <android.support.constraint.Guideline
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:id="@+id/guideline"
        android:orientation="vertical"
        app:layout_constraintGuide_percent="0.67"
        tools:layout_editor_absoluteY="0dp"
        tools:layout_editor_absoluteX="258dp" />

    <android.support.constraint.Guideline
        android:id="@+id/guideline2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        app:layout_constraintGuide_percent="0.33"
        tools:layout_editor_absoluteY="0dp"
        tools:layout_editor_absoluteX="127dp" />

    <TextView
        android:id="@+id/textView1"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:padding="10dp"
        android:textSize="18sp"
        android:gravity="center"
        android:text="唐老鸭"
        app:layout_constraintLeft_toLeftOf="@+id/guideline2"
        app:layout_constraintRight_toLeftOf="@+id/guideline"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintHorizontal_bias="0.0" />

    <TextView
        android:id="@+id/textView2"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:padding="10dp"
        android:textSize="18sp"
        android:gravity="center"
        android:text="坏蛋"
        app:layout_constraintLeft_toLeftOf="@+id/guideline"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintHorizontal_bias="0.0" />

    <TextView
        android:id="@+id/textView4"
        android:layout_width="0dp"
        android:layout_height="5dp"
        app:layout_constraintRight_toLeftOf="@+id/guideline2"
        android:background="#ff55ff"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/textView3" />

    <android.support.v4.view.ViewPager
        android:id="@+id/viewPager"
        android:layout_width="0dp"
        android:layout_height="0dp"
        app:layout_constraintLeft_toLeftOf="parent"
        android:layout_marginLeft="0dp"
        android:layout_marginRight="0dp"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintHorizontal_bias="0.0"
        app:layout_constraintBottom_toBottomOf="parent"
        android:layout_marginBottom="0dp"
        android:layout_marginTop="0dp"
        app:layout_constraintTop_toBottomOf="@+id/textView4">

    </android.support.v4.view.ViewPager>


</android.support.constraint.ConstraintLayout>
```

 

2. 新建三个Fragment ，Fragment比较简单，就不贴了。
3. 下面上主布局代码
	

```
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

```

