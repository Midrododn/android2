package com.example.data_store_try2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.data_store_try2.databinding.FragmentDBSQLiteTry2Binding;

import java.io.File;
import java.util.Vector;


// refactor to privet and empty rows; in construction;
public class DB_SQLite_try2 extends Fragment{

    private FragmentDBSQLiteTry2Binding binding;

    Button btnAdd, btnRead, btnClear;
    EditText etName, etMail;
    TextView txtView;
    String name = new String();
    String mail = new  String();
    Vector<Integer> id_vector = new Vector();

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
        txtView.setMovementMethod(new ScrollingMovementMethod());
        etName = view.findViewById(R.id.editText3_name);
        etMail = view.findViewById(R.id.editText3_mail);
        name = "";
        mail = "";
        File pth_root = getActivity().getFilesDir();
        String pthStr = pth_root.toString();

        binding.button3Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt = new String();
                txt = "Dir:\n";
                //File rootDataDir = getActivity().getFilesDir();
                //txt = rootDataDir.toString();
                txt += pthStr;
                txtView.setText(txt);
            }
        });

        binding.button3Read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tmp = new String();
                //File rootDataDir = getActivity().getFilesDir();
                //tmp = rootDataDir.toString();
                tmp = c_readtxt(pthStr);
                txtView.setText(tmp);

            }
        });

        binding.button3Clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tmp1 = new String();
                tmp1 = "Tmp txt";
                //File rootDataDir = getActivity().getFilesDir();
                //tmp1 = rootDataDir.toString();
                tmp1 = c_strfun(pthStr);
                txtView.setText(tmp1);
            }
        });

        binding.button3Createdb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tmp = new String();
                //File rootDataDir = getActivity().getFilesDir();
                //tmp = rootDataDir.toString();
                //tmp = c_generateDB(tmp);
                tmp = c_generateDB(pthStr);
                txtView.setText(tmp);
            }
        });

        binding.button3Appenddb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tmp = new String();
                //File rootDataDir = getActivity().getFilesDir();
                //tmp = rootDataDir.toString();
                tmp = c_appendDB(pthStr, etName.getText().toString());
                txtView.setText(tmp);
            }
        });

        binding.button3Readdb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tmp = new String();
                //File rootDataDir = getActivity().getFilesDir();
                //tmp = rootDataDir.toString();
                tmp = c_readDB(pthStr);
                txtView.setText(tmp);
            }
        });

        binding.button3Find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tmp = new String();
                tmp = c_find(pthStr, etName.getText().toString());
                txtView.setText(tmp);
            }
        });

        binding.button3Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tmp = new String();
                tmp = etMail.getText().toString();
                tmp = c_deleteDB(pthStr,tmp);
                txtView.setText(tmp);

            }
        });

        binding.button3Savedb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nname = etName.getText().toString();
                String uid = etMail.getText().toString();
                String tmp = c_saveDB(pthStr,nname,uid);
                txtView.setText(tmp);
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
    public native String c_strfun(String text);
    public native String c_readtxt(String pth);
    public native String c_generateDB(String pth);
    public native String c_appendDB(String pth, String text_name);
    public native String c_readDB(String pth);
    public native String c_find(String pth, String tname);
    public native String c_deleteDB(String pth, String idstr);
    public native String c_saveDB(String pth, String newname, String idstr);


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
