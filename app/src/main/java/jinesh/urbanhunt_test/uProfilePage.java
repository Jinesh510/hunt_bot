package jinesh.urbanhunt_test;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.CountCallback;
import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jinesh on 09/07/15.
 */
public class uProfilePage extends Activity {

//    private String usrName;
//    private Context context;
//    private HuntImageAdapter hlvAdapter;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.user_profile_page);
//        String userObjectID = getIntent().getStringExtra("user");
//        context = this;
//
//        HorizontalListView hlvHunts = (HorizontalListView)findViewById(R.id.hlvHunts);
////      org.lucasr.twowayview.widget.TwoWayView hlvHunts = (org.lucasr.twowayview.widget.TwoWayView)findViewById(R.id.hlvHunts);
//        ArrayList<Hunt> imgHunts = new ArrayList<Hunt>();
//        hlvAdapter = new HuntImageAdapter(this, imgHunts);
//        hlvHunts.setAdapter(hlvAdapter);
//
//
//        ParseQuery<ParseUser> userParseQuery = ParseUser.getQuery();
//        userParseQuery.getInBackground(userObjectID, new GetCallback<ParseUser>() {
//            @Override
//            public void done(ParseUser parseUser, ParseException e) {
//
//                fetchUserData(parseUser);
//                fetchHuntImages(parseUser);
//
//            }
//        });
//
//    }
//        private void fetchUserData(ParseUser parseUser){
//            usrName = parseUser.getUsername();
//            TextView uName = (TextView) findViewById(R.id.usrName);
//            uName.setText(usrName);
//
//    }
//        private void fetchHuntImages(ParseUser parseUser){
//        ParseQuery<Hunt> parseQuery = ParseQuery.getQuery(Hunt.class);
//        parseQuery.whereEqualTo("author",parseUser);
////                parseQuery.getFirstInBackground(new GetCallback<Hunt>() {
////                    @Override
////                    public void done(Hunt hunt, ParseException e) {
////                        ImageView profImage = (ImageView)findViewById(R.id.profImage);
////                        Picasso.with(context).load(hunt.getPhotoFile().getUrl()).into(profImage);
////                    }
////
////                });
//        parseQuery.findInBackground(new FindCallback<Hunt>() {
//            @Override
//            public void done(List<Hunt> list, ParseException e) {
//                for(Hunt h : list){
//                    hlvAdapter.add(h);
//
//                }
//                hlvAdapter.notifyDataSetChanged();
//            }
//        });
//
//
//    }

    private String usrName;
    private Context context;
    private HuntImageAdapter hlvAdapter;
    ArrayList<Hunt> imgHunts;

    String currentUserID = ParseUser.getCurrentUser().getObjectId();
    ParseUser currentUser = ParseUser.getCurrentUser();

    ArrayList<String> ufollowingArray = (ArrayList<String>)currentUser.get("following");

    Button follow;
    Button unfollow;
    TextView followerCount;
    TextView followingCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile_page);

        context = this;

        final String userObjectID = getIntent().getStringExtra("user");


        HorizontalListView hlvHunts = (HorizontalListView) findViewById(R.id.hlvHunts);
