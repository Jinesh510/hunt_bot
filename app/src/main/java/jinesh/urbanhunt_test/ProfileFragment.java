package jinesh.urbanhunt_test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.ArrayList;

/**
 * Created by Jinesh on 23/08/15.
 */
public class ProfileFragment extends Fragment {

    String wishListCount;
    String favBrandCount;
    String creditCount;
    TextView wLCountView;
    TextView cCountView;
    TextView fBCountView;


    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        RelativeLayout v = (RelativeLayout)inflater.inflate(R.layout.user_profile,container,false);

        de.hdodenhof.circleimageview.CircleImageView circleImageView = (de.hdodenhof.circleimageview.CircleImageView)v.findViewById(R.id.profileImage);

        ImageView iv = (ImageView)v.findViewById(R.id.coverImage);
        wLCountView = (TextView)v.findViewById(R.id.wishListCount);

        cCountView = (TextView)v.findViewById(R.id.creditCount);
        creditCount = "100";
        cCountView.setText(creditCount);
        fBCountView = (TextView)v.findViewById(R.id.favBrandCount);

        ParseUser user = ParseUser.getCurrentUser();
        user.fetchInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject parseObject, ParseException e) {

                ArrayList<String> wishList = (ArrayList<String>)parseObject.get("wishList");

                wishListCount = String.valueOf(wishList.size());
                wLCountView.setText(wishListCount);

                ArrayList<String> favBrandList = (ArrayList<String>)parseObject.get("favBrand");
                favBrandCount = String.valueOf(favBrandList.size());
                fBCountView.setText(favBrandCount);


            }
        });

        return v;
    }
}
