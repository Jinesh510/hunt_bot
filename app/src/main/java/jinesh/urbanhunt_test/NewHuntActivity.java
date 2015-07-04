package jinesh.urbanhunt_test;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.parse.ParseFile;
import com.parse.ParseUser;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class NewHuntActivity extends ActionBarActivity {


    private Button btnUp;
    private ImageView iv;
    // private File ImagePath;
    private Button btnSave;
    private EditText category;
    private EditText budget;
    private EditText preferences;
    private byte[] imgByte;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_hunt);

        btnUp = (Button)findViewById(R.id.btnUpload);
        iv = (ImageView)findViewById(R.id.imgview);
        btnSave = (Button)findViewById(R.id.btnSave);

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


    }

    private void postRequest() {

        String pCategory;
        String pBudget;
        String pPreferences;

        category = (EditText)findViewById(R.id.category);
        budget = (EditText)findViewById(R.id.budget);
        preferences = (EditText)findViewById(R.id.preferences);

        pCategory = category.getText().toString().trim();
        pBudget = budget.getText().toString().trim();
        pPreferences = preferences.getText().toString().trim();

       // byte[] imageByte = "1435949872662.jpg".getBytes();

        ParseFile imageFile = new ParseFile("image1.jpg",imgByte);

        Hunt hunt = new Hunt();

        hunt.setAuthor(ParseUser.getCurrentUser());

        hunt.setTitle("First");

        hunt.setCategory(pCategory);

        hunt.setBudget(pBudget);

        hunt.setPreferencecs(pPreferences);

        hunt.setPhotoFile(imageFile);

        hunt.saveInBackground();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_hunt, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void selectImage(){

        final CharSequence[] items = {"Take Photo", "Choose from Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(NewHuntActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(items[i].equals("Take Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 1);
                }
                else if(items[i].equals("Choose from Gallery")){
                    Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent. setType("image/*");
                    startActivityForResult(Intent.createChooser(intent,"Select File"),2);

                } else if (items[i].equals("Cancel")){
                    dialogInterface.dismiss();
                }


            }
        });

        builder.show();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(resultCode == RESULT_OK) {
            if(requestCode == 1){
                Bitmap bp = (Bitmap) data.getExtras().get("data");

                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bp.compress(Bitmap.CompressFormat.JPEG,90,bytes);

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
                String[] projection = {MediaStore.MediaColumns.DATA};
                Cursor cursor = getContentResolver().query(selectedImageUri, projection, null, null, null);
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
                cursor.moveToFirst();


                String selectedImagePath = cursor.getString(column_index);

                cursor.close();

                Bitmap bp;
                BitmapFactory.Options options = new BitmapFactory.Options();
                bp = BitmapFactory.decodeFile(selectedImagePath, options);

                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bp.compress(Bitmap.CompressFormat.JPEG,90,bytes);
                imgByte = bytes.toByteArray();



//                File destination = new File(selectedImagePath);
//
//                ImagePath = destination;

                iv.setImageBitmap(bp);






            }
        }



    }



}
