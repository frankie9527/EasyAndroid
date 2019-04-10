package org.easy.android;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.easy.tools.utils.ToastUtils;
import org.easy.ui.StatusControlLayout;

/**
 * author：jyh
 * QQ：847145851
 * time：2019/4/10
 * describe：
 **/
public class StatusControlActivity extends AppCompatActivity {
    StatusControlLayout control_layout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_control);
        initView();
    }

    private void initView() {
        control_layout=findViewById(R.id.control_layout);
        control_layout.setOnRetryListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.getInstance().show("onClick");
            }
        });
    }



    public void empty(View view){
        control_layout.showEmpty();
    }
    public void error(View view){
        control_layout.showError();
    }
    public void loading(View view){
        control_layout.showLoading();
    }
    public void noNet(View view){
        control_layout.showNoNet();
    }
    public void success(View view){

    }
}