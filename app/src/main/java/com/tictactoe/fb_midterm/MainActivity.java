package com.tictactoe.fb_midterm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //image
    ImageView FB_image;
    Button FB_takefotobutton;
    Bitmap FB_bitmapimage;

    //radio groups
    RadioButton FB_radiobutton;
    RadioGroup FB_radioGroup;

    //spinner
    private String[] FB_ilceler={"AKYURT","ALTINDAĞ","AYAŞ","BALA","BEYPAZARI","ÇAMLIDERE","ÇANKAYA","ÇUBUK","ELMADAĞ","ETİMESGUT","EVREN","GÖLBAŞI","GÜDÜL","HAYMANA","KALECİK","KAZAN","KEÇİÖREN","KIZILCAHAMAM","MAMAK","NALLIHAN","POLATLI","PURSAKLAR","SİNCAN","ŞEREFLİKOÇHİSAR","YENİMAHALLE"};
    private Spinner FB_ilcespinner;
    private ArrayAdapter<String> FB_dataAdapterForIlce;
    public String FB_ilce;

    //editText
    EditText FB_yemekismiedittext;

    //checkbox
    CheckBox FB_checkbox;

    private Uri FB_imageUri;

    private static  final int IMAGE_REQUEST = 2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FB_image=findViewById(R.id.imageView);
        FB_takefotobutton=findViewById(R.id.FB_foto);
        FB_ilcespinner=(Spinner) findViewById(R.id.FB_spinner);
        FB_yemekismiedittext=(EditText) findViewById(R.id.FB_yemekedit);
        FB_checkbox  = findViewById(R.id.FB_checkbox);
        FB_radioGroup= findViewById(R.id.radioGroup);





        // SET ADAPTER FOR Spinners
        FB_dataAdapterForIlce = new ArrayAdapter<String >(this, android.R.layout.simple_spinner_item,FB_ilceler);

        //determine the view option
        FB_dataAdapterForIlce.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Add adapters to spinner
        FB_ilcespinner.setAdapter(FB_dataAdapterForIlce);



        FB_ilcespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // select the item to add adapter
                FB_dataAdapterForIlce=new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item,FB_ilceler);

                FB_dataAdapterForIlce.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                Toast.makeText(getBaseContext(),""+FB_ilcespinner.getSelectedItem().toString(),Toast.LENGTH_LONG).show();

                FB_ilce=FB_ilcespinner.getSelectedItem().toString();



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        FB_takefotobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //askCameraPermission();
                //uploadImage();
                openImage();

            }


        });

    }

    private void openImage() {
        Intent intent = new Intent();
        intent.setType("image/");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,IMAGE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == IMAGE_REQUEST && resultCode == RESULT_OK){
            FB_imageUri = data.getData();

            uploadImage();
        }
    }

    private String getFileExtention(Uri uri){
        ContentResolver contentResolver = getContentResolver();

        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void uploadImage() {

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Uploading");
        pd.show();

        if(FB_imageUri != null){
            StorageReference fileRef= FirebaseStorage.getInstance().getReference().child("uploads").child(System.currentTimeMillis() + "." + getFileExtention(FB_imageUri));

            fileRef.putFile(FB_imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String url = uri.toString();

                            Log.d("Download url: ",url);
                            pd.dismiss();
                            Toast.makeText(MainActivity.this,"Image upload successfull",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }

    }

    private void askCameraPermission() {

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.CAMERA},101);
        }
        else{
            openCamera();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 101){
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                openCamera();
            }
            else{
                Toast.makeText(this,"Camera permission is Required to use camera.",Toast.LENGTH_LONG).show();
            }
        }
    }

    private void openCamera() {
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera,102);
    }



    public void goUrun(View view){

        // Take values
        String FB_yemekismiString = FB_yemekismiedittext.getText().toString();

        //create intent
        Intent intent = new Intent(this,FB_urun.class);

        // put text
        intent.putExtra("yemekIsmiString",FB_yemekismiString);

        //put image
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        FB_bitmapimage.compress(Bitmap.CompressFormat.JPEG,50,bs);
        intent.putExtra("imageExtra",bs.toByteArray());

        //put spinner data
        intent.putExtra("spinnerdata",FB_ilce);

        //put Radio data
        int selectedId = FB_radioGroup.getCheckedRadioButtonId();

        FB_radiobutton = findViewById(selectedId);
        String FB_radiostring= FB_radiobutton.getText().toString();
        intent.putExtra("radioString",FB_radiostring);


        //put checkbox data
        if(FB_checkbox.isChecked()){

            String FB_kapıyabırakString= "Kapıya Bırakınız!!";
            intent.putExtra("kapıyaAs",FB_kapıyabırakString);

        }






        startActivity(intent);

    }

    public void goListView(View view){
        Intent intent = new Intent(this,FB_listview.class);

        startActivity(intent);
    }

}