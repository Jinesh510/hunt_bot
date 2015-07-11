package jinesh.urbanhunt_test;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jinesh on 03/07/15.
 */
public class HuntListActivity extends Activity {

    private HuntListAdapter adapterHunts;
//    private ListView lvHunts;

    public HuntListActivity() {
        super();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        GridView lvHunts;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.hunt_list);
        lvHunts = (GridView)findViewById(R.id.lvHunts);
        ArrayList<Hunt> aHunts = new ArrayList<>();
        adapterHunts = new HuntListAdapter(this,aHunts);
        lvHunts.setAdapter(adapterHunts);

        fetchHunts();

    }

    private void fetchHunts() {

        ParseQuery<Hunt> huntParseQuery = ParseQuery.getQuery(Hunt.class);
        huntParseQuery.findInBackground(new FindCallback<Hunt>() {
            @Override
            public void done(List<Hunt> list, ParseException e) {
                for (Hunt h : list){

                    adapterHunts.add(h);
                }
                adapterHunts.notifyDataSetChanged();
            }
        });
    }




}
