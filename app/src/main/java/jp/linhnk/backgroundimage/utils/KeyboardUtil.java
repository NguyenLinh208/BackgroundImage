package jp.linhnk.backgroundimage.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by HAL Team on 10/6/16.
 * Copyright © 2016 GMO Media Inc. All rights reserved.
 */

public class KeyboardUtil {

    public static void dismissKeyboard(View view) {
        InputMethodManager keyboard = (InputMethodManager)
                view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        keyboard.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void showKeyboard(View view) {
        InputMethodManager keyboard = (InputMethodManager)
                view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        keyboard.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }

}
