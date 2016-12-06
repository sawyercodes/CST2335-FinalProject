package com.example.victo.cst2335_finalproject;

import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.ToggleButton;

public class GarageFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_garage_fragment, container, false);

        toggleGarageDoor = (ToggleButton) view.findViewById(R.id.toggle_garage_door);
        toggleGarageLight = (ToggleButton) view.findViewById(R.id.toggle_garage_light);

        cam = Camera.open();

        final Toast toast = new Toast(view.getContext());
        toast.setDuration(Toast.LENGTH_LONG);

        toggleGarageDoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (toggleGarageDoor.isChecked()) {
                    toast.setText("Opening Garage");
                    toast.show();
                } else {
                    toast.setText("Closing Garage");
                    toast.show();
                }
                toggleGarageLight.toggle();
                toggleFlashOn();

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        toggleGarageLight.toggle();
                        toggleFlashOff();
                    }
                }, 5000);
            }
        });

        toggleGarageLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (toggleGarageLight.isChecked()) {
                    toggleFlashOn();
                } else {
                    toggleFlashOff();
                }
            }
        });

        return view;
    }

    private void toggleFlashOn() {
        try {
            if (getContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
                //cam = Camera.open();
                Camera.Parameters params = cam.getParameters();
                params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                cam.setParameters(params);
                cam.startPreview();
            }
        } catch(Exception e) {
            Toast toast = new Toast(view.getContext());
            toast.makeText(view.getContext(), "Light On", Toast.LENGTH_SHORT).show();
        }
    }

    private void toggleFlashOff() {
        try {
            if (getContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
                Camera.Parameters params = cam.getParameters();
                params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                cam.setParameters(params);
                cam.stopPreview();
            }
        } catch (Exception e) {
            Toast toast = new Toast(view.getContext());
            toast.makeText(view.getContext(), "Light Off", Toast.LENGTH_SHORT).show();
        }
    }

    private View view;
    private ToggleButton toggleGarageDoor;
    private ToggleButton toggleGarageLight;
    private Camera cam;

}
