package org.easy.android.recycler.simple;


import android.os.Bundle;

import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;


import org.easy.android.R;
import org.easy.tools.utils.ToastUtils;
import org.easy.tools.utils.UiUtils;
import org.easy.ui.recycler.RxRecyclerViewDividerTool;
import org.easy.ui.recycler.listener.ItemClickListener;
import org.easy.ui.recycler.listener.ItemLongClickListener;

import java.util.ArrayList;
import java.util.List;


/**
 * author : jyh
 * date : 2019/4/11
 * qq : 84714581
 * https://github.com/ZengChong500373
 * describe :
 */
public class RecyclerActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener, ItemClickListener, ItemLongClickListener {
    Toolbar toolbar;
    RecyclerView recycler;
    SimpleAdapter adapter;
    RecyclerView.LayoutManager manager;
    List<Integer> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        initView();
        initData();
    }


    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu_recycler);
        toolbar.setOnMenuItemClickListener(this);
        recycler = findViewById(R.id.recycler);
        manager = new LinearLayoutManager(this);
        recycler.setLayoutManager(manager);
        adapter = new SimpleAdapter();
        recycler.setAdapter(adapter);
        recycler.addItemDecoration(new RxRecyclerViewDividerTool(0, 0, UiUtils.dp2px(1), 0));
        adapter.setItemListener(this);
        adapter.setLongListener(this);
    }

    private void initData() {
        list.add(R.drawable.item1);
        list.add(R.drawable.item2);
        list.add(R.drawable.item3);
        list.add(R.drawable.item4);
        list.add(R.drawable.item5);
        list.add(R.drawable.item6);
        list.add(R.drawable.item7);
        list.add(R.drawable.item8);
        list.add(R.drawable.item9);
        list.add(R.drawable.item10);
        adapter.setData(list);
    }


    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        int id = menuItem.getItemId();
        if (id == R.id.action_linear) {
            manager = new LinearLayoutManager(this);
            recycler.setLayoutManager(manager);
            recycler.addItemDecoration(new RxRecyclerViewDividerTool(0, 0, UiUtils.dp2px(1), 0));
            adapter.setType(0);
        } else if (id == R.id.action_grid) {
            manager = new GridLayoutManager(this, 3);
            recycler.setLayoutManager(manager);
            recycler.addItemDecoration(new RxRecyclerViewDividerTool(1, 1, UiUtils.dp2px(1), 0));
            adapter.setType(1);
        }
        return false;
    }

    @Override
    public void onItemClick(View view, int postion) {
        if (view.getId() == R.id.tv) {
            ToastUtils.getInstance().show("tv postion=" + postion);
        } else {
            ToastUtils.getInstance().show("父布局 postion=" + postion);
        }

    }


    @Override
    public void onItemLongClick(View view, int postion) {
        if (view.getId() == R.id.tv) {
            ToastUtils.getInstance().show("tv LongClick postion=" + postion);
        } else {
            ToastUtils.getInstance().show("父布局 LongClick postion=" + postion);
        }

    }
}
