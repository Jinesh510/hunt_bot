package jinesh.urbanhunt_test;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
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

    private GridView categoryGrid;
    private LinearLayout CategoryLayout;
    private CategoriesArrayAdapter categoriesArrayAdapter;

    public static SelectCategoryFragment newInstance() {
        SelectCategoryFragment fragment = new SelectCategoryFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

//    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        CategoryLayout = (LinearLayout)inflater.inflate(R.layout.category,container,false);

        ArrayList<Category> categoryArray = new ArrayList<>();

        Bitmap apparels  = BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.apparels_cat);
        Bitmap bags  = BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.bags_cat);
        Bitmap beauty  = BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.beauty_cat);
        Bitmap accesories  = BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.accesories_cat);
        Bitmap footwear  = BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.footwear_cat);
        Bitmap watches  = BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.watches_cat);


        categoryArray.add(new Category("Apparels",apparels));
        categoryArray.add(new Category("Bags",bags));
        categoryArray.add(new Category("Beauty",beauty));
        categoryArray.add(new Category("Accessories",accesories));
        categoryArray.add(new Category("Footwear",footwear));
        categoryArray.add(new Category("Others",watches));

        categoryGrid = (GridView)CategoryLayout.findViewById(R.id.categoryGrid);
//        ArrayAdapter<Category> CategoriesArrayAdapter = new ArrayAdapter<Category>(getActivity(),R.layout.item_category,categoryArray);
        categoriesArrayAdapter = new CategoriesArrayAdapter(getActivity(),categoryArray);
        categoryGrid.setAdapter(categoriesArrayAdapter);


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
