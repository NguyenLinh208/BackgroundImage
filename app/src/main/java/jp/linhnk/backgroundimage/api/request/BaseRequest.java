package jp.linhnk.backgroundimage.api.request;

import java.util.HashMap;
import java.util.Map;

import jp.linhnk.backgroundimage.api.WrapperObjectApiRequest;
import jp.linhnk.backgroundimage.constant.AppConstant;

/**
 * Created by usr0200475 on 2016/12/28.
 */

public abstract class BaseRequest<T> extends WrapperObjectApiRequest<T> {
    HashMap<String, String> params = new HashMap<>();

    @Override
    public Map<String, String> getRequestParams() {
        params.put("key", AppConstant.API_KEY);
        return params;
    }
}
