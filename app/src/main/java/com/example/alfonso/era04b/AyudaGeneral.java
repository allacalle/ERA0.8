package com.example.alfonso.era04b;

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
 * Created by Alfonso on 18/03/2016.
 * Ultima modificación: 20/07/2016

 */

public class AyudaGeneral extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda_general);
        //Activo el boton atras
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

      //Accedemos a nuestro layout vacio que rellenaremos con los campos despues
        final LinearLayout lm = (LinearLayout) findViewById(R.id.LytContenedor);

        //Se crea un parametro auxiliar para cuestiones de diseño con el TextView y el EditText
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);
        

        //Abrimos la base de datos con las formulas en modo lectura

        FormulasSQLiteHelper usdbh =
                new FormulasSQLiteHelper(this, "DbEra", null, 1);

        SQLiteDatabase db = usdbh.getReadableDatabase();


        //Hacemos una consulta para  tomar el nombre de cada formula y su referencia bibliografca
        Cursor cursorAyuda = db.rawQuery(" SELECT  NombreCompleto,Bibliografia FROM Formulas", null);

        //Contamos las formulas
        int numeroFormulas;

        numeroFormulas = cursorAyuda.getCount();

        //Movemos el cursor al primer elemento
        cursorAyuda.moveToFirst();

        //En un bucle vamos creando etiquetas de texto con el siguiente formado
        /*
           ----------------Formula 1--------------
           Referencia:Referencia bibliografica

           -------------
           .
           .
           .
            ----------------Formula N--------------
           Referencia:Referencia bibliografica
           */

        for(int i=0;i< numeroFormulas; i++)
        {
            //Creamos un linear layout auxiliar donde iremos introduciendo los elementos que queremos mostrar
            LinearLayout layoutAuxTexto = new LinearLayout(this);
            layoutAuxTexto.setOrientation(LinearLayout.VERTICAL);
            layoutAuxTexto.setBackgroundResource(R.drawable.customborder2);
            //Creamos un textView para mostrar el nombre de la formula
            TextView txtNombreFormula = new TextView(this);
            txtNombreFormula.setText(cursorAyuda.getString(0));
            txtNombreFormula.setBackgroundResource(R.drawable.customborder);
            layoutAuxTexto.addView(txtNombreFormula);
            //Creamos un textView para mostrar el nombre de la referencia bibliográfica.
            TextView txtBibliografia = new TextView(this);
            txtBibliografia.setText(cursorAyuda.getString(1));
            txtBibliografia.setTypeface(null, Typeface.ITALIC);

            layoutAuxTexto.addView(txtBibliografia);
            lm.addView(layoutAuxTexto);
            cursorAyuda.moveToNext();
        }

        cursorAyuda.close();
        db.close();

        /*

        final Button btnRegresar = new Button(this);
        btnRegresar.setText("Regresar");
        btnRegresar.setBackgroundResource(R.drawable.seleccion);
        btnRegresar.setTextColor(Color.parseColor("#FFFFFF"));
        lm.addView(btnRegresar);

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creamos el Intent
                finish();

            }
        });
        */

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
