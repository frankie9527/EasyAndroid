package org.easy.android.drag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import org.easy.android.R;
import org.easy.ui.dragview.DragGridview;
import org.easy.ui.dragview.DragListView;


import java.util.ArrayList;
import java.util.List;

/**
 * author：jyh
 * QQ：847145851
 * time：2019/4/2
 * describe：
 **/
public class DragViewActivity extends AppCompatActivity {
    private FrameLayout frame_parent;
    private List<String> list;
    private DragListView lv=null;
    private DragGridview gd=null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        frame_parent = findViewById(R.id.frame_container);
        initData();
        initGrid();
    }
    private void initData() {
        list = new ArrayList<>();
        for (int i = 0; i < 300; i++) {
            list.add("the " + i + " item");
        }
    }
    public void initList() {
        lv=new DragListView(this);
        frame_parent.removeAllViews();
        frame_parent.addView(lv);
        ListAdapter  adapter = new ListAdapter();
        lv.setAdapter(adapter);
        adapter.setdata(list);
        lv.initActionBarHeight(this);

    }

    public void initGrid() {
        gd=new DragGridview(this);
        gd.setNumColumns(3);
        frame_parent.removeAllViews();
        frame_parent.addView(gd);
        GridAdaper  adapter = new GridAdaper();
        gd.setAdapter(adapter);
        adapter.setdata(list);
        gd.initActionBarHeight(this);
    }
}
