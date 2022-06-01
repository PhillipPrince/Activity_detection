package com.example.activitydetection;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;

import java.util.List;

public class ActivityRecognizedService extends IntentService {
    private static final String TAG="Activity";
    MainActivity mainActivity=new MainActivity();
    public ActivityRecognizedService(){
        super("ActivityRecognizedService");
    }
    public ActivityRecognizedService(String name){
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if(ActivityRecognitionResult.hasResult(intent)){
            ActivityRecognitionResult result=ActivityRecognitionResult.extractResult(intent);
            handleDetectedActivity(result.getProbableActivities());
        }

    }

    private void handleDetectedActivity(List<DetectedActivity> probableActivity) {
        for(DetectedActivity activity:probableActivity){
            switch(activity.getType()){
                case DetectedActivity.IN_VEHICLE:{
                    //Toast.makeText(getApplicationContext(), "in vehicle", Toast.LENGTH_SHORT).show();
                    Log.d(TAG,"InVehicle"+ activity.getConfidence());
                    break;
                }
                case DetectedActivity.ON_BICYCLE:{
                  Log.d(TAG, "On Bicycle"+ activity.getConfidence());
                  break;
                }
                case DetectedActivity.ON_FOOT:{
                    Log.d(TAG, "On Foot"+ activity.getConfidence());
                  break;
                }
                case DetectedActivity.RUNNING:{
                    Log.d(TAG,"Running"+activity.getConfidence());
                    //mainActivity.running();
                    break;
                }
                case DetectedActivity.STILL:{
                    Log.d(TAG,"Still"+activity.getConfidence());
                    //mainActivity.still();
                    break;
                }
                case DetectedActivity.WALKING:{
                    Log.d(TAG,"Walking"+activity.getConfidence());
                   // mainActivity.walking();
                    break;
                }
                case DetectedActivity.TILTING:{
                    Log.d(TAG,"Tilting"+activity.getConfidence());
                    break;
                }
                case DetectedActivity.UNKNOWN:{
                    Log.d(TAG,"Unknown"+activity.getConfidence());
                    break;
                }

            }
        }
    }
}
