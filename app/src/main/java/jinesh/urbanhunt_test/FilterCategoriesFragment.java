package jinesh.urbanhunt_test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by Jinesh on 12/08/15.
 */
public class FilterCategoriesFragment extends Fragment {


    LinearLayout categoryListLayout;
    CategoryPagerAdapter categoryPagerAdapter;
    ViewPager viewPager;
    private TabLayout tabLayout;
    Button filter;


    public static FilterCategoriesFragment newInstance( String collectionName,String categoryName, int categoryId) {
        FilterCategoriesFragment fragment = new FilterCategoriesFragment();
        Bundle bundle = new Bundle();
        bundle.putString("collectionName",collectionName);
        bundle.putString("categoryName", categoryName);
        bundle.putInt("categoryId",categoryId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        String collectionName = getArguments().getString("collectionName");
        String categoryName = getArguments().getString("categoryName");
        int categoryId = getArguments().getInt("categoryId");


        categoryListLayout = (LinearLayout)inflater.inflate(R.layout.filter_categories_frag,container,false);

        if (collectionName != null){

            categoryPagerAdapter = new CategoryPagerAdapter(getActivity().getSupportFragmentManager(), getActivity(), collectionName);
            Log.d("colName", collectionName);

        }
        else {

            Log.d("category", "" + categoryId);
//        huntListFragment  = new HuntListActivity();

            categoryPagerAdapter = new CategoryPagerAdapter(getActivity().getSupportFragmentManager(), getActivity(), categoryId, categoryName);

        }
        viewPager = (ViewPager)categoryListLayout.findViewById(R.id.viewpager);
//        viewPager.setAdapter(new HomeFragmentPagerAdapter(getActivity().getSupportFragmentManager(), getActivity()));

        viewPager.setAdapter(categoryPagerAdapter);
        tabLayout = (TabLayout)categoryListLayout.findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        filter = (Button)categoryListLayout.findViewById(R.id.filter);


        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((FilterCategoriesActivity)getActivity()).openFilterList();
            }
        });



        return categoryListLayout;
    }

}
