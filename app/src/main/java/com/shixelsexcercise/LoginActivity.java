package com.shixelsexcercise;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import util.SharedPreferencesUtil;

public class LoginActivity extends AppCompatActivity {
    private EditText usernameEditText, passwordEditText;
    private CheckBox rememberMeBox;
    private Button loginButton;
    private LoginButton facebookLoginButton;

    private CallbackManager callbackManager;
    private boolean status = false;
    private String username, password;
    private static final String TAG = LoginActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = (EditText) findViewById(R.id.username_edittext);
        passwordEditText = (EditText) findViewById(R.id.password_edittext);
        rememberMeBox = (CheckBox) findViewById(R.id.rememberCheckBox);
        facebookLoginButton = (LoginButton) findViewById(R.id.login_button);

        loginButton = (Button) findViewById(R.id.sign_in_button);

        //FacebookSdk.sdkInitialize(getApplicationContext());

        checkDetails();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        callbackManager = CallbackManager.Factory.create();

        //facebookLoginButton.setReadPermissions("email");

        facebookLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                username = loginResult.getAccessToken().getUserId();

                goToActivity();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    private void goToActivity() {
        JSONObject object = new JSONObject();
        try {
            object.put("username", username);
            object.put("password" , "password");

            //object.put("status", true);
            if (!status) {
                object.put("status", rememberMeBox.isEnabled());
            }
        }
        catch (JSONException ex) {
            Log.d(TAG, "Login JSON Exception");
            ex.printStackTrace();
        }

        // login to the main activity
        Intent intent = new Intent("com.shixelexcercise.MainActivity");
        intent.putExtra("login_details", object.toString());
        startActivity(intent);
    }
    //private void facebookLogin()

    private void checkDetails() {
        String result = SharedPreferencesUtil.readSharedLoginPrefs(this);
        if (result != null) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                username = jsonObject.getString("username");
                password = jsonObject.getString("password");
                status = jsonObject.getBoolean("status");
            }
            catch (JSONException ex) {
                Log.d(TAG, "Check Details JSON Exception");
                ex.printStackTrace();
            }

            if (status) {
                usernameEditText.setText(username);
                passwordEditText.setText(password);
            }
        }
    }

    private void login() {
        if (inputIsValid()) {
            username = usernameEditText.getText().toString();
            password = passwordEditText.getText().toString();

            JSONObject object = new JSONObject();
            try {
                object.put("username", username);
                object.put("password" , password);

                if (!status) {
                    object.put("status", rememberMeBox.isEnabled());
                }
            }
            catch (JSONException ex) {
                Log.d(TAG, "Login JSON Exception");
                ex.printStackTrace();
            }

            SharedPreferencesUtil.storeSharedLoginPrefs(this, object.toString());

            // login to the main activity
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            //intent.putExtra("login_details", object.toString());
            startActivity(intent);
        }
    }

    private boolean inputIsValid() {
        String errorString = "";

        if (usernameEditText.getText().toString().equals("")) {
            errorString += "\nPlease enter the username";
        }
        if (passwordEditText.getText().toString().equals("")) {
            errorString += "\nPlease enter the password";
        }

        if (errorString.equals("")) {
            return true;
        }
        else {
            setError("Invalid Fields", errorString);
            return false;
        }
    }

    private void setError(String title, String error) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(error)
                .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //finish();
                        dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }


}
