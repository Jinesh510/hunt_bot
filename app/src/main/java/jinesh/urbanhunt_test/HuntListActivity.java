package jinesh.urbanhunt_test;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.amlcurran.showcaseview.ShowcaseView;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jinesh on 03/07/15.
 */
public class HuntListActivity extends Fragment {


    private HuntRecyclerViewAdapter adapterHunts;
    private FragmentActivity fActivity;

    private CoordinatorLayout huntListLayout;
    private RecyclerView rvHunts;

    private ArrayList<Hunt> hunts;

    private String category;

    ShowcaseView sv;


//    private ListView lvHunts;

//    public HuntListActivity() {
//        super();
//    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//
//        GridView lvHunts;
//
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.hunt_list);
//        lvHunts = (GridView)findViewById(R.id.lvHunts);
//        ArrayList<Hunt> aHunts = new ArrayList<>();
//        adapterHunts = new HuntListAdapter(this,aHunts);
//        lvHunts.setAdapter(adapterHunts);
//
//        fetchHunts();
//
//    }

    public static HuntListActivity newInstance( String category) {
        HuntListActivity fragment = new HuntListActivity();
        Bundle bundle = new Bundle();
        bundle.putString("category", category);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        fActivity = (FragmentActivity) super.getActivity();
        huntListLayout = (CoordinatorLayout)inflater.inflate(R.layout.hunt_list, container, false);
        hunts = new ArrayList<Hunt>();


        rvHunts = (RecyclerView)huntListLayout.findViewById(R.id.rvHunts);
//        ArrayList<Hunt> aHunts = new ArrayList<>();
        category = getArguments().getString("category");
        adapterHunts = new HuntRecyclerViewAdapter(getActivity(),hunts);

        if(category == "All"){
            fetchHunts();
        }
        else {
            fetchHunts(category);
        }
        rvHunts.setAdapter(adapterHunts);
        rvHunts.setLayoutManager(new GridLayoutManager(getActivity(), 2));
//        rvHunts.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvHunts.setItemAnimator(new DefaultItemAnimator());

        final FloatingActionButton Post = (FloatingActionButton)huntListLayout.findViewById(R.id.post);
        Post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), NewHuntActivity.class);
                getActivity().startActivity(i);

            }
        });


//        SubActionButton.Builder rLSubBuilder = new SubActionButton.Builder(getActivity());
//        ImageView rlIcon1 = new ImageView(getActivity());
//        ImageView rlIcon2 = new ImageView(getActivity());
//        ImageView rlIcon3 = new ImageView(getActivity());
//        ImageView rlIcon4 = new ImageView(getActivity());
//
////        Drawable d = ContextCompat.getDrawable(getActivity(), R.drawable.dress);
//        rlIcon1.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.dress));
//        rlIcon2.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.watch));
//        rlIcon3.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.bag));
//        rlIcon4.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.sunglasses));
//
//
//        final FloatingActionMenu rightLowerMenu = new FloatingActionMenu.Builder(getActivity())
//                .addSubActionView(rLSubBuilder.setContentView(rlIcon1).build())
//                .addSubActionView(rLSubBuilder.setContentView(rlIcon2).build())
//                .addSubActionView(rLSubBuilder.setContentView(rlIcon3).build())
//                .addSubActionView(rLSubBuilder.setContentView(rlIcon4).build())
//                .attachTo(Post)
//                .build();
//
//        rightLowerMenu.setStateChangeListener(new FloatingActionMenu.MenuStateChangeListener() {
//            @Override
//            public void onMenuOpened(FloatingActionMenu menu) {
//                // Rotate the icon of rightLowerButton 45 degrees clockwise
//                Post.getDrawable().setRotation(0);
//                PropertyValuesHolder pvhR = PropertyValuesHolder.ofFloat(View.ROTATION, 45);
//                ObjectAnimator animation = ObjectAnimator.ofPropertyValuesHolder(Post.getDrawable(), pvhR);
//                animation.start();
//            }
//
//        }


//        RelativeLayout.LayoutParams lps = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        lps.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
//        lps.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
//        int margin = ((Number) (getResources().getDisplayMetrics().density * 12)).intValue();
//        lps.setMargins(margin, margin, margin, margin);
//
//
//        ViewTarget viewTarget = new ViewTarget(R.id.post, getActivity());
//        sv = new ShowcaseView.Builder(getActivity(), true)
//                .setTarget(viewTarget)
//                .setContentTitle("Hunt")
//                .setContentText("Click here to Post")
//                .build();

//        sv.setButtonPosition(lps);

            return huntListLayout;
        }

    private void fetchHunts(String categoryName) {

            ParseQuery<Hunt> huntParseQuery = ParseQuery.getQuery(Hunt.class);
            huntParseQuery.whereEqualTo("category",categoryName);
            huntParseQuery.findInBackground(new FindCallback<Hunt>() {
                @Override
                public void done(List<Hunt> list, ParseException e) {

                    for (Hunt h : list){

                        hunts.add(h);
                    }
                    adapterHunts.setHuntList(hunts);
                }
            });

    }

    private void fetchHunts() {

        ParseQuery<Hunt> huntParseQuery = ParseQuery.getQuery(Hunt.class);
        huntParseQuery.findInBackground(new FindCallback<Hunt>() {
            @Override
            public void done(List<Hunt> list, ParseException e) {

                for (Hunt h : list){

                    hunts.add(h);
                }
                adapterHunts.setHuntList(hunts);
            }
        });

    }






}
