package jinesh.urbanhunt_test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jinesh on 20/07/15.
 */
public class CategoryArrayAdapter extends ArrayAdapter<Category> {

    ArrayList<Category> categoryArrayList;
    public CategoryArrayAdapter(Context context,ArrayList<Category> categoryArray) {
        super(context, 0, categoryArray);
        categoryArrayList = categoryArray;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_category, parent, false);
        }

        ImageView categoryImage = (ImageView)convertView.findViewById(R.id.categoryImage);
        TextView categoryName = (TextView)convertView.findViewById(R.id.categoryName);

        categoryName.setText(categoryArrayList.get(position).getCategoryName());
        categoryImage.setImageBitmap(categoryArrayList.get(position).image);

//        final String strCategoryName = categoryName.getText().toString();
//        categoryImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(getContext(),HuntListActivity.class);
//                i.putExtra("category",strCategoryName);
//                getContext().startActivity(i);
//            }
//        });



//        Picasso.with(getContext()).load(categoryArrayList.get)

        return convertView;
    }
}
