package org.easy.android.vp;

import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * author : jyh
 * date : 2019/4/11
 * qq : 84714581
 * describe :
 */
public class ViewPagerFragment extends Fragment {
    String [] colors=new String[]{"#9FD661","#3FD0AD","#AC8FEF","#87CEEB","#ff4081"};
    TextView textView;
    public static ViewPagerFragment newInstance(int position) {
        Bundle args = new Bundle();
        ViewPagerFragment fragment = new ViewPagerFragment();
        args.putInt("position", position);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         textView=new TextView(container.getContext());
        textView.setTextColor(Color.parseColor("#ffffff"));
        textView.setGravity(Gravity.CENTER);
        return textView;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        int   current_position = getArguments().getInt("position");
        textView.setBackgroundColor(Color.parseColor(colors[current_position]));
        textView.setText("hello this is the "+current_position+" item");
    }
}
