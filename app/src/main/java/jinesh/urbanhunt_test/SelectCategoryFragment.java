package jinesh.urbanhunt_test;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.analytics.Tracker;
import com.quinny898.library.persistentsearch.SearchBox;

import java.util.ArrayList;

/**
 * Created by Jinesh on 20/07/15.
 */
public class SelectCategoryFragment extends Fragment {

    private HorizontalListView categoryGrid;
    private RelativeLayout CategoryLayout;
    private CategoriesArrayAdapter categoriesArrayAdapter;
    private CollectionsAdapter collectionsAdapter;
    private ListView collectionList;
    private SearchBox search;
    private Category category;
    private Collection collection;
    private Tracker mTracker;


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
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        CategoryLayout = (RelativeLayout)inflater.inflate(R.layout.collections,container,false);

//        search = (SearchBox)CategoryLayout.findViewById(R.id.searchbox);
//        search.enableVoiceRecognition(this);

        TextView collectionHeader = (TextView)CategoryLayout.findViewById(R.id.collectionHeader);
        View sepLine = (View)CategoryLayout.findViewById(R.id.sepline);

        ArrayList<Category> categoryArray = new ArrayList<>();
        ArrayList<Collection> collectionArray = new ArrayList<>();

        Bitmap apparels  = BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.apparels_cat);
        Bitmap bags  = BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.bags_cat);
//        Bitmap beauty  = BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.beauty_cat);
        Bitmap accesories  = BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.accesories_cat);
        Bitmap footwear  = BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.footwear_cat);
        Bitmap watches  = BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.watches_cat);
        Bitmap walp  = BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.splash_image);


        categoryArray.add(new Category(1,"Apparels",apparels));
        categoryArray.add(new Category(2,"Bags",bags));
//        categoryArray.add(new Category("Beauty",beauty));
        categoryArray.add(new Category(3,"Accessories",accesories));
        categoryArray.add(new Category(4,"Footwear",footwear));
        categoryArray.add(new Category(5,"Others",watches));


        categoryGrid = (HorizontalListView)CategoryLayout.findViewById(R.id.categoryList);
//        ArrayAdapter<Category> CategoriesArrayAdapter = new ArrayAdapter<Category>(getActivity(),R.layout.item_category,categoryArray);
        categoriesArrayAdapter = new CategoriesArrayAdapter(getActivity(),categoryArray);
        categoryGrid.setAdapter(categoriesArrayAdapter);

        collectionList = (ListView)CategoryLayout.findViewById(R.id.collectionsList);
        collectionsAdapter = new CollectionsAdapter(getActivity(),collectionArray);

        collectionArray.add(new Collection("Travel", walp));
        collectionArray.add(new Collection("Wedding",apparels));
        collectionArray.add(new Collection("Bags",bags));
//        categoryArray.add(new Category("Beauty",beauty));
        collectionArray.add(new Collection("Accessories", accesories));
        collectionArray.add(new Collection("Footwear",footwear));
        collectionArray.add(new Collection("Others", watches));
        collectionArray.add(new Collection("Others", watches));
        collectionArray.add(new Collection("Others", watches));
        collectionArray.add(new Collection("Others", watches));

        collectionList.setAdapter(collectionsAdapter);

//        UrbanHuntApplication application = (UrbanHuntApplication)getActivity().getApplication();
//        mTracker = application.getDefaultTracker();



        categoryGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                category = (Category) adapterView.getItemAtPosition(i);
                int categoryId = category.getId();
                String categoryName = category.getCategoryName();
//                TextView categoryName = (TextView) view.findViewById(R.id.categoryName);
//                String strCategoryName = categoryName.getText().toString();
//                int categoryId = category.getId(strCategoryName);

                // Build and send an Event.
//                mTracker.send(new HitBuilders.EventBuilder()
//                        .setCategory("Action")
//                        .setAction("Share")
//                        .build());

                Intent intent = new Intent(getActivity(), FilterCategoriesActivity.class);
                intent.putExtra("category", categoryId);
                intent.putExtra("categoryName", categoryName);
                getActivity().startActivity(intent);
            }
        });

        collectionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                collection = (Collection)adapterView.getItemAtPosition(i);
                String collectionName = collection.getCollectionName();
                Intent intent = new Intent(getActivity(), FilterCategoriesActivity.class);
                intent.putExtra("collectionName",collectionName);
                getActivity().startActivity(intent);
            }
        });

//        for(int x = 0; x < 10; x++){
//            SearchResult option = new SearchResult("Result " + Integer.toString(x), getResources().getDrawable(R.drawable.bag, null));
//            search.addSearchable(option);
//        }
//        search.setMenuListener(new SearchBox.MenuListener() {
//
//
//            @Override
//            public void onMenuClick() {
//                //Hamburger has been clicked
//                Toast.makeText(getActivity(), "Menu click", Toast.LENGTH_LONG).show();
//            }
//
//        });
//
//
//        search.setSearchListener(new SearchBox.SearchListener() {
//
//
//            @Override
//            public void onSearchOpened() {
//                //Use this to tint the screen
//            }
//
//
//            @Override
//            public void onSearchClosed() {
//                //Use this to un-tint the screen
//            }
//
//
//            @Override
//            public void onSearchTermChanged() {
//                //React to the search term changing
//                //Called after it has updated results
//            }
//
//
//            @Override
//            public void onSearch(String searchTerm) {
//                Toast.makeText(getActivity(), searchTerm + " Searched", Toast.LENGTH_LONG).show();
//
//            }
//
//
//            @Override
//            public void onSearchCleared() {
//                //Called when the clear button is clicked
//
//            }
//
//        });



        return CategoryLayout;


    }
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (isAdded() && requestCode == SearchBox.VOICE_RECOGNITION_CODE && resultCode == getActivity().RESULT_OK) {
//            ArrayList<String> matches = data
//                    .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
//            search.populateEditText(matches);
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }
}
