package jinesh.urbanhunt_test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

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
    LinearLayout brandProfileLayout;
    String brandId;
    Brand currentBrand;


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

        brandProfileLayout = (LinearLayout)inflater.inflate(R.layout.brand_profile, container, false);

        bName = (TextView)brandProfileLayout.findViewById(R.id.brandName);
        brandName = getArguments().getString("brandName");

        bName.setText(brandName);

        brandLogoUrl = getArguments().getString("brandLogoUrl");
        brandLogo = (ImageView)brandProfileLayout.findViewById(R.id.brandLogo);
        Picasso.with(getActivity()).load(brandLogoUrl).into(brandLogo);

        ImageView favBrand = (ImageView)brandProfileLayout.findViewById(R.id.favBrand);

        storeList = (ListView)brandProfileLayout.findViewById(R.id.storeList);

        final ArrayList<Store> storeArrayList = new ArrayList<>();
        storeListAdapter = new StoreListAdapter(getActivity(),storeArrayList);

        storeList.setAdapter(storeListAdapter);

        ParseQuery<Store> storeParseQuery = ParseQuery.getQuery(Store.class);
        storeParseQuery.whereEqualTo("storeBrand", brandName);
        storeParseQuery.findInBackground(new FindCallback<Store>() {
            @Override
            public void done(List<Store> list, ParseException e) {

                for (Store s : list) {
                    storeArrayList.add(s);
                }
                storeListAdapter.setStoreList(storeArrayList);
            }
        });


        brandPop = (HorizontalListView)brandProfileLayout.findViewById(R.id.brandPop);
        final ArrayList<Product_1> brandProductList = new ArrayList<>();

        horizontalImageAdapter = new HorizontalImageAdapter(getActivity(),brandProductList);
        brandPop.setAdapter(horizontalImageAdapter);

        brandId = getArguments().getString("brandId");
        ParseQuery<Brand> brandParseQuery = ParseQuery.getQuery(Brand.class);
        brandParseQuery.getInBackground(brandId, new GetCallback<Brand>() {
            @Override
            public void done(Brand brand, ParseException e) {
                 currentBrand = brand;
            }
        });

        ParseQuery<Product_1> productParseQuery = ParseQuery.getQuery(Product_1.class);
        productParseQuery.whereEqualTo("brandName", brandName);
//        productParseQuery.whereEqualTo("brandPointer",currentBrand);
        productParseQuery.whereEqualTo("popular", true);
        productParseQuery.findInBackground(new FindCallback<Product_1>() {
            @Override
            public void done(List<Product_1> list, ParseException e) {

                int i;
                for (i = 0; i < 5; i++) {
                    brandProductList.add(list.get(i));
                }
                horizontalImageAdapter.setImageList(brandProductList);
            }
        });


        btnBrandCollection = (Button)brandProfileLayout.findViewById(R.id.btnBrandCollection);
        btnBrandCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        final ParseUser user = ParseUser.getCurrentUser();

        favBrand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.add("favBrand",brandId);
                user.saveEventually();
//                openSuccessDialog();
            }
        });

        return brandProfileLayout;
    }



}
