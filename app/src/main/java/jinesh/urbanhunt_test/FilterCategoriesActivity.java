package jinesh.urbanhunt_test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Jinesh on 21/07/15.
 */
public class FilterCategoriesActivity extends AppCompatActivity implements DetailViewInterface{

    Toolbar toolbar;
    String id;
    boolean flag;
    String collectionName;
    String categoryName;
    int categoryId;
    ArrayList<String> brandArray;
    ArrayList<String> priceArray;
    ArrayList<String> sizeArray;
    ArrayList<String> colorArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter_categories);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        collectionName = getIntent().getStringExtra("collectionName");

        categoryId = getIntent().getIntExtra("category", 0);
        categoryName = getIntent().getStringExtra("categoryName");
//        toolbar.setTitle(strCategoryName);

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


//    @Override
//    onBackPressed()
}
