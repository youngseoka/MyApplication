package com.example.youngseok.myapplication.GroupContent.Shot;



import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.media.ExifInterface;
import android.media.Image;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;

import com.example.youngseok.myapplication.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.SecureRandom;

public class ShotActivity extends AppCompatActivity implements View.OnClickListener{

    Camera mCamera;
    HorizontalScrollView horizontalScrollView;
    private final int PERMISSION_CONSTANT = 1000;
    public static String img_add="";
    public static final int SELECT_GALLERY_IMAGE = 101;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shot);

        ImageView ivCapture = findViewById(R.id.ivCapture);
        ImageView ivFilter = findViewById(R.id.ivFilter);
        horizontalScrollView = findViewById(R.id.filterLayout);

        checkPermissionAndGive();
        ivCapture.setOnClickListener(this);
        ivFilter.setOnClickListener(this);
    }

    private void checkPermissionAndGive(){
        initialize();
    }
    private void initialize(){
        mCamera=getCameraInstance();
        CameraPreview mPreview = new CameraPreview(this,mCamera);

        FrameLayout rlCamPreviewFrame = findViewById(R.id.rlCameraPreview);
        if(rlCamPreviewFrame != null){
            rlCamPreviewFrame.addView(mPreview);
        }
    }
    private Camera getCameraInstance(){

        Camera c =null;

        try {
            c = Camera.open();
        }catch (Exception e){
            e.printStackTrace();
        }
        return c;
    }

    private Camera.PictureCallback mPicture = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
        //    camera.setDisplayOrientation(90);




            File pictureFile = getOutputMediaFile();

            if(pictureFile==null){
                return;
            }
            MediaScannerConnection.scanFile(ShotActivity.this,
                    new String[]{pictureFile.toString()},null,
                    new MediaScannerConnection.OnScanCompletedListener() {
                        @Override

                        public void onScanCompleted(String path, Uri uri){


                            mCamera.startPreview();
                            Intent intent = new Intent(ShotActivity.this,Camera_Edit_Activity.class);
                            intent.putExtra("file_path",path);
                            img_add=path;
                            startActivityForResult(intent,SELECT_GALLERY_IMAGE);


                        }
                    });

            try {
                FileOutputStream fos = new FileOutputStream(pictureFile);
                fos.write(data);
                fos.close();
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }




        }
    };
    private File getOutputMediaFile(){
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"My images");

        if(!mediaStorageDir.exists()){
            if(!mediaStorageDir.mkdirs()){
                return null;
            }
        }
        SecureRandom random = new SecureRandom();
        int num = random.nextInt(100000);
        File save = new File(mediaStorageDir.getAbsolutePath()+File.separator+"IMG"+num+".jpg");

        return save;
    }

    @Override
    public void onClick(View v){

        switch (v.getId()){
            case R.id.ivFilter:
                if(horizontalScrollView.getVisibility()==View.VISIBLE){
                    horizontalScrollView.setVisibility(View.GONE);
                }
                else{
                    horizontalScrollView.setVisibility(View.VISIBLE);
                }
                break;

            case R.id.ivCapture:
                mCamera.takePicture(null,null,mPicture);
        }
    }


    public void colorEffectFilter(View v){
        try {

            Camera.Parameters parameters = mCamera.getParameters();
            switch (v.getId()){
                case R.id.rlNone:
                    parameters.setColorEffect(Camera.Parameters.EFFECT_NONE);
                    mCamera.setParameters(parameters);
                    break;

                case R.id.rlAua:
                    parameters.setColorEffect(Camera.Parameters.EFFECT_AQUA);
                    mCamera.setParameters(parameters);
                    break;
                case R.id.rlBlackboard:
                    parameters.setColorEffect(Camera.Parameters.EFFECT_BLACKBOARD);
                    mCamera.setParameters(parameters);
                    break;
            }


        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private int exifOrientationToDegrees(int exifOrientation) {
        if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) {

            return 180;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180) {

            return 270;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) {

            return 0;
        }

        return 90;
    }

    private Bitmap rotate(Bitmap bitmap, float degree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }




}




























