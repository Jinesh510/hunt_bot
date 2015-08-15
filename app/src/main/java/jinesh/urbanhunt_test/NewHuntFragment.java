package jinesh.urbanhunt_test;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.parse.ParseFile;
import com.parse.ParseUser;
import com.wefika.flowlayout.FlowLayout;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Jinesh on 24/07/15.
 */
public class NewHuntFragment extends Fragment {


    private Button btnUp;
    private ImageView iv;
    // private File ImagePath;
    private Button btnSave;
//    private EditText category;
//    private EditText budget;
//    private EditText preferences;
    private String category;
    private byte[] imgByte;

    ArrayList<String> selectedTags = new ArrayList<>();
    boolean [] tagState;
    FlowLayout tagsLayout;

    private RelativeLayout newHuntLayout;

    public static NewHuntFragment newInstance( String category) {
        NewHuntFragment fragment = new NewHuntFragment();
        Bundle bundle = new Bundle();
        bundle.putString("category", category);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        category = getArguments().getString("category");
        newHuntLayout = (RelativeLayout)inflater.inflate(R.layout.activity_new_hunt,container,false);
        btnUp = (Button)newHuntLayout.findViewById(R.id.btnUpload);
        iv = (ImageView)newHuntLayout.findViewById(R.id.imgview);
        btnSave = (Button)newHuntLayout.findViewById(R.id.btnSave);

        tagsLayout = (FlowLayout)newHuntLayout.findViewById(R.id.huntTags);

        FlowLayout.LayoutParams params = new FlowLayout.LayoutParams(FlowLayout.LayoutParams.WRAP_CONTENT, FlowLayout.LayoutParams.WRAP_CONTENT);

        params.setMargins(10,10,10,10);

        ArrayList<String> tags = new ArrayList();

        switch (category){
            case "bags":
                tags.add("Ethnic");
                tags.add("ClubWear");
                tags.add("PartyWear");
                break;
            case "apparels":
                tags.add("Casual");
                tags.add("OfficeWear");
                tags.add("Club");
                tags.add("Party");

        }

        tagState = new boolean[tags.size()];
        Arrays.fill(tagState, false);

        for (String tag : tags){
            tagsLayout.addView(createTextView(tag, params));
        }

        int totalTags = tagsLayout.getChildCount();
        for(int i =0 ; i< totalTags; i++) {
            TextView t = (TextView) tagsLayout.getChildAt(i);
            addOnClickListener(t, i);
        }

        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectImage();

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                postRequest();

            }
        });

        return newHuntLayout;
    }

    private void addOnClickListener(final TextView t, final int i){

        t.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {

                if(!tagState[i]){

                    t.setBackground(getActivity().getResources().getDrawable(R.drawable.tv_s_background,null));
                    t.setTextColor(Color.WHITE);
                    addToSelected(t);
                    tagState[i] = true;
                    Log.d("addtag", "" + i + tagState[i]);
                }
                else{
                    removeFromSelected(t);
                    tagState[i] = false;
                    Log.d("retag", "" + i + tagState[i]);

                }

            }
        });

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private TextView createTextView(String tag, FlowLayout.LayoutParams params){

        TextView t = new TextView(getActivity());
        t.setLayoutParams(params);
        t.setPadding(10,10,10,10);
        t.setText(tag);
        t.setTextColor(Color.RED);
//        Drawable d = Bitm
        t.setBackground(getActivity().getResources().getDrawable(R.drawable.tv_background,null));
//        addClickListener(t);
        return t;
    }

    private void addToSelected(TextView t){
        selectedTags.add(t.getText().toString());
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void removeFromSelected(TextView t){

        selectedTags.remove(t.getText().toString());
        t.setBackground(getActivity().getResources().getDrawable(R.drawable.tv_background, null));
        t.setTextColor(Color.RED);
        Log.d("removed", "" + selectedTags.size());

    }



    private void postRequest() {
//
//        String pCategory;
//        String pBudget;
//        String pPreferences;
////
//        category = (EditText)newHuntLayout.findViewById(R.id.category);
//        budget = (EditText)newHuntLayout.findViewById(R.id.budget);
//        preferences = (EditText)newHuntLayout.findViewById(R.id.preferences);

//        pCategory = category.getText().toString().trim();
//        pBudget = budget.getText().toString().trim();
//        pPreferences = preferences.getText().toString().trim();

        // byte[] imageByte = "1435949872662.jpg".getBytes();

        ParseFile imageFile = new ParseFile("image1.jpg",imgByte);

        Hunt hunt = new Hunt();

        hunt.setAuthor(ParseUser.getCurrentUser());

        hunt.setTitle("First");

        hunt.setCategory(category);

//        hunt.setBudget(pBudget);
//
//        hunt.setPreferencecs(pPreferences);

        hunt.setTags(selectedTags);

        hunt.setPhotoFile(imageFile);

        hunt.saveInBackground();

    }


    public void selectImage(){

        final CharSequence[] items = {"Take Photo", "Choose from Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(items[i].equals("Take Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 1);
                }
                else if(items[i].equals("Choose from Gallery")){
//                    Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");
                    startActivityForResult(Intent.createChooser(intent,"Select File"),2);

                } else if (items[i].equals("Cancel")){
                    dialogInterface.dismiss();
                }


            }
        });

        builder.show();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(resultCode == getActivity().RESULT_OK) {
            if(requestCode == 1){
                Bitmap bp = (Bitmap) data.getExtras().get("data");

                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bp.compress(Bitmap.CompressFormat.JPEG,40,bytes);

                File destination = new File(Environment.getExternalStorageDirectory(),System.currentTimeMillis() + ".jpg");


                FileOutputStream fo;
                try {
                    destination.createNewFile();
                    fo = new FileOutputStream(destination);
                    fo.write(bytes.toByteArray());
                    fo.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e){
                    e.printStackTrace();
                }

                byte[] imgByteArray = bytes.toByteArray();

                imgByte = imgByteArray;



                //  ImagePath = destination;

                iv.setImageBitmap(bp);

            }
            else if (requestCode == 2){
                Uri selectedImageUri = data.getData();
//                String[] projection = {MediaStore.MediaColumns.DATA};
//                Cursor cursor = getContentResolver().query(selectedImageUri, projection, null, null, null);
//                int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
//                cursor.moveToFirst();
//
//
//                String selectedImagePath = cursor.getString(column_index);
//
//                cursor.close();

                //trial
                try {
                    Bitmap bp1= MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImageUri);

                    ByteArrayOutputStream bytes1 = new ByteArrayOutputStream();
                    bp1.compress(Bitmap.CompressFormat.JPEG,40,bytes1);
                    imgByte = bytes1.toByteArray();

                    iv.setImageBitmap(bp1);

                } catch (IOException e) {
                    e.printStackTrace();
                }



                //ends
//                Bitmap bp;
//                BitmapFactory.Options options = new BitmapFactory.Options();
//                bp = BitmapFactory.decodeFile(selectedImagePath, options);
//
//                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//                bp.compress(Bitmap.CompressFormat.JPEG,90,bytes);
//                imgByte = bytes.toByteArray();
//
//
//
////                File destination = new File(selectedImagePath);
////
////                ImagePath = destination;
//
//                iv.setImageBitmap(bp);



            }
        }



    }

}
