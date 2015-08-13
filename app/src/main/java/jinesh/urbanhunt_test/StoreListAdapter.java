package jinesh.urbanhunt_test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jinesh on 13/08/15.
 */
public class StoreListAdapter extends ArrayAdapter<Store> {

    ArrayList<Store> storeArrayList;
    public StoreListAdapter(Context context, ArrayList<Store> storeList) {
        super(context, 0 ,  storeList );
        this.storeArrayList = storeList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Store store = getItem(position);
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.store_list_item, parent, false);
        }

        TextView storeAddress = (TextView)convertView.findViewById(R.id.storeAddress);

        storeAddress.setText(store.getAddress());

        return convertView;

    }

    public void setStoreList(ArrayList<Store> storeList) {
        storeArrayList = storeList;
        notifyDataSetChanged();
    }
}
