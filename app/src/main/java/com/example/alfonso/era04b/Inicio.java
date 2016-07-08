package com.example.alfonso.era04b;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Inicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        //Buscamos nuestros botones de Alto,Medio, Bajo

        Button BtnRecientes = (Button) findViewById(R.id.BtnRecientes) ;
        ImageButton BtnAlta = (ImageButton) findViewById(R.id.BtnAlta);
        ImageButton BtnMedia = (ImageButton) findViewById(R.id.BtnMedia);
        ImageButton BtnBaja = (ImageButton) findViewById(R.id.BtnBaja);
        Button BtnAyuda = (Button) findViewById(R.id.BtnAyuda) ;



        setTitle("ERA");


        //Al pulsar uno de estos botones cargara la actividad listado_formulas con el valor de cada boton. (Alto,Medio,Bajo)
        assert BtnAlta != null;
        BtnAlta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creamos el Intent
                Intent intent =
                        new Intent(Inicio.this, FormulasPrioridad.class);

                //Creamos la información a pasar entre actividades
                Bundle b = new Bundle();
                b.putString("Prioridad", "Alta");

                //Añadimos la información al intent
                intent.putExtras(b);

                //Iniciamos la nueva actividad
                startActivity(intent);

            }
        });

        assert BtnMedia != null;
        BtnMedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creamos el Intent
                Intent intent =
                        new Intent(Inicio.this, FormulasPrioridad.class);

                //Creamos la información a pasar entre actividades
                Bundle b = new Bundle();
                b.putString("Prioridad", "Media");

                //Añadimos la información al intent
                intent.putExtras(b);

                //Iniciamos la nueva actividad
                startActivity(intent);

            }
        });

        assert BtnBaja != null;
        BtnBaja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creamos el Intent
                Intent intent =
                        new Intent(Inicio.this, FormulasPrioridad.class);

                //Creamos la información a pasar entre actividades
                Bundle b = new Bundle();
                b.putString("Prioridad", "Baja");

                //Añadimos la información al intent
                intent.putExtras(b);

                //Iniciamos la nueva actividad
                startActivity(intent);

            }
        });

        assert BtnRecientes != null;
        BtnRecientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creamos el Intent
                Intent intent =
                        new Intent(Inicio.this, FormulasRecientes.class);


                //Iniciamos la nueva actividad
                startActivity(intent);

            }
        });

        assert BtnAyuda != null;

        BtnAyuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Creamos el Intent
                Intent intent =
                        new Intent(Inicio.this, AyudaGeneral.class);


                //Iniciamos la nueva actividad
                startActivity(intent);
            }
        });





    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.inicio, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.error:
                //metodoAdd()
                //info.setText("Se presionó Añadir");
                return true;
            case R.id.busqueda:
                //metodoSearch()
                //info.setText("Se presionó Buscar");
                return true;
            case R.id.configuracion:
                Intent intent =
                        new Intent(Inicio.this, MensajeCambiarConfiguracion.class);
                //Iniciamos la nueva actividad
                startActivity(intent);
                //metodoEdit()
                //info.setText("Se presionó Editar");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }










}

