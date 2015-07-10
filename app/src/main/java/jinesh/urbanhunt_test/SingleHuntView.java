package jinesh.urbanhunt_test;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.squareup.picasso.Picasso;

/**
 * Created by Jinesh on 09/07/15.
 */
public class SingleHuntView extends Activity {

    private Hunt hunt;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_hunt);

        context = this;

        String huntId = getIntent().getStringExtra("huntId");

        ParseQuery<Hunt> parseQuery = ParseQuery.getQuery(Hunt.class);
        parseQuery.getInBackground(huntId, new GetCallback<Hunt>() {
            @Override
            public void done(Hunt hunt, ParseException e) {

                ImageView huntImage = (ImageView) findViewById(R.id.singleHuntImage);

                Picasso.with(context).load(hunt.getPhotoFile().getUrl()).into(huntImage);

            }
        });

    }
}
