package com.example.data_store_try2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.data_store_try2.databinding.FragmentDBSQLiteTry2Binding;

/*
 * A simple {@link Fragment} subclass.
 * Use the {@link DB_SQLite_try2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DB_SQLite_try2 extends Fragment {

    private FragmentDBSQLiteTry2Binding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDBSQLiteTry2Binding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
