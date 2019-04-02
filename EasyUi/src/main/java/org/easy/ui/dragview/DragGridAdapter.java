package org.easy.ui.dragview;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;




import java.util.List;

/**
 * Created by jyh on 2016/7/21.
 */
public class DragGridAdapter<T> extends BaseAdapter {

    protected List<T> list;


    @Override
    public int getCount() {
        if (list == null) return 0;
        return list.size();
    }

    @Override
    public T getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }


    public void setdata(List list) {
        this.list = list;
        notifyDataSetChanged();
    }



    public void exchangePosition(int onDragPosition, int destnation_position) {
        T onDrag_data = list.get(onDragPosition);
        T finger_data = list.get(destnation_position);
        list.remove(onDragPosition);
        list.add(onDragPosition, finger_data);
        list.remove(destnation_position);
        list.add(destnation_position, onDrag_data);



        notifyDataSetChanged();
    }
}
