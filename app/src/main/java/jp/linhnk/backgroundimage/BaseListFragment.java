package jp.linhnk.backgroundimage;

import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.volley.NoConnectionError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;

import butterknife.BindView;
import jp.gmomedia.commons.list.listener.LoadMoreListener;
import jp.gmomedia.commons.util.DebugLog;
import jp.linhnk.backgroundimage.adapter.BaseListAdapter;
import jp.linhnk.backgroundimage.api.MyNetWorkUtils;
import jp.linhnk.backgroundimage.constant.AppConstant;
import jp.linhnk.backgroundimage.fragment.BaseFragment;
import jp.linhnk.backgroundimage.listener.OnRecyclerViewItemClick;

/**
 * Created by HAL Team on 10/11/16.
 * Copyright © 2016 GMO Media Inc. All rights reserved.
 */
public abstract class BaseListFragment<D, T> extends BaseFragment implements OnRecyclerViewItemClick<T> {

    @BindView(R.id.recyclerView)
    protected RecyclerView recyclerView;

    @BindView(R.id.swipe_container)
    protected SwipeRefreshLayout swipeRefreshLayout;

    protected int page = 1;
    protected boolean isLoading = false;

    protected LoadMoreListener loadMoreListener;
    protected AppConstant.RequestType requestType;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list;
    }

    @Override
    protected void initView() {
        super.initView();
        recyclerView.setLayoutManager(getLayoutManager());

        if (needRefresh()) {
            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    swipeRefreshLayout.setRefreshing(true);
                    if (loadMoreListener != null) {
                        loadMoreListener.resetLoadMore();
                    }
                    refreshRequest();
                }
            });
        }
        swipeRefreshLayout.setEnabled(needRefresh());

        if (needLoadMore()) {
            loadMoreListener = new LoadMoreListener(getLayoutManager(), AppConstant.VISIBLE_THRESHOLD) {
                @Override
                public void onLoadMore() {
                    loadMoreRequest();
                }
            };

            recyclerView.addOnScrollListener(loadMoreListener);
        }

        if (getAdapter() != null) {
            recyclerView.setAdapter(getAdapter());
        }

    }

    protected abstract LinearLayoutManager getLayoutManager();

    @Override
    protected void initData() {
        super.initData();
        initialLoadingProgress();
        initRequest();
    }

    protected abstract BaseListAdapter getAdapter();

    protected abstract boolean needLoadMore();

    protected boolean needRefresh() {
        return true;
    }

    protected abstract void request();

    protected void initRequest() {
        page = 1;
        requestType = AppConstant.RequestType.Initial;
        request();
    }

    protected void loadMoreRequest() {
        if (!isLoading) {
            setLoadingState(true);
            page += 1;
            requestType = AppConstant.RequestType.LoadMore;
            request();
        }
    }

    protected void refreshRequest() {
        page = 1;
        requestType = AppConstant.RequestType.Refresh;
        request();
    }

    protected void setLoadingState(boolean isLoading) {
        this.isLoading = isLoading;
        if (loadMoreListener != null) {
            loadMoreListener.setLoadingFlag(isLoading);
        }
    }

    protected void handleResponse(D response) {
        switch (requestType) {
            case LoadMore:
                handleLoadMoreResponse(response);
                break;
            case Refresh:
                handleRefreshResponse(response);
                break;
            default:
                handleInitialResponse(response);
                break;
        }

        if (needLoadMore()) {
            handleEndOfList(response);
        }

    }

    protected abstract void handleInitialResponse(D response);

    protected void handleLoadMoreResponse(D response) {
        //TODO: Handler loadmore if need
    }

    protected void handleRefreshResponse(D response) {
        //TODO: Handler refresh list if need
    }

    protected void handleEndOfList(D response) {
        //TODO: Handler end of list if need
    }

    protected void handleErrorResponse(@NonNull VolleyError error) {
        if (error instanceof NoConnectionError || error instanceof TimeoutError) {
            initialNetworkError();
        } else {
            String message = MyNetWorkUtils.getErrorMessage(error);
            //TODO: handle error
            initialNetworkError();
        }
    }

    @Override
    public void onItemClick(View view, T item) {
        //TODO: Item click
    }
}
