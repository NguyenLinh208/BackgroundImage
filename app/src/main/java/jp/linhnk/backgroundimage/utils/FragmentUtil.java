package jp.linhnk.backgroundimage.utils;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.transition.TransitionInflater;
import android.view.View;

import jp.gmomedia.commons.util.DebugLog;
import jp.linhnk.backgroundimage.R;

/**
 * Created by HAL Team on 10/6/16.
 * Copyright © 2016 GMO Media Inc. All rights reserved.
 */

public class FragmentUtil {

    public static void pushFragment(FragmentActivity activity, @NonNull Fragment fragment, @Nullable Bundle data) {
        showFragment(activity, fragment, true, data, null, false);
    }

    public static void replaceFragment(FragmentActivity activity, @NonNull Fragment fragment, @Nullable Bundle data) {
        showFragment(activity, fragment, false, data, null, false);
    }

    public static void showFragment(FragmentActivity activity, @NonNull Fragment fragment, boolean isPushInsteadOfReplace, @Nullable Bundle data, @Nullable String tag, boolean isShowAnimation) {
        if (activity == null) {
            return;
        }

        if (data != null) {
            fragment.setArguments(data);
        }

        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();

        if (isShowAnimation) {
            fragmentTransaction.setCustomAnimations(R.anim.slide_in_up,
                    R.anim.slide_out_up);
        }

        fragmentTransaction.replace(R.id.container, fragment, tag);
        if (isPushInsteadOfReplace) {
            fragmentTransaction.addToBackStack(null);
        }

        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }

    public static void pushSharedFragment(FragmentActivity activity, Fragment fromFragment, Fragment fragment, String tag, View sharedView, String transitionName) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            fromFragment.setSharedElementReturnTransition(TransitionInflater.from(activity).inflateTransition(R.transition.trans_move));
            fromFragment.setExitTransition(TransitionInflater.from(activity).inflateTransition(android.R.transition.explode));

            fragment.setSharedElementEnterTransition(TransitionInflater.from(activity).inflateTransition(R.transition.trans_move));
            fragment.setEnterTransition(TransitionInflater.from(activity).inflateTransition(android.R.transition.explode));

            FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container, fragment);
            fragmentTransaction.addSharedElement(sharedView, transitionName);
            fragmentTransaction.addToBackStack(null);
            try {
                activity.getSupportFragmentManager().executePendingTransactions();
                fragmentTransaction.commit();
            } catch (Exception ex) {
                DebugLog.e("Can not perform this action after onSaveInstanceState!");
            }
        } else {
            pushFragment(activity, fragment, null);
        }
    }

}
