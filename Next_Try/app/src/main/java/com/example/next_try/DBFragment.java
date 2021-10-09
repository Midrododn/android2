package com.example.next_try;

import android.database.sqlite.SQLiteDatabase;
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

    private int putToDB = 0;
    private LinkedList<String> varList = new LinkedList<String>();
    private SQLiteDatabase myData ;

    static {
        System.loadLibrary("next_try");
    }

    private void parseAndWrite (int var){
        String par = Integer.toString(var);
        String ins = "INSERT INTO vars_table VALUES ('" + par + "','" + par + "');";
        myData.execSQL(ins);
    }
    public void generateDB(){
        myData = SQLiteDatabase.openOrCreateDatabase("db",null);

        /*String table;
        table = "CREATE TABLE IF NOT EXISTS vars_table (" +
                "   var_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "   var_name text NOT NULL);";
        myData.execSQL("CREATE TABLE IF NOT EXISTS vars_table ( var_id INTEGER PRIMARY KEY AUTOINCREMENT,  var_name text NOT NULL);"); */
    }

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
        TextView counterLabel = view.findViewById(R.id.label_page_3);
        counterLabel.setText("0");
        varList.add("List from DB(will):\n");

        binding.button3rdMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                putToDB--;
                counterLabel.setText(Integer.toString(putToDB));
            }
        });

        binding.button3rdPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                putToDB++;
                counterLabel.setText(Integer.toString(putToDB));
            }
        });

        binding.button3rdAddtodb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String conv = Integer.toString(putToDB) + Integer.toString(ret_int());
                varList.add(conv);
                conv = varList.toString();
                conv = conv.replace("["," ");
                conv = conv.replace(","," ");
                conv = conv.replace("]"," ");
                listDB.setText(conv);
            }
        });

    }

    public native int ret_int();
}