package jp.linhnk.backgroundimage.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import jp.gmomedia.commons.util.DebugLog;
import jp.linhnk.backgroundimage.BaseActivity;
import jp.linhnk.backgroundimage.MainActivity;
import jp.linhnk.backgroundimage.MyApplication;
import jp.linhnk.backgroundimage.R;
import jp.linhnk.backgroundimage.api.MyNetWorkUtils;
import jp.linhnk.backgroundimage.helper.EventBusHelper;

/**
 * Created by HAL Team on 10/6/16.
 * Copyright © 2016 GMO Media Inc. All rights reserved.
 */
public class BaseFragment extends Fragment {

    private boolean reInitDataOnResume;
    //For Butterknife
    private Unbinder unbinder;

    public interface CommonNetworkLoadingAndErrorHandling {
    }

    protected ViewGroup rootView;
    private ViewGroup fragmentViewParent;

    @BindView(R.id.initialProgressBar)
    View initialProgressBar;

    @BindView(R.id.initialNetworkError)
    View initialNetworkError;

    @BindView(R.id.initialEmptyList)
    View initialEmptyList;

    @BindView(R.id.iv_empty_placeholder)
    ImageView ivEmptyPlaceHolder;

    public View getInitialNetworkError() {
        return initialNetworkError;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DebugLog.i("Lifecycle " + this.getClass().getSimpleName());
        Bundle arguments = getArguments();
        if (arguments != null) {
            handleArguments(arguments);
        }

        if (savedInstanceState != null) {
            handleSavedInstanceState(savedInstanceState);
        }

        Bundle extras = getActivity().getIntent().getExtras();
        if (extras != null) {
            handleExtras(extras);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        DebugLog.i("Lifecycle " + this.getClass().getSimpleName());
        return rootView != null ? rootView : createRootView(inflater, container);
    }

    private View createRootView(LayoutInflater inflater, ViewGroup container) {
        rootView = (ViewGroup) inflater.inflate(R.layout.base_fragment, container, false);
        fragmentViewParent = (ViewGroup) rootView.findViewById(R.id.fragmentViewParent);
        fragmentViewParent.addView(inflater.inflate(getLayoutId(), container, false));
        unbinder = ButterKnife.bind(this, rootView);
        bypassCommonNetworkLoadingIfNecessary();
        initView();
        initData();
        sendScreenName();
        return rootView;
    }

    public void sendScreenName() {
//        FAHelper.sendScreenName(getScreenName());
    }

    public String getGoogleAnalyticsType() {
        return getScreenName();
    }

    protected String getScreenName() {
        return getClass().getSimpleName().replace("Fragment", "");
    }

    private void bypassCommonNetworkLoadingIfNecessary() {
        if (!(this instanceof CommonNetworkLoadingAndErrorHandling)) {
            initialResponseHandled();
        }
    }

    protected void handleArguments(Bundle arguments) {
        // TODO
    }

    protected void handleSavedInstanceState(Bundle savedInstanceState) {
        // TODO
    }

    protected void handleExtras(Bundle extras) {
        // TODO
    }


    protected void initView() {
        if (ivEmptyPlaceHolder != null) {
            ivEmptyPlaceHolder.setImageResource(getEmptyViewResources());
        }
    }

    protected void initData() {
    }

    protected void reInitData() {
        if (isVisible()) {
            reInitDataOnResume = false;
            initialProgressBar.setVisibility(View.VISIBLE);
            initialNetworkError.setVisibility(View.GONE);
            fragmentViewParent.setVisibility(View.GONE);
            initData();
        } else {
            reInitDataOnResume = true;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        DebugLog.i("Lifecycle " + this.getClass().getSimpleName());
    }

    public boolean isClosed() {
        return !isVisible() || getActivity() == null;
    }

    @Override
    public void onDestroy() {
        DebugLog.i("Lifecycle " + this.getClass().getSimpleName());
        unbinder.unbind();
        EventBusHelper.unregister(this);
        fragmentViewParent = null;
        rootView = null;
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getActivity() instanceof AppCompatActivity) {
            String titleString = getTitleString(false);
            if (titleString != null) {
                getMainActivity().getSupportActionBar().setLogo(null);
                getMainActivity().getSupportActionBar().setTitle(titleString);
            } else {
                getMainActivity().getSupportActionBar().setLogo(null);
                getMainActivity().getSupportActionBar().setTitle("");
            }
        }

        if (reInitDataOnResume) {
            reInitData();
        }
    }

    protected int getLayoutId() {
        return 0;
    }

    private void showAndHideOthers(View target) {
        showOrHide(initialProgressBar, target);
        showOrHide(initialNetworkError, target);
        showOrHide(initialEmptyList, target);
        showOrHide(fragmentViewParent, target);
    }

    private void showOrHide(View subject, View target) {
        if (subject == null || target == null) {
            return;
        }

        subject.setVisibility(subject == target ? View.VISIBLE : View.GONE);
    }

    protected void showInitialLoadingProgress() {
        showAndHideOthers(initialProgressBar);
    }

    protected void initialLoadingProgress() {
        showAndHideOthers(initialProgressBar);
    }

    protected void initialNetworkError() {
        showAndHideOthers(initialNetworkError);
    }

    protected void initialEmptyList() {
        showAndHideOthers(initialEmptyList);
    }

    protected void initialResponseHandled() {
        showAndHideOthers(fragmentViewParent);
    }

//    @OnClick(R.id.initialReloadButton)
//    public void onInitialReloadButtonClicked(Button button) {
//        checkNetworkConnected();
//        showAndHideOthers(initialProgressBar);
//        initData();
//    }

    public int getTitleResId() {
        return 0;
    }

    public String getTitleString(boolean isFromActivity) {
        int titleResId = getTitleResId();
        if (titleResId > 0) {
            return getString(titleResId);
        } else {
            if (isFromActivity) {
                return "";
            } else {
                return null;
            }
        }
    }

    public BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }

    @Nullable
    /**
     * @return MainActivity instance or null if activity is not MainActivity
     */
    public MainActivity getMainActivity() {
        FragmentActivity activity = getActivity();
        return activity instanceof MainActivity ? (MainActivity) activity : null;
    }

    public void checkNetworkConnected() {
        if (!MyNetWorkUtils.getInstance(MyApplication.getInstance()).isNetworkConnected()) {
            Toast.makeText(MyApplication.getInstance(),"NO CONNECTION", Toast.LENGTH_SHORT).show();
        }
    }

    public int getEmptyViewResources() {
        return R.drawable.img_load_fail;
    }

//    private void setImageSize(int type) {
//        final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        float height;
//        if (type == AppConstant.EmptyViewType.Error.ordinal()) {
//            height = ScreenHelper.dpToPx(210);
//        } else if (type == AppConstant.EmptyViewType.Favorite.ordinal()) {
//            height = ScreenHelper.dpToPx(180);
//        } else {
//            height = ScreenHelper.dpToPx(160);
//        }
//        layoutParams.height = Math.round(height);
//        ivEmptyPlaceHolder.setLayoutParams(layoutParams);
//    }
//
//    private AppConstant.EmptyViewType getEmptyViewType() {
//        if (getEmptyViewResources() == R.drawable.img_load_fail) {
//            return AppConstant.EmptyViewType.Error;
//        } else if (getEmptyViewResources() == R.drawable.img_search_default) {
//            return AppConstant.EmptyViewType.Search;
//        } else {
//            return AppConstant.EmptyViewType.Favorite;
//        }
//    }
//
//    public AppConstant.IndicatorState getIndicationState() {
//        return AppConstant.IndicatorState.Back;
//    }

}
