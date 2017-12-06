package com.example.user.camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity {
    private static final int CAMERA_REQUEST = 1888;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        Button button = findViewById(R.id.camera);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                String pictureName = getPictureName();

                File path = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath() + "/PrescripXion");
                if(!path.exists()){
                    path.mkdirs();
                }
                File image = new File(path, pictureName);
                Uri pictureUri = Uri.fromFile(image);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, pictureUri);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });
    }



    private String getPictureName(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timeStamp = sdf.format(new Date());
        return "PresImg"+timeStamp+".jpg";
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RESULT_OK){
            if(requestCode==CAMERA_REQUEST){
                Bitmap cameraImage = (Bitmap)data.getExtras().get("data");
                imageView.setImageBitmap(cameraImage);
            }
        }
    }
}
