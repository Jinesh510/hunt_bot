package jinesh.urbanhunt_test;

import android.app.Application;
import android.util.Log;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.StandardExceptionParser;
import com.google.android.gms.analytics.Tracker;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import io.branch.referral.Branch;

/**
 * Created by Jinesh on 03/07/15.
 */
public class UrbanHuntApplication extends Application {

    private Tracker mTracker;
    private static UrbanHuntApplication mInstance;

    public static final String TAG = UrbanHuntApplication.class
            .getSimpleName();


//    HashMap<TrackerName, Tracker> mTrackers = new HashMap<TrackerName, Tracker>();

    @Override
    public void onCreate(){

        super.onCreate();

        Branch.getAutoInstance(this);

        ParseObject.registerSubclass(Hunt.class);

        ParseObject.registerSubclass(Product_1.class);

        ParseObject.registerSubclass(Brand.class);

        ParseObject.registerSubclass(Store.class);


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

        mInstance = this;
        getDefaultTracker();
//
//        AnalyticsTrackers.initialize(this);
//        AnalyticsTrackers.getInstance().get(AnalyticsTrackers.Target.APP);
//
//


    }

    /**
     * Gets the default {@link Tracker} for this {@link Application}.
     * @return tracker
     */
//    From Google
    synchronized public Tracker getDefaultTracker() {
        if (mTracker == null) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
            mTracker = analytics.newTracker("UA-66538525-4");
        }
        return mTracker;
    }


//    public synchronized Tracker getTracker(TrackerName trackerId) {
//
//        if (!mTrackers.containsKey(trackerId)) {
//            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
//            Tracker t = analytics.newTracker(R.xml.app_tracker);
//            mTrackers.put(trackerId, t);
//        }
//        return mTrackers.get(trackerId);
//    }
//
//    public enum TrackerName {
//        APP_TRACKER,
//        GLOBAL_TRACKER,
//        E_COMMERCE_TRACKER,
//    }




    public static synchronized UrbanHuntApplication getInstance() {
        return mInstance;
    }

    public synchronized Tracker getGoogleAnalyticsTracker() {
        AnalyticsTrackers analyticsTrackers = AnalyticsTrackers.getInstance();
        return analyticsTrackers.get(AnalyticsTrackers.Target.APP);
    }

    /***
     * Tracking screen view
     *
     * @param screenName screen name to be displayed on GA dashboard
     */
    public void trackScreenView(String screenName) {
        Tracker t = getGoogleAnalyticsTracker();

        // Set screen name.
        t.setScreenName(screenName);

        // Send a screen view.
        t.send(new HitBuilders.ScreenViewBuilder().build());

        GoogleAnalytics.getInstance(this).dispatchLocalHits();
    }

    /***
     * Tracking exception
     *
     * @param e exception to be tracked
     */
    public void trackException(Exception e) {
        if (e != null) {
            Tracker t = getGoogleAnalyticsTracker();

            t.send(new HitBuilders.ExceptionBuilder()
                            .setDescription(
                                    new StandardExceptionParser(this, null)
                                            .getDescription(Thread.currentThread().getName(), e))
                            .setFatal(false)
                            .build()
            );
        }
    }

    /***
     * Tracking event
     *
     * @param category event category
     * @param action   action of the event
     * @param label    label
     */
    public void trackEvent(String category, String action, String label) {
        Tracker t = getGoogleAnalyticsTracker();

        // Build and send an Event.
        t.send(new HitBuilders.EventBuilder().setCategory(category).setAction(action).setLabel(label).build());
    }
}
