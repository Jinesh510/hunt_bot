package jinesh.urbanhunt_test;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

/**
 * Created by Jinesh on 15/08/15.
 */

public class CollapseActivity extends AppCompatActivity {

    CollapsingToolbarLayout collapsingToolbarLayout;
    AppBarLayout appBarLayout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dummy_collapse);

        appBarLayout = (AppBarLayout)findViewById(R.id.appbar);
        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);

        ImageView iv = (ImageView)findViewById(R.id.backdrop);
        toolbar = (Toolbar)findViewById(R.id.toolbar);

//        ViewCompat.setTransitionName(appBarLayout, iv);
//        supportPostponeEnterTransition();

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NestedScrollView nv = (NestedScrollView)findViewById(R.id.scroll);

    }
}
