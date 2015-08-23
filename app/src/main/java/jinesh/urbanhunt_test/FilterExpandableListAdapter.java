package jinesh.urbanhunt_test;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jinesh on 11/08/15.
 */
public class FilterExpandableListAdapter extends BaseExpandableListAdapter {

    Context _context;
    List<String> listDataHeader;
    HashMap<String,List<String>> listDataChild;
    boolean [] checkboxState;
    Filter_Products filter_products;
    HashMap<String,List<String>> selectedItemsMap = null;
    ArrayList<String> selectedItemsBrand = new ArrayList<String>();
    ArrayList<String> selectedItemsPrice = new ArrayList<String>();
    ArrayList<String> selectedItemsSize = new ArrayList<String>();
    ArrayList<String> selectedItemsColor = new ArrayList<String>();
    DataTransferInterface dtInterface;
    ArrayList<Boolean> brandPositionArray;
    ArrayList<Boolean> pricePositionArray;
    ArrayList<Boolean> sizePositionArray;
    ArrayList<Boolean> colorPositionArray;

    ArrayList<String> brandArray;
    final ArrayList<String> priceArray;
    ArrayList<String> sizeArray;
    ArrayList<String> colorArray;

    ArrayList<Boolean> brandBooleanArray;
    ArrayList<Boolean> priceBooleanArray;
    ArrayList<Boolean> sizeBooleanArray;
    ArrayList<Boolean> colorBooleanArray;





    int selected [];


    public FilterExpandableListAdapter(Context _context, List<String> listDataHeader, HashMap<String, List<String>> listDataChild, DataTransferInterface dtInterface) {
        this._context = _context;
        this.listDataHeader = listDataHeader;
        this.listDataChild = listDataChild;
        this.dtInterface = dtInterface;

        brandArray = ((FilterCategoriesActivity) _context).getBrandArrayList();
        priceArray = ((FilterCategoriesActivity) _context).getPriceArrayList();
        sizeArray = ((FilterCategoriesActivity) _context).getSizeArrayList();
        colorArray = ((FilterCategoriesActivity) _context).getColorArrayList();
        brandBooleanArray = ((FilterCategoriesActivity)_context).getBrandPositionArray();
        priceBooleanArray = ((FilterCategoriesActivity)_context).getPricePositionArray();
        sizeBooleanArray = ((FilterCategoriesActivity)_context).getSizePositionArray();
        colorPositionArray = ((FilterCategoriesActivity)_context).getColorPositionArray();

        brandPositionArray = new ArrayList<Boolean>(getChildrenCount(0));
        for(int i =0;i<getChildrenCount(0);i++){
            brandPositionArray.add(false);
        }

        pricePositionArray = new ArrayList<Boolean>(getChildrenCount(1));
        for(int i =0;i<getChildrenCount(1);i++){
            pricePositionArray.add(false);
        }

        sizePositionArray = new ArrayList<Boolean>(getChildrenCount(2));
        for(int i =0;i<getChildrenCount(2);i++){
            sizePositionArray.add(false);
        }

        colorPositionArray = new ArrayList<Boolean>(getChildrenCount(3));
        for(int i =0;i<getChildrenCount(3);i++){
            colorPositionArray.add(false);
        }

        if(brandBooleanArray !=null){
            brandPositionArray = brandBooleanArray;
            dtInterface.setBoolean(0,brandPositionArray);
        }

        if(priceBooleanArray !=null){
            pricePositionArray = priceBooleanArray;
            dtInterface.setBoolean(1,pricePositionArray);

        }

        if(sizeBooleanArray !=null){
            sizePositionArray = sizeBooleanArray;
            dtInterface.setBoolean(2,sizePositionArray);

        }

        if(colorBooleanArray !=null){
            colorPositionArray = colorBooleanArray;
            dtInterface.setBoolean(3,colorPositionArray);

        }

        if(brandArray != null){
            selectedItemsBrand = brandArray;
            dtInterface.setValues(0,selectedItemsBrand);
        }

        if(priceArray !=null){
            selectedItemsPrice = priceArray;
            dtInterface.setValues(1,selectedItemsPrice);
        }

        if(sizeArray !=null){
            selectedItemsSize = sizeArray;
            dtInterface.setValues(2,selectedItemsSize);
        }

        if(colorArray !=null){
            selectedItemsColor = colorArray;
            dtInterface.setValues(3,selectedItemsColor);
        }


    }

