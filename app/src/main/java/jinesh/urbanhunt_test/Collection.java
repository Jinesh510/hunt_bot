package jinesh.urbanhunt_test;

import android.graphics.Bitmap;

/**
 * Created by Jinesh on 09/08/15.
 */
public class Collection {

    String name;
    Bitmap image;

    public Collection(String name, Bitmap image) {
        this.name = name;
        this.image = image;
    }

    public String getCollectionName(){
        return name;
    }

}
