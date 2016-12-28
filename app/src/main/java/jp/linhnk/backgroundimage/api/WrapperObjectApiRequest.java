package jp.linhnk.backgroundimage.api;

import com.android.volley.VolleyError;

import jp.gmomedia.commons.api.volley.ObjectApiRequest;
import jp.gmomedia.commons.api.volley.Response.Response;
import jp.gmomedia.commons.api.volley.callback.OnFailListener;
import jp.gmomedia.commons.api.volley.callback.OnSuccessListener;
import jp.gmomedia.commons.api.volley.request.BaseTypeRequest;

/**
 * Created by HAL Team on 10/7/16.
 * Copyright © 2016 GMO Media Inc. All rights reserved.
 */
public abstract class WrapperObjectApiRequest<T> extends ObjectApiRequest<T> implements OnSuccessListener<T>, OnFailListener {

    abstract public void onRequestSuccess(T response);

    abstract public void onRequestError(VolleyError error, String message);

    CommonRequestHandle commonRequestHandle = new CommonRequestHandle();

    public WrapperObjectApiRequest() {
        setListener(this, this);
    }

    @Override
    public void onFail(BaseTypeRequest request, Response response) {
        if (commonRequestHandle.handleWithdrawn(response.error.networkResponse)) {
            return;
        }
        String message = WrapperObjectApiRequest.getErrorResponseMessage(response.error);
        onRequestError(response.error, message);
    }

    @Override
    public void onPrerequisiteFail(BaseTypeRequest request, VolleyError error) {
        onRequestError(error, "onPrerequisiteFail");
    }

    @Override
    public void onSuccess(BaseTypeRequest request, Response<T> response) {
        commonRequestHandle.handleResponseHeader(response.headers);
        onRequestSuccess(response.responseObject);
    }

    @Override
    public boolean getNeedAutoParseCamelCase() {
        return false;
    }

}
