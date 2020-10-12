package com.example.flashlight;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class FlashLight extends AppCompatActivity {

    private CameraManager cameraManager;
    private String cameraId;
    ImageView onFlashLight, offFlashLight;
    boolean OnOrOff;
    MediaPlayer mediaPlayerOnline;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashlight);
        onFlashLight = findViewById(R.id.on_flashlight);
        offFlashLight = findViewById(R.id.off_flashLight);
        OnOrOff = false;
        onFlashLight.setVisibility(View.VISIBLE);
        offFlashLight.setVisibility(View.INVISIBLE);
        mediaPlayerOnline = MediaPlayer.create(this, R.raw.online);


        cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        try {
            cameraId = cameraManager.getCameraIdList()[0];
        }catch (CameraAccessException e){

        }

        onFlashLight.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                onFlashLight.setVisibility(View.INVISIBLE);
                offFlashLight.setVisibility(View.VISIBLE);
                OnOrOff = true;
                mediaPlayerOnline.start();
                switchFlashLight(OnOrOff);
            }
        });

        offFlashLight.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                offFlashLight.setVisibility(View.INVISIBLE);
                onFlashLight.setVisibility(View.VISIBLE);
                OnOrOff = false;
                switchFlashLight(OnOrOff);
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void switchFlashLight(boolean status) {
        try {
            cameraManager.setTorchMode(cameraId, status);
        } catch (CameraAccessException e) {

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayerOnline.release();
    }
}