package jp.linhnk.backgroundimage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by usr0200475 on 2016/12/26.
 */

public abstract class BaseActivity extends AppCompatActivity {

    public interface OnActivityListener {
        boolean canBackPressed();
    }

    protected OnActivityListener onActivityListener;

    public void setOnActivityListener(OnActivityListener onActivityListener) {
        this.onActivityListener = onActivityListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onPreSetContentView(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void onBackPressed() {
        popOrBack();
    }

    @Override
    public boolean onSupportNavigateUp() {
        return popOrBack();
    }

    @Override
    public boolean onNavigateUp() {
        return popOrBack();
    }

    public boolean popOrBack() {
        boolean handled = false;

        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
            handled = true;
        }

        if (!handled) {
            super.onBackPressed();
        }

        supportInvalidateOptionsMenu();

        return handled;
    }

    protected void onPreSetContentView(Bundle savedInstanceState) {

    }

    abstract protected int getLayoutId();

    abstract protected void initView();

    abstract protected void initData();

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
