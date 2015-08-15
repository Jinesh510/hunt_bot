package jinesh.urbanhunt_test;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Jinesh on 14/08/15.
 */
public class ProductDetail extends Fragment {

    RelativeLayout productDetailLayout;
    ImageView productImage;
    Button btnAddWish;
    Button btnShareImage;
    Button btnReserve;
    String productId;
    String productName;
    ArrayList<String> wishList;
    Product_1 p;

    public static ProductDetail newInstance( String id) {
        ProductDetail fragment = new ProductDetail();
        Bundle bundle = new Bundle();
        bundle.putString("productId", id);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        productDetailLayout = (RelativeLayout)inflater.inflate(R.layout.product_detail,container,false);
        productId = getArguments().getString("productId");

        btnAddWish = (Button)productDetailLayout.findViewById(R.id.btnAddWish);
        btnShareImage = (Button)productDetailLayout.findViewById(R.id.btnShareImage);
        btnReserve = (Button)productDetailLayout.findViewById(R.id.btnReserve);

        ParseQuery<Product_1> parseQuery = ParseQuery.getQuery(Product_1.class);
        parseQuery.getInBackground(productId, new GetCallback<Product_1>() {
            @Override
            public void done(Product_1 product_1, ParseException e) {

                productImage = (ImageView)productDetailLayout.findViewById(R.id.productImage);
                p = product_1;
                Picasso.with(getActivity()).load(product_1.getProductImage().getUrl()).into(productImage);
                productName = product_1.getProductName();

            }
        });

        btnAddWish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToWishList(productId);
//                addToWishList(p);
            }
        });

        btnShareImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareProductImage(view);
            }
        });


        return productDetailLayout;

    }
//
    private void addToWishList(String productId) {

        ParseUser.getCurrentUser().add("wishList",productId);
        ParseUser.getCurrentUser().saveEventually();

    }

//
//    private void addToWishList(Product_1 product) {
//
//        ParseUser.getCurrentUser().add("wishList",product);
//        ParseUser.getCurrentUser().saveEventually();
//
//    }

    private void shareProductImage(View view){

        Uri productUri = getLocalBitmapUri(productImage);
        if(productUri != null){

            Intent shareProductIntent = new Intent();
            shareProductIntent.setAction(Intent.ACTION_SEND);
            shareProductIntent.putExtra(Intent.EXTRA_STREAM, productUri);
            shareProductIntent.setType("*/*");
            shareProductIntent.putExtra(Intent.EXTRA_TEXT, productName);


            view.getContext().startActivity(Intent.createChooser(shareProductIntent,"Share Product"));
        }

    }

    public Uri getLocalBitmapUri(ImageView imageView){

        Drawable drawable = imageView.getDrawable();
        Bitmap bmp = null;
        if(drawable instanceof BitmapDrawable){
            bmp = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        }
        else {
            return null;
        }

        Uri bmpUri = null;

        try{
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "share_image_" + System.currentTimeMillis() + ".png");
            file.getParentFile().mkdirs();
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();
            bmpUri = Uri.fromFile(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bmpUri;

    }
}
