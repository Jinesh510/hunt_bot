package jinesh.urbanhunt_test;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Jinesh on 21/07/15.
 */
public class HomeFragmentPagerAdapter extends FragmentPagerAdapter{

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
//                fragmentClass = HuntListActivity.class;
//                try {
//                    fragment = (Fragment) fragmentClass.newInstance("All");
//                } catch (InstantiationException e) {
//                    e.printStackTrace();
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                }
//                fragmentClass = HuntListActivity.class;
                fragment = (Fragment) HuntListActivity.newInstance("All");
                break;
            case 1:
                fragment = (Fragment)SelectCategoryFragment.newInstance();
//                fragmentClass = SecondFragment.class;
                break;
            case 2:
                try {
                    fragment = (Fragment)FirstFragment.class.newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            default:
                fragment = (Fragment) HuntListActivity.newInstance("All");
        }

//        try {
////            fragment = (Fragment) fragmentClass.newInstance();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        return fragment;
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
