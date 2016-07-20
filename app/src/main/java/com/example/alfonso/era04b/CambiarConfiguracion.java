package com.example.alfonso.era04b;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.drm.DrmStore;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.example.alfonso.era04b.Clases.FormulasSQLiteHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alfonso on 20/03/2016.
 * Ultima modificación: 20/07/2016

 */

public class CambiarConfiguracion extends AppCompatActivity {

    private String cadena = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encuesta);

        /*Esta pantalla debe ser muy parecida a la pantalla de encuesta aqui se debe mostrar

        1. Mostramos todas las formulas de la aplicación
        2. Mostramos a su lado los botones de alta, media, baja
        3. Rellenamos estos botones con la prioridad que tiene ahora la formula
        4. Creamos 2 botones abajo:
            4.1: Boton cambiar configuración: Boton que al pulsarlo cambia la configuración de las formulas.
                4.1.1: Nos lleva a una pantalla donde se nos dice que la configuración ha sido cambiada.
            4.2: Boton Volver: Nos regresa a la pantalla de inicio sin hacer los cambios.

        */

        FormulasSQLiteHelper usdbh =
                new FormulasSQLiteHelper(this, "DbEra", null, 1);
        final SQLiteDatabase db = usdbh.getWritableDatabase();
        final List<RadioGroup> allRgs = new ArrayList<RadioGroup>();



        //Hacer una consulta para coger todas las formulas de la base de datos.
        //Cursor abreviatura = db.rawQuery(" SELECT  Abreviatura FROM Formulas", null);
        Cursor abreviatura = db.rawQuery(" SELECT F.Abreviatura, P.Tipo  FROM Formulas F,Prioridad P WHERE F.IdFormula = P.IdFormula ", null);



        final LinearLayout lm = (LinearLayout) findViewById(R.id.LytContenedor);

        //Se crea un parametro auxiliar para cuestiones de diseño con el TextView y el EditText
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);

        //Creamos otro parametro para el formato del texto de las columnas

        LinearLayout.LayoutParams param2 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT, 2.5f);


        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();

        alertDialog.setButton("Continuar..", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // here you can add functions
            }
        });


        //Contamos el numero de formulas
        int numeroFormulas;
        numeroFormulas = abreviatura.getCount();
        //Movemos el cursor a la primera.
        abreviatura.moveToFirst();

        //Declaramos una variable j para asignar identificadores unicos a los botones que iremos creando.
        int j = 0;

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

        //queremos que se marquen los botones que fueron seleccionados la ultima vez.!!!

        //Debemos abrir la base de datos y hacer una consulta




        for(int i=0;i< numeroFormulas; i++)
        {
            //Creamos una cadena para indicar que prioridad tiene la formula en esta posición
            String TipoPrioridad = "";

            //Creamos un linear layout auxiliar donde iremos introduciendo los elementos que queremos mostrar
            LinearLayout auxTexto = new LinearLayout(this);
            auxTexto.setOrientation(LinearLayout.HORIZONTAL);
            auxTexto.setBackgroundResource(R.drawable.customborder2);
            //El nombre de la formula
            TextView text1 = new TextView(this);
            text1.setText(abreviatura.getString(0));
            text1.setLayoutParams(param);
            //Capturamos el tipo de prioridad.
            TipoPrioridad = abreviatura.getString(1);

            abreviatura.moveToNext();
            text1.setTypeface(null, Typeface.BOLD);
            auxTexto.addView(text1);
            //Otro Linear Layout para los botones
            LinearLayout auxBotones = new LinearLayout(this);

            //Los botones de alta,media,baja
            RadioGroup urgencia = new RadioGroup(this);
            urgencia.setOrientation(LinearLayout.HORIZONTAL);
            RadioButton Alta = new RadioButton(this);
            //Alta.setText("Alta");
            //Asignamos color e id a la Alta
            Alta.setBackgroundColor(Color.parseColor("#FF8A80"));
            Alta.setId(j);
            RadioButton Media  = new RadioButton(this);
            //Media.setText("Media");
            //Asignamos color e id a la Media
            Media.setBackgroundColor(Color.parseColor("#FFF59D"));
            Media.setId(j+1);
            RadioButton Baja = new RadioButton(this);
            //Baja.setText("Baja");
            //Asignamos color e id a la Baja
            Baja.setBackgroundColor(Color.parseColor("#CCFF90"));
            Baja.setId(j + 2);

            //Rellenamos los botones con el valor anterior segun el tipo de prioridad
            switch(TipoPrioridad)
            {
                case "Alta":
                    Alta.setChecked(true);
                    break;
                case "Media":
                    Media.setChecked(true);
                    break;
                case "Baja":
                    Baja.setChecked(true);
                    break;
            }


            urgencia.addView(Alta);
            urgencia.addView(Media);
            urgencia.addView(Baja);
            //auxBotones.addView(urgencia);
            auxTexto.addView(urgencia);
            lm.addView(auxTexto);
            //lm.addView(auxBotones);
            allRgs.add(urgencia);
            j = j +3;
        }

        //queremos que se marquen los botones que fueron seleccionados la ultima vez.!!!

        //Debemos


        //Creamos un vector de String con los valores Alto,Medio,Bajo cada posicion del vector coincidira con
        // la id de los botones creados, asi sera muy facil tomar su valor.

        final String vectorPrioridad [] = new String[numeroFormulas*3] ;

        j = 0;


        for (int i =0; i < numeroFormulas ; i++)
        {
            vectorPrioridad[j] = "Alta";
            vectorPrioridad[j+1] = "Media";
            vectorPrioridad[j+2] = "Baja";
            j = j+3;


        }





        final Button btnAceptar = new Button(this);
        btnAceptar.setText("Aceptar Resultados");
        btnAceptar.setBackgroundResource(R.drawable.seleccion);
        btnAceptar.setTextColor(Color.parseColor("#FFFFFF"));
        lm.addView(btnAceptar);
        final Button btnLimpiar = new Button(this);
        btnLimpiar.setText("Reiniciar encuesta");
        btnLimpiar.setBackgroundResource(R.drawable.seleccion);
        btnLimpiar.setTextColor(Color.parseColor("#FFFFFF"));
        lm.addView(btnLimpiar);
        final Button btnInicio = new Button(this);
        btnInicio.setText("Volver a Inicio");
        btnInicio.setBackgroundResource(R.drawable.seleccion);
        btnInicio.setTextColor(Color.parseColor("#FFFFFF"));
        lm.addView(btnInicio);
        //final TextView resultado = new TextView(this);
        //cadena = "";



        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //Voy a coger las ids de los botones.
                //Creo un bucle para recorrer la lista de radial group

                boolean noseleccionado = false;
                String cadenaResultado = "";
                int idFormula;

                for (int i = 0; i < allRgs.size(); i++) {
                    if (allRgs.get(i).getCheckedRadioButtonId() == -1) {
                        cadena = " Por favor debe rellenar todas las posibles opciones antes de continuar ";
                        noseleccionado = true;
                        alertDialog.setMessage(cadena);
                        alertDialog.show();
                    }
                }



                if (!noseleccionado) {


                    for (int i = 0; i < allRgs.size(); i++) {
                        cadena = cadena + "Formula" + (i + 1) + ": Valor:" + vectorPrioridad[allRgs.get(i).getCheckedRadioButtonId()] + "\n";
                        //Ahora mismo se coge asi porque estan en orden, si ese orden se cambiase habra que crear un vector con los valores de la id en cada posicion
                        //db.execSQL("INSERT INTO Prioridad (IdPrioridad,IdFormula,Tipo)  VALUES ('" + (i + 1) + "','" + (i + 1) + "','" + vectorPrioridad[allRgs.get(i).getCheckedRadioButtonId()] + "');");
                        cadenaResultado = cadenaResultado + vectorPrioridad[allRgs.get(i).getCheckedRadioButtonId()];
                        if (i != (allRgs.size() -1) )
                            cadenaResultado = cadenaResultado + "," ;

                        //resultado.setText(cadenaResultado);
                    }


                    //Debemos meter todos estos valores seleccionados en una cadena de texto que analizaremos en ResultadosEncuesta
                    //resultado.setText(cadena);
                    Intent intent = new Intent(CambiarConfiguracion.this, MensajePostEncuesta.class);

                    //Creamos la información a pasar entre actividades
                    Bundle b = new Bundle();
                    b.putString("Resultado", cadenaResultado);

                    //Añadimos la información al intent
                    intent.putExtras(b);

                    //Iniciamos la nueva actividad
                    startActivity(intent);

                }


            }
        });

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);

            }
        });


        btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CambiarConfiguracion.this, Inicio.class);
                startActivity(intent);

            }
        });




        //resultado.setText(cadena);






    }
}
