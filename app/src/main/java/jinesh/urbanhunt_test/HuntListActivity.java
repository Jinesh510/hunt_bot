package jinesh.urbanhunt_test;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jinesh on 03/07/15.
 */
public class HuntListActivity extends Fragment {

//    private HuntListAdapter adapterHunts;

    private HuntRecyclerViewAdapter adapterHunts;
    private FragmentActivity fActivity;

    private CoordinatorLayout huntListLayout;
//    private GridView lvHunts;
    private RecyclerView rvHunts;

    private ArrayList<Hunt> hunts;


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


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        fActivity = (FragmentActivity) super.getActivity();
        huntListLayout = (CoordinatorLayout)inflater.inflate(R.layout.hunt_list, container, false);
        hunts = new ArrayList<Hunt>();


        rvHunts = (RecyclerView)huntListLayout.findViewById(R.id.rvHunts);
//        ArrayList<Hunt> aHunts = new ArrayList<>();

        adapterHunts = new HuntRecyclerViewAdapter(getActivity(),hunts);
        fetchHunts();
        rvHunts.setAdapter(adapterHunts);
        rvHunts.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        FloatingActionButton Post = (FloatingActionButton)huntListLayout.findViewById(R.id.post);
        Post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),NewHuntActivity.class);
                getActivity().startActivity(i);

            }
        });


        return huntListLayout;
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
