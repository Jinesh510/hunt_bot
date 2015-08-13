package jinesh.urbanhunt_test;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * Created by Jinesh on 21/07/15.
 */
public class FilterCategoriesActivity extends AppCompatActivity {

    Toolbar toolbar;
    Fragment huntListFragment;
//    int id;
    String id;
    CategoryPagerAdapter categoryPagerAdapter;
    ViewPager viewPager;
    private TabLayout tabLayout;
    boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter_categories);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String collectionName = getIntent().getStringExtra("collectionName");

        int categoryId = getIntent().getIntExtra("category", 0);
        String categoryName = getIntent().getStringExtra("categoryName");
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


//    @Override
//    onBackPressed()
}
