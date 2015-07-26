package jinesh.urbanhunt_test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;


public class NewHuntActivity extends ActionBarActivity {

    String category;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter_categories);

        category = getIntent().getStringExtra("category");

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        Fragment newHuntFragment = NewHuntFragment.newInstance(category);
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.huntCategory, newHuntFragment).commit();


    }
}
