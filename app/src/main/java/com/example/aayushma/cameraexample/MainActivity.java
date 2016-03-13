package com.example.aayushma.cameraexample;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {
    ImageView img;
    ImageView image;
    public static int RESULT_LOAD_IMG=1;
    public static int RESULT_CAPTURE_IMG=2;
    String imgDecodableString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (v.getId() == R.id.buttonChooseImage) {
                    DialogFragment fragment = new ChooseFragment();
                    fragment.show(getSupportFragmentManager(), "updateDelete");

                }
            }
        });
    }



    public void initView(){
        img=(ImageView) findViewById(R.id.buttonChooseImage);
        image=(ImageView) findViewById(R.id.image);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try{

            Log.v("req","dd"+requestCode  +"::"+resultCode  +(data ==null ?true:false));
            //when the image is picked
            if(requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK & null != data)
            {
                //GET the image from the data
                Uri selectedImage = data.getData();
                image.setImageURI(selectedImage);
               /* Log.v("uri path",selectedImage.toString());


                String[] filePathColumn = { MediaStore.Images.Media.DATA,MediaStore.Images.Thumbnails.DATA };

                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imgDecodableString = cursor.getString(columnIndex);
                cursor.close();
                Log.v("decodedstring", imgDecodableString);

                image.setImageURI(selectedImage);

                //convert to base64

                final BitmapFactory.Options bitmapOptions=new BitmapFactory.Options();
                bitmapOptions.inDensity=4;
                bitmapOptions.inSampleSize=calculateInSampleSize(bitmapOptions, 200, 200);
                bitmapOptions.inTargetDensity=1;

                Bitmap bm = BitmapFactory.decodeFile(imgDecodableString,bitmapOptions);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.JPEG, 70, baos); //bm is the bitmap object
                byte[] b = baos.toByteArray();
                String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
                Log.v("encodedImage", encodedImage);
                image.setImageBitmap(bm);*/
            }
            else if(requestCode == RESULT_CAPTURE_IMG && resultCode == RESULT_OK & null != data){
                Bundle extras=data.getExtras();
                Bitmap photo=(Bitmap) extras.get("data");
                image.setImageBitmap(photo);
            }
            else {
                Toast.makeText(this, "You haven't picked Image", Toast.LENGTH_LONG).show();
            }
        }
        catch (Exception ex){
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
        }
    }




    //Given the bitmap size and View size calculate a subsampling size (powers of 2)
    static int calculateInSampleSize( BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int inSampleSize = 1;	//Default subsampling size
        // See if image raw height and width is bigger than that of required view
        if (options.outHeight > reqHeight || options.outWidth > reqWidth) {
            //bigger
            final int halfHeight = options.outHeight / 2;
            final int halfWidth = options.outWidth / 2;
            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

}
