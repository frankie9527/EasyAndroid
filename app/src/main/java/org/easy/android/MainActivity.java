package org.easy.android;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import org.easy.android.drag.DragViewActivity;
import org.easy.android.recycler.multiple.MultipleRecyclerActivity;
import org.easy.android.recycler.simple.RecyclerActivity;
import org.easy.android.vp.ViewPagerActivity;

/**
 * 怎么去创建自己的 依赖库
 * https://blog.csdn.net/seevc/article/details/81485482
 *
 * https://juejin.im/entry/5cb3e0095188251b056a14bd
 * */
public class MainActivity extends AppCompatActivity {
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
/**
 * 拖动的listview 和grideview
 *
 * */
    public void drag(View view) {
        intent=new Intent(this, DragViewActivity.class);
        startActivity(intent);
    }
    /**
     * 侧拉栏
     *
     * */
    public void slide(View view) {
        intent=new Intent(this, SlidingActivity.class);
        startActivity(intent);
    }
    /**
     * 布局控制器，控制布局显示空视图 加载视图  加载成功视图
     *
     * */
    public void statusControl(View view) {
        intent=new Intent(this, StatusControlActivity.class);
        startActivity(intent);
    }
    /**
     * vp 滑动特效
     *
     * */
    public void vp(View view) {
        intent=new Intent(this, ViewPagerActivity.class);
        startActivity(intent);
    }
    /**
     * 简单 recycler
     *
     * */
    public void simpleRecycler(View view) {
        intent=new Intent(this, RecyclerActivity.class);
        startActivity(intent);
    }
    /**
     * 加载不同布局
     * recycler
     * */
    public void multipleRecycler(View view){
        intent=new Intent(this, MultipleRecyclerActivity.class);
        startActivity(intent);
    }
}
