package jinesh.urbanhunt_test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

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


    public static FilterCategoriesFragment newInstance( String collectionName,String categoryName, int categoryId, ArrayList<String> brandList,ArrayList<String> priceList,ArrayList<String> sizeList,ArrayList<String> colorList) {
        FilterCategoriesFragment fragment = new FilterCategoriesFragment();
        Bundle bundle = new Bundle();
        bundle.putString("collectionName",collectionName);
        bundle.putString("categoryName", categoryName);
        bundle.putInt("categoryId", categoryId);
        bundle.putStringArrayList("brandList", brandList);
        bundle.putStringArrayList("priceList",priceList);
        bundle.putStringArrayList("sizeList",sizeList);
        bundle.putStringArrayList("colorList",colorList);

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
        ArrayList<String> brandList = getArguments().getStringArrayList("brandList");
        ArrayList<String> priceList = getArguments().getStringArrayList("priceList");
        ArrayList<String> sizeList = getArguments().getStringArrayList("sizeList");
        ArrayList<String> colorList = getArguments().getStringArrayList("colorList");


        categoryListLayout = (LinearLayout)inflater.inflate(R.layout.filter_categories_frag,container,false);

        if (collectionName !=null) {
            if (brandList != null || priceList != null || sizeList != null || colorList != null) {
                categoryPagerAdapter = new CategoryPagerAdapter(getActivity().getSupportFragmentManager(), getActivity(), collectionName, brandList, priceList, sizeList, colorList);
            } else {
                categoryPagerAdapter = new CategoryPagerAdapter(getActivity().getSupportFragmentManager(), getActivity(), collectionName);

            }


//            categoryPagerAdapter = new CategoryPagerAdapter(getActivity().getSupportFragmentManager(), getActivity(), collectionName,categoryId,categoryName,brandList,priceList,sizeList,colorList);

        }
        else {
            if (brandList != null || priceList != null || sizeList != null || colorList != null){
                categoryPagerAdapter = new CategoryPagerAdapter(getActivity().getSupportFragmentManager(), getActivity(), categoryId, categoryName,brandList, priceList, sizeList, colorList );

            }else{
                categoryPagerAdapter = new CategoryPagerAdapter(getActivity().getSupportFragmentManager(), getActivity(), categoryId, categoryName);

            }
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
