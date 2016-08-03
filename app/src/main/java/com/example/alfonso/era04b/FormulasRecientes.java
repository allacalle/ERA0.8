package com.example.alfonso.era04b;

import android.app.ActionBar;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
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
 * Created by Alfonso on 18/03/2016.
 * Ultima modificación: 03/08/2016

 */

public class FormulasRecientes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulas_recientes);
        //Boton de atras
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Cargamos el layout base de nuestro xml
        LinearLayout layoutBase = (LinearLayout) findViewById(R.id.LytContenedor);
        //Cargamos el boton Inicio de nuestro xml.
        Button BtnInicio = (Button) findViewById(R.id.BtnInicio) ;

        assert BtnInicio != null;

        BtnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creamos el Intent
                Intent intent =
                        new Intent(FormulasRecientes.this, Inicio.class);


                //Iniciamos la nueva actividad
                startActivity(intent);

            }
        });



        //Abro la base de datos.
        FormulasSQLiteHelper usdbh =
                new FormulasSQLiteHelper(this, "DbEra", null, 1);

        SQLiteDatabase db = usdbh.getReadableDatabase();

        //creamos un cursos, en el string(0) tenemos el parametro, en el string(1) tenemos el tipo de formula
        Cursor Contador = db.rawQuery("SELECT COUNT(*) FROM Recientes", null);
        Contador.moveToFirst();
        int numeroCampos = Contador.getInt(0);
        Contador.close();
        Cursor c = db.rawQuery("SELECT IdFormula,Fecha FROM Recientes ORDER BY Fecha DESC ", null);
        c.moveToFirst();

        String cadena = "";

        //Solo quiero mostrar las 5 primeras como mucho.
        if (numeroCampos > 7)
            numeroCampos = 7;

        for (int i = 0; i < numeroCampos; i++) {

            //Creamos un objeto drawable para dar formato a los elementos auxiliares.
            GradientDrawable drawable = new GradientDrawable();
            drawable.setShape(GradientDrawable.RECTANGLE);
            drawable.setStroke(5, Color.parseColor("#BDBDBD"));


            //Cojo cada una de las ids de las formulas
            final int idFormula = c.getInt(0);

            //Busco en la tabla Formulas el nombre abreviado de las IDS y se lo asigno al boton.

            //Creamos un cursor para las formulas
            Cursor cursorFormula = db.rawQuery("SELECT Abreviatura FROM Formulas WHERE IdFormula = '" + idFormula + "'  ", null);
            cursorFormula.moveToFirst();
            String abreviatura = cursorFormula.getString(0);
            cursorFormula.close();


            //Busco en la tabla Prioridad la prioridad asignada a esas IDS y dependiendo de la que sea asigno un color a ese boton.
            Cursor cursorPrioridad = db.rawQuery("SELECT Tipo FROM Prioridad WHERE IdFormula = '" + idFormula + "'  ", null);
            cursorPrioridad.moveToFirst();
            String prioridad = cursorPrioridad.getString(0);
            cursorPrioridad.close();

            //Aplico al drawable el color de la prioridad actual

            switch (prioridad) {
                case "Alta":
                    drawable.setColor(Color.parseColor("#FF8A80"));
                    break;
                case "Media":
                    drawable.setColor(Color.parseColor("#FFF59D"));
                    break;
                case "Baja":
                    drawable.setColor(Color.parseColor("#CCFF90"));
                    break;
            }


            //Creo un boton
            final Button btnFormula = new Button(this);

            //Le asigno el texto
            btnFormula.setText(abreviatura);
            btnFormula.setId(idFormula);

            //Le aplico el layout
            btnFormula.setBackgroundDrawable(drawable);

            //Meto el boton en el layout
            layoutBase.addView(btnFormula);


            //Definimos la funcion del boton
            btnFormula.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Creamos el Intent
                    Intent intent =
                            new Intent(FormulasRecientes.this, CalcularFormula.class);

                    //Creamos la información a pasar entre actividades
                    Bundle b = new Bundle();
                    String cadenaId= "";
                    cadenaId = cadenaId + btnFormula.getId() ;
                    b.putString("IdFormula", (String) cadenaId);

                    //Añadimos la información al intent
                    intent.putExtras(b);

                    //Iniciamos la nueva actividad
                    startActivity(intent);

                }
            });





            //Paso al valor siguiente del cursor
            c.moveToNext();
        }

        c.close();
        db.close();




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
