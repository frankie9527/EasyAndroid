package org.easy.android.vp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.ToxicBakery.viewpager.transforms.AccordionTransformer;
import com.ToxicBakery.viewpager.transforms.BackgroundToForegroundTransformer;
import com.ToxicBakery.viewpager.transforms.CubeInTransformer;
import com.ToxicBakery.viewpager.transforms.CubeOutTransformer;
import com.ToxicBakery.viewpager.transforms.DefaultTransformer;
import com.ToxicBakery.viewpager.transforms.DepthPageTransformer;
import com.ToxicBakery.viewpager.transforms.DrawerTransformer;
import com.ToxicBakery.viewpager.transforms.FlipHorizontalTransformer;
import com.ToxicBakery.viewpager.transforms.FlipVerticalTransformer;
import com.ToxicBakery.viewpager.transforms.ForegroundToBackgroundTransformer;
import com.ToxicBakery.viewpager.transforms.RotateDownTransformer;
import com.ToxicBakery.viewpager.transforms.ScaleInOutTransformer;
import com.ToxicBakery.viewpager.transforms.StackTransformer;
import com.ToxicBakery.viewpager.transforms.TabletTransformer;
import com.ToxicBakery.viewpager.transforms.ZoomInTransformer;
import com.ToxicBakery.viewpager.transforms.ZoomOutSlideTransformer;
import com.ToxicBakery.viewpager.transforms.ZoomOutTransformer;

import org.easy.android.R;

import java.util.HashMap;
import java.util.Map;

/**
 * author : jyh
 * date : 2019/4/11
 * qq : 84714581
 * https://github.com/ZengChong500373
 * describe : viewpager 各种滑动类型
 */
public class ViewPagerActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener {
    Toolbar toolbar;
    ViewPager vp;
    DemoAdapter adapter;
    Map<String, ViewPager.PageTransformer> map = new HashMap<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        initViews();
        initData();
    }

    private void initData() {
        map.put("Default", new DefaultTransformer());
        map.put("Accordion", new AccordionTransformer());
        map.put("BackgroundToForeground", new BackgroundToForegroundTransformer());
        map.put("CubeIn", new CubeInTransformer());
        map.put("CubeOut", new CubeOutTransformer());
        map.put("DepthPage", new DepthPageTransformer());
        map.put("FlipHorizontal", new FlipHorizontalTransformer());
        map.put("FlipVertical", new FlipVerticalTransformer());
        map.put("ForegroundToBackground", new ForegroundToBackgroundTransformer());
        map.put("RotateDown", new RotateDownTransformer());
        map.put("ScaleInOut", new ScaleInOutTransformer());
        map.put("Stack", new StackTransformer());
        map.put("Tablet", new TabletTransformer());
        map.put("ZoomIn", new ZoomInTransformer());
        map.put("ZoomOutSlide", new ZoomOutSlideTransformer());
        map.put("ZoomOut", new ZoomOutTransformer());
        map.put("Drawer", new DrawerTransformer());
        toolbar.setTitle("Default");
        vp.setPageTransformer(true, map.get("Default"));
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu_viewpager_trans);
        toolbar.setOnMenuItemClickListener(this);
        vp = findViewById(R.id.vp);
        adapter = new DemoAdapter(getSupportFragmentManager());
        vp.setAdapter(adapter);


    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        String str = (String) menuItem.getTitle();
        ViewPager.PageTransformer transformer=  map.get(str);
        if (transformer!=null){
            vp.setPageTransformer(true, transformer);
            toolbar.setTitle(str);
        }
        return false;
    }

    public class DemoAdapter extends FragmentStatePagerAdapter {
        private DemoAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return ViewPagerFragment.newInstance(i);
        }

        @Override
        public int getCount() {
            return 5;
        }
    }
}
