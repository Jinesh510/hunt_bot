package jinesh.urbanhunt_test;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by Jinesh on 19/07/15.
 */
@ParseClassName("Store")
public class Store extends ParseObject{

    public Store() {
        super();
    }

    public String getName(){
        return getString("storeName");
    }

    public String getBrandName(){
        return getString("storeBrand");
    }

    public String getAddress(){
        return getString("storeAddress");
    }

    public String getContactNumber(){
        return getString("storeContactNumber");
    }

}
