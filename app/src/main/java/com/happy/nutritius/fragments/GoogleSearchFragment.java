package com.happy.nutritius.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.happy.nutritius.R;

import static com.google.android.gms.plus.PlusOneDummyView.TAG;

public class GoogleSearchFragment extends Fragment {
    private EditText editTextInput;
    private TextView name;
    private static final String KEY_USER_NAME = "keyusername";
    private static final String SHARED_PREF_NAME = "HAPPY_PREFS";
    private FloatingActionButton search;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_google_search,container,false);

         super.onCreateView(inflater, container, savedInstanceState);
        editTextInput = (EditText) view.findViewById(R.id.editTextInput);
        search=view.findViewById(R.id.search);
        name=view.findViewById(R.id.name);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        name.setText(sharedPreferences.getString(KEY_USER_NAME,"Hey there!"));
        Log.d(TAG, "onCreateView: "+sharedPreferences.getAll());
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSearchClick();
            }
        });
         return  view;
    }


        public void onSearchClick()
        {

            String term = editTextInput.getText().toString();
            if (term!="") {
                try {
                    Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                    intent.putExtra(SearchManager.QUERY, term);
                    startActivity(intent);
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        }

}
