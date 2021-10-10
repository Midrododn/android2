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
    private int prev = 0;
    private LinkedList<String> varList = new LinkedList<String>();
    private SQLiteDatabase myData ;

    static {
        System.loadLibrary("next_try");
    }

    /*
    private void parseAndWrite (int var){
        String par = Integer.toString(var);
        String ins = "INSERT INTO vars_table VALUES ('" + par + "','" + par + "');";
        myData.execSQL(ins);
    }
    public void generateDB(){
        myData = SQLiteDatabase.openOrCreateDatabase("db",null);

        String table;
        table = "CREATE TABLE IF NOT EXISTS vars_table (" +
                "   var_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "   var_name text NOT NULL);";
        myData.execSQL("CREATE TABLE IF NOT EXISTS vars_table ( var_id INTEGER PRIMARY KEY AUTOINCREMENT,  var_name text NOT NULL);");
    }
    */

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
        TextView fileStatus = view.findViewById(R.id.view_3rg_dbstatus);
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
                String rsum = "(" + Integer.toString(rollSum(prev,putToDB)) + ")";
                String conv = Integer.toString(putToDB) + Integer.toString(ret_int()) +
                        rsum;
                prev = putToDB;
                varList.add(conv);
                conv = varList.toString();
                conv = conv.replace("["," ");
                conv = conv.replace(","," ");
                conv = conv.replace("]"," ");
                listDB.setText(conv);
            }
        });

        binding.button3rdGendb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int err = try_gen_db();
                String err_num = Integer.toString(err);
                if (err == 0){
                    fileStatus.setText("Failed " + err_num);
                } else if (err == 1){
                    fileStatus.setText("Exist " + err_num);
                } else {
                    fileStatus.setText("Unknown error " + err_num);
                }
            }
        });

        binding.but3rdMaketxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = make_txt();
                String code = Integer.toString(i);
                fileStatus.setText("TXT " + i);
            }
        });

    }

    // https://stackoverflow.com/questions/33870296/cant-create-file-in-the-internal-storage
    public native int ret_int();
    public native int rollSum(int a, int b);
    public native int try_gen_db();
    public native int make_txt();
}