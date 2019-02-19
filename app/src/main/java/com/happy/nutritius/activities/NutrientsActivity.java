package com.happy.nutritius.activities;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.happy.nutritius.R;
import com.happy.nutritius.adapters.FoodHolder;
import com.happy.nutritius.api.APIService;
import com.happy.nutritius.api.Api;
import com.happy.nutritius.model.Nutrient;

import java.util.List;

public class NutrientsActivity extends AppCompatActivity implements FoodHolder.foodClick {
TextView textViewResult;
    private static final String TAG = "NutrientsActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrients);
        textViewResult=findViewById(R.id.result);

        //building retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Defining retrofit api service
        APIService service = retrofit.create(APIService.class);

        Call<List<Nutrient>> call = service.getNutrients();

        call.enqueue(new Callback<List<Nutrient>>() {
            @Override
            public void onResponse(Call<List<Nutrient>> call, Response<List<Nutrient>> response) {
                Log.d(TAG, "onResponse: "+response);


                if(!response.isSuccessful()){
                    textViewResult.setText(response.message());
                    return;
                }

                List<Nutrient> posts = response.body();

                for (Nutrient nutrient : posts) {
                    String content = "";
                    content += "name: " + nutrient.getName() + "\n";
                    content += "Desc: " + nutrient.getDescription() + "\n";
                    content += "Title: " + nutrient.getNutrients() + "\n";
                    content += "Text: " + String.valueOf(nutrient.getId())+ "\n\n";

                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Nutrient>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });


        }


    @Override
    public void onFoodClick(int position) {

    }
}
