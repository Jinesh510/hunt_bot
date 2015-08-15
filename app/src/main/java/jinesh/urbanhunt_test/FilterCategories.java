package jinesh.urbanhunt_test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

/**
 * Created by Jinesh on 21/07/15.
 */
public class FilterCategories extends AppCompatActivity {

    Toolbar toolbar;
    Fragment huntListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter_categories);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String strCategoryName = getIntent().getStringExtra("category");
        toolbar.setTitle(strCategoryName);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        Log.d("category",strCategoryName);
//        huntListFragment  = new HuntListActivity();

        huntListFragment = HuntListActivity.newInstance(strCategoryName);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.huntCategory, huntListFragment).commit();

    }

//    @Override
//    onBackPressed()
}
