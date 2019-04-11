package org.easy.ui.dragview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import org.easy.tools.utils.ThreadManager;
import org.easy.ui.R;

/**
 * Created by jyh on 2016/7/21.
 * https://github.com/ZengChong500373
 */
public class DragListView extends ListView {

    /**
     * isOnDrag
     * 是否在拖动
     * boolean 值 如果是true 表示 gridview 的item 正在被拖动中
     */
    private Boolean isOnDrag = false;
    /**
     * 计算状态栏高度
     * <p/>
     * 手指拖动view 判断手指到了那个item的 区域范围
     * 就要减去这个状态栏高度
     */
    private int StatusHeight;
    /**
     * 正在被拖动的 item的 position
     */
    //  private int drag_position;

    /**
     * 当前手指的所处在的item 位置
     * 这个位置是和拖动的item 要转换的位置
     * 称之为 目的地位置
     */
    private int destination_position;
    /**
     * 上一个手指所处item的位置
     * （要交换的位置）
     * 如果 last_destination_postion 和 destination_position 位置不一样
     * 手指滑动的时候就把拖着的item 和手指滑动到item的位置进行
     * 交换
     */
    private int last_destination_position;
    /**
     * 对应上面的destination_position
     */
    private View destination_view;

    /**
     * */
    private WindowManager mWindowManager;
    /**
     *
     * */
    private WindowManager.LayoutParams params;

    /**
     * 正在拖动的拷贝的imgview
     */
    private ImageView dragImgView = null;


    private int downx = 0;
    private int downy = 0;

    private int img_show = 0;
    private int img_remove = 1;

    /**
     * 应用页面的标题栏高度
     */
    private int actionbarHeight = 0;
    /**
     * 手指上一个滑动经过的view
     */
    private View last_destination_position_view;

    /**
     * 把 listview 的所有view 缓存起来
     */
    private SparseArray view_map;

    private SparseArray smoothing_map;

    private int smooth_last_position = -1;

    public DragListView(Context context) {
        super(context);
        initViews(context);
    }

