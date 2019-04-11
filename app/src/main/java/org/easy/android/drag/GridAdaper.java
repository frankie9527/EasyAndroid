package org.easy.android.drag;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.easy.android.App;
import org.easy.android.R;
import org.easy.ui.dragview.DragGridAdapter;

/**
 * author：jyh
 * QQ：847145851
 * time：2019/4/2
 * https://github.com/ZengChong500373
 * describe：
 **/
public class GridAdaper extends DragGridAdapter<String> {
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(App.getContext(), R.layout.drag_grid_item, null);
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
