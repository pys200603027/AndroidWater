package water.android.io.uikit.view.pager.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * 默认ViewPagerAdapter
 */
public class SamplePagerAdapter extends FragmentStatePagerAdapter {

    private Fragment[] fragments;

    public SamplePagerAdapter(Fragment[] fragments, FragmentManager fm) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments[i];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }
}
