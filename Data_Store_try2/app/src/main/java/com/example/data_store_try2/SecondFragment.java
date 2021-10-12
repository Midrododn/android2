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

    private EditText txtBar;
    private Button btnLoad, btnAdd;
    private TextView label1;
    private SharedPreferences sPref;
    private LinkedList<String> lList = new LinkedList<String>();
    private LinkedList<String> appendList = new LinkedList<String>();

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

        binding.button2Nextsqldb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_DBsqlite);
            }
        });

        binding.button2Addtolist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_list();
                label1.setText("DB saved. Click Load DB.");
            }
        });

        binding.button2Loadlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lList = load_list();
                String labelText = "Loaded data:\n";
                labelText = labelText + prnt_list(lList);
                label1.setText(labelText);
            }
        });

        binding.button2Append.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt = "";
                txt = txtBar.getText().toString();
                appendList.add(txt);
                txt = "List generated :" + prnt_list(appendList);
                label1.setText(txt);
                txtBar.setText("");
            }
        });

        binding.button2Delldb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                del_db();
                label1.setText("DB is empty now.");
            }
        });
    }

    private void del_db() {
        LinkedList<String> emptyList = new LinkedList<String>();
        sPref = getActivity().getSharedPreferences("pref1", Context.MODE_PRIVATE);
        SharedPreferences.Editor editVar = sPref.edit();
        Gson gtmp = new Gson();
        String jsonStr = "";
        jsonStr = gtmp.toJson(emptyList);
        editVar.putString("courses", jsonStr);
        editVar.apply();

    }

    private String prnt_list(LinkedList<String> toPrnt) {
        LinkedList<String> outList = toPrnt;
        Iterator<String> iterator = outList.iterator();
        String row = "";
        int count = 0;
        while (iterator.hasNext()){
            row = row + "\n" + Integer.toString(++count) + ") " + iterator.next();
        }

        return row;
    }

    private void add_list() {
        sPref = getActivity().getSharedPreferences("pref1", Context.MODE_PRIVATE);
        SharedPreferences.Editor editVar = sPref.edit();
        Gson gtmp = new Gson();
        String jsonStr = "";
        LinkedList<String> loadedList = new LinkedList<String>();

        loadedList = load_list();
        Iterator<String> iterator = appendList.iterator();
        String row = "";
        while (iterator.hasNext()){
            loadedList.add(iterator.next());
        }
        jsonStr = gtmp.toJson(loadedList);
        editVar.putString("courses", jsonStr);
        editVar.apply();
        appendList = new LinkedList<String>();
        //Toast.makeText(getActivity(), "Saved list", Toast.LENGTH_SHORT).show();
    }

    private LinkedList<String> load_list() {
        sPref = getActivity().getSharedPreferences("pref1",Context.MODE_PRIVATE);
        Gson gtmp = new Gson();
        LinkedList<String> listOut = new LinkedList<String>();

        String json = sPref.getString("courses", null);
        Type type = new TypeToken<LinkedList<String>>() {}.getType();
        listOut = gtmp.fromJson(json, type);

        //Toast.makeText(getActivity(), "Loaded", Toast.LENGTH_SHORT).show();
        return  listOut;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}