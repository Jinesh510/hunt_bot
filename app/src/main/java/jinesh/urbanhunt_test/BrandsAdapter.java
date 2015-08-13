package jinesh.urbanhunt_test;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;
import com.squareup.picasso.Picasso;

/**
 * Created by Jinesh on 13/08/15.
 */
public class BrandsAdapter extends ParseQueryAdapter<Brand> {


    public BrandsAdapter(Context context, Class<? extends ParseObject> clazz) {
        super(context, clazz);
    }

    public BrandsAdapter(Context context, QueryFactory<Brand> queryFactory) {
        super(context, queryFactory);
    }

    public static class ViewHolder {
        TextView brandName;
        ImageView brandLogo;

    }

    @Override
    public View getItemView(Brand object, View v, ViewGroup parent) {


        ViewHolder viewHolder;
        if (v == null) {
            viewHolder = new ViewHolder();
            v = View.inflate(getContext(), R.layout.brand_list_item, null);
//            viewHolder.brandName = (TextView)v.findViewById(R.id.brandName);
            viewHolder.brandLogo = (ImageView)v.findViewById(R.id.brandLogo);
            v.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder)v.getTag();
        }
//
//        if(v==null){
//            v= View.inflate(getContext(), R.layout.item_hunt_row, parent);
//        }
//
//        huntTitle1 = (TextView)v.findViewById(R.id.huntTitle);
//        huntImage1 = (ImageView)v.findViewById(R.id.huntImage);

//        viewHolder.brandName.setText(object.getName());

        Picasso.with(getContext()).load(object.getLogo().getUrl()).into(viewHolder.brandLogo);
        Picasso.with(getContext()).setIndicatorsEnabled(true);

        return v;


    }

    @Override
    public View getNextPageView(View v, ViewGroup parent) {
        loadObjects();
        return super.getNextPageView(v, parent);
    }
}
