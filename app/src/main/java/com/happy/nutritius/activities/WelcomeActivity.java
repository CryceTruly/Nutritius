package com.happy.nutritius.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.happy.nutritius.R;
import com.happy.nutritius.activities.ActivitySignUp;
import com.happy.nutritius.model.Nutrient;

public class WelcomeActivity extends AppCompatActivity {
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


    }


}
