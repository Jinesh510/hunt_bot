package jinesh.urbanhunt_test;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;

/**
 * Created by Jinesh on 10/08/15.
 */
@ParseClassName("Product_1")
public class Product_1 extends ParseObject{

//    String id;
//    String category;
//    String subCategory;
//    ParseFile productImage;

    public Product_1() {
        super();
    }

    //    public String getId() {
//        return getString("");
//    }

    public String getCategory() {
        return getString("category");
    }

    public String getSubCategory() {
        return getString("subCategory");
    }

    public ParseFile getProductImage() {
        return getParseFile("productImage");
    }

    public String getPrice(){
        return getString("price");
    }

}
