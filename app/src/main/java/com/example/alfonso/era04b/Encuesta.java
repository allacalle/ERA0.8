package com.example.alfonso.era04b;

/**
 * Created by Alfonso on 14/05/2016.
 * Ultima modificación: 20/07/2016

 */

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.example.alfonso.era04b.Clases.FormulasSQLiteHelper;
import java.util.ArrayList;
import java.util.List;

public class Encuesta extends AppCompatActivity {

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
            4.2: Boton Volver: Nos regresa a la pantalla de inicio sin hacer los cambios.

        */

        //Abrimos la base de datos en modo escritura ya que cambiaremos la tabla Prioridad.
        FormulasSQLiteHelper usdbh =
                new FormulasSQLiteHelper(this, "DbEra", null, 1);
        final SQLiteDatabase db = usdbh.getWritableDatabase();
        final List<RadioGroup> allRgs = new ArrayList<RadioGroup>();



        //Hacer una consulta para coger todas las formulas de la base de datos.
        Cursor abreviatura = db.rawQuery(" SELECT  Abreviatura FROM Formulas", null);
        final LinearLayout layoutBase = (LinearLayout) findViewById(R.id.LytContenedor);

        //Se crea un parametro auxiliar para cuestiones de diseño con el TextView y el EditText
        LinearLayout.LayoutParams layoutFormato = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);

        //Creamos otro parametro para el formato del texto de las columnas

        LinearLayout.LayoutParams layoutFormato2 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT, 2.5f);


        //Creamos un alertDialog para mostrar los mensajes de Error al usuario.
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
        LinearLayout layoutCabecera = new LinearLayout(this);
        layoutCabecera.setOrientation(LinearLayout.HORIZONTAL);
        layoutCabecera.setBackgroundResource(R.drawable.customborder);


        TextView txtCabecera1 = new TextView(this);
        txtCabecera1.setText("Formulas");
        txtCabecera1.setLayoutParams(layoutFormato);

        TextView txtCabecera2 = new TextView(this);
        txtCabecera2.setText("Prioridad");
        txtCabecera2.setLayoutParams(layoutFormato2);

        //TextView textPrioridad = new TextView(this);
        //textPrioridad.setText("Prioridades");

        //textPrioridad.setTypeface(null, Typeface.BOLD);
        txtCabecera1.setTypeface(null, Typeface.BOLD);
        txtCabecera2.setTypeface(null, Typeface.BOLD);



        layoutCabecera.addView(txtCabecera1);
        layoutCabecera.addView(txtCabecera2);
        //layoutCabecera.addView(textPrioridad);


        layoutBase.addView(layoutCabecera);






        for(int i=0;i< numeroFormulas; i++)
        {
            //Creamos un linear layout auxiliar donde iremos introduciendo los elementos que queremos mostrar
            LinearLayout layoutFormula = new LinearLayout(this);
            layoutFormula.setOrientation(LinearLayout.HORIZONTAL);
            layoutFormula.setBackgroundResource(R.drawable.customborder2);
            //El nombre de la formula
            TextView txtNombreFormula = new TextView(this);
            txtNombreFormula.setText(abreviatura.getString(0));
            txtNombreFormula.setLayoutParams(layoutFormato);
            abreviatura.moveToNext();
            txtNombreFormula.setTypeface(null, Typeface.BOLD);
            layoutFormula.addView(txtNombreFormula);
            //Otro Linear Layout para los botones
            LinearLayout auxBotones = new LinearLayout(this);

            //Los botones de alta,media,baja
            RadioGroup rgpPrioridad = new RadioGroup(this);
            rgpPrioridad.setOrientation(LinearLayout.HORIZONTAL);
            RadioButton RbtnAlta = new RadioButton(this);
            //Alta.setText("Alta");
            //Asignamos color e id a la Alta
            RbtnAlta.setBackgroundColor(Color.parseColor("#FF8A80"));
            RbtnAlta.setId(j);
            RadioButton RbtnMedia  = new RadioButton(this);
            //Media.setText("Media");
            //Asignamos color e id a la Media
            RbtnMedia.setBackgroundColor(Color.parseColor("#FFF59D"));
            RbtnMedia.setId(j+1);
            RadioButton RbtnBaja = new RadioButton(this);
            //Baja.setText("Baja");
            //Asignamos color e id a la Baja
            RbtnBaja.setBackgroundColor(Color.parseColor("#CCFF90"));
            RbtnBaja.setId(j + 2);
            rgpPrioridad.addView(RbtnAlta);
            rgpPrioridad.addView(RbtnMedia);
            rgpPrioridad.addView(RbtnBaja);
            //auxBotones.addView(rgpPrioridad);
            layoutFormula.addView(rgpPrioridad);
            layoutBase.addView(layoutFormula);
            //layoutBase.addView(auxBotones);
            allRgs.add(rgpPrioridad);
            j = j +3;
        }

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


        //Creamos un boton aceptar, para aceptar los resultados de la encuesta.
        final Button btnAceptar = new Button(this);
        btnAceptar.setText("Aceptar Resultados");
        btnAceptar.setBackgroundResource(R.drawable.seleccion);
        btnAceptar.setTextColor(Color.parseColor("#FFFFFF"));
        layoutBase.addView(btnAceptar);

        //Creamos un botón limpiar para reiniciar la encuesta y borrar los campos seleccionados.
        final Button btnLimpiar = new Button(this);
        btnLimpiar.setText("Reiniciar encuesta");
        btnLimpiar.setBackgroundResource(R.drawable.seleccion);
        btnLimpiar.setTextColor(Color.parseColor("#FFFFFF"));
        layoutBase.addView(btnLimpiar);

        //Creamos un boton inicio para volver al principio de la explicación.
        final Button btnInicio = new Button(this);
        btnInicio.setText("Explicamelo de nuevo");
        btnInicio.setBackgroundResource(R.drawable.seleccion);
        btnInicio.setTextColor(Color.parseColor("#FFFFFF"));
        layoutBase.addView(btnInicio);
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
                        Intent intent = new Intent(Encuesta.this, MensajePostEncuesta.class);

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
                Intent intent = new Intent(Encuesta.this, Bienvenida.class);
                startActivity(intent);

            }
        });




        //resultado.setText(cadena);






    }
}
