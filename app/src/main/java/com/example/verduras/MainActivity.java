package com.example.verduras;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editTextCb;
    private EditText editTextTh;
    private Button btnCalcular;
    private Button btnLimpiar;
    private TextView advertencia;
    private TextView advertencia2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextCb = findViewById(R.id.Cb);
        editTextTh = findViewById(R.id.Th);
        btnCalcular = findViewById(R.id.btnCalcular);
        btnLimpiar = findViewById(R.id.btnLimpiar);
        advertencia = findViewById(R.id.advertencia);
        advertencia2 = findViewById(R.id.advertencia2);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtener los valores ingresados por el usuario
                String cbText = editTextCb.getText().toString();
                String thText = editTextTh.getText().toString();

                // Verificar si los campos están vacíos
                boolean cbEmpty = cbText.isEmpty();
                boolean thEmpty = thText.isEmpty();

                // Verificar si los campos son numéricos
                boolean cbNumeric = isNumeric(cbText);
                boolean thNumeric = isNumeric(thText);

                // Mostrar u ocultar las advertencias según las condiciones
                if (cbEmpty || thEmpty) {
                    advertencia2.setVisibility(View.VISIBLE);
                    advertencia.setVisibility(View.INVISIBLE);
                } else {
                    advertencia2.setVisibility(View.INVISIBLE);
                    if (!cbNumeric || !thNumeric) {
                        advertencia.setVisibility(View.VISIBLE);
                    } else {
                        advertencia.setVisibility(View.INVISIBLE);

                        // Si ambos campos son numéricos y no están vacíos, realizar el cálculo
                        double cb = Double.parseDouble(cbText);
                        double th = Double.parseDouble(thText);
                        double resultado = cb * th;

                        // Crear un intent para iniciar ResultadoActivity y pasar el resultado como extra
                        Intent intent = new Intent(MainActivity.this, Resultado.class);
                        intent.putExtra("resultado", resultado);

                        // Iniciar la actividad ResultadoActivity
                        startActivity(intent);
                    }
                }
            }
        });

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Limpiar los EditText estableciendo el texto en blanco
                editTextCb.setText("");
                editTextTh.setText("");

                // Ocultar ambas advertencias
                advertencia.setVisibility(View.INVISIBLE);
                advertencia2.setVisibility(View.INVISIBLE);
            }
        });
    }

    // Método para verificar si una cadena es numérica
    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}