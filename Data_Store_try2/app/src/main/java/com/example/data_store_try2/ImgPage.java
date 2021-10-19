package com.example.data_store_try2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.data_store_try2.databinding.FragmentImgPageBinding;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOError;
import java.io.IOException;
import java.util.Scanner;

public class ImgPage extends Fragment {

    private FragmentImgPageBinding binding;
    TextView dir_txt;

    public static ImgPage newInstance(String param1, String param2) {
        ImgPage fragment = new ImgPage();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentImgPageBinding.inflate(inflater, container, false);
        //return inflater.inflate(R.layout.fragment_img_page, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        dir_txt = view.findViewById(R.id.textView4_dir);

        binding.button4Showdir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt = new String();
                txt = "Dir:\n";
                File rootDataDir = getActivity().getFilesDir();
                txt = rootDataDir.toString();
                dir_txt.setText(txt);
            }
        });

        binding.button4Createtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String file_pth = new String();
                file_pth = getActivity().getFilesDir().toString();
                file_pth += "/test.txt";
                String err = new String();
                File fileObj = null;
                try {
                    fileObj = new File(file_pth);
                    err = "Created";
                    fileObj.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                    err = e.toString();
                }
                dir_txt.setText(err);

                try {
                    FileWriter fWiter = new FileWriter(file_pth);
                    fWiter.write("TEST STRING");
                    fWiter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        binding.button4Showtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String file_pth = new String();
                file_pth = getActivity().getFilesDir().toString();
                file_pth += "/test.txt";
                String txt_out = new String();
                txt_out = "";
                try {
                    File fObj = new File(file_pth);
                    Scanner myRedare = new Scanner(fObj);
                    while (myRedare.hasNextLine()){
                        txt_out = myRedare.nextLine() + "\n";
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    txt_out = e.toString();
                }
                dir_txt.setText(txt_out);
            }
        });
    }
}






















