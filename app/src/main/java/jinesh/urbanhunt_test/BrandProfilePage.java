package jinesh.urbanhunt_test;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jinesh on 13/08/15.
 */
public class BrandProfilePage extends AppCompatActivity {


    HorizontalListView brandPop;
    Button btnBrandCollection;
    ImageView brandLogo;
    ImageView favBrand;
    TextView brandName;
    String brand;
    StoreListAdapter storeListAdapter;
    ListView storeList;
    HorizontalImageAdapter horizontalImageAdapter;
    Toolbar toolbar;
    String brandLogoUrl;

    public BrandProfilePage() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.brand_profile);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        brand = getIntent().getStringExtra("brandName");

        Log.d("brandName", brand);
        brandName = (TextView)findViewById(R.id.brandName);
        brandName.setText(brand);

        brandLogoUrl = getIntent().getStringExtra("brandLogoUrl");
        brandLogo = (ImageView)findViewById(R.id.brandLogo);

        Picasso.with(this).load(brandLogoUrl).into(brandLogo);

        ImageView favBrand = (ImageView)findViewById(R.id.favBrand);

        storeList = (ListView)findViewById(R.id.storeList);

        final ArrayList<Store> storeArrayList = new ArrayList<>();
        storeListAdapter = new StoreListAdapter(this,storeArrayList);

        storeList.setAdapter(storeListAdapter);

        ParseQuery<Store> storeParseQuery = ParseQuery.getQuery(Store.class);
        storeParseQuery.whereEqualTo("storeBrand", brand);
        storeParseQuery.findInBackground(new FindCallback<Store>() {
            @Override
            public void done(List<Store> list, ParseException e) {

                for (Store s : list) {
                    storeArrayList.add(s);
                }
                storeListAdapter.setStoreList(storeArrayList);
            }
        });

        brandPop = (HorizontalListView)findViewById(R.id.brandPop);
        final ArrayList<Product_1> brandProductList = new ArrayList<>();

        horizontalImageAdapter = new HorizontalImageAdapter(this,brandProductList);
        brandPop.setAdapter(horizontalImageAdapter);

        ParseQuery<Product_1> productParseQuery = ParseQuery.getQuery(Product_1.class);
        productParseQuery.whereEqualTo("brandName", brand);
        productParseQuery.whereEqualTo("popular",true);
        productParseQuery.findInBackground(new FindCallback<Product_1>() {
            @Override
            public void done(List<Product_1> list, ParseException e) {

                int i;
                for (i = 0; i < 5; i++) {
                    brandProductList.add(list.get(i));
                }
                horizontalImageAdapter.setImageList(brandProductList);
            }
        });


        btnBrandCollection = (Button)findViewById(R.id.btnBrandCollection);
        btnBrandCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                PackageManager pm=getPackageManager();
//                try {
//
//                    Intent waIntent = new Intent(Intent.ACTION_SEND);
//                    waIntent.setType("text/plain");
//                    String text = "YOUR TEXT HERE";
//
//                    PackageInfo info=pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
//                    //Check if package exists or not. If not then code
//                    //in catch block will be called
//                    waIntent.setPackage("com.whatsapp");
//
//                    waIntent.putExtra(Intent.EXTRA_TEXT, text);
//                    startActivity(Intent.createChooser(waIntent, "Share with"));
//
//                } catch (PackageManager.NameNotFoundException e) {
//                }

                Uri uri = Uri.parse("smsto:" + "9173435462");
                Intent i = new Intent(Intent.ACTION_SENDTO, uri);
                i.setPackage("com.whatsapp");
                startActivity(Intent.createChooser(i, ""));
            }
        });


    }
}
