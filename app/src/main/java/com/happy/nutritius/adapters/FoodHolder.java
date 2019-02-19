package com.happy.nutritius.adapters;

import android.content.Context;
import android.print.PrinterId;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.happy.nutritius.R;
import com.happy.nutritius.model.Nutrient;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Callback;

public class FoodHolder extends RecyclerView.Adapter<FoodHolder.ItemHolder> {
    private List<Nutrient> nutrients=new ArrayList<>();
    private Context mContext;
    private static final String TAG = "FoodHolder";
    private foodClick foodClick;
    public FoodHolder(List<Nutrient> nutrients, foodClick onFoodListener) {
        this.nutrients = nutrients;
        this.foodClick = onFoodListener;
    }
    public FoodHolder() {
    }

    public FoodHolder(List<Nutrient> foods, Callback<List<Nutrient>> listCallback) {
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_food,parent,false);
        return new ItemHolder(view,foodClick);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: "+nutrients.get(position));
        holder.setName(nutrients.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return nutrients.size();
    }

    public static class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
private TextView name;
foodClick foodClick;

        public ItemHolder(@NonNull View itemView,foodClick foodClick) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            this.foodClick=foodClick;
            itemView.setOnClickListener(this);
        }
        public void setName(String name_text){
            name.setText(name_text);
        }

        @Override
        public void onClick(View v) {

        }
    }
    public interface foodClick{
        void onFoodClick(int position);
    }
}
