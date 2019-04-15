package org.easy.android.vp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

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
import org.easy.ui.viewpager.transforms2.AlphaPageTransformer;
import org.easy.ui.viewpager.transforms2.NonPageTransformer;
import org.easy.ui.viewpager.transforms2.RotateDownPageTransformer;
import org.easy.ui.viewpager.transforms2.RotateUpPageTransformer;
import org.easy.ui.viewpager.transforms2.RotateYTransformer;
import org.easy.ui.viewpager.transforms2.ScaleInTransformer;

import java.util.HashMap;
import java.util.Map;

/**
 * author : jyh
 * date : 2019/4/11
 * qq : 84714581
 * https://github.com/ZengChong500373
 * describe : viewpager 各种滑动特效
 */
public class ViewPagerActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener {
    Toolbar toolbar;
    ViewPager vp,vp2;
    DemoAdapter adapter,adapter2;
    Map<String, ViewPager.PageTransformer> map = new HashMap<>();
    Map<String, ViewPager.PageTransformer> map2 = new HashMap<>();
    RadioGroup rg;
    private TextView tv_title,tv_title2;
    private int checkType=0;
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

        map2.put("RotateDown",new RotateDownPageTransformer());
        map2.put("RotateUp",new RotateUpPageTransformer());
        map2.put("RotateY", new RotateYTransformer(45));
        map2.put("Standard", NonPageTransformer.INSTANCE);
        map2.put("Alpha",new AlphaPageTransformer());
        map2.put("ScaleIn",new ScaleInTransformer());
        map2.put("RotateDown and Alpha",new RotateDownPageTransformer(new AlphaPageTransformer()));
        map2.put("RotateDown and Alpha And ScaleIn",new RotateDownPageTransformer(new AlphaPageTransformer(new ScaleInTransformer())));
        tv_title=findViewById(R.id.tv_title);
        tv_title2=findViewById(R.id.tv_title2);
    }

    private void initViews() {

        toolbar = findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu_viewpager_trans);
        toolbar.setOnMenuItemClickListener(this);


        vp = findViewById(R.id.vp);
        adapter = new DemoAdapter(getSupportFragmentManager());
        vp.setAdapter(adapter);

        vp2=findViewById(R.id.vp2);
        vp2.setPageMargin(40);
        vp2.setOffscreenPageLimit(3);
        adapter2 = new DemoAdapter(getSupportFragmentManager());
        vp2.setAdapter(adapter2);

        rg= findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.up:
                        toolbar.getMenu().clear();
                        toolbar.inflateMenu(R.menu.menu_viewpager_trans);
                        checkType=0;
                        break;
                    case R.id.down:
                        toolbar.getMenu().clear();
                        toolbar.inflateMenu(R.menu.menu_viewpager_trans2);
                        checkType=1;
                        break;
                }
            }
        });
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        String str = (String) menuItem.getTitle();
            ViewPager.PageTransformer transformer=  null;
            if ( checkType==0){
                transformer=map.get(str);
                vp.setPageTransformer(true, transformer);
                tv_title.setText(str);
            }else {
                transformer=map2.get(str);
                vp2.setPageTransformer(true, transformer);
               vp2.setAdapter(adapter2);
                tv_title2.setText(str);
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
