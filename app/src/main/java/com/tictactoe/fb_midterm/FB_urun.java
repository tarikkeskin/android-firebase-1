package com.tictactoe.fb_midterm;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class FB_urun extends AppCompatActivity {


    TextView FB_yemekismiTxt;
    ImageView FB_yemekImage;
    TextView FB_spinnerTxt;
    TextView FB_radioTxt;
    TextView FB_kapıAsTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f_b_urun);

        FB_yemekismiTxt= (TextView) findViewById(R.id.FB_yemekismitextview);
        FB_yemekImage = findViewById(R.id.FB_yemekresmi);
        FB_spinnerTxt = findViewById(R.id.spinner);
        FB_radioTxt= findViewById(R.id.isAcılı);
        FB_kapıAsTxt=findViewById(R.id.kapıAs);

        Bundle bundle = getIntent().getExtras();

        String FB_yemekIsimString = bundle.getString("yemekIsmiString");

        String FB_spinnerString = bundle.getString("spinnerdata");

        String FB_radioString = bundle.getString("radioString");

        String FB_kapıyabırakString = bundle.getString("kapıyaAs");


        FB_radioTxt.setText(FB_radioString);
        FB_spinnerTxt.setText(FB_spinnerString);
        FB_yemekismiTxt.setText(FB_yemekIsimString);
        FB_kapıAsTxt.setText(FB_kapıyabırakString);



        if(getIntent().hasExtra("imageExtra")) {

            Bitmap bitmap = BitmapFactory.decodeByteArray(
                    getIntent().getByteArrayExtra("imageExtra"), 0, getIntent().getByteArrayExtra("imageExtra").length);
            if(bitmap!=null) {
                FB_yemekImage.setImageBitmap(bitmap);
            }
        }


    }


}