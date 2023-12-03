package com.myapplicationname.legalhelp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HomeFragment extends Fragment {
    Button button2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        button2 = (Button) view.findViewById(R.id.fillform1);

        // set onclick listener on buttons
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // perform any action on button click
                Fragment secondFrag = new DisclaimerFragment();
                FragmentTransaction fn = getActivity().getSupportFragmentManager().beginTransaction();
                fn.replace(R.id.drawer,secondFrag,null).addToBackStack(null).commit();
            }
        });




        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).setActionBarTitle("Home");
    }
}