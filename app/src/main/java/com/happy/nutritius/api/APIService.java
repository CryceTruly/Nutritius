package com.happy.nutritius.api;

import com.happy.nutritius.model.Food;
import com.happy.nutritius.model.Nutrient;
import com.happy.nutritius.model.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIService {

    //The register call
    @FormUrlEncoded
    @POST("register")
    Call<Result> createUser(@Field("username") String name,@Field("email") String email, @Field("password")
                            String password,@Field("first_name") String first_name,@Field("last_name") String last_name);

    //the signin call
    @FormUrlEncoded
    @POST("login")
    Call<com.happy.nutritius.model.Result> userLogin(
            @Field("username") String username,
            @Field("password") String password
    );
    @GET("nutrients")
    Call<List<Nutrient>> getNutrients();

    @GET("foodstoavoid")
    Call<List<Food>> getFoodToAvoid();

}
