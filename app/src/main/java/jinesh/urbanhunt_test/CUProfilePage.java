package jinesh.urbanhunt_test;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.parse.ParseUser;

public class CUProfilePage extends Fragment {

    ParseUser parseUser;
    LinearLayout cProfilePageLayout;
    String cUserName;

    public static CUProfilePage newInstance() {
        CUProfilePage fragment = new CUProfilePage();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        parseUser = ParseUser.getCurrentUser();
        cUserName = parseUser.getUsername();
        cProfilePageLayout = (LinearLayout)inflater.inflate(R.layout.fragment_cu_profile_page,container,false);
        TextView cUserNameView = (TextView)cProfilePageLayout.findViewById(R.id.cUserName);
        cUserNameView.setText(cUserName);


        return cProfilePageLayout;

    }
}
