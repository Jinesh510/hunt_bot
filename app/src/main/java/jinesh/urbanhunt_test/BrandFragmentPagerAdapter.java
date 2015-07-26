package jinesh.urbanhunt_test;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Jinesh on 23/07/15.
 */
public class BrandFragmentPagerAdapter extends FragmentStatePagerAdapter {

    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[] { "Feed", "Categories", "Stores" };
    private Context context;

    public BrandFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }


    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;

        Class fragmentClass;
        switch(position) {
            case 0:
                fragment = HuntListActivity.newInstance("All");
                return fragment;
            case 1:
                fragment = SelectCategoryFragment.newInstance();
                return fragment;
            case 2:
                try {
                    fragment = FirstFragment.class.newInstance();
                    return fragment;
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
        }

        return null;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
