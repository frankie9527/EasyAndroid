package org.easy.android.recycler.simple;

import androidx.annotation.NonNull;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.easy.android.R;
import org.easy.tools.utils.UiUtils;
import org.easy.ui.recycler.base.BaseRecyclerAdapter;
import org.easy.ui.recycler.base.BaseRecyclerHolder;

/**
 * author : jyh
 * date : 2019/4/15
 * qq : 84714581
 * https://github.com/ZengChong500373
 * describe :
 */
public class SimpleAdapter extends BaseRecyclerAdapter<Integer, SimpleAdapter.MyHolder> {
    private int type = 0;

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recycler_item, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void MyHolder(@NonNull MyHolder holder, int position) {
        super.MyHolder(holder, position);
        int myType = getType();
        ViewGroup.LayoutParams params = null;
        switch (myType) {
            case 0:
                params = holder.rl_parent.getLayoutParams();
                params.width = ViewGroup.LayoutParams.MATCH_PARENT;
                params.height = UiUtils.dp2px(230);
                break;
            case 1:
                params = holder.rl_parent.getLayoutParams();
                params.width = ViewGroup.LayoutParams.MATCH_PARENT;
                params.height = UiUtils.dp2px(180);
                break;
        }
        holder.rl_parent.setLayoutParams(params);
        holder.image.setImageResource(list.get(position));
        String str="this is "+position+"item";
        holder.tv.setText(str);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
        notifyDataSetChanged();
    }

    class MyHolder extends BaseRecyclerHolder {
        RelativeLayout rl_parent;
        ImageView image;
        TextView tv;

        private MyHolder(View itemView) {
            super(itemView);
            rl_parent = itemView.findViewById(R.id.rl_parent);
            image = itemView.findViewById(R.id.image);
            tv = itemView.findViewById(R.id.tv);

            /**
             * 对布局指定控件监听
             * */
            tv.setOnClickListener(this);
            tv.setOnLongClickListener(this);
        }
    }
}
