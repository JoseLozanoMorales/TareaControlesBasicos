package com.example.tareacontrolesbasicos;

import android.content.Intent;
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
import android.widget.Toast;

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
        // ---------- SPINNER DÍA ----------
        Spinner spinnerdia = findViewById(R.id.spnDia);
        String[] datosDias = new String[32];
        datosDias[0] = "Día";
        for (int i = 1; i <= 31; i++) {
            datosDias[i] = String.valueOf(i);
        }
        ArrayAdapter<String> adapterdia = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, datosDias);
        spinnerdia.setAdapter(adapterdia);


        // ---------- SPINNER MES ----------
        Spinner spinnermes = findViewById(R.id.spnMes);
        String[] datosMes = new String[13];
        datosMes[0] = "Mes";
        for (int i = 1; i <= 12; i++) {
            datosMes[i] = String.valueOf(i);
        }
        ArrayAdapter<String> adaptermes = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, datosMes);
        spinnermes.setAdapter(adaptermes);


        // ---------- SPINNER AÑO ----------
        Spinner spinneraño = findViewById(R.id.spnAño);
        int inicio = 1990;
        int fin = 2030;
        String[] datosAño = new String[(fin - inicio) + 2];
        datosAño[0] = "Año";
        for (int i = 1; i < datosAño.length; i++) {
            datosAño[i] = String.valueOf(inicio + (i - 1));
        }
        ArrayAdapter<String> adapteraño = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, datosAño);
        spinneraño.setAdapter(adapteraño);
    }

    public void Enviar(View view)
    {
        EditText txtNombre = findViewById(R.id.txtNombre);

        if(!txtNombre.getText().toString().isEmpty())
        {
            Intent intent = new Intent(this, Menu.class);
            Bundle b = new Bundle();

            b.putString("NOMBRE", txtNombre.getText().toString());
            intent.putExtras(b);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "Debe ingresar su nombre", Toast.LENGTH_SHORT).show();
        }
    }
}