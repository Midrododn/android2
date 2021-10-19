package com.example.goggle_tut;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.goggle_tut.databinding.FragmentFirstBinding;

import java.io.File;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    private Context context;

    File file = new File(context.getFilesDir(), "text1.txt");
    String tmp1 = new String();

    TextView tView;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tView = view.findViewById(R.id.textview1_dir);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        binding.button1Dir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tmp1 = context.getFilesDir().toString();
                tView.setText(tmp1);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}