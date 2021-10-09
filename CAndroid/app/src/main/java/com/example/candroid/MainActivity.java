package com.example.candroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.candroid.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'candroid' library on application startup.
    static {
        System.loadLibrary("candroid");
    }

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Example of a call to a native method
        TextView tv = binding.sampleText;
        String row = stringFromJNI() + " " + Integer.toString(ret_int()) +
                " " + Integer.toString(new_int());
        tv.setText(row);
    }

    /**
     * A native method that is implemented by the 'candroid' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
    public native int ret_int();
    public native int new_int();
}