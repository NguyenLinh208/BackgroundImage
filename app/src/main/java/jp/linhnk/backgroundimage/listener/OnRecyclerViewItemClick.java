package jp.linhnk.backgroundimage.listener;

import android.view.View;

/**
 * Created by HAL Team on 10/6/16.
 * Copyright © 2016 GMO Media Inc. All rights reserved.
 */

public interface OnRecyclerViewItemClick<T> {
    public void onItemClick(View view, T item);
}
