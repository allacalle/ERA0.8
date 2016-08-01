package com.example.alfonso.era04b;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.alfonso.era04b.Clases.FormulasSQLiteHelper;

/**
 * Created by Alfonso on 08/02/2016.
 * Ultima modificación: 20/07/2016

 */


public class MensajePostEncuesta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensaje_post_encuesta);
        //Activo el boton atras
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Recuperamos la información pasada en el intent
        Bundle bundle = this.getIntent().getExtras();
        //Construimos el mensaje a mostrar
        final String valorRecibido = bundle.getString("Resultado");
        //creamos el layout dinamico como pros!
        final LinearLayout lm = (LinearLayout) findViewById(R.id.LytContenedor);
        //TextView texto = new TextView(this);
        //texto.setText(valorRecibido);
        //lm.addView(texto);

        //Se crea un parametro auxiliar para cuestiones de diseño con el TextView y el EditText
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);

        //Creamos otro parametro para el formato del texto de las columnas

        LinearLayout.LayoutParams param2 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT, 2.5f);

        //Vamos a crear primero la cabecera
        LinearLayout auxCabecera = new LinearLayout(this);
        auxCabecera.setOrientation(LinearLayout.HORIZONTAL);
        auxCabecera.setBackgroundResource(R.drawable.customborder);


        TextView textCabecera1 = new TextView(this);
        textCabecera1.setText("Formulas");
        textCabecera1.setLayoutParams(param);

        TextView textCabecera2 = new TextView(this);
        textCabecera2.setText("Prioridad");
        textCabecera2.setLayoutParams(param2);

        //TextView textPrioridad = new TextView(this);
        //textPrioridad.setText("Prioridades");

        //textPrioridad.setTypeface(null, Typeface.BOLD);
        textCabecera1.setTypeface(null, Typeface.BOLD);
        textCabecera2.setTypeface(null, Typeface.BOLD);



        auxCabecera.addView(textCabecera1);
        auxCabecera.addView(textCabecera2);
        //auxCabecera.addView(textPrioridad);


        lm.addView(auxCabecera);



        //Tenemos que abrir la base de datos
        FormulasSQLiteHelper usdbh =
                new FormulasSQLiteHelper(this, "DbEra", null, 1);
        final SQLiteDatabase db = usdbh.getWritableDatabase();

        //Si existen datos en la tabla prioridad los borramos todos
        //db.execSQL("Delete  FROM Prioridad Where IdPrioridad >= 0");

        //Hacer una consulta de las formulas exactamente igual que en la pantalla de encuesta
        final Cursor identificadores = db.rawQuery(" SELECT  IdFormula,Abreviatura FROM Formulas", null);
        identificadores.moveToFirst();
        final int numeroFormulas;
        numeroFormulas = identificadores.getCount();


        //Meter la cadena de la encuesta en un vector utilizando la funcion split ','
        final String[] prioridad =  valorRecibido.split(",");

        /*
        String cadenaAuxiliar="";
        //Vamos a mostrar el vector para ver si coincide
        for(int i =0; i< numeroFormulas; i++)
        {
            cadenaAuxiliar = cadenaAuxiliar + prioridad + ", " ;
        }
        TextView texto2 = new TextView(this);
        texto2.setText(valorRecibido);
        lm.addView(texto2);
        */

        //Mostramos al usuario los valores que ha elegido para cada formula



        String cadenaAuxiliar="";
        for(int i =0; i< numeroFormulas; i++)
        {
            //Creamos un linear layout auxiliar donde iremos introduciendo los elementos que queremos mostrar
            LinearLayout auxTexto = new LinearLayout(this);
            auxTexto.setOrientation(LinearLayout.HORIZONTAL);
            auxTexto.setBackgroundResource(R.drawable.customborder2);
            //El nombre de la formula
            TextView TxtAbreviatura = new TextView(this);
            TxtAbreviatura.setText(identificadores.getString(1));
            TxtAbreviatura.setLayoutParams(param);
            identificadores.moveToNext();
            TxtAbreviatura.setTypeface(null, Typeface.BOLD);
            auxTexto.addView(TxtAbreviatura);
            TextView TxtPrioridad = new TextView(this);
            TxtPrioridad.setText(prioridad[i]);
            TxtPrioridad.setLayoutParams(param2);
            TxtPrioridad.setTypeface(null, Typeface.BOLD);

            //Asignamos un color a cada prioridad



            auxTexto.addView(TxtPrioridad);

            lm.addView(auxTexto);


            /*
            cadenaAuxiliar = identificadores.getString(1) + " con Prioridad: " +prioridad[i] ;
            TextView textoFormulas = new TextView(this);
            textoFormulas.setText(cadenaAuxiliar);
            lm.addView(textoFormulas);
            identificadores.moveToNext();
            */
        }



        //Creamos dos botones uno para volver a la pantalla anterior y otro para aceptar las formulas
        Button btnRegresar = new Button(this);
        btnRegresar.setText("Regresar a Encuesta");
        btnRegresar.setBackgroundResource(R.drawable.seleccion);
        btnRegresar.setTextColor(Color.parseColor("#FFFFFF"));

        lm.addView(btnRegresar);

        Button btnAceptar = new Button(this);
        btnAceptar.setText("Aceptar Encuesta");
        btnAceptar.setBackgroundResource(R.drawable.seleccion);
        btnAceptar.setTextColor(Color.parseColor("#FFFFFF"));

        lm.addView(btnAceptar);


        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creamos el Intent
                // intent =
                      //  new Intent(MensajePostEncuesta.this, Encuesta.class);

                //Creamos la información a pasar entre actividades
                //Bundle b = new Bundle();
                //b.putString("NOMBRE", (String) boton.getText());

                //Añadimos la información al intent
                //intent.putExtras(b);

                //Iniciamos la nueva actividad
               // startActivity(intent);

                finish();

            }
        });

        //Si pulsamos el boton de aceptar los resultados
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creamos el Intent
                Intent intent =
                        new Intent(MensajePostEncuesta.this, MensajePostEncuesta2.class);

                db.execSQL("Delete  FROM Prioridad Where 1 ");


                identificadores.moveToFirst();
                for(int i=0; i < numeroFormulas; i++)
                {
                    db.execSQL("INSERT INTO Prioridad (IdPrioridad,IdFormula,Tipo) VALUES('" + i + "','" + identificadores.getInt(0) + "','" + prioridad[i] + "')");
                    identificadores.moveToNext();
                }

                //Iniciamos la nueva actividad
                startActivity(intent);

                //En la tabla Prioridad Metemos la id de la formula y su valor de prioridad.

            }
        });


    }


    //Botron atrasssssss
    @Override
    public boolean onOptionsItemSelected (MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                Log.i("ActionBar", "Atrás");
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}