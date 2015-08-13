package jinesh.urbanhunt_test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jinesh on 11/08/15.
 */
public class Filter_Products extends Fragment implements DataTransferInterface {

    ExpandableListView expandableListView;
    FilterExpandableListAdapter filterExpandableListAdapter;
    List<String> listDataHeader;
    HashMap<String,List<String>> listDataChild;
    RelativeLayout filterListLayout;
    boolean [] Brand;
    boolean [] Price;
    boolean [] Size;
    boolean [] Color;
    List<String> brandList;
    List<String> priceList;
    List<String> sizeList;
    List<String> colorList;



    public Filter_Products() {
    }

    public static Filter_Products newInstance() {
        Filter_Products frag = new Filter_Products();
        Bundle args = new Bundle();
//        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

//    @NonNull
//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        Dialog dialog = super.onCreateDialog(savedInstanceState);
//        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
//        return dialog;
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        Dialog dialog = getDialog();
//        if (dialog != null) {
//            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
////            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        }
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        filterListLayout = (RelativeLayout) inflater.inflate(R.layout.filter_list, container, false);
        expandableListView = (ExpandableListView)filterListLayout.findViewById(R.id.expandableListView);

        Button btnApplyFilter = (Button)filterListLayout.findViewById(R.id.btnApplyFilter);
        prepareListData();

//        expandableListDetail = ExpandableListDataPump.getData();
        filterExpandableListAdapter = new FilterExpandableListAdapter(getActivity(),listDataHeader,listDataChild,this);

        expandableListView.setAdapter(filterExpandableListAdapter);

        Brand = new boolean[filterExpandableListAdapter.getChildrenCount(0)];
        Arrays.fill(Brand, false);

        Price = new boolean[filterExpandableListAdapter.getChildrenCount(1)];
        Arrays.fill(Price,false);


        Size = new boolean[filterExpandableListAdapter.getChildrenCount(2)];
        Arrays.fill(Size,false);

        Color = new boolean[filterExpandableListAdapter.getChildrenCount(3)];
        Arrays.fill(Color,false);


        Log.d("listSize",""+filterExpandableListAdapter.selectedItemsBrand.size());

        return filterListLayout;

    }



    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        listDataHeader.add("Brand");
        listDataHeader.add("Price");
        listDataHeader.add("Size");
        listDataHeader.add("Color");

        List<String> brand = new ArrayList<String>();
        brand.add("Pepe Jeans");
        brand.add("Spykar");
        brand.add("Mustard");
        brand.add("BIBA");


        List<String> price = new ArrayList<String>();
        price.add("Rs.0 - 1000");
        price.add("Rs.1000 - 2000");
        price.add("Rs.2000 - 3000");
        price.add("Rs.3000+");


        List<String> size = new ArrayList<String>();
        size.add("S");
        size.add("M");
        size.add("L");
        size.add("XL");

        List<String> color = new ArrayList<String>();
        color.add("Black");
        color.add("Brown");
        color.add("White");
        color.add("Pink");
        color.add("Red");

        listDataChild.put(listDataHeader.get(0), brand);
        listDataChild.put(listDataHeader.get(1),price);
        listDataChild.put(listDataHeader.get(2),size);
        listDataChild.put(listDataHeader.get(3),color);





    }
//
//    @Override
//    public void addValues(int group, String item) {
//        switch (group){
//            case 0:
////                brandList = arraylist;
//                brandList.add(item);
//                Log.d("bsize",""+brandList.size());
//                break;
//            case 1:
////                priceList = arrayList;
//                priceList.add(item);
//                Log.d("bsize",""+priceList.size());
//                break;
//            case 2:
////                sizeList = arrayList;
//                sizeList.add(item);
//                break;
//            case 3:
////                colorList = arrayList;
//                colorList.add(item);
//                break;
//        }
//
//
//    }
//
//    @Override
//    public void removeValues(int group, String item) {
//
//        switch (group){
//            case 0:
////                brandList = arraylist;
//                brandList.remove(item);
//                Log.d("bsize",""+brandList.size());
//                break;
//            case 1:
////                priceList = arrayList;
//                priceList.remove(item);
//                Log.d("bsize",""+priceList.size());
//                break;
//            case 2:
////                sizeList = arrayList;
//                sizeList.remove(item);
//                break;
//            case 3:
////                colorList = arrayList;
//                colorList.remove(item);
//                break;
//        }
//
//    }

    @Override
    public void setValues(int group, List<String> items) {

        switch (group) {
            case 0:
                brandList = items;
//                brandList.add(item);
//                Log.d("bsize",""+brandList.size());
                break;
            case 1:
                priceList = items;
//                priceList.add(item);
//                Log.d("bsize",""+priceList.size());
                break;
            case 2:
                sizeList = items;
//                sizeList.add(item);
                break;
            case 3:
                colorList = items;
//                colorList.add(item);
                break;

        }

    }
}
