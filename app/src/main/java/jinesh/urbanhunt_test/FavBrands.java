package jinesh.urbanhunt_test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Jinesh on 14/07/15.
 */
public class FavBrands extends Fragment{

    GridView gvFavBrands;
    private ParseUser user;
    ArrayList<String> brandArrayList;
    private ParseQueryAdapter.QueryFactory<Brand> pqf;
    BrandsAdapter brandsAdapter;

    public static FavBrands newInstance() {
        FavBrands fragment = new FavBrands();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.brand_list,container,false);

        gvFavBrands = (GridView)v.findViewById(R.id.gvBrands);
        user =  ParseUser.getCurrentUser();

        user.fetchInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject parseObject, ParseException e) {


                brandArrayList = (ArrayList<String>) parseObject.get("favBrand");
                Log.d("brSize", "" + brandArrayList.size());
                final String[] array = brandArrayList.toArray(new String[brandArrayList.size()]);


                pqf = new ParseQueryAdapter.QueryFactory<Brand>() {
                    @Override
                    public ParseQuery<Brand> create() {
                        ParseQuery<Brand> productParseQuery = ParseQuery.getQuery(Brand.class);
//                wishList = (ArrayList<String>)user.get("wishList");
                        String[] b = {"7qN5JqQqNO", "tGMwe8os8E"};
                        Log.d("bSize", b[1]);
//                productParseQuery.whereContainedIn("objectId",Arrays.asList(wishList));
                        productParseQuery.whereContainedIn("objectId", Arrays.asList(array));
                        return productParseQuery;
                    }
                };

                brandsAdapter = new BrandsAdapter(getActivity(), pqf);
                brandsAdapter.setObjectsPerPage(8);
                brandsAdapter.setPaginationEnabled(true);

                gvFavBrands.setAdapter(brandsAdapter);
            }
        });


        return v;
//        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
