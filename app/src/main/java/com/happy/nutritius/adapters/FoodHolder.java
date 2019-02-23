package com.happy.nutritius.adapters;

import android.content.Context;
import android.content.Intent;
import android.print.PrinterId;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.happy.nutritius.R;
import com.happy.nutritius.activities.FoodDetailActivity;
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

    public FoodHolder(List<Nutrient> nutrients,Context mContext) {
        this.nutrients = nutrients;
        this.mContext=mContext;

    }
    public FoodHolder() {
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_food,parent,false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: "+nutrients.get(position));
        holder.setName(nutrients.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(mContext, FoodDetailActivity.class);
                i.putExtra("food",nutrients.get(position));
                mContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return nutrients.size();
    }

    public static class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private TextView name;


        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            itemView.setOnClickListener(this);
        }
        public void setName(String name_text){
            name.setText(name_text);
        }


        @Override
        public void onClick(View v) {

        }
    }
}
