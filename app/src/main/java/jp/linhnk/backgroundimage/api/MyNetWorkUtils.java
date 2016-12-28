package jp.linhnk.backgroundimage.api;

import android.content.Context;
import android.webkit.WebView;

import java.util.HashMap;

import jp.gmomedia.commons.api.NetworkUtils;
import jp.linhnk.backgroundimage.constant.APIConstant;
import jp.linhnk.backgroundimage.MyApplication;

/**
 * Created by usr0200475 on 2016/12/26.
 */

public class MyNetWorkUtils extends NetworkUtils {

    public MyNetWorkUtils(Context context) {
        super(context);
    }

    public static void updateHeaders() {
        setCommonHeaders(getHeaders());
    }

    public static HashMap<String, String> getHeaders() {
        HashMap<String, String> headers = new HashMap<>();
        String userAgent = "RSTravelApp " + new WebView(MyApplication.getInstance()).getSettings().getUserAgentString();
        headers.put(APIConstant.HEADER_USER_AGENT, userAgent);

        return headers;
    }
}
