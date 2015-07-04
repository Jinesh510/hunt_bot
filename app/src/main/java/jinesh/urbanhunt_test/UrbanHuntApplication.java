package jinesh.urbanhunt_test;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseObject;
import com.parse.ParseUser;

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

        ParseACL.setDefaultACL(defaultACL,true);

    }
}