    public static class ViewHolder{
        TextView txtListChild;
        CheckBox checkListChild;
    }

    public static class GroupViewholder{

        TextView lblListHeader;
    }

    @Override
    public int getGroupCount() {
        return this.listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.listDataChild.get(this.listDataHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.listDataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.listDataChild.get(this.listDataHeader.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        String headerTitle = (String) getGroup(groupPosition);
        GroupViewholder groupViewholder = null;

        if (convertView == null) {
            groupViewholder = new GroupViewholder();
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.filter_group, null);
            groupViewholder.lblListHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
            convertView.setTag(groupViewholder);

        }else {
            groupViewholder = (GroupViewholder)convertView.getTag();
        }

        groupViewholder.lblListHeader.setTypeface(null, Typeface.BOLD);
        groupViewholder.lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
        public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        Log.d("Open", "true");
        final String childText = (String) getChild(groupPosition, childPosition);
        ViewHolder viewHolder = null;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.filter_group_item, null);
            viewHolder.txtListChild = (TextView) convertView.findViewById(R.id.lblListItem);
            viewHolder.checkListChild = (CheckBox)convertView.findViewById(R.id.filter_check);

            convertView.setTag(viewHolder);

        }
        else{

            viewHolder = (ViewHolder)convertView.getTag();
            viewHolder.checkListChild.setOnCheckedChangeListener(null);
        }

        switch (groupPosition){
            case 0:
                viewHolder.checkListChild.setChecked(brandPositionArray.get(childPosition));
                break;
            case 1:
                viewHolder.checkListChild.setChecked(pricePositionArray.get(childPosition));
                break;
            case 2:
                viewHolder.checkListChild.setChecked(sizePositionArray.get(childPosition));
                break;
            case 3:
                viewHolder.checkListChild.setChecked(colorPositionArray.get(childPosition));
                break;

        }


//        TextView txtListChild = (TextView) convertView.findViewById(R.id.lblListItem);
//        final CheckBox checkListChild = (CheckBox)convertView.findViewById(R.id.filter_check);

        checkboxState = new boolean[getChildrenCount(groupPosition)];
        Arrays.fill(checkboxState, false);



//        switch (groupPosition){
//            case 0:
//                if(brandArray != null){
//                    boolean temp_0 = brandArray.contains(childText);
//                    if (temp_0 == true){
////                        viewHolder.checkListChild.setChecked(true);
//                        if(!selectedItemsBrand.contains(childText)) {
//                            selectedItemsBrand.add(childText);
//                            Log.d("Added", "" + selectedItemsBrand.size());
//                            dtInterface.setValues(groupPosition, selectedItemsBrand);
//                        }
//                    }
//                }
//
//                break;
//            case 1:
//                if(priceArray !=null){
//                    boolean temp_1 = priceArray.contains(childText);
//                    if (temp_1 == true){
////                        viewHolder.checkListChild.setChecked(true);
//                        if(!selectedItemsPrice.contains(childText)) {
//                            selectedItemsPrice.add(childText);
//                            dtInterface.setValues(groupPosition, selectedItemsPrice);
//                        }
//                    }
//                }
//                break;
//            case 2:
//                if(sizeArray !=null) {
//                    boolean temp_2 = sizeArray.contains(childText);
//                    if (temp_2 == true) {
////                        viewHolder.checkListChild.setChecked(true);
//                        if(!selectedItemsSize.contains(childText)) {
//                            selectedItemsSize.add(childText);
//                            dtInterface.setValues(groupPosition, selectedItemsSize);
//                        }
//                    }
//                }
//                break;
//            case 3:
//                if(colorArray !=null) {
//                    boolean temp_3 = colorArray.contains(childText);
//                    if (temp_3 == true) {
////                        viewHolder.checkListChild.setChecked(true);
//                        if(!selectedItemsColor.contains(childText)) {
//                            selectedItemsColor.add(childText);
//                            dtInterface.setValues(groupPosition, selectedItemsColor);
//                        }
//                    }
//                }
//                break;
//
//
//        }