//      org.lucasr.twowayview.widget.TwoWayView hlvHunts = (org.lucasr.twowayview.widget.TwoWayView)findViewById(R.id.hlvHunts);
        imgHunts = new ArrayList<Hunt>();
        hlvAdapter = new HuntImageAdapter(this, imgHunts);
        hlvHunts.setAdapter(hlvAdapter);

        unfollow = (Button)findViewById(R.id.unfollow);
        unfollow.setVisibility(View.INVISIBLE);

        follow = (Button)findViewById(R.id.follow);
        follow.setVisibility(View.INVISIBLE);




        ParseQuery<ParseUser> userParseQuery = ParseUser.getQuery();
        userParseQuery.getInBackground(userObjectID, new GetCallback<ParseUser>() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {

                ArrayList<String> followerArray = (ArrayList<String>) parseUser.get("followers");
                ArrayList<String> followingArray = (ArrayList<String>) parseUser.get("following");

                fetchUserData(parseUser, followerArray, followingArray);
                fetchUserRelationship(parseUser);
                fetchHuntImages(parseUser);
                fetchFollowerCount(parseUser);
                fetchFollowingCount(parseUser);

            }
        });





        follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                ParseQuery<ParseUser> userFollowQuery = ParseUser.getQuery();
                userFollowQuery.getInBackground(userObjectID, new GetCallback<ParseUser>() {
                    @Override
                    public void done(ParseUser parseUser, ParseException e) {
                        ParseObject followObject = new ParseObject("Follow");
                        followObject.put("follower", currentUser);
                        followObject.put("following", parseUser);
                        followObject.saveEventually();
                        followerUI();
                        updatefollowerCount();
                    }
                });



            }
        });

        unfollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("clicked","button clicked");

                ParseQuery<ParseUser> userFollowQuery = ParseUser.getQuery();
                userFollowQuery.getInBackground(userObjectID, new GetCallback<ParseUser>() {
                    @Override
                    public void done(ParseUser parseUser, ParseException e) {

                        runUnfollow(parseUser);

                        }
                    });

            }
            });




    }

    private void runUnfollow(ParseUser parseUser){
        ParseQuery<ParseObject> uRQuery = ParseQuery.getQuery("Follow");
        uRQuery.whereEqualTo("follower", currentUser);
        uRQuery.whereEqualTo("following", parseUser);
        uRQuery.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject parseObject, ParseException e) {
//                            Log.d("OBJ","Object Found - delete it" + parseObject.getObjectId() );
                parseObject.deleteInBackground(new DeleteCallback() {
                    @Override
                    public void done(ParseException e) {
                        Log.d("DEL","Object Deleted");
                        unfollowUI();
                        Log.d("update", "updated UI");
                        updateUnfollowCount();

                    }
                });
//                    Log.d("User","User obtained");

            }
        });


    }

    private void updatefollowerCount(){
        followerCount = (TextView)findViewById(R.id.followerCount);
        int followerCountInt = Integer.parseInt(followerCount.getText().toString());
        followerCountInt = followerCountInt + 1;
        followerCount.setText("" + followerCountInt);

    }

    private void updateUnfollowCount(){
        followerCount = (TextView)findViewById(R.id.followerCount);
        int followerCountInt = Integer.parseInt(followerCount.getText().toString());
        followerCountInt = followerCountInt - 1;
        followerCount.setText("" + followerCountInt);

    }
    private void followerUI(){

        follow.setVisibility(View.VISIBLE);
        follow.setClickable(false);
        follow.setText("Following");
        follow.setTextColor(Color.parseColor("#7f8c8d"));
        unfollow.setVisibility(View.VISIBLE);

    }

    private void unfollowUI(){

        follow.setVisibility(View.VISIBLE);
        follow.setClickable(true);
        follow.setText("Follow");
        follow.setTextColor(follow.getTextColors().getDefaultColor());
        unfollow.setVisibility(View.INVISIBLE);

    }

    private void fetchUserRelationship(ParseUser parseUser){

        ParseQuery uRQuery = ParseQuery.getQuery("Follow");
        uRQuery.whereEqualTo("follower",currentUser);
        uRQuery.whereEqualTo("following", parseUser);
        uRQuery.countInBackground(new CountCallback() {
            @Override
            public void done(int i, ParseException e) {
                if(i > 0)
                {
                    follow.setVisibility(View.VISIBLE);
                    follow.setClickable(false);
                    follow.setText("Following");
                    follow.setTextColor(Color.parseColor("#7f8c8d"));
                    unfollow.setVisibility(View.VISIBLE);


                }
                else{
                    follow.setVisibility(View.VISIBLE);
//                unfollow.setVisibility(View.INVISIBLE);
                }
            }
        });


    }



    private void fetchFollowerCount(ParseUser parseUser){


        ParseQuery<ParseObject> followerCountQuery = ParseQuery.getQuery("Follow");
        followerCountQuery.whereEqualTo("following", parseUser);
        followerCountQuery.countInBackground(new CountCallback() {
            @Override
            public void done(int followerCountInt, ParseException e) {

                String followerCountStr = Integer.toString(followerCountInt);
                followerCount = (TextView)findViewById(R.id.followerCount);
                followerCount.setText(followerCountStr);
            }
        });
//        try {
//            int followerCountInt = followerCountQuery.count();
//
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

    }

    private void fetchFollowingCount(ParseUser parseUser){

        ParseQuery followingCountQuery = ParseQuery.getQuery("Follow");
        followingCountQuery.whereEqualTo("follower",parseUser );
        followingCountQuery.countInBackground(new CountCallback() {
            @Override
            public void done(int followingCountInt, ParseException e) {
                String followingCountStr = Integer.toString(followingCountInt);
                followingCount = (TextView)findViewById(R.id.followingCount);
                followingCount.setText(followingCountStr);
            }
        });
//        try {
//            int followingCountInt = followingCountQuery.count();
//
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

    }

    private void fetchUserData(ParseUser parseUser, ArrayList<String> followerArray, ArrayList<String> followingArray) {
        usrName = parseUser.getUsername();
        TextView uName = (TextView) findViewById(R.id.usrName);
        uName.setText(usrName);

    }


    private void fetchHuntImages(ParseUser parseUser) {
        ParseQuery<Hunt> parseQuery = ParseQuery.getQuery(Hunt.class);
        parseQuery.whereEqualTo("author", parseUser);
        parseQuery.findInBackground(new FindCallback<Hunt>() {
            @Override
            public void done(List<Hunt> list, ParseException e) {
                for (Hunt h : list) {
                    imgHunts.add(h);

                    Log.d("uProfilePage", "List Size: " + list.size());
                    Log.d("uProfilePage", "Image Url: " + h.getPhotoFile().getUrl());
                }
                hlvAdapter.setHuntList(imgHunts);
//                hlvAdapter.notifyDataSetChanged();
            }
        });

    }




}
