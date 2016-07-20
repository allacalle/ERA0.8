package com.example.alfonso.era04b;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Alfonso on 08/02/2016.
 * Ultima modificación: 20/07/2016

 */


public class MensajePreEncuesta1 extends AppCompatActivity {

    private Button btnSiguiente ;
    private Button btnAnterior ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensaje_pre_encuesta1);

        //Obtenemos una referencia a los controles de la interfaz
        btnSiguiente = (Button)findViewById(R.id.BtnSiguiente);
        btnAnterior = (Button)findViewById(R.id.BtnAnterior);



        //Implementamos el evento click del botón
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creamos el Intent
                Intent intent =
                        new Intent(MensajePreEncuesta1.this, MensajePreEncuesta2.class);


                //Iniciamos la nueva actividad
                startActivity(intent);
            }
        });

        //Implementamos el evento click del botón
        btnAnterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creamos el Intent
                Intent intent =
                        new Intent(MensajePreEncuesta1.this, Bienvenida.class);


                //Iniciamos la nueva actividad
                startActivity(intent);
            }
        });



    }





}