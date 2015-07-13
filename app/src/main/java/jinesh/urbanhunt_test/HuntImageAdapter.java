package jinesh.urbanhunt_test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Jinesh on 10/07/15.
 */
public class HuntImageAdapter extends ArrayAdapter<Hunt> {

//    public HuntImageAdapter(Context context,ArrayList<Hunt> imgHunts) {
//        super(context,0, imgHunts);
//    }
//
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//        final Hunt hunt = getItem(position);
//
//        if (convertView ==null){
//            LayoutInflater inflater = LayoutInflater.from(getContext());
//            convertView = inflater.inflate(R.layout.hunt_horizontal_item,parent,false);
//        }
//
//        //Lookup views in item layout
//
//        ImageView hlvHuntImage = (ImageView)convertView.findViewById(R.id.hlvHuntImage);
//
//
////        ParseImageView huntImage = (ParseImageView)convertView.findViewById(R.id.huntImage);
//
//        //loading data into template
//
//
//        Picasso.with(getContext()).load(hunt.getPhotoFile().getUrl()).into(hlvHuntImage);
//
//        return convertView;
//
////        return super.getView(position, convertView, parent);
//
//
//    }

    ArrayList<Hunt> huntArrayList;

    public HuntImageAdapter(Context context, ArrayList<Hunt> imgHunts) {
        super(context, 0, imgHunts);
        huntArrayList = imgHunts;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

//        final Hunt hunt = getItem(position);

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.hunt_horizontal_item, parent, false);
        }

        //Lookup views in item layout

        ImageView hlvHuntImage = (ImageView) convertView.findViewById(R.id.hlvHuntImage);


//        ParseImageView huntImage = (ParseImageView)convertView.findViewById(R.id.huntImage);

        //loading data into template


        Picasso.with(getContext()).load(huntArrayList.get(position).getPhotoFile().getUrl()).into(hlvHuntImage);

        return convertView;

//        return super.getView(position, convertView, parent);
    }

    public void setHuntList(ArrayList<Hunt> huntList) {
        huntArrayList = huntList;
        notifyDataSetChanged();
    }

}
