package water.android.io.uikit.view.pager;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import net.lucode.hackware.magicindicator.MagicIndicator;

import water.android.io.water.base.R;
import water.android.io.uikit.app.BaseActivity;

/**
 * ViewPager+Indicator(底部按钮) 模版
 */
public abstract class TemplateViewPagerActivity extends BaseActivity {

    protected NoScrollViewPager viewPager;
    protected MagicIndicator indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewPager = findViewById(R.id.view_pager);
        indicator = findViewById(R.id.indicator);

        SampleFraViewPagerHelper sampleFraViewPagerHelper = new SampleFraViewPagerHelper(viewPager, indicator);
        sampleFraViewPagerHelper
                .initFragments(preparedFragments())
                .initViewPager(getSupportFragmentManager())
                .initIndicator(this, preparedIndicatorTitle(), preparedIndicatorDrawable());
    }

    @Override
    protected int setContentViewId() {
        return R.layout.template_activity_main;
    }

    protected abstract Fragment[] preparedFragments();

    protected abstract String[] preparedIndicatorTitle();

    protected abstract int[] preparedIndicatorDrawable();
}