    public DragListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(context);
    }

    public DragListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context);
    }

    private void initViews(Context mContext) {
        this.setOnItemLongClickListener(listener);
        StatusHeight = getStatusHeight(mContext);
        mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);

        params = new WindowManager.LayoutParams();

        dragImgView = new ImageView(mContext);
        dragImgView.setTag(img_show);
        view_map = new SparseArray();
        smoothing_map = new SparseArray();
    }


    /**
     * 状态栏和toolbar 一共的高度
     */
    public void initActionBarHeight(AppCompatActivity activity) {
        if (activity.getSupportActionBar()==null){
            return;
        }
        actionbarHeight = activity.getSupportActionBar().getHeight();
        if (actionbarHeight != 0)
            return ;
        final TypedValue tv = new TypedValue();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
            if (activity.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true))
                actionbarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
        }else if (activity.getTheme().resolveAttribute(R.attr.actionBarSize,tv,true)){
            actionbarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
        }
    }

    /**
     * 获得状态栏的高度
     */
    public static int getStatusHeight(Context context) {
        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downx = (int) ev.getRawX();
                downy = (int) ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (isOnDrag) {
                    params.x = (int) ev.getRawX() - (dragImgView.getWidth() / 2);
                    params.y = (int) ev.getRawY() - (dragImgView.getHeight() / 2);
                    mWindowManager.updateViewLayout(dragImgView, params);
                    /**
                     * 这句话特备重要
                     * 如果不计算 状态栏高度
                     * 和标题栏高度
                     * 那么会导致计算手指所在item的不准确
                     * */
                    if (destination_view != null) destination_view.setVisibility(VISIBLE);

                    int real_heihth = (int) ev.getRawY() - actionbarHeight - StatusHeight;
                    destination_position = pointToPosition((int) ev.getRawX(), real_heihth);

                    destination_view = getChildAt(destination_position - getFirstVisiblePosition());
                    smoothScroll(destination_position);
                    last_destination_position_view = getChildAt(last_destination_position - getFirstVisiblePosition());
                    if (destination_position != AdapterView.INVALID_POSITION) {
                        DragListAdapter adapter = (DragListAdapter) getAdapter();
                        if (destination_view != null) {
                            destination_view.setVisibility(INVISIBLE);
                            view_map.put(destination_position, destination_view);
                        }
                        if (destination_position != last_destination_position) {
                            if (last_destination_position_view == null) {
                                last_destination_position_view = (View) view_map.get(last_destination_position);
                            }
                            last_destination_position_view.setVisibility(VISIBLE);
                            adapter.exchangePosition(last_destination_position, destination_position);
                            last_destination_position = destination_position;
                        }
                    } else {
                        if (last_destination_position_view == null) {
                            last_destination_position_view = (View) view_map.get(last_destination_position);
                        }
                        last_destination_position_view.setVisibility(INVISIBLE);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                int tag = (int) dragImgView.getTag();
                if (isOnDrag && tag == img_show) {
                    dragImgView.setTag(img_remove);
                    mWindowManager.removeView(dragImgView);
                    if (last_destination_position_view != null)
                        last_destination_position_view.setVisibility(VISIBLE);
                    if (destination_view != null) destination_view.setVisibility(VISIBLE);

                }

                isOnDrag = false;
                break;
            default:
                break;

        }
        return super.onTouchEvent(ev);
    }

    public void smoothScroll(final int current_position) {
        int all_show_count = getChildCount();
        int frist_postion = getFirstVisiblePosition();
        /**
         * 上边界临界值
         * 如果current_postion 小于等于 frist_postion
         * 他就往上滑动
         * */
        int up_boundary_postion = frist_postion + 1;
        int down_boundary_postion = frist_postion + all_show_count - 1;
        Boolean isSmooth = (Boolean) smoothing_map.get(current_position);
        if (isSmooth != null && isSmooth == true) return;
        if (smooth_last_position == -1)
            smooth_last_position = current_position;
        int distance = Math.abs(current_position - smooth_last_position);
        if (distance >= all_show_count) return;
        Runnable runnable = null;
        if (current_position <= up_boundary_postion) {
            runnable = new Runnable() {
                @Override
                public void run() {
                    smoothing_map.put(current_position, true);
                    smoothScrollToPosition(current_position - 1);
                    smoothing_map.put(current_position, false);
                }
            };
        } else if (current_position >= down_boundary_postion) {
            runnable = new Runnable() {
                @Override
                public void run() {
                    smoothing_map.put(current_position, true);
                    smoothScrollToPosition(current_position + 1);
                    smoothing_map.put(current_position, false);
                }
            };
        }
        if (runnable != null)
            ThreadManager.getInstance().addRun(runnable);
            smooth_last_position = current_position;
    }

    public OnItemLongClickListener listener = new OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
            {
                isOnDrag = true;
                last_destination_position = position;

                dragImgView.setTag(img_show);

                last_destination_position_view = view;
                view.setVisibility(INVISIBLE);
                //获取被长按item的drawing cache
                view.destroyDrawingCache();
                view.setDrawingCacheEnabled(true);

                //通过被长按item，获取拖动item的bitmap
                Bitmap dragBitmap = Bitmap.createBitmap(view.getDrawingCache());
                dragImgView.setImageBitmap(dragBitmap);
                //设置拖动item的参数
                params.gravity = Gravity.TOP | Gravity.LEFT;
                params.height = (int) (1.2f * view.getHeight());
                params.width = (int) (1.2f * view.getWidth());
                params.x = downx - (params.width / 2);
                params.y = downy - (params.height / 2);
                params.format = PixelFormat.TRANSLUCENT;
                params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;

                mWindowManager.addView(dragImgView, params);
                return true;
            }
        }
    };
}
