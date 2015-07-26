package jinesh.urbanhunt_test;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.ArrayList;

/**
 * Created by Jinesh on 03/07/15.
 */
@ParseClassName("Hunt")
public class Hunt extends ParseObject {

    public Hunt() {
        // A default constructor is required.
        super();
    }


    public String getTitle() {
        return getString("title");
    }

    public void setTitle(String title) {
        put("title", title);
    }

    public ParseUser getAuthor() {
        return getParseUser("author");
    }

    public void setAuthor(ParseUser user) {
        put("author", user);
    }

    public String getCategory() {
        return getString("category");
    }

    public void setCategory(String category) {
        put("category", category);
    }

    public String getTags(){
        return getString("tags");
    }

    public void setTags(ArrayList<String> tags){
        put("tags",tags);
    }

    public String getBudget(){
        return getString("budget");
    }

    public void setBudget(String budget){
        put("budget",budget);
    }

    public String getPreferences(){
        return getString("preferences");
    }

    public void setPreferencecs(String preferencecs){
        put("preferences",preferencecs);
    }

    public ParseFile getPhotoFile() {
        return getParseFile("photo");
    }

    public void setPhotoFile(ParseFile file) {
        put("photo", file);
    }

}