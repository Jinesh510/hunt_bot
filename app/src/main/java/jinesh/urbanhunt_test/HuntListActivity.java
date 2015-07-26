package jinesh.urbanhunt_test;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParsePush;
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

    private RelativeLayout relLay;
    private FrameLayout oView;

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

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        fActivity = (FragmentActivity) super.getActivity();
        huntListLayout = (CoordinatorLayout)inflater.inflate(R.layout.hunt_list, container, false);
        relLay = (RelativeLayout)huntListLayout.findViewById(R.id.relLay);
        oView = (FrameLayout)huntListLayout.findViewById(R.id.shadowView);
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

//        final FloatingActionButton Post = (FloatingActionButton)huntListLayout.findViewById(R.id.post);


        final FloatingActionMenu menu = (FloatingActionMenu)huntListLayout.findViewById(R.id.menu2);
        menu.showMenuButton(true);
        menu.setClosedOnTouchOutside(true);

        FloatingActionButton fab12 = (FloatingActionButton)huntListLayout.findViewById(R.id.fab12);
        FloatingActionButton fab22 = (FloatingActionButton)huntListLayout.findViewById(R.id.fab22);
        FloatingActionButton fab32 = (FloatingActionButton)huntListLayout.findViewById(R.id.fab32);


        menu.setOnMenuToggleListener(new FloatingActionMenu.OnMenuToggleListener() {
            @Override
            public void onMenuToggle(boolean opened) {
                String text = "";
                if (opened) {
//                    oView.setVisibility(View.VISIBLE);
                    oView.setBackgroundColor(Color.parseColor("#b8ffffff"));
                    text = "Menu opened";
                } else {
//                    relLay.setBackgroundColor(Color.TRANSPARENT);
                    oView.setBackgroundColor(Color.TRANSPARENT);
                    text = "Menu closed";
                }
                Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
            }
        });

//        final com.github.clans.fab.FloatingActionButton Post =(com.github.clans.fab.FloatingActionButton)huntListLayout.findViewById(R.id.post);
        fab12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getActivity(), NewHuntActivity.class);
                i.putExtra("category","bags");
                getActivity().startActivity(i);

                ParsePush push = new ParsePush();
                push.setMessage("The Giants just scored! It's now 2-2 against the Mets.");
                push.sendInBackground();


            }
        });

//        com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton fab1 = (com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton)huntListLayout.findViewById(R.id.fab11);

//        ImageView fab = new ImageView(getActivity());
//        fab.setImageDrawable(getResources().getDrawable(R.drawable.bag, null));
//        com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton actionBtn = new com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton.Builder(getActivity()).setContentView(fab).build();
//
//
//        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(getActivity());
//
//        ImageView fabItem = new ImageView(getActivity());
//        fabItem.setImageDrawable(getResources().getDrawable(R.drawable.ic_home,null));
//        SubActionButton button1 = itemBuilder.setContentView(fabItem).build();
//
//        ImageView fabItem2 = new ImageView(getActivity());
//        fabItem2.setImageDrawable(getResources().getDrawable(R.drawable.ic_home,null));
//        SubActionButton button2 = itemBuilder.setContentView(fabItem2).build();
//
//        ImageView fabItem3 = new ImageView(getActivity());
//        fabItem3.setImageDrawable(getResources().getDrawable(R.drawable.ic_home, null));
//        SubActionButton button3 = itemBuilder.setContentView(fabItem3).build();
//
//
//
//        com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu actionMenu = new com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu.Builder(getActivity()).addSubActionView(button1).addSubActionView(button2).addSubActionView(button3).attachTo(actionBtn).build();

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

//                for (Hunt h : list){
//
//                    hunts.add(h);
//                }
                hunts = (ArrayList<Hunt>)list;
                adapterHunts.setHuntList(hunts);
            }
        });

    }






}
