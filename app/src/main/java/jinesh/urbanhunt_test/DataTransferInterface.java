package jinesh.urbanhunt_test;

import java.util.ArrayList;

/**
 * Created by Jinesh on 12/08/15.
 */
public interface DataTransferInterface {
//    public void addValues(int group, String item);
//    public void removeValues(int group, String item);

    public void setValues(int group,ArrayList<String> items);

    public void setBoolean(int group, ArrayList<Boolean> items);
}
