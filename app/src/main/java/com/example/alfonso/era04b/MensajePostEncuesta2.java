package com.example.alfonso.era04b;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MensajePostEncuesta2 extends AppCompatActivity {

    private Button btnInicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensaje_post_encuesta2);

        btnInicio = (Button)findViewById(R.id.BtnInicio);



        //Implementamos el evento click del botón
        btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creamos el Intent
                Intent intent =
                        new Intent(MensajePostEncuesta2.this, Inicio.class);


                //Iniciamos la nueva actividad
                startActivity(intent);
            }
        });


    }
}
