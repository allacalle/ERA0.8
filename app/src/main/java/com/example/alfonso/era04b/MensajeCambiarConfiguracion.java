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


public class MensajeCambiarConfiguracion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensaje_cambiar_configuracion);

        //En esta pantalla que ya viene despues de haber elegido un si o un no en alert

        //Mostramos al usuario un mensaje diciendo que si pulsar en el boton continuar volverá a realizar la encuesta

        //Si el usuario decide que quiere cambiarla cargamos la pantalla de cambiar configuración

        Button BtnCambiar = (Button) findViewById(R.id.BtnCambiar) ;
        Button BtnRegresar = (Button) findViewById(R.id.BtnRegresar) ;


        assert BtnCambiar != null;
        BtnCambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creamos el Intent
                Intent intent =
                        new Intent(MensajeCambiarConfiguracion.this, CambiarConfiguracion.class);


                //Iniciamos la nueva actividad
                startActivity(intent);

            }
        });

        assert BtnRegresar != null;

        BtnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Creamos el Intent
                Intent intent =
                        new Intent(MensajeCambiarConfiguracion.this, Inicio.class);


                //Iniciamos la nueva actividad
                startActivity(intent);
            }
        });



    }
}
