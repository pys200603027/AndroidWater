package water.android.io.uikit.view.pager.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.view.ViewPager;
import android.view.View;

import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

/**
 * 简单的指示器Adapter
 */
public class SampleNavigatorAdapter extends CommonNavigatorAdapter {

    /**
     * title
     */
    private String[] indicatorTitle;
    /**
     * drawable
     */
    private int[] indicatorDrawable;

    private ViewPager viewPager;

    /**
     * @param indicatorTitle    文字
     * @param indicatorDrawable 图标
     */
    public SampleNavigatorAdapter(String[] indicatorTitle, int[] indicatorDrawable, ViewPager viewPager) {
        this.indicatorTitle = indicatorTitle;
        this.indicatorDrawable = indicatorDrawable;
        this.viewPager = viewPager;
    }


    @Override
    public int getCount() {
        return indicatorTitle.length;
    }

    @Override
    public IPagerTitleView getTitleView(Context context, final int i) {
        ColorTransitionPagerTitleView titleView = new ColorTransitionPagerTitleView(context);
        titleView.setNormalColor(Color.GRAY);
        titleView.setSelectedColor(Color.BLACK);
        titleView.setText(indicatorTitle[i]);
        titleView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        titleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(i);
            }
        });
        return titleView;
    }

    @Override
    public IPagerIndicator getIndicator(Context context) {
        return null;
    }
}
