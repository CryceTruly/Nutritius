package com.happy.nutritius.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.print.PrinterId;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.happy.nutritius.R;
import com.happy.nutritius.activities.FoodDetailActivity;
import com.happy.nutritius.adapters.FoodHolder;
import com.happy.nutritius.api.APIService;
import com.happy.nutritius.api.Api;
import com.happy.nutritius.model.Nutrient;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FoodsFragment extends Fragment implements FoodHolder.foodClick{
private RecyclerView results;
    private static final String TAG = "FoodsFragment";
private TextView textViewResult;
private ProgressBar progressBar;
   private List<Nutrient> foods=new ArrayList<>();
    LinearLayout linearLayout;
    public FoodsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view= inflater.inflate(R.layout.fragment_foods, container, false);

        textViewResult=view.findViewById(R.id.textViewResult);
        results=view.findViewById(R.id.result);
        results.setHasFixedSize(true);
        linearLayout=view.findViewById(R.id.lyt_no_connection);
        results.setLayoutManager(new LinearLayoutManager(getActivity()));
        progressBar=view.findViewById(R.id.progress_bar1);
final FoodHolder holder=new FoodHolder();
results.setAdapter(holder);

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
                progressBar.setVisibility(View.GONE);


                if(!response.isSuccessful()){
                    textViewResult.setText(response.message());
                    return;
                }
                for (Nutrient nutrient:response.body()){
                    foods.add(nutrient);
                }

                results.setAdapter(new FoodHolder(foods,this));

              progressBar.setVisibility(View.GONE);
              linearLayout.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<Nutrient>> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
                progressBar.setVisibility(View.GONE);
                if(t.getMessage().contains("Unable to resolve host")){

                    linearLayout.setVisibility(View.VISIBLE);
                }
            }
        });

return view;
    }
    @Override
    public void onFoodClick(int position) {
        Intent intent=new Intent(getContext(), FoodDetailActivity.class);
        intent.putExtra("food",foods.get(position));
        startActivity(intent);

    }

}
