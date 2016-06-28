package com.example.alfonso.era04b;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class Bienvenida extends AppCompatActivity {

    private Button btnSiguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenida);

        //Obtenemos una referencia a los controles de la interfaz
        btnSiguiente = (Button)findViewById(R.id.BtnSiguiente);


        //Implementamos el evento click del botón
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creamos el Intent
                Intent intent =
                        new Intent(Bienvenida.this, MensajePreEncuesta1.class);


                //Iniciamos la nueva actividad
                startActivity(intent);
            }
        });
    }

}
