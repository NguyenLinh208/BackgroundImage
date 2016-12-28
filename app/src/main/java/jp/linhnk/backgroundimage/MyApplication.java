package jp.linhnk.backgroundimage;

import jp.gmomedia.commons.CommonApplication;
import jp.gmomedia.commons.util.DebugLog;
import jp.linhnk.backgroundimage.api.MyNetWorkUtils;
import jp.linhnk.backgroundimage.constant.APIConstant;

/**
 * Created by usr0200475 on 2016/12/26.
 */

public class MyApplication extends CommonApplication {

    private static MyApplication instance;

    public MyApplication() {
        instance = this;
    }

    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        DebugLog.setIsDebuggable(APIConstant.IS_DEBUG_BUILD_TYPE);
        MyNetWorkUtils.updateHeaders();

    }

}
