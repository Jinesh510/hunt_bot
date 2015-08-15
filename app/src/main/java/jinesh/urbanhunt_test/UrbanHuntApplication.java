package jinesh.urbanhunt_test;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.ParseUser;
import com.parse.SaveCallback;

/**
 * Created by Jinesh on 03/07/15.
 */
public class UrbanHuntApplication extends Application {

    @Override
    public void onCreate(){

        super.onCreate();

        ParseObject.registerSubclass(Hunt.class);

        Parse.initialize(this, "Jx9KzZNE3SHq8PXMVUlTgdqza0yFiNUyH7vdECOB", "tc3GyC7gvGZgexIOFKgE9gOwq8GJ0CFYThrmIlCa");

        ParseUser.enableAutomaticUser();

        ParseACL defaultACL = new ParseACL();

        defaultACL.setPublicReadAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);

        ParsePush.subscribeInBackground("", new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.d("com.parse.push", "successfully subscribed to the broadcast channel.");
                } else {
                    Log.e("com.parse.push", "failed to subscribe for push", e);
                }
            }
        });

        ParseInstallation installation = ParseInstallation.getCurrentInstallation();
        installation.put("user", ParseUser.getCurrentUser());
        installation.saveInBackground();

    }
}
