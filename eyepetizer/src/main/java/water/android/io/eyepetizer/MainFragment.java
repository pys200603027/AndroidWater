package water.android.io.eyepetizer;

import android.support.v4.app.Fragment;
import water.android.io.uikit.view.pager.TemplateViewPagerFragment;

public class MainFragment extends TemplateViewPagerFragment {

    @Override
    protected Fragment[] preparedFragments() {
        return new Fragment[]{
                new SecondFragment(),
                new SecondFragment(),
                new SecondFragment(),
        };
    }

    @Override
    protected String[] preparedIndicatorTitle() {
        return new String[]{"一", "二", "三"};
    }

    @Override
    protected int[] preparedIndicatorDrawable() {
        return new int[]{};
    }
}
