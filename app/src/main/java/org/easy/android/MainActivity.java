package org.easy.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import org.easy.android.drag.DragViewActivity;
import org.easy.android.vp.ViewPagerActivity;

/**
 * 怎么去创建自己的 依赖库
 * https://blog.csdn.net/seevc/article/details/81485482
 * */
public class MainActivity extends AppCompatActivity {
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void drag(View view) {
        intent=new Intent(this, DragViewActivity.class);
        startActivity(intent);
    }
    public void slide(View view) {
        intent=new Intent(this, SlidingActivity.class);
        startActivity(intent);
    }
    public void statusControl(View view) {
        intent=new Intent(this, StatusControlActivity.class);
        startActivity(intent);
    }
    public void vp(View view) {
        intent=new Intent(this, ViewPagerActivity.class);
        startActivity(intent);
    }
}
