package org.easy.android.vp;


import android.os.Bundle;

import android.support.annotation.Nullable;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;


import org.easy.android.R;



/**
 * author : jyh
 * date : 2019/4/11
 * qq : 84714581
 * https://github.com/ZengChong500373
 * describe :
 */
public class BannerActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        initView();

    }

    private void initView() {
    }

    private void initData() {

    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        return false;
    }
}
