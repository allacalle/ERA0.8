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
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
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
        //Activo el boton atras
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*Esta pantalla debe ser muy parecida a la pantalla de encuesta aqui se debe mostrar

        1. Mostramos todas las formulas de la aplicación
        2. Mostramos a su lado los botones de alta, media, baja
        3. Rellenamos estos botones con la prioridad que tiene ahora la formula
        4. Creamos 2 botones abajo:
            4.1: Boton cambiar configuración: Boton que al pulsarlo cambia la configuración de las formulas.
            4.2: Boton Volver: Nos regresa a la pantalla de inicio sin hacer los cambios.

        */

        //Abrimos la base de datos en modo escritura porque debemos actualizar la tabla Prioridad.

        FormulasSQLiteHelper usdbh =
                new FormulasSQLiteHelper(this, "DbEra", null, 1);
        final SQLiteDatabase db = usdbh.getWritableDatabase();
        final List<RadioGroup> allRgs = new ArrayList<RadioGroup>();



        //Hacer una consulta para coger todas las formulas de la base de datos ademas del tipo de Prioridad que tiene actualmente.
        //Para lograr esto hacemos un JOIN uniendo ambas tablas por el campo IdFormula
        Cursor cursorAbreviatura = db.rawQuery(" SELECT F.Abreviatura, P.Tipo  FROM Formulas F,Prioridad P WHERE F.IdFormula = P.IdFormula ", null);


        //Cargamos el layout del xml de nuestra actividad , en este layout agregaremos dinamicamente los elementos que necesitemos.
        final LinearLayout layoutBase = (LinearLayout) findViewById(R.id.LytContenedor);

        //Creamos dos layout auxiliares que llevaran un formato diferentes para dar mejor apariencia a la página. 
        LinearLayout.LayoutParams layoutFormato = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);


        LinearLayout.LayoutParams layoutFormato2 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT, 2.5f);


        //creamos una alerta para avisar al usuario si se ha dejado algún campo sin rellenar.
        
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();

        alertDialog.setButton("Continuar..", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // here you can add functions
            }
        });


        //Contamos el numero de formulas
        int numeroFormulas;
        numeroFormulas = cursorAbreviatura.getCount();
        //Movemos el cursor a la primera.
        cursorAbreviatura.moveToFirst();

        //Declaramos una variable j para asignar identificadores unicos a los botones que iremos creando.
        int j = 0;

          /*
        Las posiciones j indicaran que se ha seleccionado la prioridad Alta.
        Las posiciones j+1 indicarán que se ha seleccionado la prioridad Media
        Las posiciones j+2 indicarán que se ha seleccionado la prioridad Baja.

         */

        //Vamos a crear primero la cabecera, para ello creamos un layout 
        LinearLayout layoutCabecera = new LinearLayout(this);
        layoutCabecera.setOrientation(LinearLayout.HORIZONTAL);
        layoutCabecera.setBackgroundResource(R.drawable.customborder);


        //Creamos un TextView para mostrar la palabra Fórmulas en la parte superior.
        TextView txtCabecera1 = new TextView(this);
        txtCabecera1.setText("Fórmulas");
        txtCabecera1.setLayoutParams(layoutFormato);


        //Creamos un TextView para mostrar la palabra Prioridad en la parte superior.
        TextView txtCabecera2 = new TextView(this);
        txtCabecera2.setText("Prioridad");
        txtCabecera2.setLayoutParams(layoutFormato2);


        //Ponemos las cabeceras en negrita para que se vean mejor.
        txtCabecera1.setTypeface(null, Typeface.BOLD);
        txtCabecera2.setTypeface(null, Typeface.BOLD);

        

        //Agregamos los TextView al layout de la cabecera
        layoutCabecera.addView(txtCabecera1);
        layoutCabecera.addView(txtCabecera2);
        //layoutCabecera.addView(textPrioridad);

        
        //agregamos la cabecera al layout base de la página.    
        layoutBase.addView(layoutCabecera);

  
        
        for(int i=0;i< numeroFormulas; i++)
        {
            //Creamos una cadena para indicar que prioridad tiene la formula en esta posición
            String TipoPrioridad = "";
            //Creamos un linear layout auxiliar donde iremos introduciendo los elementos que queremos mostrar
            LinearLayout layoutFormula = new LinearLayout(this);
            layoutFormula.setOrientation(LinearLayout.HORIZONTAL);
            layoutFormula.setBackgroundResource(R.drawable.customborder2);
            //Creamos TexView donde iremos poniendo el nombre de cada una de las fórmulas accediendo al cursor.
            TextView txtNombreFormula = new TextView(this);
            txtNombreFormula.setText(cursorAbreviatura.getString(0));
            txtNombreFormula.setLayoutParams(layoutFormato);

            //Obtenemos la prioridad para esa formula y la almacenamos en la variable TipoPrioridad.
            TipoPrioridad = cursorAbreviatura.getString(1);

            //Movemos el cursor al siguiente elemento para la siguiente iteracción.
            cursorAbreviatura.moveToNext();
            //Ponemos el nombre de la formula en negrita.
            txtNombreFormula.setTypeface(null, Typeface.BOLD);
            layoutFormula.addView(txtNombreFormula);
            //Otro Linear Layout para los botones
            //LinearLayout auxBotones = new LinearLayout(this);

            //Los botones de alta,media,baja
            RadioGroup rgpPrioridad = new RadioGroup(this);
            rgpPrioridad.setOrientation(LinearLayout.HORIZONTAL);
            RadioButton rbtnAlta = new RadioButton(this);
            //RbtnAlta.setText("Alta");
            //Asignamos color e id a la Alta
            rbtnAlta.setBackgroundColor(Color.parseColor("#FF8A80"));
            //Asignamos la posicion j a la prioridad Alta.
            rbtnAlta.setId(j);
            RadioButton rbtnMedia  = new RadioButton(this);
            //Media.setText("Media");
            //Asignamos color e id a la Media
            rbtnMedia.setBackgroundColor(Color.parseColor("#FFF59D"));
            //Asignamos la posicion j+1 a la prioridad media
            rbtnMedia.setId(j+1);
            RadioButton rbtnBaja = new RadioButton(this);
            //Baja.setText("Baja");
            //Asignamos color e id a la Baja
            rbtnBaja.setBackgroundColor(Color.parseColor("#CCFF90"));
            //Asignamos la posicion j+2 a la prioridad baja
            rbtnBaja.setId(j + 2);

            //Marcamos los botones con el valor anterior según el tipo de prioridad que tenía.
            switch(TipoPrioridad)
            {
                case "Alta":
                    rbtnAlta.setChecked(true);
                    break;
                case "Media":
                    rbtnMedia.setChecked(true);
                    break;
                case "Baja":
                    rbtnBaja.setChecked(true);
                    break;
            }


            //Agregamos los radio button al radio group
            rgpPrioridad.addView(rbtnAlta);
            rgpPrioridad.addView(rbtnMedia);
            rgpPrioridad.addView(rbtnBaja);
            //Agregamos el radio group al layout de las formulas.
            layoutFormula.addView(rgpPrioridad);
            //Agregamos el layout de la formula al  layout base
            layoutBase.addView(layoutFormula);
            allRgs.add(rgpPrioridad);
            //Incrementamos la variable j para poder seguir asignando id a las prioridades.
            j = j +3;
        }


        //Creamos un vector de String con los valores Alto,Medio,Bajo cada posicion del vector coincidira con
        // la id de los botones creados, asi sera muy facil tomar su valor.

        final String vectorPrioridad [] = new String[numeroFormulas*3] ;

        j = 0;


        //rellenamos el vector de Prioridad con los valores Alta, Media o Baja dependiendo de la posición.
        for (int i =0; i < numeroFormulas ; i++)
        {
            vectorPrioridad[j] = "Alta";
            vectorPrioridad[j+1] = "Media";
            vectorPrioridad[j+2] = "Baja";
            j = j+3;


        }


        //creamos un boton para aceptar los resultados de la encuesta.
        final Button btnAceptar = new Button(this);
        btnAceptar.setText("Aceptar Resultados");
        btnAceptar.setBackgroundResource(R.drawable.seleccion);
        btnAceptar.setTextColor(Color.parseColor("#FFFFFF"));
        //Agregamos el btnAceptar al layoutBase
        layoutBase.addView(btnAceptar);

        /*
        //creamos un botón limpiar para reiniciar la encuesta dejando todos los campos a cero.
        final Button btnLimpiar = new Button(this);
        btnLimpiar.setText("Reiniciar encuesta");
        btnLimpiar.setBackgroundResource(R.drawable.seleccion);
        btnLimpiar.setTextColor(Color.parseColor("#FFFFFF"));
        layoutBase.addView(btnLimpiar);
        */


        final Button btnInicio = new Button(this);
        btnInicio.setText("Volver a Inicio");
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

        /*
        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);

            }
        });
        */


        btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CambiarConfiguracion.this, Inicio.class);
                startActivity(intent);

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
