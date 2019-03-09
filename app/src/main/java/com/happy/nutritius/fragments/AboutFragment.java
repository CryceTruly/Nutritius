package com.happy.nutritius.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.happy.nutritius.BuildConfig;
import com.happy.nutritius.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends Fragment {

private ListView listView;
private ProgressBar progressBar;
    public AboutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_about, container, false);
        listView=view.findViewById(R.id.options);
        progressBar=view.findViewById(R.id.progress_bar);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        List items=new ArrayList();
        items.add("An application to guide with nutrition");
        items.add("Version"+ BuildConfig.VERSION_CODE);

        listView.setAdapter(new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,items));
        progressBar.setVisibility(View.GONE);
    }
}
