package jinesh.urbanhunt_test;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseQueryAdapter;
import com.squareup.picasso.Picasso;

/**
 * Created by Jinesh on 28/07/15.
 */
public class CustomParseQueryAdapter extends ParseQueryAdapter<Hunt> {
    public CustomParseQueryAdapter(Context context, QueryFactory<Hunt> queryFactory) {
        super(context, queryFactory);
    }

    public static class ViewHolder2 {
         TextView huntTitle1;
         ImageView huntImage1;

    }

    @Override
    public View getItemView(Hunt object, View v, ViewGroup parent) {

        ViewHolder2 viewHolder;
        if (v == null) {
            viewHolder = new ViewHolder2();
            v = View.inflate(getContext(), R.layout.item_hunt_row, null);
            viewHolder.huntTitle1 = (TextView)v.findViewById(R.id.huntTitle);
            viewHolder.huntImage1 = (ImageView)v.findViewById(R.id.huntImage);
            v.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder2)v.getTag();
        }

//        TextView huntTitle = (TextView)v.findViewById(R.id.huntTitle);
//        ImageView huntImage = (ImageView)v.findViewById(R.id.huntImage);

        viewHolder.huntTitle1.setText(object.getTitle());
        Picasso.with(getContext()).load(object.getPhotoFile().getUrl()).into(viewHolder.huntImage1);


        return v;

    }

    @Override
    public void loadNextPage() {
        super.loadNextPage();
    }

    @Override
    public View getNextPageView(View v, ViewGroup parent) {
        return super.getNextPageView(v, parent);
    }

    @Override
    public void setObjectsPerPage(int objectsPerPage) {
        super.setObjectsPerPage(objectsPerPage);
    }

}
