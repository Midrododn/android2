package com.example.data_store_try2;

import android.os.Bundle;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
    ImageView imggraf;
    int imgNbr = 0;

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
        imggraf = view.findViewById(R.id.imgview4_graf);

        File pth_root = getActivity().getFilesDir();
        String pthStr = pth_root.toString();

        binding.button4Showdir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt = new String();
                txt = "Dir:\n";
                File rootDataDir = getActivity().getFilesDir();
                txt = rootDataDir.toString();
                dir_txt.setText(txt);
                //txt = c_dircont(pthStr);
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
                    Scanner myReader = new Scanner(fObj);
                    while (myReader.hasNextLine()){
                        txt_out = myReader.nextLine() + "\n";
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    txt_out = e.toString();
                }
                dir_txt.setText(txt_out);
            }
        });

        binding.button4Changeimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tmp =  pthStr;
                imggraf.setImageResource(R.drawable.fig_614_);
            }
        });
    }

    public native String c_mkdir(String pth);
    //public native String c_dircont(String pth);

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}






















