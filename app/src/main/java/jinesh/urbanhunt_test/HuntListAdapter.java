package jinesh.urbanhunt_test;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseUser;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Jinesh on 05/07/15.
 */
public class HuntListAdapter extends ArrayAdapter<Hunt> {

    MainActivity activity;
    public HuntListAdapter(Context context,ArrayList<Hunt> aHunts) {
        super(context,0,aHunts);
        activity = (MainActivity)context;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Hunt hunt = getItem(position);

        if (convertView ==null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_hunt_row,parent,false);
        }

        //Lookup views in item layout

        TextView huntTitle = (TextView)convertView.findViewById(R.id.huntTitle);
        ImageView huntImage = (ImageView)convertView.findViewById(R.id.huntImage);

//        ParseImageView huntImage = (ParseImageView)convertView.findViewById(R.id.huntImage);

        //loading data into template
        final ParseUser huntAuthor = hunt.getAuthor();


        huntTitle.setText(hunt.getTitle());
//        huntImage.setParseFile(hunt.getPhotoFile());
//        huntImage.loadInBackground(new GetDataCallback() {
//            @Override
//            public void done(byte[] bytes, ParseException e) {
//
//            }
//        });

        Picasso.with(getContext()).load(hunt.getPhotoFile().getUrl()).into(huntImage);

        huntTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, uProfilePage.class);
                intent.putExtra("user", huntAuthor.getObjectId());
                activity.startActivity(intent);
            }
        });

        huntImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity,SingleHuntView.class);
                intent.putExtra("huntId", hunt.getObjectId());
                activity.startActivity(intent);
            }
        });

        return convertView;
    }


}
