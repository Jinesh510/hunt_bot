package jinesh.urbanhunt_test;

import android.graphics.Bitmap;

/**
 * Created by Jinesh on 20/07/15.
 */
public class Category {

    String name;
    Bitmap image;
    int id;

    public Category() {
    }

    public Category(int id, String name, Bitmap image) {
        this.name = name;
        this.image = image;
        this.id = id;
    }

    public String getCategoryName(){
        return name;
    }

    public String getCategoryName(int id){

        this.id = id;
        return name;
    }

    public int getId(String name){
        this.name = name;
        return id;
    }

    public int getId(){
        return id;
    }

}
