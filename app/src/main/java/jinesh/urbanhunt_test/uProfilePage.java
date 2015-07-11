package jinesh.urbanhunt_test;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jinesh on 09/07/15.
 */
public class uProfilePage extends Activity {

    private String usrName;
    private Context context;
    private HuntImageAdapter hlvAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile_page);
        String userObjectID = getIntent().getStringExtra("user");
        context = this;

        HorizontalListView hlvHunts = (HorizontalListView) findViewById(R.id.hlvHunts);
//      org.lucasr.twowayview.widget.TwoWayView hlvHunts = (org.lucasr.twowayview.widget.TwoWayView)findViewById(R.id.hlvHunts);
        ArrayList<Hunt> imgHunts = new ArrayList<Hunt>();
        hlvAdapter = new HuntImageAdapter(this, imgHunts);
        hlvHunts.setAdapter(hlvAdapter);


        ParseQuery<ParseUser> userParseQuery = ParseUser.getQuery();
        userParseQuery.getInBackground(userObjectID, new GetCallback<ParseUser>() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {

                fetchUserData(parseUser);
                fetchHuntImages(parseUser);

            }
        });

    }
        private void fetchUserData(ParseUser parseUser){
            usrName = parseUser.getUsername();
            TextView uName = (TextView) findViewById(R.id.usrName);
            uName.setText(usrName);

    }
        private void fetchHuntImages(ParseUser parseUser){
        ParseQuery<Hunt> parseQuery = ParseQuery.getQuery(Hunt.class);
        parseQuery.whereEqualTo("author",parseUser);
//                parseQuery.getFirstInBackground(new GetCallback<Hunt>() {
//                    @Override
//                    public void done(Hunt hunt, ParseException e) {
//                        ImageView profImage = (ImageView)findViewById(R.id.profImage);
//                        Picasso.with(context).load(hunt.getPhotoFile().getUrl()).into(profImage);
//                    }
//
//                });
        parseQuery.findInBackground(new FindCallback<Hunt>() {
            @Override
            public void done(List<Hunt> list, ParseException e) {
                for(Hunt h : list){
                    hlvAdapter.add(h);

                }
                hlvAdapter.notifyDataSetChanged();
            }
        });


    }


}
