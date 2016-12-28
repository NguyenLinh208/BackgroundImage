package jp.linhnk.backgroundimage.adapter;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import jp.gmomedia.commons.util.DebugLog;
import jp.linhnk.backgroundimage.R;
import jp.linhnk.backgroundimage.adapter.viewholder.PicListViewHolder;
import jp.linhnk.backgroundimage.api.model.Hit;
import jp.linhnk.backgroundimage.utils.LayoutUtils;

/**
 * Created by usr0200475 on 2016/12/28.
 */

public class PicListAdapter extends BaseListAdapter<Hit> {

    private List<Hit> hits;

    public void setHits(List<Hit> hits) {
        this.hits = hits;
        DebugLog.i("hits:" + hits.size());
    }

    public PicListAdapter(FragmentActivity context) {
        super(context, false);
    }

    @Override
    protected int getActualItemViewType(int position) {
        return 0;
    }

    @Override
    public PicListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PicListViewHolder(LayoutUtils.inflate(parent, R.layout.item_pics_list_view_holder, false));
    }

    @Override
    protected void onActualBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        handleViewHolder((PicListViewHolder) holder, dataSet.get(position));
    }

    private void handleViewHolder(PicListViewHolder holder, Hit hit) {
        holder.setData(hit);
    }

    @Override
    protected RecyclerView.ViewHolder onActualCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pics_list_view_holder, parent, false);
        return new PicListViewHolder(view);
    }
}
