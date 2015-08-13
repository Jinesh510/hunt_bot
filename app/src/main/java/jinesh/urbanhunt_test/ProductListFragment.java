package jinesh.urbanhunt_test;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

import java.util.List;

/**
 * Created by Jinesh on 10/08/15.
 */
public class ProductListFragment extends Fragment {

    private CoordinatorLayout productListLayout;
    private SwipeRefreshLayout swipeContainer;
    private ParseQueryAdapter.QueryFactory<Product_1> pqf;
    private ProductAdapter productAdapter;
    private GridView gvProducts;
    RelativeLayout relativeLayout;
    String category;
    String subCategory;
    String collectionName;
    boolean i;
    FrameLayout shadowView;

    public static ProductListFragment newInstance( String collectionName, String category, boolean i) {
        ProductListFragment fragment = new ProductListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("category", category);
        bundle.putString("collectionName", collectionName);
        bundle.putBoolean("flag", i);
        fragment.setArguments(bundle);
        return fragment;
    }
    public static ProductListFragment newInstance( String category, String subCategory) {
        ProductListFragment fragment = new ProductListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("category", category);
        bundle.putString("subCategory", subCategory);
        fragment.setArguments(bundle);
        return fragment;
    }

    public static ProductListFragment newInstance( String category) {
        ProductListFragment fragment = new ProductListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("category", category);
        fragment.setArguments(bundle);
        return fragment;
    }

    public static ProductListFragment newInstance() {
        ProductListFragment fragment = new ProductListFragment();
        Bundle bundle = new Bundle();
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

        productListLayout = (CoordinatorLayout)inflater.inflate(R.layout.product_list, container, false);
//        swipeContainer = (SwipeRefreshLayout) productListLayout.findViewById(R.id.swipeContainer);

        gvProducts = (GridView)productListLayout.findViewById(R.id.gvProducts);
        shadowView = (FrameLayout)productListLayout.findViewById(R.id.shadowView);
        relativeLayout = (RelativeLayout)productListLayout.findViewById(R.id.relativeLayout);

        category = getArguments().getString("category");
        subCategory = getArguments().getString("subCategory");
        collectionName = getArguments().getString("collectionName");
        i = getArguments().getBoolean("flag");




        if(collectionName == null && category == null && subCategory == null){
            fetchProducts();
        }else if(collectionName != null ){
            fetchProducts(collectionName,category,i);
        }else if(subCategory != null){
            fetchProducts(category,subCategory);
        }

        productAdapter = new ProductAdapter(getActivity(),pqf);
        productAdapter.setObjectsPerPage(8);
        Log.d("list","true");
        productAdapter.setPaginationEnabled(true);
        gvProducts.setAdapter(productAdapter);

        final ProgressDialog p = new ProgressDialog(getActivity());

        productAdapter.addOnQueryLoadListener(new ParseQueryAdapter.OnQueryLoadListener<Product_1>() {
            @Override
            public void onLoading() {
                p.show();
            }

            @Override
            public void onLoaded(List<Product_1> list, Exception e) {

                p.hide();


            }
        });

        return productListLayout;
    }



    private void fetchProducts(final String collectionName,final String category, boolean i) {

        pqf = new ParseQueryAdapter.QueryFactory<Product_1>() {
            @Override
            public ParseQuery<Product_1> create() {
                ParseQuery<Product_1> productParseQuery = ParseQuery.getQuery(Product_1.class);
                if(category == "All"){
                    productParseQuery.whereEqualTo("collection",collectionName);
                }else {
                    productParseQuery.whereEqualTo("collection", collectionName);
                    productParseQuery.whereEqualTo("category", category);
                }
                return productParseQuery;
            }
        };
    }

    private void fetchProducts(final String category, final String subCategory) {

        pqf = new ParseQueryAdapter.QueryFactory<Product_1>() {
            @Override
            public ParseQuery<Product_1> create() {
                ParseQuery<Product_1> productParseQuery = ParseQuery.getQuery(Product_1.class);
                if(subCategory == "All"){
                    productParseQuery.whereEqualTo("category",category);
                }else{
                    productParseQuery.whereEqualTo("category",category);
                    productParseQuery.whereEqualTo("subCategory",subCategory);
                }

                return productParseQuery;
            }
        };
    }


    private void fetchProducts(final String category) {

        pqf = new ParseQueryAdapter.QueryFactory<Product_1>() {
            @Override
            public ParseQuery<Product_1> create() {
                ParseQuery<Product_1> productParseQuery = ParseQuery.getQuery(Product_1.class);
                productParseQuery.whereEqualTo("category",category);
                return productParseQuery;
            }
        };
    }

    private void fetchProducts(){
        pqf = new ParseQueryAdapter.QueryFactory<Product_1>() {
            @Override
            public ParseQuery<Product_1> create() {
                ParseQuery<Product_1> productParseQuery = ParseQuery.getQuery(Product_1.class);

                return productParseQuery;
            }
        };
    }
}
