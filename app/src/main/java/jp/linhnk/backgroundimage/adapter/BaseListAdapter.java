package jp.linhnk.backgroundimage.adapter;

import android.support.v4.app.FragmentActivity;

import java.util.List;

import jp.gmomedia.commons.list.adapter.NormalAdapter;
import jp.gmomedia.commons.util.Utils;
import jp.linhnk.backgroundimage.listener.OnRecyclerViewItemClick;

/**
 * Created by usr0200475 on 2016/12/28.
 */

public abstract class BaseListAdapter<T> extends NormalAdapter<T> {

    protected boolean hasMore = false;
    protected OnRecyclerViewItemClick<T> onRecyclerViewItemClick;
    protected FragmentActivity activity;

    public BaseListAdapter(FragmentActivity activity, boolean hasCustomLoadMoreHolder) {
        super(activity, hasCustomLoadMoreHolder);
        this.activity = activity;
    }

    @Override
    public boolean hasMoreData() {
        return hasMore;
    }

    public void setOnRecyclerViewItemClick(OnRecyclerViewItemClick<T> onRecyclerViewItemClick) {
        this.onRecyclerViewItemClick = onRecyclerViewItemClick;
    }
    public void setHasMoreData(boolean hasMore) {
        this.hasMore = hasMore;
    }


    public void loadMoreData(List<T> moreList, boolean hasLoadMore) {
        if (moreList.size() == 0) {
            this.hasMore = false;
            notifyDataSetChanged();
            return;
        }

        final long start = System.currentTimeMillis();

        dataSet.addAll(moreList);

        this.hasMore = hasLoadMore;

        if (System.currentTimeMillis() - start > THRESHOLD) { //likely to have loaded in background
            notifyDataSetChanged();
        } else {
            Utils.postDelayed(new Runnable() {
                @Override
                public void run() {
                    notifyDataSetChanged();
                }
            }, THRESHOLD);
        }
    }
}
