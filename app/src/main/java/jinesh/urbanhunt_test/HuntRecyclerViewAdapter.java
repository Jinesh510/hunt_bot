package jinesh.urbanhunt_test;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Jinesh on 16/07/15.
 */
public class HuntRecyclerViewAdapter extends RecyclerView.Adapter<HuntRecyclerViewAdapter.ViewHolder> {


    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView huntTitle;
        public ImageView huntImage;

        public ViewHolder(View itemView){
            super(itemView);
            this.huntTitle = (TextView)itemView.findViewById(R.id.huntTitle);
            this.huntImage = (ImageView)itemView.findViewById(R.id.huntImage);
        }
    }

    private ArrayList<Hunt> hunts;
    private Context context;

    public HuntRecyclerViewAdapter(Context context, ArrayList<Hunt> hunts ) {
        this.hunts = hunts;
        this.context = context;
    }

    @Override
    public HuntRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView  = LayoutInflater.from(context).inflate(R.layout.item_hunt_row,parent,false);

        return new HuntRecyclerViewAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HuntRecyclerViewAdapter.ViewHolder holder, int position) {
        Hunt hunt = hunts.get(position);
        holder.huntTitle.setText(hunt.getTitle());
        Picasso.with(context).load(hunt.getPhotoFile().getUrl()).into(holder.huntImage);

    }

    public void setHuntList(ArrayList<Hunt> huntList) {
        hunts = huntList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {

        return hunts.size();
    }
}
