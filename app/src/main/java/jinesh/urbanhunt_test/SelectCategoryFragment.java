package jinesh.urbanhunt_test;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jinesh on 20/07/15.
 */
public class SelectCategoryFragment extends Fragment {

    GridView categoryGrid;
    LinearLayout CategoryLayout;
    CategoryArrayAdapter categoryArrayAdapter;

    public static SelectCategoryFragment newInstance() {
        SelectCategoryFragment fragment = new SelectCategoryFragment();
//        Bundle bundle = new Bundle();
//        fragment.setArguments(bundle);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        CategoryLayout = (LinearLayout)inflater.inflate(R.layout.category,container,false);

        ArrayList<Category> categoryArray = new ArrayList<>();

        Bitmap clothes  = BitmapFactory.decodeResource(this.getResources(),R.drawable.profile);

        categoryArray.add(new Category("Apparels",clothes));
        categoryArray.add(new Category("Bags",clothes));
        categoryArray.add(new Category("Beauty",clothes));
        categoryArray.add(new Category("Accessories",clothes));
        categoryArray.add(new Category("Jewellery",clothes));
        categoryArray.add(new Category("Others",clothes));

        categoryGrid = (GridView)CategoryLayout.findViewById(R.id.categoryGrid);
//        ArrayAdapter<Category> CategoryArrayAdapter = new ArrayAdapter<Category>(getActivity(),R.layout.item_category,categoryArray);
        categoryArrayAdapter = new CategoryArrayAdapter(getActivity(),categoryArray);
        categoryGrid.setAdapter(categoryArrayAdapter);


        categoryGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                TextView categoryName = (TextView)view.findViewById(R.id.categoryName);
                String strCategoryName = categoryName.getText().toString();
                Intent intent = new Intent(getActivity(),FilterCategories.class);
                intent.putExtra("category", strCategoryName);
                getActivity().startActivity(intent);
            }
        });


        return CategoryLayout;


    }
}
