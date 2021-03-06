package water.android.io.uikit.view.webview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import water.android.io.uikit.app.BaseActivity;
import water.android.io.water.base.R;

/**
 * 带RefreshLayout的WebView
 */
public class SampleWebViewActivity extends BaseActivity {

    public static void startSampleWebViewActivity(Context context, String url) {
        Intent intent = new Intent(context, SampleWebViewActivity.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String url = getIntent().getStringExtra("url");
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .add(R.id.fragment_container, SampleWebViewFragment.newInstance(url))
                .commit();
    }


    @Override
    protected int setContentViewId() {
        return R.layout.activity_android_web;
    }
}
