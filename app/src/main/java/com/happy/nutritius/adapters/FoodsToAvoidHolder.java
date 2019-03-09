package com.happy.nutritius.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.happy.nutritius.R;
import com.happy.nutritius.activities.FoodDetailActivity;
import com.happy.nutritius.model.Food;
import com.happy.nutritius.model.Nutrient;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FoodsToAvoidHolder extends RecyclerView.Adapter<FoodsToAvoidHolder.ItemHolder> {
    public List<Food> foods=new ArrayList<>();
    private Context mContext;
    private static final String TAG = "FoodsToAvoidHolder";
    private ItemHolder.OnItemClickListener onItemClickListener;

    public FoodsToAvoidHolder(List<Food> foods, Context mContext, ItemHolder.OnItemClickListener onItemClickListener) {
        this.foods = foods;
        this.mContext = mContext;
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemClickListener(ItemHolder.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    public FoodsToAvoidHolder() {
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_food,parent,false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: "+foods.get(position));
        holder.setName(foods.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: user tries navigating");
                if (onItemClickListener != null) {
                    Log.d(TAG, "onClick: clicked an item at"+foods.get(position));
                    Food food=foods.get(position);
                    onItemClickListener.onItemClick(view, food, position);
                }
            }
        });

    }
    public Food getItem(int position) {
        return foods.get(position);
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    public static class ItemHolder extends RecyclerView.ViewHolder{
    private TextView name;


        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);

        }


        public void setName(String name_text){
            name.setText(name_text);
        }
        public interface OnItemClickListener {
            void onItemClick(View view, Food obj, int pos);
        }

    }
}
