package jinesh.urbanhunt_test;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;

/**
 * Created by Jinesh on 21/07/15.
 */
public class FilterCategoriesActivity extends AppCompatActivity implements DetailViewInterface{

    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayout;
    AppBarLayout appBarLayout;
    CoordinatorLayout coordinatorLayout;

    String id;
    boolean flag;
    String collectionName;
    String categoryName;
    int categoryId;
    ArrayList<String> brandArray;
    ArrayList<String> priceArray;
    ArrayList<String> sizeArray;
    ArrayList<String> colorArray;

    ArrayList<Boolean> brandPositionArray;
    ArrayList<Boolean> pricePositionArray;
    ArrayList<Boolean> sizePositionArray;
    ArrayList<Boolean> colorPositionArray;

    FrameLayout flContent;
//    NestedScrollView flContent;
    Menu menu_1;
    FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.filter_categories);
//        LinearLayout lL = (LinearLayout)findViewById(R.id.lL);

//        coordinatorLayout = (CoordinatorLayout)findViewById(R.id.main_content);

//        appBarLayout = (AppBarLayout)findViewById(R.id.appbar);
//        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
//        collapsingToolbarLayout.setTitle("Collapse");
//        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);

//        fab = (FloatingActionButton)findViewById(R.id.fab);
//
//        ImageView iv = (ImageView)findViewById(R.id.backdrop);

//        flContent = (FrameLayout)findViewById(R.id.flContent);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
        flContent = (FrameLayout)findViewById(R.id.flContent);

        collectionName = getIntent().getStringExtra("collectionName");

        categoryId = getIntent().getIntExtra("category", 0);
        categoryName = getIntent().getStringExtra("categoryName");
//        toolbar.setTitle(strCategoryName);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            flContent.setNestedScrollingEnabled(true);
//        }

//        if(fab.getVisibility() == View.INVISIBLE){
//        invalidateOptionsMenu();
//        }
//        AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) collapsingToolbarLayout.getLayoutParams();
//        params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL|AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS_COLLAPSED);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        flag = true;
        if (flag) {

            Fragment filterCategoriesFragment = FilterCategoriesFragment.newInstance(collectionName, categoryName, categoryId);
            FragmentManager fragmentManager = this.getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.flContent, filterCategoriesFragment).commit();


        }


//
//        huntListFragment = HuntListActivity.newInstance(categoryId);
//        FragmentManager fm = getSupportFragmentManager();
//        FragmentTransaction ft = fm.beginTransaction();
//        ft.replace(R.id.huntCategory, huntListFragment).commit();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu_1 = menu;
        getMenuInflater().inflate(R.menu.menu_filter, menu);

        MenuItem m = menu_1.findItem(R.id.action_search);
//        if(fab.getVisibility() == View.VISIBLE){
//            m.setVisible(false);
//        }else{
//            m.setVisible(true);
//        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void openFilterList(){

        Fragment filter_products = Filter_Products.newInstance();
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, filter_products).commit();

    }

    @Override
    public void openDetailView(String id) {

        Fragment productDetail = ProductDetail.newInstance(id);
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, productDetail).commit();

    }

    public void openFilteredList(ArrayList<String> brandList,ArrayList<String> priceList,ArrayList<String> sizeList,ArrayList<String> colorList){

        brandArray =brandList;
        priceArray = priceList;
        sizeArray = sizeList;
        colorArray = colorList;
        Fragment filterCategoriesFragment = FilterCategoriesFragment.newInstance(collectionName, categoryName, categoryId,brandList,priceList,sizeList,colorList);
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, filterCategoriesFragment).commit();

    }

    public ArrayList<String> getBrandArrayList(){

        return brandArray;
    }

    public ArrayList<String> getPriceArrayList(){

        return priceArray;
    }
    public ArrayList<String> getSizeArrayList(){

        return sizeArray;
    }
    public ArrayList<String> getColorArrayList(){

        return colorArray;
    }



    public void setBoolean(ArrayList<Boolean> brand, ArrayList<Boolean> price, ArrayList<Boolean> size, ArrayList<Boolean> color){
        brandPositionArray = brand;
        pricePositionArray = price;
        sizePositionArray = size;
        colorPositionArray = color;

    }

    public ArrayList<Boolean> getBrandPositionArray(){
        return brandPositionArray;
    }

    public ArrayList<Boolean> getPricePositionArray(){
        return pricePositionArray;
    }

    public ArrayList<Boolean> getSizePositionArray() {
        return sizePositionArray;
    }

    public ArrayList<Boolean> getColorPositionArray() {
        return colorPositionArray;
    }


//    @Override
//    onBackPressed()
}


