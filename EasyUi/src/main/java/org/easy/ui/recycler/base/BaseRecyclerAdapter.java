package org.easy.ui.recycler.base;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;


import org.easy.ui.recycler.listener.ItemClickListener;
import org.easy.ui.recycler.listener.ItemLongClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * author : jyh
 * date : 2019/4/15
 * qq : 84714581
 * https://github.com/ZengChong500373
 * describe :
 */
public abstract class BaseRecyclerAdapter<D, H extends BaseRecyclerHolder> extends RecyclerView.Adapter<H> {
    protected List<D> list = null;
    protected ItemClickListener itemListener;

    public void setItemListener(ItemClickListener mListener) {
        this.itemListener = mListener;
    }

    protected ItemLongClickListener longListener;

    public void setLongListener(ItemLongClickListener mListener) {
        this.longListener = mListener;
    }

    public BaseRecyclerAdapter() {
        this.list = new ArrayList<>();
    }


    @Override
    public void onBindViewHolder(@NonNull H holder, int i) {
        if (itemListener!=null){
            holder.setItemListener(itemListener);
        }
        if (longListener!=null){
            holder.setItemListener(longListener);
        }
        MyHolder(holder,i);
    }
    public void MyHolder(@NonNull H holder,int position){

    }

    public void setData(List<D> data) {
        list.addAll(data);
        notifyDataSetChanged();
    }

    public void setDataInEnd(D data) {
        list.add(data);
        notifyDataSetChanged();
    }

    public void clear() {
        list.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
