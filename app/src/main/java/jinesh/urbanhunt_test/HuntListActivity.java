package jinesh.urbanhunt_test;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Jinesh on 03/07/15.
 */
public class HuntListActivity extends Activity {

    private Hunt hunt;

    public HuntListActivity() {
        super();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        hunt = new Hunt();

        super.onCreate(savedInstanceState);
    }
}
