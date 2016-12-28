package jp.linhnk.backgroundimage.api;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import java.util.Map;
import jp.linhnk.backgroundimage.MyApplication;

/**
 * Created by HAL Team on 10/6/16.
 * Copyright © 2016 GMO Media Inc. All rights reserved.
 */

public class CommonRequestHandle {

    public void handleResponseHeader(Map<String, String> headers) {
        //TODO: Nothing
    }

    public boolean handleWithdrawn(NetworkResponse response) {
        if (response != null && isWithdrawn(response)) {
            cancelAll();
            return true;
        } else {
            return false;
        }
    }


    private boolean isWithdrawn(NetworkResponse response) {
        boolean result = false;

        if (response.statusCode == 401) {
            String errorCode = response.headers.get("X-API-ERROR-CODE");
            if (errorCode != null) {
                result = errorCode.equals("401100") || errorCode.equals("401200");
            }
        }

        return result;
    }


    private void cancelAll() {
        MyNetWorkUtils.getInstance(MyApplication.getInstance()).getRequestQueue().cancelAll(new RequestQueue.RequestFilter() {
            @Override
            public boolean apply(Request<?> request) {
                return true;
            }
        });
    }

}
