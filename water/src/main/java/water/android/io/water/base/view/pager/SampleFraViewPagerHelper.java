package water.android.io.water.base.view.pager;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;

import water.android.io.water.base.view.pager.adapter.SampleNavigatorAdapter;
import water.android.io.water.base.view.pager.adapter.SamplePagerAdapter;

/**
 * 简化ViewPager +Indicator 操作 <p/>
 * 调用顺序:initFragments->initViewPager->initIndicator
 */
public class SampleFraViewPagerHelper {

    ViewPager viewPager;
    MagicIndicator indicator;
    Fragment[] fragments;

    public SampleFraViewPagerHelper(ViewPager viewPager, MagicIndicator indicator) {
        this.viewPager = viewPager;
        this.indicator = indicator;
    }

    public SampleFraViewPagerHelper initFragments(Fragment[] fragments) {
        this.fragments = fragments.clone();
        return this;
    }

    public SampleFraViewPagerHelper initViewPager(FragmentManager fragmentManager) {
        if (fragments == null) {
            throw new RuntimeException("before initViewPager,you must initFragments first!");
        }

        viewPager.setOffscreenPageLimit(fragments.length);
        viewPager.setAdapter(new SamplePagerAdapter(fragments, fragmentManager));
        return this;
    }

    public void initIndicator(Context context, String[] titles, int[] titleDrawables) {
        if (viewPager == null) {
            throw new RuntimeException("before initIndicator,you must viewPager first!");
        }
        CommonNavigator navigator = new CommonNavigator(context);
        navigator.setAdjustMode(true);
        navigator.setAdapter(new SampleNavigatorAdapter(titles, titleDrawables, viewPager));
        indicator.setNavigator(navigator);
        ViewPagerHelper.bind(indicator, viewPager);
    }
}
