package jinesh.urbanhunt_test;

import android.graphics.Bitmap;

/**
 * Created by Jinesh on 20/07/15.
 */
public class Category {

    String name;
    Bitmap image;

    public Category(String name, Bitmap image) {
        this.name = name;
        this.image = image;
    }

    public String getCategoryName(){
        return name;
    }

}
