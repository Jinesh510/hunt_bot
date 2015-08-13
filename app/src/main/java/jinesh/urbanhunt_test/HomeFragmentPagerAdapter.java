package jinesh.urbanhunt_test;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Jinesh on 21/07/15.
 */
public class HomeFragmentPagerAdapter extends FragmentStatePagerAdapter {

    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[] { "Feed", "Categories", "Stores" };
    private Context context;

    public HomeFragmentPagerAdapter(FragmentManager fm, Context context) {
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
                fragment = BrandFragment.newInstance();
                return fragment;

//                fragment = BrandFragment.newInstance();
//                return fragment;
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
