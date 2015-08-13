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
 * Created by Jinesh on 09/08/15.
 */
public class CollectionsAdapter extends ArrayAdapter<Collection> {

    ArrayList<Collection> collectionArrayList;
    //    Context cContext;
    public CollectionsAdapter(Context context, ArrayList<Collection> collectionsArray) {
        super(context, 0, collectionsArray);
        collectionArrayList = collectionsArray;
//        cContext = context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

//        if (convertView == null) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.collection_item, parent, false);
//        }

        ImageView categoryImage = (ImageView)convertView.findViewById(R.id.collectionImage);
//        de.hdodenhof.circleimageview.CircleImageView categoryImage = (CircleImageView)convertView.findViewById(R.id.categoryImage);
        TextView categoryName = (TextView)convertView.findViewById(R.id.collectionName);

        categoryName.setText(collectionArrayList.get(position).getCollectionName());
        categoryImage.setImageBitmap(collectionArrayList.get(position).image);

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
