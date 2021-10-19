package com.example.data_store_try2;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCaller;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.data_store_try2.databinding.FragmentDBSQLiteTry2Binding;

import java.io.File;

/*
 * A simple {@link Fragment} subclass.
 * Use the {@link DB_SQLite_try2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DB_SQLite_try2 extends Fragment{

    private FragmentDBSQLiteTry2Binding binding;

    Button btnAdd, btnRead, btnClear;
    EditText etName, etMail;
    TextView txtView;
    String name = new String();
    String mail = new  String();

    DBHelper dbHelper;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDBSQLiteTry2Binding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        btnAdd = view.findViewById(R.id.button3_add);
        btnRead = view.findViewById(R.id.button3_read);
        btnClear = view.findViewById(R.id.button3_clear);
        txtView = view.findViewById(R.id.textView3);
        etName = view.findViewById(R.id.editText3_name);
        etMail = view.findViewById(R.id.editText3_mail);
        name = "";
        mail = "";

        binding.button3Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt = new String();
                txt = "Dir:\n";
                File rootDataDir = getActivity().getFilesDir();
                txt = rootDataDir.toString();
                txtView.setText(txt);
            }
        });

        binding.button3Read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int res = c_retstr();
                txtView.setText(Integer.toString(res));
            }
        });

        binding.button3Clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        binding.button4Toimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(DB_SQLite_try2.this)
                        .navigate(R.id.action_DB_SQL_to_img_Page);
            }
        });

    }

    public native int c_retstr();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
