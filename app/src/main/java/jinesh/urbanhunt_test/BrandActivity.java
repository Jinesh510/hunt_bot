package jinesh.urbanhunt_test;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * Created by Jinesh on 14/08/15.
 */
public class BrandActivity extends AppCompatActivity implements DetailViewInterface {

    Toolbar toolbar;
    String brand;
    String brandLogoUrl;
    private String brandId;
    AppBarLayout appBarLayout;
    CoordinatorLayout coordinatorLayout;
    FrameLayout flContent;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.brand_activity);


//        linearLayout = (LinearLayout)findViewById(R.id.linearLayout);
        coordinatorLayout = (CoordinatorLayout)findViewById(R.id.main_content);


        appBarLayout = (AppBarLayout)findViewById(R.id.appbar);


        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        flContent = (FrameLayout)findViewById(R.id.flContent);

        brand = getIntent().getStringExtra("brandName");
        brandLogoUrl = getIntent().getStringExtra("brandLogoUrl");
        brandId = getIntent().getStringExtra("brandId");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            flContent.setNestedScrollingEnabled(true);
        }

        Fragment brandProfilePage = BrandProfilePage.newInstance(brandId, brand, brandLogoUrl);
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, brandProfilePage).commit();



    }

    @Override
    public void openDetailView(String id) {

        Fragment productDetail = ProductDetail.newInstance(id);
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, productDetail).commit();

    }


}
