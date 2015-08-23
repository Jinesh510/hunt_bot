package jinesh.urbanhunt_test;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.GridView;
import android.widget.ImageView;

import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

import java.util.List;

/**
 * Created by Jinesh on 15/08/15.
 */

public class CollapseActivity extends AppCompatActivity {

    CollapsingToolbarLayout collapsingToolbarLayout;
    AppBarLayout appBarLayout;
    private Toolbar toolbar;
    private ParseQueryAdapter.QueryFactory<Product_1> pqf;
    CoordinatorLayout coordinatorLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dummy_scroll);
        coordinatorLayout = (CoordinatorLayout)findViewById(R.id.main_content);


        appBarLayout = (AppBarLayout)findViewById(R.id.appbar);
        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("Collapse");
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);

        ImageView iv = (ImageView)findViewById(R.id.backdrop);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        NestedScrollView nv = (NestedScrollView)findViewById(R.id.nested);
        GridView gvProducts = (GridView)findViewById(R.id.gvProducts);
        fetchProducts();
        ProductAdapter productAdapter;
        productAdapter = new ProductAdapter(this,pqf);
        productAdapter.setObjectsPerPage(8);
        productAdapter.setPaginationEnabled(true);
        gvProducts.setAdapter(productAdapter);


        final ProgressDialog p = new ProgressDialog(this);

        productAdapter.addOnQueryLoadListener(new ParseQueryAdapter.OnQueryLoadListener<Product_1>() {
            @Override
            public void onLoading() {
//                progressView.startAnimation();
                p.show();
            }

            @Override
            public void onLoaded(List<Product_1> list, Exception e) {
                p.hide();


            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            gvProducts.setNestedScrollingEnabled(true);
        }
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
