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
 * Created by Jinesh on 21/07/15.
 */
public class HomeFragment extends Fragment {

    LinearLayout homeLayout;
    HomeFragmentPagerAdapter homeFragmentPagerAdapter;
    ViewPager viewPager;
    TabLayout tabLayout;


    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
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

        homeFragmentPagerAdapter = new HomeFragmentPagerAdapter(getActivity().getSupportFragmentManager(),this.getActivity());
        viewPager = (ViewPager)homeLayout.findViewById(R.id.viewpager);
//        viewPager.setAdapter(new HomeFragmentPagerAdapter(getActivity().getSupportFragmentManager(), getActivity()));

        viewPager.setAdapter(homeFragmentPagerAdapter);
        tabLayout = (TabLayout)homeLayout.findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        return homeLayout;
    }
//
//    public static String POSITION = "POSITION";
//
//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putInt(POSITION, tabLayout.getSelectedTabPosition());
//    }
//
//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        viewPager.setCurrentItem(savedInstanceState.getInt(POSITION);
//    }
}
