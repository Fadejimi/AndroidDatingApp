package util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by Fadejimi on 1/5/17.
 */

public class SharedPreferencesUtil {
    public static final String LOGIN_PREF = "login";
    private static final String TAG = SharedPreferencesUtil.class.getSimpleName();

    public static void storeSharedLoginPrefs(Context context, String loginString) {
        SharedPreferences preferences = context.getSharedPreferences(LOGIN_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("login", loginString);
        Log.d(TAG, "storeSharedLoginPrefs: " + loginString);
        editor.apply();
    }

    public static String readSharedLoginPrefs(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(LOGIN_PREF, Context.MODE_PRIVATE);
        Log.d(TAG, "readSharedLoginPrefs: " + preferences.getString("login", null));
        return preferences.getString("login", null);
    }

    public static void clearSharedLoginPres(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(LOGIN_PREF, Context.MODE_PRIVATE);
        preferences.edit().clear().apply();
    }
}
