package org.easy.android.recycler.multiple;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.easy.android.R;
import org.easy.android.recycler.simple.SimpleAdapter;
import org.easy.tools.utils.UiUtils;
import org.easy.ui.recycler.RxRecyclerViewDividerTool;

import java.util.ArrayList;
import java.util.List;

/**
 * author : jyh
 * date : 2019-09-06
 * qq : 84714581
 * https://github.com/ZengChong500373
 * describe : 加载不同布局
 */
public class MultipleRecyclerActivity extends AppCompatActivity {
    RecyclerView recycler;

    RecyclerView.LayoutManager manager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        initView();
        iniData();
    }



    private void initView() {

        recycler=findViewById(R.id.recycler);
        manager=new LinearLayoutManager(this);
        recycler.setLayoutManager(manager);

        recycler.addItemDecoration(new RxRecyclerViewDividerTool(0,0, UiUtils.dp2px(1),0));

    }
    private void iniData() {

    }
}
