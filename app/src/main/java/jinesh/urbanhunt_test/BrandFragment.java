package jinesh.urbanhunt_test;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

import java.util.ArrayList;

/**
 * Created by Jinesh on 23/07/15.
 */
public class BrandFragment extends Fragment {

    private HorizontalListView categoryGrid;
    private RelativeLayout brandGridLayout;
    private CategoriesArrayAdapter categoriesArrayAdapter;
    private BrandsAdapter brandsAdapter;
    private GridView brandList;
    private Category category;
    private Collection collection;
    private ParseQueryAdapter.QueryFactory<Brand> pqf;
    private Brand brand;


//    LinearLayout homeLayout;
//    BrandFragmentPagerAdapter brandFragmentPagerAdapter;
//    ViewPager viewPager;
//    TabLayout tabLayout;

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

        brandGridLayout = (RelativeLayout)inflater.inflate(R.layout.brand_list,container,false);
//
//        brandFragmentPagerAdapter = new BrandFragmentPagerAdapter(getActivity().getSupportFragmentManager(),this.getActivity());
//        viewPager = (ViewPager)homeLayout.findViewById(R.id.viewpager);
////        viewPager.setAdapter(new HomeFragmentPagerAdapter(getActivity().getSupportFragmentManager(), getActivity()));
//
//        viewPager.setAdapter(brandFragmentPagerAdapter);
//        tabLayout = (TabLayout)homeLayout.findViewById(R.id.sliding_tabs);
//        tabLayout.setupWithViewPager(viewPager);

        ArrayList<Category> categoryArray = new ArrayList<>();
        ArrayList<Collection> brandListArray = new ArrayList<>();

        Bitmap apparels  = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.apparels_cat);
        Bitmap bags  = BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.bags_cat);
//        Bitmap beauty  = BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.beauty_cat);
        Bitmap accesories  = BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.accesories_cat);
        Bitmap footwear  = BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.footwear_cat);
        Bitmap watches  = BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.watches_cat);

        categoryArray.add(new Category(1,"Apparels",apparels));
        categoryArray.add(new Category(2,"Bags",bags));
//        categoryArray.add(new Category("Beauty",beauty));
        categoryArray.add(new Category(3,"Accessories",accesories));
        categoryArray.add(new Category(4,"Footwear",footwear));
        categoryArray.add(new Category(5,"Others",watches));

        categoryGrid = (HorizontalListView)brandGridLayout.findViewById(R.id.categoryList);

        categoriesArrayAdapter = new CategoriesArrayAdapter(getActivity(),categoryArray);
        categoryGrid.setAdapter(categoriesArrayAdapter);

        brandList = (GridView)brandGridLayout.findViewById(R.id.gvBrands);

        brandsAdapter = new BrandsAdapter(getActivity(),Brand.class);

        brandList.setAdapter(brandsAdapter);

        categoryGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                category = (Category) adapterView.getItemAtPosition(i);
                int categoryId = category.getId();
                final String categoryName = category.getCategoryName();


                pqf = new ParseQueryAdapter.QueryFactory<Brand>() {
                    @Override
                    public ParseQuery<Brand> create() {
                        ParseQuery<Brand> brandParseQuery = ParseQuery.getQuery(Brand.class);
                        brandParseQuery.whereEqualTo("category", categoryName);

                        return brandParseQuery;
                    }
                };

                brandsAdapter = new BrandsAdapter(getActivity(), pqf);
                brandList.setAdapter(brandsAdapter);
                brandsAdapter.loadObjects();

//                Intent intent = new Intent(getActivity(), FilterCategoriesActivity.class);
//                intent.putExtra("category", categoryId);
//                intent.putExtra("categoryName", categoryName);
//                getActivity().startActivity(intent);

            }
        });

        brandList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                brand = (Brand) adapterView.getItemAtPosition(i);
                String brandName = brand.getName();
                String brandLogoUrl = brand.getLogo().getUrl();
                Intent intent = new Intent(getActivity(), BrandProfilePage.class);
                intent.putExtra("brandName",brandName);
                intent.putExtra("brandLogoUrl",brandLogoUrl);
                getActivity().startActivity(intent);


//                intent.putExtra("category", categoryId);
//                intent.putExtra("categoryName", categoryName);
//                getActivity().startActivity(intent);


            }
        });

        return brandGridLayout;
    }
}
