package water.android.io.eyepetizer;


import android.support.v4.app.Fragment;

import water.android.io.water.base.view.pager.TemplateViewPagerActivity;


public class MainActivity extends TemplateViewPagerActivity {

    @Override
    protected Fragment[] preparedFragments() {
        return new Fragment[]{
                new MainFragment(),
                new SecondFragment(),
                new MainFragment()
        };
    }

    @Override
    protected String[] preparedIndicatorTitle() {
        return new String[]{"首页", "关注", "我的"};
    }

    @Override
    protected int[] preparedIndicatorDrawable() {
        return new int[]{};
    }
}
