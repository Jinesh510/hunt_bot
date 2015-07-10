package jinesh.urbanhunt_test;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.squareup.picasso.Picasso;

/**
 * Created by Jinesh on 09/07/15.
 */
public class uProfilePage extends Activity {

    private String usrName;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile_page);
        String userObjectID = getIntent().getStringExtra("user");
        context = this;

        ParseQuery<ParseUser> userParseQuery = ParseUser.getQuery();
        userParseQuery.getInBackground(userObjectID, new GetCallback<ParseUser>() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {
                usrName = parseUser.getUsername();
                TextView uName = (TextView) findViewById(R.id.usrName);
                uName.setText(usrName);

                ParseQuery<Hunt> parseQuery = ParseQuery.getQuery(Hunt.class);
                parseQuery.whereEqualTo("author",parseUser);
                parseQuery.getFirstInBackground(new GetCallback<Hunt>() {
                    @Override
                    public void done(Hunt hunt, ParseException e) {
                        ImageView profImage = (ImageView)findViewById(R.id.profImage);
                        Picasso.with(context).load(hunt.getPhotoFile().getUrl()).into(profImage);
                    }

                });
            }
        });



    }
}
