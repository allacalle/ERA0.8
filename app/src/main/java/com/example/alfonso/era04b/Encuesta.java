package com.example.alfonso.era04b;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
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

        FormulasSQLiteHelper usdbh =
                new FormulasSQLiteHelper(this, "DbEra", null, 1);
        final SQLiteDatabase db = usdbh.getWritableDatabase();
        final List<RadioGroup> allRgs = new ArrayList<RadioGroup>();



        //Hacer una consulta para coger todas las formulas de la base de datos.
        Cursor abreviatura = db.rawQuery(" SELECT  Abreviatura FROM Formulas", null);
        final LinearLayout lm = (LinearLayout) findViewById(R.id.LytContenedor);

        //Contamos el numero de formulas
        int numeroFormulas;
        numeroFormulas = abreviatura.getCount();
        //Movemos el cursor a la primera.
        abreviatura.moveToFirst();

        //Declaramos una variable j para asignar identificadores unicos a los botones que iremos creando.
        int j = 0;

        for(int i=0;i< numeroFormulas; i++)
        {
            //Creamos un linear layout auxiliar donde iremos introduciendo los elementos que queremos mostrar
            LinearLayout auxTexto = new LinearLayout(this);
            //El nombre de la formula
            TextView text1 = new TextView(this);
            text1.setText(abreviatura.getString(0));
            abreviatura.moveToNext();
            auxTexto.addView(text1);
            //Otro Linear Layout para los botones
            LinearLayout auxBotones = new LinearLayout(this);

            //Los botones de alta,media,baja
            RadioGroup urgencia = new RadioGroup(this);
            urgencia.setOrientation(LinearLayout.HORIZONTAL);
            RadioButton Alta = new RadioButton(this);
            Alta.setText("Alta");
            //Asignamos color e id a la Alta
            Alta.setBackgroundColor(Color.parseColor("#FF8A80"));
            Alta.setId(j);
            RadioButton Media  = new RadioButton(this);
            Media.setText("Media");
            //Asignamos color e id a la Media
            Media.setBackgroundColor(Color.parseColor("#FFF59D"));
            Media.setId(j+1);
            RadioButton Baja = new RadioButton(this);
            Baja.setText("Baja");
            //Asignamos color e id a la Baja
            Baja.setBackgroundColor(Color.parseColor("#CCFF90"));
            Baja.setId(j + 2);
            urgencia.addView(Alta);
            urgencia.addView(Media);
            urgencia.addView(Baja);
            auxBotones.addView(urgencia);
            lm.addView(auxTexto);
            lm.addView(auxBotones);
            allRgs.add(urgencia);
            j = j +3;
        }

        //Creamos un vector de String con los valores Alto,Medio,Bajo cada posicion del vector coincidira con
        // la id de los botones creados, asi sera muy facil tomar su valor.

        final String vectorPrioridad [] = new String[numeroFormulas*3] ;

        j = 0;
        final TextView resultado = new TextView(this);
        lm.addView(resultado);



        for (int i =0; i < numeroFormulas ; i++)
        {
            vectorPrioridad[j] = "Alta";
            vectorPrioridad[j+1] = "Media";
            vectorPrioridad[j+2] = "Baja";
            j = j+3;


        }


        final Button btnConfiguracion = new Button(this);
        btnConfiguracion.setText("Aceptar Resultados");
        lm.addView(btnConfiguracion);
        //final TextView resultado = new TextView(this);
        //cadena = "";



        btnConfiguracion.setOnClickListener(new View.OnClickListener() {
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
                        resultado.setText(cadena);
                    }
                }


                if (!noseleccionado) {

                    for (int i = 0; i < allRgs.size(); i++) {
                        cadena = cadena + "Formula" + (i + 1) + ": Valor:" + vectorPrioridad[allRgs.get(i).getCheckedRadioButtonId()] + "\n";
                        //Ahora mismo se coge asi porque estan en orden, si ese orden se cambiase habra que crear un vector con los valores de la id en cada posicion
                        //db.execSQL("INSERT INTO Prioridad (IdPrioridad,IdFormula,Tipo)  VALUES ('" + (i + 1) + "','" + (i + 1) + "','" + vectorPrioridad[allRgs.get(i).getCheckedRadioButtonId()] + "');");
                        cadenaResultado = cadenaResultado +  vectorPrioridad[allRgs.get(i).getCheckedRadioButtonId()];
                        if (i != (allRgs.size() -1) )
                            cadenaResultado = cadenaResultado + "," ;

                        //resultado.setText(cadenaResultado);


                        //Debemos meter todos estos valores seleccionados en una cadena de texto que analizaremos en ResultadosEncuesta
                        //resultado.setText(cadena);
                        Intent intent = new Intent(Encuesta.this, MensajePostEncuesta.class);

                        //Creamos la información a pasar entre actividades
                        Bundle b = new Bundle();
                        b.putString("NOMBRE", (String) cadenaResultado);

                        //Añadimos la información al intent
                        intent.putExtras(b);

                        //Iniciamos la nueva actividad
                        startActivity(intent);

                    }
                }


            }
        });

        //resultado.setText(cadena);


    }

}