        final ViewHolder finalViewHolder = viewHolder;
        viewHolder.checkListChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((CheckBox) view).isChecked()) {
//                    finalViewHolder.checkListChild.setChecked(true);
                    finalViewHolder.checkListChild.setChecked(true);

                    Log.d("isCheck", "" + finalViewHolder.checkListChild.isChecked());

//                    dtInterface.addValues((int)groupPosition,childText);

                    switch (groupPosition) {
                        case 0:
                            brandPositionArray.set(childPosition,true);
                            dtInterface.setBoolean(groupPosition,brandPositionArray);
                            selectedItemsBrand.add(childText);
                            Log.d("Added", "" + selectedItemsBrand.size());
                            dtInterface.setValues(groupPosition, selectedItemsBrand);

                            break;
                        case 1:
                            pricePositionArray.set(childPosition,true);
                            dtInterface.setBoolean(groupPosition, pricePositionArray);
                            selectedItemsPrice.add(childText);
                            dtInterface.setValues(groupPosition, selectedItemsPrice);

                            break;
                        case 2:
                            sizePositionArray.set(childPosition,true);
                            dtInterface.setBoolean(groupPosition, sizePositionArray);
                            selectedItemsSize.add(childText);
                            dtInterface.setValues(groupPosition, selectedItemsSize);

                            break;
                        case 3:
                            colorPositionArray.set(childPosition,true);
                            dtInterface.setBoolean(groupPosition, colorPositionArray);
                            selectedItemsColor.add(childText);
                            dtInterface.setValues(groupPosition, selectedItemsColor);
                            break;

                    }
//                    selecteditems.add(childText);
//                    selectedItemsMap.put((String) getGroup(groupPosition), selecteditems);
//                    selectedItemsMap.remove(getGroup(groupPosition));

                } else {
                    finalViewHolder.checkListChild.setChecked(false);
                    Log.d("isNotCheck", "" + finalViewHolder.checkListChild.isChecked());

//                    dtInterface.removeValues(groupPosition,childText);
//
                    switch (groupPosition) {
                        case 0:
                            brandPositionArray.set(childPosition,false);
                            dtInterface.setBoolean(groupPosition, brandPositionArray);
                            selectedItemsBrand.remove(childText);
                            Log.d("Removed", "" + selectedItemsBrand.size());
                            dtInterface.setValues(groupPosition, selectedItemsBrand);
                            break;
                        case 1:
                            pricePositionArray.set(childPosition,false);
                            dtInterface.setBoolean(groupPosition, pricePositionArray);
                            selectedItemsPrice.remove(childText);
                            dtInterface.setValues(groupPosition, selectedItemsPrice);

                            break;
                        case 2:
                            sizePositionArray.set(childPosition,false);
                            dtInterface.setBoolean(groupPosition, sizePositionArray);
                            selectedItemsSize.remove(childText);
                            dtInterface.setValues(groupPosition, selectedItemsSize);
                            break;
                        case 3:
                            colorPositionArray.set(childPosition,false);
                            dtInterface.setBoolean(groupPosition, colorPositionArray);
                            selectedItemsColor.remove(childText);
                            dtInterface.setValues(groupPosition, selectedItemsColor);
                            break;
                    }


                }

            }
        });



        viewHolder.txtListChild.setText(childText);

        return convertView;

    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


}
