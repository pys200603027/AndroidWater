package water.android.io.eyepetizer;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    MagicIndicator indicator;

    Fragment[] fragments;
    String[] indicatorTitle;
    int[] indicatorDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();

        viewPager = findViewById(R.id.view_pager);
        indicator = findViewById(R.id.indicator);
        initViewPager();
        initIndicator();
        ViewPagerHelper.bind(indicator, viewPager);
    }

    protected void initData() {
        indicatorTitle = preparedIndicatorTitle();
        indicatorDrawable = preparedIndicatorDrawable();
        fragments = preparedFragments();
    }

    protected void initViewPager() {
        viewPager.setOffscreenPageLimit(fragments.length);
        viewPager.setAdapter(new SamplePagerAdapter());
    }

    protected void initIndicator() {
        CommonNavigator navigator = new CommonNavigator(this);
        navigator.setAdjustMode(true);
        navigator.setAdapter(new DefaultSampleNavigatorAdapter());
        indicator.setNavigator(navigator);
    }

    protected Fragment[] preparedFragments() {
        return new Fragment[]{
                new MainFragment(),
                new MainFragment(),
                new MainFragment()
        };
    }

    protected String[] preparedIndicatorTitle() {
        return new String[]{"首页", "关注", "我的"};
    }

    protected int[] preparedIndicatorDrawable() {
        return new int[]{};
    }

    /**
     * 默认Adapter
     */
    private class SamplePagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return fragments.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            return fragments[position];
        }
    }

    /**
     * 默认NavigatorAdapter
     */
    private class DefaultSampleNavigatorAdapter extends CommonNavigatorAdapter {

        public DefaultSampleNavigatorAdapter() {
        }

        @Override
        public int getCount() {
            return indicatorTitle.length;
        }

        @Override
        public IPagerTitleView getTitleView(Context context, final int i) {
            ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
            colorTransitionPagerTitleView.setNormalColor(Color.GRAY);
            colorTransitionPagerTitleView.setSelectedColor(Color.BLACK);
            colorTransitionPagerTitleView.setText(indicatorTitle[i]);
            colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    viewPager.setCurrentItem(i);
                }
            });
            return colorTransitionPagerTitleView;
        }

        @Override
        public IPagerIndicator getIndicator(Context context) {
            return null;
        }
    }
}
