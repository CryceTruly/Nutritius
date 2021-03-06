package com.happy.nutritius.activities;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.happy.nutritius.R;
import com.happy.nutritius.api.APIService;
import com.happy.nutritius.api.Api;
import com.happy.nutritius.model.Result;
import com.happy.nutritius.model.User;
import com.happy.nutritius.utils.Helper;

import java.io.IOException;


public class ActivitySignUp extends AppCompatActivity implements View.OnClickListener {

    private Button buttonSignUp;
    private EditText editTextName, editTextEmail, editTextPassword,editTextPassword2;
    private RadioGroup radioGender;
    private static final String TAG = "ActivitySignUp";
    RelativeLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        buttonSignUp = findViewById(R.id.signup);
        layout=findViewById(R.id.lay);
        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextPassword2 = findViewById(R.id.editTextPassword2);
        Helper.setSystemBarColor(this,R.color.grey_5);
        buttonSignUp.setOnClickListener(this);
    }

  

    @Override
    public void onClick(View view) {
        if (view == buttonSignUp) {
            userSignUp();
        }
    }

    private void userSignUp() {



        String name = editTextName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String password2=editTextPassword2.getText().toString().trim();

        if(TextUtils.isEmpty(name)||TextUtils.isEmpty(email)||TextUtils.isEmpty(password)){
            Snackbar.make(layout,"All fields are required",Snackbar.LENGTH_LONG).show();
            return;

        }
        if(TextUtils.isEmpty(name)){
            Snackbar.make(layout,"Username required",Snackbar.LENGTH_LONG).show();
            return;

        }
        if(TextUtils.isEmpty(email)){

            Snackbar.make(layout,"Email is required",Snackbar.LENGTH_LONG).show();
            return;

        }
if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
    Snackbar.make(layout,"Email is invalid",Snackbar.LENGTH_LONG).show();
    return;
}


        if(!password.equals(password2)){

            Snackbar.make(layout,"Passwords donot match",Snackbar.LENGTH_SHORT).show();
            return;
        }else if(password.length()<6 || password.length()>16){
            Snackbar.make(layout,"Passwords should be 6 to 16 characters long",Snackbar.LENGTH_SHORT).show();
            return;
        }

        //defining a progress dialog to show while signing up
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Signing Up...");
        progressDialog.show();


        //building retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL+"auth/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Defining retrofit api service
        APIService service = retrofit.create(APIService.class);

        //Defining the user object as we need to pass it with the call
        User user = new User(name, email, password);

        //defining the call
        Call<Result> call = service.createUser(
                user.getUser_name(),
                user.getEmail(),
                user.getPassword(),
               "Johua",
                "Fluke"
        );

        //calling the api
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                //hiding progress dialog
                progressDialog.dismiss();
                Log.d(TAG, "onResponse: "+response.message());
                if (response.code() == 400) {
                    try {
                        String err = response.errorBody().string();
                        Snackbar.make(layout, err.split(":")[1].replaceAll("\"","").replace("}",""), Snackbar.LENGTH_LONG).show();
                        return;

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else if(response.code()==200){
                    Toast.makeText(ActivitySignUp.this, "Account created,you can now login", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getBaseContext(), LoginActivity.class));
                    finish();
                }

            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
                progressDialog.dismiss();
                Snackbar.make(layout,t.getMessage(),Snackbar.LENGTH_LONG).show();

            }
        });
    }
    
    
    
}