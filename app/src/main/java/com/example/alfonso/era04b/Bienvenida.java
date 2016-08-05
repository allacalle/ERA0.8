package com.example.alfonso.era04b;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Alfonso on 18/03/2016.
 * Ultima modificación: 04/08/2016

 */

public class Bienvenida extends AppCompatActivity {

    //Creamos los dos botones que aparecerán en la página de bienvenida.

    private Button btnSiguiente;
    private Button btnOmitir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenida);

        //Obtenemos una referencia a los controles de la interfaz
        btnSiguiente = (Button)findViewById(R.id.BtnSiguiente);
        btnOmitir = (Button) findViewById(R.id.BtnOmitir);


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

        //Implementamos el evento click del botón
        btnOmitir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creamos el Intent
                Intent intent =
                        new Intent(Bienvenida.this, Encuesta.class);


                //Iniciamos la nueva actividad
                startActivity(intent);
            }
        });


    }

    @Override
    public void onBackPressed() {

        return;
    }

}
