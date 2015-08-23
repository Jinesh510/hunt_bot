package jinesh.urbanhunt_test;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Jinesh on 13/08/15.
 */
public class BrandProfilePage extends Fragment {

    HorizontalListView brandPop;
    Button btnBrandCollection;
    ImageView brandLogo;
    ImageView favBrand;
    TextView bName;
    String brandName;
    StoreListAdapter storeListAdapter;
    ListView storeList;
    HorizontalImageAdapter horizontalImageAdapter;
    Toolbar toolbar;
    String brandLogoUrl;
//    LinearLayout brandProfileLayout;
    View  brandProfileLayout;
    String brandId;
    Brand currentBrand;
    private ArrayList<String> favBrandsList;
    boolean flag;

    public BrandProfilePage() {

    }

    public static BrandProfilePage newInstance(String brandId, String brandName, String brandLogoUrl) {
        BrandProfilePage fragment = new BrandProfilePage();
        Bundle bundle = new Bundle();
        bundle.putString("brandId",brandId);
        bundle.putString("brandName",brandName);
        bundle.putString("brandLogoUrl",brandLogoUrl);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

//        brandProfileLayout = (LinearLayout)inflater.inflate(R.layout.brand_profile, container, false);
        brandProfileLayout = inflater.inflate(R.layout.brand_profile, container, false);
//        NestedScrollView nsv = (NestedScrollView)brandProfileLayout.findViewById(R.id.nsv);

        bName = (TextView)brandProfileLayout.findViewById(R.id.brandName);
        brandName = getArguments().getString("brandName");

        bName.setText(brandName);

        brandLogoUrl = getArguments().getString("brandLogoUrl");
        brandLogo = (ImageView)brandProfileLayout.findViewById(R.id.brandLogo);
        Picasso.with(getActivity()).load(brandLogoUrl).into(brandLogo);

        final ImageView favBrand = (ImageView)brandProfileLayout.findViewById(R.id.favBrand);

        flag = true;


//        storeList = (ListView)brandProfileLayout.findViewById(R.id.storeList);
//
//        final ArrayList<Store> storeArrayList = new ArrayList<>();
//        storeListAdapter = new StoreListAdapter(getActivity(),storeArrayList);
//
//        storeList.setAdapter(storeListAdapter);

//        ParseQuery<Store> storeParseQuery = ParseQuery.getQuery(Store.class);
//        storeParseQuery.whereEqualTo("storeBrand", brandName);
//        storeParseQuery.findInBackground(new FindCallback<Store>() {
//            @Override
//            public void done(List<Store> list, ParseException e) {
//
//                for (Store s : list) {
//                    storeArrayList.add(s);
//                }
//                storeListAdapter.setStoreList(storeArrayList);
//            }
//        });


//        brandPop = (HorizontalListView)brandProfileLayout.findViewById(R.id.brandPop);
//        final ArrayList<Product_1> brandProductList = new ArrayList<>();
//
//        horizontalImageAdapter = new HorizontalImageAdapter(getActivity(),brandProductList);
//        brandPop.setAdapter(horizontalImageAdapter);
//
        brandId = getArguments().getString("brandId");
        final Bitmap favFilled  = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.fav_filled);
        final Bitmap favUnfilled  = BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.fav_unfill);

        final ParseUser user = ParseUser.getCurrentUser();
        user.fetchInBackground(new GetCallback<ParseObject>() {

            @Override
            public void done(ParseObject parseObject, ParseException e) {
                favBrandsList = (ArrayList<String>)parseObject.get("favBrand");
                final String[] array = favBrandsList.toArray(new String[favBrandsList.size()]);

                if (favBrandsList.contains(brandId)){
                    flag = false;
                    favBrand.setImageBitmap(favFilled);
                }else{
                    favBrand.setImageBitmap(favUnfilled);
                }

                favBrand.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(flag == true) {
                            user.add("favBrand", brandId);
                            favBrand.setImageBitmap(favFilled);
                            flag = false;
                        }else{
                            user.removeAll("favBrand", Arrays.asList(brandId));
                            favBrand.setImageBitmap(favUnfilled);
                            flag = true;
                        }
                        user.saveEventually();
//                openSuccessDialog();

                    }
                });
            }
        });
//        ParseQuery<Brand> brandParseQuery = ParseQuery.getQuery(Brand.class);
//        brandParseQuery.getInBackground(brandId, new GetCallback<Brand>() {
//            @Override
//            public void done(Brand brand, ParseException e) {
//                 currentBrand = brand;
//            }
//        });
//
//        ParseQuery<Product_1> productParseQuery = ParseQuery.getQuery(Product_1.class);
//        productParseQuery.whereEqualTo("brandName", brandName);
////        productParseQuery.whereEqualTo("brandPointer",currentBrand);
//        productParseQuery.whereEqualTo("popular", true);
//        productParseQuery.findInBackground(new FindCallback<Product_1>() {
//            @Override
//            public void done(List<Product_1> list, ParseException e) {
//
//                int i;
//                for (i = 0; i < 5; i++) {
//                    brandProductList.add(list.get(i));
//                }
//                horizontalImageAdapter.setImageList(brandProductList);
//            }
//        });


//        btnBrandCollection = (Button)brandProfileLayout.findViewById(R.id.btnBrandCollection);
//        btnBrandCollection.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
        Button button = (Button)brandProfileLayout.findViewById(R.id.viewStores);
        BrandFragmentPagerAdapter  brandFragmentPagerAdapter = new BrandFragmentPagerAdapter(getActivity().getSupportFragmentManager(),getActivity(),brandName);
        TabLayout tabLayout = (TabLayout)brandProfileLayout.findViewById(R.id.sliding_tabs);
        ViewPager viewPager = (ViewPager)brandProfileLayout.findViewById(R.id.viewpager);

        viewPager.setAdapter(brandFragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);


        return brandProfileLayout;
    }



}
