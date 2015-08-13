package jinesh.urbanhunt_test;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.anton46.collectionitempicker.CollectionPicker;
import com.anton46.collectionitempicker.Item;
import com.anton46.collectionitempicker.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jinesh on 09/08/15.
 */
public class FavouriteTags extends Activity {

    CollectionPicker mPicker;
    int counter;
    ArrayList<String> favTags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fav_tags);
        mPicker = (CollectionPicker) findViewById(R.id.collection_item_picker);
        mPicker.setItems(generateItems());

        favTags = new ArrayList<>();
        mPicker.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(Item item, int position) {
                if (item.isSelected) {
                    counter++;
                    favTags.add(item.text);
                    Log.d("count", "" + item.text + " " + counter);
                    Log.d("size", "" + favTags.size());
                } else {
                    counter--;
                    favTags.remove(item.text);
                    Log.d("remcount", "" + item.text + " " + counter);
                    Log.d("size", "" + favTags.size());
                }

            }
        });
    }


        private List<Item> generateItems() {
            List<Item> items = new ArrayList<>();
            items.add(new Item("a", " Pepe Jeans"));
            items.add(new Item("a", "Spykar Jeans"));
            items.add(new Item("a", "Levi's"));
            items.add(new Item("b", "Kurti"));
            items.add(new Item("b", "Lehenga"));
            items.add(new Item("b", "Designer"));
            items.add(new Item("b", "Exclusive Wear"));
            items.add(new Item("c", "PartyWear"));
            items.add(new Item("c", "Cool Casuals"));
            items.add(new Item("c", "Club Mania"));
            items.add(new Item("c", "Bridal Wear"));
            items.add(new Item("c", "Teenager"));
            items.add(new Item("b", "Sling bags"));
            items.add(new Item("a", "Kazo"));
            items.add(new Item("a", "Esbeda"));
            items.add(new Item("b", "Tanishq"));

            return items;
    }
}
