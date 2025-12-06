package com.example.tareacontrolesbasicos;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner;
    private EditText editTextNumber;
    private RadioGroup radioGroupSexo;
    private RadioButton radioMasculino;
    private RadioButton radioFemenino;

    private EditText txtCiudad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        spinner = findViewById(R.id.spinner);
        editTextNumber = findViewById(R.id.editTextNumber);
        editTextNumber.setEnabled(false);

        String[] opciones = new String[]{
                "Seleccione su tipo de identificación",
                "Cédula",
                "Pasaporte"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                opciones
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    editTextNumber.setEnabled(false);
                    editTextNumber.setText("");
                } else {
                    editTextNumber.setEnabled(true);
                    editTextNumber.requestFocus();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                editTextNumber.setEnabled(false);
                editTextNumber.setText("");
            }
        });

        radioGroupSexo = findViewById(R.id.radioGroupSexo);
        radioMasculino = findViewById(R.id.radioMasculino);
        radioFemenino = findViewById(R.id.radioFemenino);

        txtCiudad = findViewById(R.id.txtCiudad);

        txtCiudad.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                String upper = s.toString().toUpperCase();
                if (!upper.equals(s.toString())) {
                    txtCiudad.removeTextChangedListener(this);
                    txtCiudad.setText(upper);
                    txtCiudad.setSelection(upper.length());
                    txtCiudad.addTextChangedListener(this);
                }
            }
        });

    }

}