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
public class HorizontalImageAdapter extends ArrayAdapter<Product_1> {

//    public HorizontalImageAdapter(Context context,ArrayList<Hunt> imgHunts) {
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
//            convertView = inflater.inflate(R.layout.horizontal_list_item,parent,false);
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

    ArrayList<Product_1> hlvImageArrayList;

    public HorizontalImageAdapter(Context context, ArrayList<Product_1> brandPopList) {
        super(context, 0, brandPopList);
        hlvImageArrayList = brandPopList;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

//        final Hunt hunt = getItem(position);

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.horizontal_list_item, parent, false);
        }

        //Lookup views in item layout

        ImageView hlvImage = (ImageView) convertView.findViewById(R.id.hlvImage);


//        ParseImageView huntImage = (ParseImageView)convertView.findViewById(R.id.huntImage);

        //loading data into template


        Picasso.with(getContext()).load(hlvImageArrayList.get(position).getProductImage().getUrl()).into(hlvImage);

        return convertView;

//        return super.getView(position, convertView, parent);
    }

    public void setImageList(ArrayList<Product_1> huntList) {
        hlvImageArrayList = huntList;
        notifyDataSetChanged();
    }

}
