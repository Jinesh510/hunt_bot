package jinesh.urbanhunt_test;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.squareup.picasso.Picasso;

/**
 * Created by Jinesh on 10/08/15.
 */
public class ProductAdapter extends ParseQueryAdapter<Product_1> {
    public ProductAdapter(Context context, QueryFactory<Product_1> queryFactory) {
        super(context, queryFactory);
    }


    public static class ViewHolder_2 {
        TextView productPrice;
        ImageView productImage;

    }
//
//    TextView huntTitle1;
//    ImageView huntImage1;

    @Override
    public View getItemView(Product_1 object, View v, ViewGroup parent) {

        ViewHolder_2 viewHolder;
        if (v == null) {
            viewHolder = new ViewHolder_2();
            v = View.inflate(getContext(), R.layout.product_item, null);
            viewHolder.productPrice = (TextView)v.findViewById(R.id.productPrice);
            viewHolder.productImage = (ImageView)v.findViewById(R.id.productImage);
            v.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder_2)v.getTag();
        }
//
//        if(v==null){
//            v= View.inflate(getContext(), R.layout.item_hunt_row, parent);
//        }
//
//        huntTitle1 = (TextView)v.findViewById(R.id.huntTitle);
//        huntImage1 = (ImageView)v.findViewById(R.id.huntImage);

        viewHolder.productPrice.setText(object.getPrice());

        Picasso.with(getContext()).load(object.getProductImage().getUrl()).into(viewHolder.productImage);
        Picasso.with(getContext()).setIndicatorsEnabled(true);

        return v;

    }



    @Override
    public View getNextPageView(View v, ViewGroup parent) {
        loadNextPage();
        return super.getNextPageView(v, parent);
    }


    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public void setObjectsPerPage(int objectsPerPage) {
        super.setObjectsPerPage(objectsPerPage);
    }

    @Override
    protected void setPageOnQuery(int page, ParseQuery<Product_1> query) {
        super.setPageOnQuery(page, query);
    }

    @Override
    public void addOnQueryLoadListener(OnQueryLoadListener<Product_1> listener) {
        super.addOnQueryLoadListener(listener);
    }
}
