package com.dev.assafbz.betty;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;

/**
 * Created by assaf_000 on 20/06/2015.
 */
public class LoginManager {


    private static final String LOGIN_PREFERENCES_NAME = "LoginPreferences";
    private static final String IS_USER_LOGGED_IN_PREF_NAME = "isUserLoggedIn";

    public static void SaveLogin(Activity activity)
    {
        SharedPreferences settings = activity.getSharedPreferences(LOGIN_PREFERENCES_NAME, 0);
        SharedPreferences.Editor prefsEditor = settings.edit();
        prefsEditor.putBoolean(IS_USER_LOGGED_IN_PREF_NAME, true);
        prefsEditor.commit();
    }

    public static void CheckLogin(Activity activity)
    {
        SharedPreferences settings = activity.getSharedPreferences(LOGIN_PREFERENCES_NAME, 0);
        boolean isUserLoggedIn = settings.getBoolean(IS_USER_LOGGED_IN_PREF_NAME, false);
        if(!isUserLoggedIn){

            Intent i = new Intent(activity ,LoginActivity.class);
            activity.startActivity(i);
        }
    }
}
