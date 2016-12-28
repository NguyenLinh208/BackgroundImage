package jp.linhnk.backgroundimage.api.request;

import com.android.volley.Request;
import com.android.volley.VolleyError;

import java.util.HashMap;
import java.util.Map;

import jp.linhnk.backgroundimage.api.WrapperObjectApiRequest;
import jp.linhnk.backgroundimage.api.model.SearchPhotoResponse;
import jp.linhnk.backgroundimage.constant.APIConstant;
import jp.linhnk.backgroundimage.constant.AppConstant;

/**
 * Created by usr0200475 on 2016/12/28.
 */

public class GetImageListRequest extends WrapperObjectApiRequest<SearchPhotoResponse> {

    public String query;
    public int page;

    public void setCallBack(SearchImageCallBack callBack) {
        this.callBack = callBack;
    }

    public SearchImageCallBack callBack;

    public GetImageListRequest(String query, int page) {
        this.query = query;
        this.page = page;
    }

    @Override
    public Map<String, String> getRequestParams() {
        HashMap<String, String> params = new HashMap<>();
        params.put("key", AppConstant.API_KEY);
        params.put("q", query);
        params.put("image_type", "photo");
        params.put("page", String.valueOf(page));
        return params;
    }

    @Override
    public void onRequestSuccess(SearchPhotoResponse response) {
        if (response != null) {
            callBack.onResponse(response, null);
        }
    }

    @Override
    public void onRequestError(VolleyError error, String message) {
        if (error != null) {
            callBack.onResponse(null, error);
        }
    }

    @Override
    public int getMethod() {
        return Request.Method.GET;
    }

    @Override
    public String getRequestURL() {
        return APIConstant.BASE_URL;
    }

    @Override
    public Class getResponseClass() {
        return SearchPhotoResponse.class;
    }

    public interface SearchImageCallBack {
        void onResponse(SearchPhotoResponse response, VolleyError error);
    }
}
