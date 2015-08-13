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
    List<String> selectedItemsBrand = new ArrayList<String>();
    List<String> selectedItemsPrice = new ArrayList<String>();
    List<String> selectedItemsSize = new ArrayList<String>();
    List<String> selectedItemsColor = new ArrayList<String>();
    DataTransferInterface dtInterface;


    int selected [];


    public FilterExpandableListAdapter(Context _context, List<String> listDataHeader, HashMap<String, List<String>> listDataChild, DataTransferInterface dtInterface) {
        this._context = _context;
        this.listDataHeader = listDataHeader;
        this.listDataChild = listDataChild;
        this.dtInterface = dtInterface;
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
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.filter_group, null);
        }

        TextView lblListHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
        public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.filter_group_item, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.lblListItem);
        final CheckBox checkListChild = (CheckBox)convertView.findViewById(R.id.filter_check);

        checkboxState = new boolean[getChildrenCount(groupPosition)];
        Arrays.fill(checkboxState, false);



        checkListChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((CheckBox) view).isChecked()) {
                    checkListChild.setChecked(true);
                    Log.d("isCheck", "" + checkListChild.isChecked());

//                    dtInterface.addValues((int)groupPosition,childText);

                    switch (groupPosition) {
                        case 0:
                            selectedItemsBrand.add(childText);
                            Log.d("Added", "" + selectedItemsBrand.size());
                            dtInterface.setValues(groupPosition, selectedItemsBrand);

                            break;
                        case 1:
                            selectedItemsPrice.add(childText);
                            break;
                        case 2:
                            selectedItemsSize.add(childText);
                            break;
                        case 3:
                            selectedItemsColor.add(childText);
                            break;

                    }
//                    selecteditems.add(childText);
//                    selectedItemsMap.put((String) getGroup(groupPosition), selecteditems);
//                    selectedItemsMap.remove(getGroup(groupPosition));

                } else {
                    checkListChild.setChecked(false);
                    Log.d("isNotCheck", "" + checkListChild.isChecked());

//                    dtInterface.removeValues(groupPosition,childText);
//
                    switch (groupPosition) {
                        case 0:
                            selectedItemsBrand.remove(childText);
                            Log.d("Removed", "" + selectedItemsBrand.size());
                            break;
                        case 1:
                            selectedItemsPrice.remove(childText);
                            break;
                        case 2:
                            selectedItemsSize.remove(childText);
                            break;
                        case 3:
                            selectedItemsColor.remove(childText);
                            break;
                    }



                }

            }
        });



        txtListChild.setText(childText);

        return convertView;

    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


}
