package jp.linhnk.backgroundimage.utils;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by usr0200417 on 15/04/03.
 */
public class LayoutUtils {

    public static
    @NonNull
    View inflate(@NonNull ViewGroup viewGroup, int layoutId, boolean attachToRoot) {
        return LayoutInflater
                .from(viewGroup.getContext())
                .inflate(layoutId, viewGroup, attachToRoot);
    }

}
