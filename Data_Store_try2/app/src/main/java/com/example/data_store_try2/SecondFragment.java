package com.example.data_store_try2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
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

import com.example.data_store_try2.databinding.FragmentSecondBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    EditText txtBar;
    Button btnLoad, btnAdd;
    TextView label1;
    SharedPreferences sPref;
    LinkedList<String> lList = new LinkedList<String>();

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtBar = view.findViewById(R.id.editText2_text);

        label1 = view.findViewById(R.id.textview_second);
        btnAdd = view.findViewById(R.id.button2_addtolist);
        btnLoad = view.findViewById(R.id.button2_loadlist);
        lList = new LinkedList<String>();
        label1.setMovementMethod(new ScrollingMovementMethod());

        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });

        binding.button2Addtolist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_list();
            }
        });

        binding.button2Loadlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load_list();
            }
        });

        binding.button2Append.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt = "";
                txt = "\n" + txtBar.getText().toString();
                lList.add(txt);
                txt = "List generated :" + prnt_list();
                label1.setText(txt);
            }
        });

        binding.button2Delldb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lList = new LinkedList<String>();
                add_list();
            }
        });
    }

    private String prnt_list() {
        Iterator<String> iterator = lList.iterator();
        String row = "";
        while (iterator.hasNext()){
            row = row + iterator.next();
        }

        return row;
    }

    private void add_list() {
        sPref = getActivity().getSharedPreferences("pref1", Context.MODE_PRIVATE);
        SharedPreferences.Editor editVar = sPref.edit();
        Gson gtmp = new Gson();
        String jsonStr = gtmp.toJson(lList);

        editVar.putString("courses", jsonStr);
        editVar.apply();
        Toast.makeText(getActivity(), "Saved list", Toast.LENGTH_SHORT).show();
    }

    private void load_list() {
        sPref = getActivity().getSharedPreferences("pref1",Context.MODE_PRIVATE);
        Gson gtmp = new Gson();

        String json = sPref.getString("courses", null);
        Type type = new TypeToken<LinkedList<String>>() {}.getType();
        lList = gtmp.fromJson(json, type);

        String label_txt = "Loaded list : \n";
        Iterator<String> iterator = lList.iterator();
        while (iterator.hasNext()){
            label_txt = label_txt + iterator.next();
        }
        label1.setText(label_txt);
        Toast.makeText(getActivity(), "Loaded", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}