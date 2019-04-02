package org.easy.android.drag;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.easy.android.App;
import org.easy.android.R;
import org.easy.ui.dragview.DragListAdapter;

/**
 * author：jyh
 * QQ：847145851
 * time：2019/4/2
 * describe：
 **/
public class ListAdapter extends DragListAdapter<String> {
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(App.getContext(), R.layout.drag_list_item, null);
            holder = new ViewHolder();
            holder.item = (TextView) convertView.findViewById(R.id.tv_item);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.item.setText((String) list.get(position));
        return convertView;
    }
    class ViewHolder {
        TextView item;
    }
}
