package jp.linhnk.backgroundimage.fragment;

import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import com.android.volley.VolleyError;
import jp.gmomedia.commons.util.DebugLog;
import jp.linhnk.backgroundimage.BaseListFragment;
import jp.linhnk.backgroundimage.adapter.BaseListAdapter;
import jp.linhnk.backgroundimage.adapter.PicListAdapter;
import jp.linhnk.backgroundimage.api.model.Hit;
import jp.linhnk.backgroundimage.api.model.SearchPhotoResponse;
import jp.linhnk.backgroundimage.api.request.GetImageListRequest;

/**
 * Created by usr0200475 on 2016/12/28.
 */

public class PicListFragment extends BaseListFragment<SearchPhotoResponse, Hit> implements GetImageListRequest.SearchImageCallBack {

    PicListAdapter picListAdapter;
    private int totalPage = 1;

    @Override
    protected void initView() {
        super.initView();
        picListAdapter.setOnRecyclerViewItemClick(this);
    }

    @Override
    protected LinearLayoutManager getLayoutManager() {
        return new GridLayoutManager(getMainActivity(), 3);
    }

    @Override
    protected BaseListAdapter<Hit> getAdapter() {
        return picListAdapter = new PicListAdapter(getMainActivity());
    }

    @Override
    public String getTitleString(boolean isFromActivity) {
        return "画像リスト";
    }

    @Override
    protected void request() {
        setLoadingState(true);
        GetImageListRequest request = new GetImageListRequest("anime", page);
        request.setCallBack(this);
        request.execute();
    }

    @Override
    protected void handleInitialResponse(SearchPhotoResponse response) {
        totalPage = response.totalHits/response.hits.size();
        if (response.hits.isEmpty()) {
            initialEmptyList();
        } else {
            initialResponseHandled();
            picListAdapter.loadInitialDataSet(response.hits);
        }
    }

    @Override
    protected void handleRefreshResponse(SearchPhotoResponse response) {
        swipeRefreshLayout.setRefreshing(false);
        picListAdapter.refreshData();
        picListAdapter.loadInitialDataSet(response.hits);
    }

    @Override
    public void onResponse(@Nullable SearchPhotoResponse response, @Nullable VolleyError error) {
        if (!isAdded()) {
            return;
        }

        setLoadingState(false);

        if (error != null) {
            handleErrorResponse(error);
        } else if (response != null) {
            handleResponse(response);
        }
    }

    @Override
    public void onItemClick(View view, Hit item) {
        super.onItemClick(view, item);
        //TODO: handle item click
    }

    @Override
    protected boolean needLoadMore() {
        return true;
    }

    @Override
    protected void handleLoadMoreResponse(SearchPhotoResponse response) {
        picListAdapter.loadMore(response.hits);
    }

    @Override
    protected void handleEndOfList(SearchPhotoResponse response) {
        picListAdapter.setHasMoreData(page < totalPage);
        if (loadMoreListener != null) {
            loadMoreListener.setEndOfList(page == totalPage);
        }
    }
}
