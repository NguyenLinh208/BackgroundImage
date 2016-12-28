package jp.linhnk.backgroundimage.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.gmomedia.commons.util.DebugLog;
import jp.linhnk.backgroundimage.R;
import jp.linhnk.backgroundimage.api.model.Hit;

/**
 * Created by usr0200475 on 2016/12/28.
 */

public class PicListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.imgPic)
    ImageView imageView;

    public PicListViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setData(Hit hit) {
        DebugLog.i(hit.previewURL);
        Glide.with(itemView.getContext()).load(hit.previewURL).into(imageView);
    }
}
