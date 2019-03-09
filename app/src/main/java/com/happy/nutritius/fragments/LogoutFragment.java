package com.happy.nutritius.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.happy.nutritius.R;
import com.happy.nutritius.SharedPrefManager;
import com.happy.nutritius.activities.LoginActivity;

public class LogoutFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String SHARED_PREF_NAME = "HAPPY_PREFS";

    public LogoutFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



      new Handler().postDelayed(new Runnable() {
          @Override
          public void run() {
             SharedPrefManager.logoutUser(getContext());
              startActivity(new Intent(getContext(), LoginActivity.class));
              getActivity().finish();
          }
      },3000);
        return inflater.inflate(R.layout.fragment_logout, container, false);
    }




}
