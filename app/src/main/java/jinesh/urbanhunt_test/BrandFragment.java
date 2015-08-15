package jinesh.urbanhunt_test;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by Jinesh on 23/07/15.
 */
public class BrandFragment extends Fragment {

    LinearLayout homeLayout;
    BrandFragmentPagerAdapter brandFragmentPagerAdapter;
    ViewPager viewPager;
    TabLayout tabLayout;

    public static BrandFragment newInstance() {
        BrandFragment fragment = new BrandFragment();
//        Bundle bundle = new Bundle();
//        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        homeLayout = (LinearLayout)inflater.inflate(R.layout.home,container,false);

        brandFragmentPagerAdapter = new BrandFragmentPagerAdapter(getActivity().getSupportFragmentManager(),this.getActivity());
        viewPager = (ViewPager)homeLayout.findViewById(R.id.viewpager);
//        viewPager.setAdapter(new HomeFragmentPagerAdapter(getActivity().getSupportFragmentManager(), getActivity()));

        viewPager.setAdapter(brandFragmentPagerAdapter);
        tabLayout = (TabLayout)homeLayout.findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        return homeLayout;
    }
}
