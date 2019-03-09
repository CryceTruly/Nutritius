package com.happy.nutritius.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.happy.nutritius.R;
import com.happy.nutritius.SharedPrefManager;
import com.happy.nutritius.api.APIService;
import com.happy.nutritius.api.Api;
import com.happy.nutritius.model.Result;
import com.happy.nutritius.model.User;
import com.happy.nutritius.utils.Helper;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "LoginActivity";
    private EditText editTextEmail, editTextPassword;
    private Button buttonSignIn;
    TextView create,forgot;
    LinearLayout view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        view=findViewById(R.id.sign_up_for_account);
        buttonSignIn = (Button) findViewById(R.id.buttonSignIn);
create=findViewById(R.id.create);
forgot=findViewById(R.id.forgot);
Helper.setSystemBarColor(this,R.color.grey_5);




create.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(getBaseContext(),ActivitySignUp.class));
    }
});

forgot.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(getBaseContext(),ForgotPasswordActivity.class));
    }
});
        buttonSignIn.setOnClickListener(this);
    }

    private void userSignIn() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(email.trim().length()<1){
            Snackbar.make(view,"Username is required",Snackbar.LENGTH_LONG).show();
            return ;
        }
        if(password.trim().length()<0){
            Snackbar.make(view,"password is required",Snackbar.LENGTH_LONG).show();
            return ;
        }
        if(password.trim().length()<5){
            Snackbar.make(view,"password should be atleast 6 characters long",Snackbar.LENGTH_LONG).show();
            return ;
        }
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Signing you in...");
        progressDialog.show();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL+"auth/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);


        Call<Result> call = service.userLogin(email, password);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDialog.dismiss();

                if (response.isSuccessful()) {
                    User user=response.body().getUser();
                    SharedPrefManager.getInstance(getApplicationContext()).userLogin(new User
                            (user.getUser_name(),user.getEmail(),user.getPassword()));


                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view == buttonSignIn) {
            userSignIn();
        }
    }
    public void goToSignUp(View view){
        startActivity(new Intent(getBaseContext(),ActivitySignUp.class));
    }
}