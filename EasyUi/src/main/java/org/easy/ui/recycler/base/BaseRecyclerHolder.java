package org.easy.ui.recycler.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.easy.ui.recycler.listener.ItemClickListener;
import org.easy.ui.recycler.listener.ItemLongClickListener;


/**
 * Created by jyh on 2016/9/12.
 *
 */
public class BaseRecyclerHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
    private ItemClickListener mListener = null;
    private ItemLongClickListener mLongClickListener = null;

    public BaseRecyclerHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (mListener != null) {
            mListener.onItemClick(view, getAdapterPosition());
        }
    }

    @Override
    public boolean onLongClick(View view) {
        if (mLongClickListener != null) {
            mLongClickListener.onItemLongClick(view, getAdapterPosition());
        }
        return true;
    }

    public void setItemListener(ItemClickListener mListener) {
        this.mListener = mListener;
    }

    public void setItemListener(ItemLongClickListener mListener) {
        this.mLongClickListener = mListener;
    }


    public ItemClickListener getItemListener() {
        return this.mListener;
    }

}
