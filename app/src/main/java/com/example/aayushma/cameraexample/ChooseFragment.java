package com.example.aayushma.cameraexample;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;


public class ChooseFragment extends  DialogFragment{

    public ChooseFragment() {
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_choose_fragment, container,
                false);
        getDialog().setTitle("DialogFragment Tutorial");

        LinearLayout click=(LinearLayout) rootView.findViewById(R.id.click);
        LinearLayout select=(LinearLayout) rootView.findViewById(R.id.select);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                captureImage();
                getDialog().dismiss();
            }
        });
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
                getDialog().dismiss();
            }
        });
        return rootView;
    }
    public void captureImage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
       getActivity().startActivityForResult(intent, MainActivity.RESULT_CAPTURE_IMG);
    }

    public void uploadImage(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        getActivity().startActivityForResult(intent, MainActivity.RESULT_LOAD_IMG);

    }

}

