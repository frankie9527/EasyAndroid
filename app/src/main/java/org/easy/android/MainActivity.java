package org.easy.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import org.easy.android.drag.DragViewActivity;
/**
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
}
