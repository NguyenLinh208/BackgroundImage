package jp.linhnk.backgroundimage.constant;

import jp.linhnk.backgroundimage.BuildConfig;

/**
 * Created by usr0200475 on 2016/12/26.
 */

public class APIConstant {

    public static final boolean IS_DEBUG_BUILD_TYPE = BuildConfig.BUILD_TYPE.equals("debug");

    //TODO : HEADER PARAM
    public static final String HEADER_USER_AGENT = "User-Agent";
    public static final String HEADER_X_DEVICE_TYPE = "X-DEVICE-TYPE";
    public static final String HEADER_X_DEVICE_DENSITY = "X-DEVICE-DENSITY";
    public static final String HEADER_X_OS_VERSION = "X-OS-VERSION";
    public static final String HEADER_X_APP_VERSION = "X-APP-VERSION";
    public static final String HEADER_X_WSSE = "X-WSSE";
    public static final String HEADER_BADGE_COUNT = "X-BADGE-COUNT";

    public static final String BASE_URL = "https://pixabay.com/api/";

}
