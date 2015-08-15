package jinesh.urbanhunt_test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by Jinesh on 14/08/15.
 */
public class BrandActivity extends AppCompatActivity implements DetailViewInterface {

    Toolbar toolbar;
    String brand;
    String brandLogoUrl;
    private String brandId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.brand_activity);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        brand = getIntent().getStringExtra("brandName");
        brandLogoUrl = getIntent().getStringExtra("brandLogoUrl");
        brandId = getIntent().getStringExtra("brandId");

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
