package com.example.as_formularze_zad4;

import android.os.Bundle;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etWeight, etHeight;
    private RadioGroup rgGender;
    private TextView  tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etWeight = findViewById(R.id.etWeight);
        etHeight = findViewById(R.id.etHeight);
        rgGender = findViewById(R.id.rgGender);
        tvResult = findViewById(R.id.tvResult);
        Button btnCalc = findViewById(R.id.btnCalc);

        btnCalc.setOnClickListener(v -> {
            if(validate()) {
                calculate();
            }
        });
    }
    private boolean validate(){
        etWeight.setError(null);
        etHeight.setError(null);

        String wStr = etWeight.getText().toString().trim();
        if(wStr.isEmpty() || Double.parseDouble(wStr) <= 0){
            etWeight.setError(getString(R.string.error_weight));
            etWeight.requestFocus();
            return false;
        }
        String hStr = etHeight.getText().toString().trim();
        if (hStr.isEmpty()) {
            etHeight.setError(getString(R.string.error_height));
            etHeight.requestFocus();
            return false;
        }
        double h = Double.parseDouble(hStr);
        if (h <= 0 || h > 300) {
            etHeight.setError(getString(R.string.error_height));
            etHeight.requestFocus();
            return false;
        }
        if (rgGender.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, R.string.error_gender, Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
    private  void calculate(){
        double w = Double.parseDouble(etWeight.getText().toString().trim());
        double h = Double.parseDouble(etHeight.getText().toString().trim()) / 100.0;
        double bmi = w / (h * h);

        String odp;
        if (bmi < 18.5) odp = "Niedowaga";
        else if (bmi < 25) odp = "Waga prawidłowa";
        else if (bmi < 30) odp = "Nadwaga";
        else odp = "Otyłość";

        tvResult.setText(String.format("BMI: %.2f\nKategoria: %s", bmi, odp));
    }
}