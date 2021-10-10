package com.example.dbtry1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.dbtry1.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    private int count = 0;

    private FragmentFirstBinding binding;

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

        MyDBHandler dbHandler = new MyDBHandler(view.getContext(), null,null, 1);
        EditText st_id = view.findViewById(R.id.studentid);
        EditText st_name = view.findViewById(R.id.studentname);
        TextView label = view.findViewById(R.id.viewnumb_1st);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        binding.btnadd1st.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MyDBHandler dbHandler = new MyDBHandler(view.getContext(), null,null, 1);
                //EditText st_id = view.findViewById(R.id.studentid);
                int id = Integer.getInteger(st_id.getText().toString());
                //EditText st_name = view.findViewById(R.id.studentname);
                String name = st_name.getText().toString();
                Student student = new Student(id, name);
                dbHandler.addHandler(student);
                String student_row = "";
                //TextView label = view.findViewById(R.id.viewnumb_1st);
                student_row = Integer.toString(id) + " " + name;
                label.setText(student_row);
                st_id.setText("");
                st_name.setText("");
            }
        });

        binding.btnload1st.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MyDBHandler dbHandler = new MyDBHandler(view.getContext(), null, null, 1);
                //TextView label = view.findViewById(R.id.viewnumb_1st);
                label.setText(dbHandler.loadHandler());
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}