package jinesh.urbanhunt_test;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Jinesh on 03/07/15.
 */
public class HuntListActivity extends Fragment {


    private HuntRecyclerViewAdapter adapterHunts;
    private FragmentActivity fActivity;

    private CoordinatorLayout huntListLayout;
//    private RecyclerView rvHunts;
    private GridView gvHunts;
    private ArrayList<Hunt> hunts;

    private String category;

    private RelativeLayout relLay;
    private FrameLayout oView;

    private Date createdAt;
    private SwipeRefreshLayout swipeContainer;
    private ParseQueryAdapter.QueryFactory<Hunt> pqf;
    private CustomParseQueryAdapter customParseQueryAdapter;

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
    int page = 1;

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


        fActivity = super.getActivity();

        huntListLayout = (CoordinatorLayout)inflater.inflate(R.layout.hunt_list, container, false);
        swipeContainer = (SwipeRefreshLayout) huntListLayout.findViewById(R.id.swipeContainer);
        relLay = (RelativeLayout)huntListLayout.findViewById(R.id.relLay);
        oView = (FrameLayout)huntListLayout.findViewById(R.id.shadowView);
        hunts = new ArrayList<Hunt>();
//        ViewGroup vg;
//        vg = huntListLayout;

//        rvHunts = (RecyclerView)huntListLayout.findViewById(R.id.rvHunts);
//        ArrayList<Hunt> aHunts = new ArrayList<>();
        gvHunts = (GridView)huntListLayout.findViewById(R.id.gvHunts);
        category = getArguments().getString("category");
//        adapterHunts = new HuntRecyclerViewAdapter(getActivity(),hunts);





        if(category == "All"){
            fetchHunts();
        }
        else {
            fetchHunts(category);
        }


        final ProgressDialog p = new ProgressDialog(getActivity());


        customParseQueryAdapter = new CustomParseQueryAdapter(getActivity(),pqf);
//        customParseQueryAdapter = new CustomParseQueryAdapter(getActivity(), new ParseQueryAdapter.QueryFactory<Hunt>() {
//            @Override
//            public ParseQuery<Hunt> create() {
//                return huntParseQuery;
//            }
//        });





//        customParseQueryAdapter.setPaginationEnabled(false);
//        customParseQueryAdapter.setAutoload(false);
        customParseQueryAdapter.setObjectsPerPage(8);

        customParseQueryAdapter.setPaginationEnabled(true);

//        rvHunts.setAdapter(adapterHunts);
//        customParseQueryAdapter.setPageOnQuery(page, huntParseQuery);
        customParseQueryAdapter.addOnQueryLoadListener(new ParseQueryAdapter.OnQueryLoadListener<Hunt>() {
            @Override
            public void onLoading() {
                p.show();
            }

            @Override
            public void onLoaded(List<Hunt> list, Exception e) {

                p.hide();


            }
        });
        gvHunts.setAdapter(customParseQueryAdapter);

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                customParseQueryAdapter.loadObjects();
                swipeContainer.setRefreshing(false);
            }
        });




//        gvHunts.setOnScrollListener(new AbsListView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(AbsListView absListView, int i) {
//
//            }
//
//            @Override
//            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
//                if (absListView.getLastVisiblePosition()==absListView.getChildCount()) {
//                    page = page + 1;
//                    customParseQueryAdapter.setObjectsPerPage(6);
//                    customParseQueryAdapter.setPageOnQuery(page,huntParseQuery);
//                    gvHunts.setAdapter(customParseQueryAdapter);
//
//                    Log.d("s", "scrolled");
//                }
//            }
//        });

//        final GridLayoutManager hLinearLayoutManager = new GridLayoutManager(getActivity(),2);
//        rvHunts.setLayoutManager(hLinearLayoutManager);
////        rvHunts.setLayoutManager(new LinearLayoutManager(getActivity()));
//        rvHunts.setItemAnimator(new DefaultItemAnimator());

        /*Implemetented on pull refresh */
