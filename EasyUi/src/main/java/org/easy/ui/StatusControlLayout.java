package org.easy.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * author：jyh
 * QQ：847145851
 * time：2019/4/10
 * describe： 控制视图显示的布局
 * 包含 加载失败子布局  loading 布局 重新加载布局
 * <p>
 * note 如果要自定义加载的布局。一定要给重新点击的布局设置id  为 retry_view  否则点击不生效
 *
 * 因为 StatusControlLayout 是继承 FrameLayout 所以一定千万在xml 中给他唯一一个子的父布局
 *  比如demo 中的RelativeLayout
 *      <org.easy.ui.StatusControlLayout
 *         android:id="@+id/control_layout"
 *         android:layout_width="match_parent"
 *         android:layout_height="match_parent"
 *         android:layout_above="@id/ll_bottom"
 *         app:errorView="@layout/activity_status_control_error"
 *         >
 *         <RelativeLayout
 *             android:gravity="center"
 *             android:layout_width="match_parent"
 *             android:layout_height="match_parent">
 *             <TextView
 *                 android:text="success"
 *                 android:layout_width="wrap_content"
 *                 android:layout_height="wrap_content" />
 *         </RelativeLayout>
 *     </org.easy.ui.StatusControlLayout>
 **/
public class StatusControlLayout extends FrameLayout {

    private static final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT);
    protected int contentViewId;
    private int emptyViewId;
    private int errorViewId;
    private int loadingViewId;
    private int noNetworkViewId;

    protected View contentView;

    private LayoutInflater inflater;

    public StatusControlLayout(Context context) {
        this(context, null);
    }

    public StatusControlLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StatusControlLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.StatusControlLayout);
        contentViewId = a.getResourceId(R.styleable.StatusControlLayout_contentView, -1);
        emptyViewId = a.getResourceId(R.styleable.StatusControlLayout_emptyView, R.layout.empty_view);
        errorViewId = a.getResourceId(R.styleable.StatusControlLayout_errorView, R.layout.error_view);
        loadingViewId = a.getResourceId(R.styleable.StatusControlLayout_loadingView, R.layout.loading_view);
        noNetworkViewId = a.getResourceId(R.styleable.StatusControlLayout_noNetworkView, R.layout.no_network_view);
        inflater = LayoutInflater.from(context);
        a.recycle();

    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (contentViewId==-1&&getChildCount()>0){
            contentView=getChildAt(0);
            int a=1;
        }
    }

    public void showEmpty() {
        showView(emptyViewId);
    }

    public void showError() {
        showView(errorViewId);
    }

    public void showLoading() {
        showView(loadingViewId);
    }

    public void showNoNet() {
        showView(noNetworkViewId);
    }

    public void hideLoading() {
        currentView=contentView;
         if (currentView!=null){
             if (getChildCount() > 0)
                 removeAllViews();
             addView(currentView, params);
         }
    }

    public void showView(int id) {
        currentView = ViewFactory(id);
        if (currentView == null) {
            Log.e("StatusControlLayout", "getView = null");
            return;
        }
        if (getChildCount() > 0)
            removeAllViews();
        if (currentView != null && onRetryListener != null && id != loadingViewId) {
            View retryView = currentView.findViewById(R.id.retry_view);
            if (retryView != null)
                retryView.setOnClickListener(onRetryListener);
        }
        addView(currentView, params);
    }

    /**
     * 设置重试点击事件
     *
     * @param onRetryListener 重试点击事件
     */
    public void setOnRetryListener(OnClickListener onRetryListener) {
        this.onRetryListener = onRetryListener;
    }

    private OnClickListener onRetryListener;
    /**
     * SparseArray 效率比map 高
     * 性能优化要点
     */
    private SparseArray<View> views = new SparseArray<>();
    protected View currentView;

    /**
     * 简单工厂
     */
    public View ViewFactory(int id) {
        View view = views.get(id);
        if (view == null) {
            view = inflater.inflate(id, null);
            views.put(id, view);
        }
        return view;
    }
}
