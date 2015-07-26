package jinesh.urbanhunt_test;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Jinesh on 16/07/15.
 */
public class HuntRecyclerViewAdapter extends RecyclerView.Adapter<HuntRecyclerViewAdapter.ViewHolder> {


    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView huntTitle;
        public ImageView huntImage;
        public ImageView shareHunt;
        public CardView huntCard;

        public ViewHolder(View itemView){
            super(itemView);
            this.huntCard = (CardView)itemView.findViewById(R.id.huntCard);
            this.huntTitle = (TextView)itemView.findViewById(R.id.huntTitle);
            this.huntImage = (ImageView)itemView.findViewById(R.id.huntImage);
            this.shareHunt = (ImageView)itemView.findViewById(R.id.shareHunt);

            shareHunt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setShareHuntIntent(view);
                }
            });

        }

        private void setShareHuntIntent(View view){

            Uri huntUri = getLocalBitmapUri(huntImage);
            if(huntUri != null){

                Intent shareHuntIntent = new Intent();
                shareHuntIntent.setAction(Intent.ACTION_SEND);
                shareHuntIntent.putExtra(Intent.EXTRA_STREAM, huntUri);
                shareHuntIntent.setType("*/*");
                shareHuntIntent.putExtra(Intent.EXTRA_TEXT,huntTitle.getText().toString());


                view.getContext().startActivity(Intent.createChooser(shareHuntIntent,"Share Hunt"));
            }

        }

        public Uri getLocalBitmapUri(ImageView imageView){

            Drawable drawable = imageView.getDrawable();
            Bitmap bmp = null;
            if(drawable instanceof BitmapDrawable){
                bmp = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
            }
            else {
                return null;
            }

            Uri bmpUri = null;

            try{
                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "share_image_" + System.currentTimeMillis() + ".png");
                file.getParentFile().mkdirs();
                FileOutputStream out = new FileOutputStream(file);
                bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
                out.close();
                bmpUri = Uri.fromFile(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return bmpUri;

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

        View itemView  = LayoutInflater.from(context).inflate(R.layout.item_hunt_row, parent, false);

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
