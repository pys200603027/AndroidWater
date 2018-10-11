package water.android.io.water.base.view.pager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.lucode.hackware.magicindicator.MagicIndicator;

import water.android.io.water.base.R;
import water.android.io.water.base.app.BaseFragment;

/**
 * ViewPager+Indicator(底部按钮) 模版
 */
public abstract class TemplateViewPagerFragment extends BaseFragment {

    ViewPager viewPager;
    MagicIndicator indicator;

    @Override
    protected int setContentViewId() {
        return R.layout.template_fragment_main;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        indicator = view.findViewById(R.id.indicator_top);
        viewPager = view.findViewById(R.id.view_pager);

        SampleFraViewPagerHelper sampleFraViewPagerHelper = new SampleFraViewPagerHelper(viewPager, indicator);
        sampleFraViewPagerHelper
                .initFragments(preparedFragments())
                .initViewPager(getChildFragmentManager())
                .initIndicator(getContext(), preparedIndicatorTitle(), preparedIndicatorDrawable());
    }

    protected abstract Fragment[] preparedFragments();

    protected abstract String[] preparedIndicatorTitle();

    protected abstract int[] preparedIndicatorDrawable();
}