//        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//
//                Log.d("isRefreshing", "" + swipeContainer.isRefreshing());
//
////                rvHunts.onScrolled(0,5);
//                adapterHunts.clear();
//                fetchHunts();
//                swipeContainer.setRefreshing(false);
//
////                rvHunts.setAdapter(adapterHunts);
////                rvHunts.setLayoutManager(hLinearLayoutManager);
//////        rvHunts.setLayoutManager(new LinearLayoutManager(getActivity()));
////                rvHunts.setItemAnimator(new DefaultItemAnimator());
//                Log.d("refreshed", "" + hunts.size());
//                Log.d("isRefreshed", ""+swipeContainer.isRefreshing());
//            }
//        });


//        rvHunts.addOnScrollListener(new RecyclerView.OnScrollListener() {
//
//            private int previousTotal = 0; // The total number of items in the dataset after the last load
//            private boolean loading = true; // True if we are still waiting for the last set of data to load.
//            private int visibleThreshold = 5; // The minimum amount of items to have below your current scroll position before loading more.
//            int firstVisibleItem, visibleItemCount, totalItemCount,lastVisibleItem;
//            int skipCount;
//
//            private int current_page = 1;
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//
//
//
//                visibleItemCount = recyclerView.getChildCount();
//                totalItemCount = hLinearLayoutManager.getItemCount();
//                firstVisibleItem = hLinearLayoutManager.findFirstVisibleItemPosition();
//                lastVisibleItem = hLinearLayoutManager.findLastVisibleItemPosition();
//                int t = 0;
//
//
////                if(!swipeContainer.isRefreshing()){
////                    previousTotal = 0;
////                    totalItemCount = hLinearLayoutManager.getItemCount();
////                    firstVisibleItem = hLinearLayoutManager.findFirstVisibleItemPosition();
////                    Log.d("rtotalItemCount1", "" + totalItemCount);
////                    Log.d("rvisibleItemCount1",""+visibleItemCount);
////                    Log.d("rfirstVisibleItem1",""+firstVisibleItem);
////
////                };
//
//                if (firstVisibleItem == 0){
//                    swipeContainer.setEnabled(true);
////                    previousTotal = 0;
//                    Log.d("loading","" + loading);
//
//                    Log.d("totalItemCount1", "" + totalItemCount);
//                    Log.d("visibleItemCount1",""+visibleItemCount);
//                    Log.d("firstVisibleItem1",""+firstVisibleItem);
//                    Log.d("previousTotal1",""+previousTotal);
//                }
//                else{
//                    swipeContainer.setEnabled(false);
//                }
//
//
//
//                if (loading) {
//                    if (totalItemCount == lastVisibleItem) {
//                        loading = false;
////                        lastVisibleItem = totalItemCount;
//
//                        Log.d("totalItemCount",""+totalItemCount);
//                        Log.d("visibleItemCount",""+visibleItemCount);
//                        Log.d("firstVisibleItem",""+firstVisibleItem);
//                        Log.d("lastvisibleItem",""+lastVisibleItem);
//                        Log.d("scroll","true");
//
////                        previousTotal = totalItemCount;
//
//                    }
//                }
//                if (!loading && (totalItemCount - visibleItemCount)
//                        <= (firstVisibleItem + visibleThreshold)) {
//                    // End has been reached
//
////                    ParseQuery<Hunt> huntParseQuery1 = ParseQuery.getQuery(Hunt.class);
////                    huntParseQuery1.whereLessThan("createdAt", createdAt);
////                    try {
////                        t = huntParseQuery1.count();
////                    } catch (ParseException e) {
////                        e.printStackTrace();
////                    }
////
////                    // Do something
////                    if(t > totalItemCount){
////
////                    }
//                    Log.d("loaded", "" + loading);
//                    current_page++;
//
//                    skipCount = (current_page-1)*5;
//                    Log.d("skipCount",""+skipCount);
//                    Log.d("cp",""+current_page);
//
//                    ParseQuery<Hunt> huntParseQuery = ParseQuery.getQuery(Hunt.class);
//                    huntParseQuery.orderByDescending("createdAt");
//                    huntParseQuery.whereLessThan("createdAt",createdAt);
//                    huntParseQuery.setSkip(skipCount);
//                    huntParseQuery.setLimit(5);
//                    huntParseQuery.findInBackground(new FindCallback<Hunt>() {
//                        @Override
//                        public void done(List<Hunt> list, ParseException e) {
//
//                            for (Hunt h : list) {
//
//                                hunts.add(h);
//                                Log.d("huntSize" , ""+hunts.size());
//                            }
////                            hunts = (ArrayList<Hunt>)list;
//                            adapterHunts.setHuntList(hunts);
//
//                        }
//                    });
//
//                    Log.d("scrolled", "true");
//
//                    loading = true;
//                }
//
//
//            }
//        });




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
                    oView.setBackgroundColor(Color.parseColor("#b8ffffff"));
                    text = "Menu opened";
                } else {
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
                i.putExtra("category", "bags");
                getActivity().startActivity(i);

//                ParseQuery<ParseUser> userFollowQuery = ParseUser.getQuery();
//                userFollowQuery.getInBackground("otkEjMPx9r",);

                ParseQuery pushQuery = ParseInstallation.getQuery();
                pushQuery.whereEqualTo("installationId", "71c47d03-128e-49c1-ab04-5bcbcff508ca");

                JSONObject data = null;
                try {
                    data = new JSONObject("{\"id\":\"1\"}");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                ParsePush push = new ParsePush();
                push.setQuery(pushQuery);
//                push.setMessage("The Giants just scored! It's now 2-2 against the Mets.");
                push.setData(data);
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

    private void fetchHunts(final String categoryName) {
//
//            ParseQuery<Hunt> huntParseQuery = ParseQuery.getQuery(Hunt.class);
//            huntParseQuery.whereEqualTo("category",categoryName);
//            huntParseQuery.findInBackground(new FindCallback<Hunt>() {
//                @Override
//                public void done(List<Hunt> list, ParseException e) {
//
//                    for (Hunt h : list) {
//
//                        hunts.add(h);
//                    }
//                    adapterHunts.setHuntList(hunts);
//                }
//            });

        pqf = new ParseQueryAdapter.QueryFactory<Hunt>() {
            @Override
            public ParseQuery<Hunt> create() {
                ParseQuery<Hunt> huntParseQuery = ParseQuery.getQuery(Hunt.class);
                huntParseQuery.orderByDescending("createdAt");
                huntParseQuery.whereEqualTo("category", categoryName);
                huntParseQuery.whereNotEqualTo("author", ParseUser.getCurrentUser());


                return huntParseQuery;
            }
        };

    }

    private void fetchHunts() {

//        ParseQuery<Hunt> huntParseQuery = ParseQuery.getQuery(Hunt.class);
//        huntParseQuery.orderByDescending("createdAt");
//        huntParseQuery.setLimit(5);
//        huntParseQuery.findInBackground(new FindCallback<Hunt>() {
//            @Override
//            public void done(List<Hunt> list, ParseException e) {
//
//                for (Hunt h : list) {
//
//                    hunts.add(h);
//                    Log.d("size", hunts.size() + "");
//                }
////                hunts = (ArrayList<Hunt>)list;
//                adapterHunts.setHuntList(hunts);
//                Hunt hunt = hunts.get(0);
//                createdAt = hunt.getCreatedAt();
//                Log.d("created at", "" + createdAt);
//            }
//        });

        pqf = new ParseQueryAdapter.QueryFactory<Hunt>() {
            @Override
            public ParseQuery<Hunt> create() {
                ParseQuery<Hunt> huntParseQuery = ParseQuery.getQuery(Hunt.class);
                huntParseQuery.whereNotEqualTo("author", ParseUser.getCurrentUser());
                huntParseQuery.orderByDescending("createdAt");
//                huntParseQuery.setLimit(5);

                return huntParseQuery;
            }
        };
    }








}
