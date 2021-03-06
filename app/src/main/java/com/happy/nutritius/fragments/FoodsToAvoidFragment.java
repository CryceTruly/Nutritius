package com.happy.nutritius.fragments;


import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.happy.nutritius.R;
import com.happy.nutritius.adapters.FoodsToAvoidHolder;
import com.happy.nutritius.api.APIService;
import com.happy.nutritius.api.Api;
import com.happy.nutritius.model.Food;
import com.happy.nutritius.model.Nutrient;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class FoodsToAvoidFragment extends Fragment{
private RecyclerView results;
    private static final String TAG = "FoodsFragment";
private TextView textViewResult;
private ProgressBar progressBar;
    private BottomSheetBehavior mBehavior;
    private BottomSheetDialog mBottomSheetDialog;
    private View bottom_sheet;

   private List<Food> foods=new ArrayList<>();
    LinearLayout linearLayout;
    public FoodsToAvoidFragment() {
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
        bottom_sheet = view.findViewById(R.id.bottom_sheet);
        mBehavior = BottomSheetBehavior.from(bottom_sheet);

        FoodsToAvoidHolder holder=new FoodsToAvoidHolder();
results.setAdapter(holder);



        //building retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Defining retrofit api service
        APIService service = retrofit.create(APIService.class);

        Call<List<Food>> call = service.getFoodToAvoid();

        call.enqueue(new Callback<List<Food>>() {
            @Override
            public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
                Log.d(TAG, "onResponse: "+response);
                progressBar.setVisibility(View.GONE);


                if(!response.isSuccessful()){
                    textViewResult.setText(response.message());
                    return;
                }
                foods.addAll(response.body());

                results.setAdapter(new FoodsToAvoidHolder(foods, getContext(), new FoodsToAvoidHolder.ItemHolder.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, Food obj, int pos) {
                        showBottomSheetDialog(obj);
                    }
                }));
              progressBar.setVisibility(View.GONE);
              linearLayout.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<Food>> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
                progressBar.setVisibility(View.GONE);
                if(t.getMessage().contains("Unable to resolve host")){

                    linearLayout.setVisibility(View.VISIBLE);
                }
            }
        });

return view;
    }

    private void showBottomSheetDialog(Food obj) {
        if (mBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            mBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }

        final View view = getLayoutInflater().inflate(R.layout.sheet_basic, null);
        ((TextView) view.findViewById(R.id.name)).setText(obj.getName());
        ((TextView) view.findViewById(R.id.reason)).setText(obj.getReason());
        (view.findViewById(R.id.bt_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBottomSheetDialog.dismiss();
            }
        });



        mBottomSheetDialog = new BottomSheetDialog(getContext());
        mBottomSheetDialog.setContentView(view);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mBottomSheetDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        mBottomSheetDialog.show();
        mBottomSheetDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                mBottomSheetDialog = null;
            }
        });
    }


}
