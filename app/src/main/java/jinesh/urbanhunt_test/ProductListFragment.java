package jinesh.urbanhunt_test;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jinesh on 10/08/15.
 */
public class ProductListFragment extends Fragment {

    private CoordinatorLayout productListLayout;
    private ParseQueryAdapter.QueryFactory<Product_1> pqf;
    private ProductAdapter productAdapter;
    private GridView gvProducts;
    RelativeLayout relativeLayout;
    String category;
    String subCategory;
    String collectionName;
    boolean i;
    FrameLayout shadowView;
    Product_1 product;

    ArrayList<String> brandList;
    ArrayList<String> priceList;
    ArrayList<String> sizeList;
    ArrayList<String> colorList;

    String[] brandArray;
    String[] priceArray;
    String[] sizeArray;
    String[] colorArray;


    public static ProductListFragment newInstance( String collectionName, String category, boolean i, ArrayList<String> brandList,ArrayList<String> priceList,ArrayList<String> sizeList,ArrayList<String> colorList) {
        ProductListFragment fragment = new ProductListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("category", category);
        bundle.putString("collectionName", collectionName);
        bundle.putBoolean("flag", i);
        bundle.putStringArrayList("brandList", brandList);
        bundle.putStringArrayList("priceList", priceList);
        bundle.putStringArrayList("sizeList", sizeList);
        bundle.putStringArrayList("colorList", colorList);
        fragment.setArguments(bundle);
        return fragment;

    }
    public static ProductListFragment newInstance( String category, String subCategory,ArrayList<String> brandList,ArrayList<String> priceList,ArrayList<String> sizeList,ArrayList<String> colorList) {
        ProductListFragment fragment = new ProductListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("category", category);
        bundle.putString("subCategory", subCategory);
        bundle.putStringArrayList("brandList", brandList);
        bundle.putStringArrayList("priceList",priceList);
        bundle.putStringArrayList("sizeList", sizeList);
        bundle.putStringArrayList("colorList", colorList);

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
//        shadowView = (FrameLayout)productListLayout.findViewById(R.id.shadowView);
//        relativeLayout = (RelativeLayout)productListLayout.findViewById(R.id.relativeLayout);

        category = getArguments().getString("category");
        subCategory = getArguments().getString("subCategory");
        collectionName = getArguments().getString("collectionName");
        i = getArguments().getBoolean("flag");

        brandList = getArguments().getStringArrayList("brandList");
        priceList = getArguments().getStringArrayList("priceList");
        sizeList = getArguments().getStringArrayList("sizeList");
        colorList = getArguments().getStringArrayList("colorList");

        if(brandList !=null){
        brandArray = brandList.toArray(new String[brandList.size()]);
        }
        if(priceList !=null){
            priceArray = priceList.toArray(new String[priceList.size()]);

        }
        if(sizeList !=null){
            sizeArray = sizeList.toArray(new String[sizeList.size()]);
        }

        if(colorList !=null){
            colorArray = brandList.toArray(new String[colorList.size()]);
        }




        if(collectionName == null && category == null && subCategory == null){
            fetchProducts();
        }else if(collectionName != null ){
            fetchProducts(collectionName,category,i,brandArray,priceArray,sizeArray,colorArray);
        }else if(subCategory != null){
            fetchProducts(category,subCategory,brandArray,priceArray,sizeArray,colorArray);
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

        gvProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                product = (Product_1)adapterView.getItemAtPosition(i);
                String productId = product.getObjectId();
                Activity activity = getActivity();
                if(activity instanceof FilterCategoriesActivity){
                    FilterCategoriesActivity filterCategoriesActivity = (FilterCategoriesActivity)activity;
                    filterCategoriesActivity.openDetailView(productId);
                }
            }
        });

        return productListLayout;
    }



    private void fetchProducts(final String collectionName,final String category, boolean i, final String[] brandList, final String[] priceList, final String[] sizeList, final String[] colorList) {

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

                if(brandList !=null){
                    productParseQuery.whereContainedIn("brandName", Arrays.asList(brandList));
                }
                if(priceList !=null){
                    productParseQuery.whereContainedIn("priceRange", Arrays.asList(priceList));
                }
                if(sizeList !=null){
                    productParseQuery.whereContainedIn("size",Arrays.asList(sizeList));
                }
                if(colorList !=null){
                    productParseQuery.whereContainedIn("color", Arrays.asList(colorList));
                }

                return productParseQuery;
            }
        };
    }

    private void fetchProducts(final String category, final String subCategory, final String[] brandList, final String[] priceList, final String[] sizeList, final String[] colorList) {

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

                if(brandList !=null){
                    productParseQuery.whereContainedIn("brandName", Arrays.asList(brandList));
                }
                if(priceList !=null){
                    productParseQuery.whereContainedIn("priceRange", Arrays.asList(priceList));
                }
                if(sizeList !=null){
                    productParseQuery.whereContainedIn("size",Arrays.asList(sizeList));
                }
                if(colorList !=null){
                    productParseQuery.whereContainedIn("color",Arrays.asList(colorList));
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
