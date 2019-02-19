package com.happy.nutritius.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.happy.nutritius.R;
import com.happy.nutritius.model.Nutrient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FoodDetailActivity extends AppCompatActivity {
Nutrient nutrient;
private Toolbar toolbar;
private ListView nutrients;
private TextView name,description;
private List nutrientsList=new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        name=findViewById(R.id.name);
        description=findViewById(R.id.desc);
        toolbar=findViewById(R.id.toolbar);
        nutrients=findViewById(R.id.nutrrients);        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        if(getIntent().hasExtra("food")){
            nutrient=getIntent().getParcelableExtra("food");
            name.setText(nutrient.getName());
            description.setText(nutrient.getDescription());

            nutrients.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,getNutrients(nutrient.getNutrients())));




        }else{
            name.setText("Something went wrong");
        }
    }

    private List<String> getNutrients(String nutrients) {
        return Arrays.asList(nutrients.split(","));

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
