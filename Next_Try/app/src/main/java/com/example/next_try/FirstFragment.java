package com.example.next_try;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.next_try.databinding.FragmentFirstBinding;
import com.google.android.material.textfield.TextInputEditText;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    private int count = 0;
    //private String batTXT = new String();

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

        TextView msg = view.findViewById(R.id.textview_first);
        EditText in_txt = view.findViewById(R.id.text_bar1);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        binding.buttonToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String batTXT = in_txt.getText().toString();
                //batTXT = "Text example";
                //batTXT = in_txt.getText().toString();
                count++;
                msg.setText("Button pressed " + count + " times.");
                Toast myToast = Toast.makeText(getActivity(), batTXT, Toast.LENGTH_SHORT);
                myToast.show();
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}