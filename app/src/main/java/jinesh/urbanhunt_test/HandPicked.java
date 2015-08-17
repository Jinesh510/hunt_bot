package jinesh.urbanhunt_test;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.andtinder.model.CardModel;
import com.andtinder.view.CardContainer;
import com.andtinder.view.SimpleCardStackAdapter;

/**
 * Created by Jinesh on 08/08/15.
 */
public class HandPicked extends Activity {

    private CardContainer cardContainer;
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tinder_layout);

        cardContainer = (CardContainer)findViewById(R.id.layoutview);

        SimpleCardStackAdapter adapter = new SimpleCardStackAdapter(this);
        adapter.add(new CardModel("Accesories", "Description goes here", getResources().getDrawable(R.drawable.accesories_cat,null)));
        adapter.add(new CardModel("Apparels", "Description goes here", getResources().getDrawable(R.drawable.apparels_cat, null)));
        adapter.add(new CardModel("Title1", "Description goes here", getResources().getDrawable(R.drawable.bags_cat, null)));

        CardModel cardModel = new CardModel("Title1", "Description goes here", getResources().getDrawable(R.drawable.beauty_cat, null));
        cardModel.setOnClickListener(new CardModel.OnClickListener() {
            @Override
            public void OnClickListener() {
                Log.i("Swipeable Cards", "I am pressing the card");
//                Log.d("tImg",cardModel.getTitle());
            }
        });

//        ParseQuery<CardModel> parseQuery = ParseQuery.getQuery(CardModel.class);

        cardModel.setOnCardDimissedListener(new CardModel.OnCardDimissedListener() {
            @Override
            public void onLike() {
                Log.i("Swipeable Cards", "I like the card");
            }


            @Override
            public void onDislike() {
                Log.i("Swipeable Cards", "I dislike the card");
            }
        });

        adapter.add(cardModel);

        cardContainer.setAdapter(adapter);

    }
}
