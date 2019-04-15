package org.easy.android.recycler;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

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
public class RecyclerDemoAdapter extends BaseRecyclerAdapter<Integer, RecyclerDemoAdapter.MyHolder> {
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
                params = holder.ll_parent.getLayoutParams();
                params.width = ViewGroup.LayoutParams.MATCH_PARENT;
                params.height = UiUtils.dp2px(230);
                break;
            case 1:
                params = holder.ll_parent.getLayoutParams();
                params.width = ViewGroup.LayoutParams.MATCH_PARENT;
                params.height = UiUtils.dp2px(180);
                break;
        }
        holder.ll_parent.setLayoutParams(params);
        holder.image.setImageResource(list.get(position));
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
        notifyDataSetChanged();
    }

    class MyHolder extends BaseRecyclerHolder {
        LinearLayout ll_parent;
        ImageView image;

        private MyHolder(View itemView) {
            super(itemView);
            ll_parent=itemView.findViewById(R.id.ll_parent);
            image = itemView.findViewById(R.id.image);
        }
    }
}
