package org.easy.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
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
 **/
public class StatusControlLayout extends FrameLayout {

    private static final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT);
    private int contentViewId;
    private int emptyViewId;
    private int errorViewId;
    private int loadingViewId;
    private int noNetworkViewId;


    private View contentView;
    private View emptyView;
    private View errorView;
    private View loadingView;
    private View noNetworkView;

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
        contentViewId = a.getResourceId(R.styleable.StatusControlLayout_contentView, 0);
        emptyViewId = a.getResourceId(R.styleable.StatusControlLayout_emptyView, R.layout.empty_view);
        errorViewId = a.getResourceId(R.styleable.StatusControlLayout_errorView, R.layout.error_view);
        loadingViewId = a.getResourceId(R.styleable.StatusControlLayout_loadingView, R.layout.loading_view);
        noNetworkViewId = a.getResourceId(R.styleable.StatusControlLayout_noNetworkView, R.layout.no_network_view);
        inflater = LayoutInflater.from(context);
        a.recycle();

    }

    public void showEmpty() {
        if (emptyView == null) {
            emptyView = inflater.inflate(emptyViewId, null);
        }
        if (getChildCount() > 0)
        removeAllViews();
        if (emptyView != null && onRetryListener != null) {
            onRetryListener.onClick(emptyView);
        }
        addView(emptyView, params);
    }

    public void showError() {
        if (errorView == null) {
            errorView = inflater.inflate(errorViewId, null);
        }
        if (getChildCount() > 0)
        removeAllViews();
        if (errorView != null && onRetryListener != null) {
            onRetryListener.onClick(errorView);
        }
        addView(errorView, params);
    }

    public void showLoading() {
        if (loadingView == null) {
            loadingView = inflater.inflate(loadingViewId, null);

        }
        if (getChildCount() > 0)
        removeAllViews();
        if (loadingView != null && onRetryListener != null) {
            onRetryListener.onClick(loadingView);
        }
        addView(loadingView, params);
    }

    public void showNoNet() {
        if (noNetworkView == null) {
            noNetworkView = inflater.inflate(noNetworkViewId, null);
        }
        if (getChildCount() > 0)
            removeAllViews();
        if (noNetworkView != null && onRetryListener != null) {
            onRetryListener.onClick(noNetworkView);
        }
        addView(noNetworkView, params);
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
}
