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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.happy.nutritius.MainActivity;
import com.happy.nutritius.MapsActivity;
import com.happy.nutritius.R;
import com.happy.nutritius.api.APIService;
import com.happy.nutritius.api.Api;
import com.happy.nutritius.model.User;

import javax.xml.transform.Result;


public class ActivitySignUp extends AppCompatActivity implements View.OnClickListener {

    private Button buttonSignUp;
    private EditText editTextName, editTextEmail, editTextPassword;
    private RadioGroup radioGender;
    private static final String TAG = "ActivitySignUp";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        buttonSignUp = (Button) findViewById(R.id.signup);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        radioGender = (RadioGroup) findViewById(R.id.radioGender);

        buttonSignUp.setOnClickListener(this);
    }

  

    @Override
    public void onClick(View view) {
        if (view == buttonSignUp) {
            userSignUp();
        }
    }

    private void userSignUp() {

        //defining a progress dialog to show while signing up
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Signing Up...");
        progressDialog.show();


        String name = editTextName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();


        //building retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL+"register/")
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
                user.getPassword()
        );

        //calling the api
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                //hiding progress dialog
                progressDialog.dismiss();
                Log.d(TAG, "onResponse: Registered");
startActivity(new Intent(getBaseContext(), MapsActivity.class));
                //displaying the message from the response as toast
                Toast.makeText(getApplicationContext(), response.body().toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    
    
    
}