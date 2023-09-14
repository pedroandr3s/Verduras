package com.example.verduras;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

public class Resultado extends AppCompatActivity {

    private ListView historialListView;
    private ArrayAdapter<String> historialAdapter;
    private Set<String> resultadosHistorial;

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

        // Obtener una referencia al ListView
        historialListView = findViewById(R.id.historial);

        // Inicializar la lista de resultadosHistorial usando SharedPreferences
        SharedPreferences preferences = getSharedPreferences("Historial", MODE_PRIVATE);
        resultadosHistorial = preferences.getStringSet("resultadosHistorial", new HashSet<String>());

        // Configurar el adaptador para el ListView
        historialAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<>(resultadosHistorial));
        historialListView.setAdapter(historialAdapter);

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

        historialListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Manejar la selección de un elemento del historial si es necesario
            }
        });

        // Agregar el resultado actual al historial
        resultadosHistorial.add("Ultimo Resultado: $" + resultado);

        // Actualizar SharedPreferences con el historial actualizado
        SharedPreferences.Editor editor = preferences.edit();
        editor.putStringSet("resultadosHistorial", resultadosHistorial);
        editor.apply();

        // Notificar al adaptador que los datos han cambiado
        historialAdapter.notifyDataSetChanged();
    }
}
