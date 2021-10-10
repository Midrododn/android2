package com.example.data_store_try2;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Shader;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.data_store_try2.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    EditText txtBar;
    Button btnLoad, btnAdd;
    TextView label1;
    SharedPreferences sPref;
    final String SAVED_TEXT = "saved_text= ";

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

        txtBar = view.findViewById(R.id.editText_1st_text);

        btnAdd = view.findViewById(R.id.button1_add);
        btnLoad = view.findViewById(R.id.button1_load);
        label1 = view.findViewById(R.id.textview_first);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        binding.button1Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String check = "";
                check = saveText();
                String textRow = check;
                textRow = textRow + ("(Add)\nText from bar :\n " + txtBar.getText().toString());
                //label1.setText(textRow);
            }
        });

        binding.button1Load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String loadedData = "";
                loadedData = loadText();
                String textRow = "(Load)\nText from bar :\n " + txtBar.getText().toString();
                textRow = "(Load)\nText from bar :\n " + loadedData;
                label1.setText(textRow);
            }
        });
    }

    private String saveText() {
        sPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        String txt_bar = txtBar.getText().toString();
        ed.putString(SAVED_TEXT, txt_bar);
        ed.commit();
        label1.setText(txt_bar);

        return txt_bar;
    }
    private String loadText() {
        sPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        String savedText = sPref.getString(SAVED_TEXT, "");

        String textRow = "(Load)\nText from bar :\n " + savedText;
        label1.setText(textRow);

        String Ttext = "";
        if (savedText == ""){
            Ttext = "Empty string loaded";
        } else {
            Ttext = "Data loaded";
        }
        Toast.makeText(getActivity(), Ttext, Toast.LENGTH_SHORT).show();

        return savedText;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}