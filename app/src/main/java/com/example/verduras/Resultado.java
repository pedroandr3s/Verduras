package com.example.verduras;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Resultado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        TextView txtResultado = findViewById(R.id.txtResultado);

        // Recuperar el resultado del Intent
        double resultado = getIntent().getDoubleExtra("resultado", 0.0);

        // Mostrar el resultado en el TextView
        txtResultado.setText("El resultado es: $" + resultado);

        Button btnVolver = findViewById(R.id.btnVolver);

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crear un intent para volver a MainActivity
                Intent intent = new Intent(Resultado.this, MainActivity.class);

                // Enviar el resultado de vuelta a MainActivity
                intent.putExtra("resultado", resultado);

                setResult(RESULT_OK, intent);
                finish(); // Finalizar la actividad actual
            }
        });
    }
}
