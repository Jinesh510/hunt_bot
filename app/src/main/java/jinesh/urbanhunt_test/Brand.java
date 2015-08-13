package jinesh.urbanhunt_test;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;

/**
 * Created by Jinesh on 19/07/15.
 */
@ParseClassName("Brand")
public class Brand extends ParseObject{

    public Brand() {
        super();
    }

    public String getName(){
        return getString("brandName");
    }

    public ParseFile getLogo(){
        return getParseFile("logo");
    }

    public String getCategoryName(){
        return getString("brandCategoryName");
    }
}

