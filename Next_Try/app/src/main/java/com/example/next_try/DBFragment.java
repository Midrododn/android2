package com.example.next_try;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.next_try.databinding.FragmentDBBinding;

import java.util.LinkedList;

public class DBFragment extends Fragment {

    private FragmentDBBinding binding;

    private int iPlus = 0;
    private LinkedList<String> varList = new LinkedList<String>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentDBBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView listDB = view.findViewById(R.id.textView_dbout);
        varList.add("List from DB:\n");

        binding.button3rdAddtodb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                varList.add("var\n");
                String conv = varList.toString();
                conv = conv.replace("["," ");
                conv = conv.replace(","," ");
                conv = conv.replace("]"," ");
                listDB.setText(conv);
            }
        });
    }
}