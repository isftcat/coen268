package com.example.a.Listener;

import android.content.SharedPreferences;
import android.util.Log;

import com.example.a.api.ApiVolley;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by apple on 2018/3/10.
 */

public class MyInstanceIDListenerService extends FirebaseInstanceIdService {

    private static final String TAG = "MyInstanceIDLS";

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. This call is initiated by the
     * InstanceID provider.
     */
    // [START refresh_token]
    @Override
    public void onTokenRefresh() {
        // Fetch updated Instance ID token and notify our app's server of any changes (if applicable).
        //Intent intent = new Intent(this, RegistrationIntentService.class);
        //startService(intent);
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        SharedPreferences preferences=getSharedPreferences("UserObject",MODE_PRIVATE);

        Log.d("Token",refreshedToken);

        if(preferences.getInt("ownerId",0)!=0)
        {
            ApiVolley apiVolley=new ApiVolley(getApplicationContext());
            apiVolley.updateToken(getApplicationContext(),refreshedToken,preferences.getInt("ownerId",0));

            //apiVolley.updateToken();
        }
        preferences.edit().putString("deviceToken",refreshedToken).apply();


        Log.d(TAG, "Refreshed token: " + refreshedToken);
        // TODO: Implement this method to send any registration to your app's servers.
        //sendRegistrationToServer(refreshedToken);
    }
    // [END refresh_token]
}
