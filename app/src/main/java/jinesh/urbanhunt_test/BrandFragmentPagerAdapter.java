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
    private String tabTitles[] = new String[] { "popular", "newArrival", "discount" };
    private Context context;
    String brandId;

    public BrandFragmentPagerAdapter(FragmentManager fm, Context context, String brand) {
        super(fm);
        this.context = context;
        this.brandId = brand;
    }


    @Override
    public Fragment getItem(int position) {
        ProductListFragment productListFragment = null;
        return productListFragment.newInstance(brandId,tabTitles[position]);
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
