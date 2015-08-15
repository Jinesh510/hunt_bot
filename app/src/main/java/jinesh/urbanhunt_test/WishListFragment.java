package jinesh.urbanhunt_test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
 * Created by Jinesh on 14/08/15.
 */
public class WishListFragment extends Fragment {

    private CoordinatorLayout wishListLayout;

    private ParseQueryAdapter.QueryFactory<Product_1> pqf;
    private ProductAdapter productAdapter;
    GridView gvWishes;
    ArrayList<Product_1> wishProducts;
    ArrayList<String> wishList;
//    String[] wishList;
    ParseUser user;


    public static WishListFragment newInstance() {
        WishListFragment fragment = new WishListFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        wishListLayout = (CoordinatorLayout)inflater.inflate(R.layout.product_list, container, false);
        gvWishes = (GridView)wishListLayout.findViewById(R.id.gvProducts);

        user =  ParseUser.getCurrentUser();


//        Log.d("usr", user.getUsername());
//        wishList = (ArrayList<Product_1>)user.get("wishList");

//        Log.d("wSize", "" + wishList.size());
//        Log.d("wish",wishList.get(0));
//        wishList = new ArrayList<String>();
//        wishList.add("V5hik7xq6N");
//        wishList.add("VLi8DZouNl");
        wishProducts = new ArrayList<>();

//        wishListAdapter = new WishListAdapter(getActivity(),wishProducts);


        user.fetchInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject parseObject, ParseException e) {
//                wishProducts = (ArrayList<Product_1>) parseObject.get("wishList");
                wishList = (ArrayList<String>)parseObject.get("wishList");
                final String[] array = wishList.toArray(new String[wishList.size()]);
                Log.d("wSize","" + wishList.get(0));

                pqf = new ParseQueryAdapter.QueryFactory<Product_1>() {
                    @Override
                    public ParseQuery<Product_1> create() {
                        ParseQuery<Product_1> productParseQuery = ParseQuery.getQuery(Product_1.class);
//                wishList = (ArrayList<String>)user.get("wishList");
                        String[] b = {"7qN5JqQqNO","tGMwe8os8E"};
                        Log.d("bSize", b[1]);
//                productParseQuery.whereContainedIn("objectId",Arrays.asList(wishList));
                        productParseQuery.whereContainedIn("objectId",Arrays.asList(array));
                        return productParseQuery;
                    }
                };

                productAdapter = new ProductAdapter(getActivity(),pqf);
                productAdapter.setObjectsPerPage(8);
                productAdapter.setPaginationEnabled(true);




//                Log.d("wish", "" + wishProducts.size());

                gvWishes.setAdapter(productAdapter);

//                wishListAdapter.setProductList(wishProducts);

            }

        });

        Log.d("y", "u");

//        gvWishes.setAdapter(wishListAdapter);

//
//        ParseQuery<ParseUser> userParseQuery = ParseUser.getQuery();
//        userParseQuery.whereEqualTo("objectId",user.getObjectId());
//        userParseQuery.getFirstInBackground(new GetCallback<ParseUser>() {
//            @Override
//            public void done(ParseUser parseUser, ParseException e) {
////                wishList = (ArrayList<String>)parseUser.get("wishList");
//                wishList = (String[])parseUser.get("wishList");
//
//                Log.d("a", "" + wishList);
//
//
//            }
//        });

//        if(wishList != null) {
////            fetchproducts();
//        }

//        fetchproducts();

//        productAdapter = new ProductAdapter(getActivity(),pqf);
//        productAdapter.setObjectsPerPage(8);
//        productAdapter.setPaginationEnabled(true);
//        gvWishes.setAdapter(productAdapter);


//        final ProgressDialog p = new ProgressDialog(getActivity());
//
//        productAdapter.addOnQueryLoadListener(new ParseQueryAdapter.OnQueryLoadListener<Product_1>() {
//            @Override
//            public void onLoading() {
//                p.show();
//            }
//
//            @Override
//            public void onLoaded(List<Product_1> list, Exception e) {
//
//                p.hide();
//
//
//            }
//        });

        gvWishes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Product_1 product = (Product_1)adapterView.getItemAtPosition(i);
                String productId = product.getObjectId();
                ((MainActivity)getActivity()).openDetailView(productId);

            }
        });


        return wishListLayout;

    }

    private void fetchproducts() {

        pqf = new ParseQueryAdapter.QueryFactory<Product_1>() {
            @Override
            public ParseQuery<Product_1> create() {
                ParseQuery<Product_1> productParseQuery = ParseQuery.getQuery(Product_1.class);
//                wishList = (ArrayList<String>)user.get("wishList");
                String[] b = {"7qN5JqQqNO","tGMwe8os8E"};
//                productParseQuery.whereContainedIn("objectId",Arrays.asList(wishList));
                productParseQuery.whereContainedIn("objectId", Arrays.asList(wishList));
                return productParseQuery;
            }
        };
    }
}
