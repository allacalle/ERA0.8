package com.example.alfonso.era04b;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.alfonso.era04b.Clases.FormulasSQLiteHelper;

public class AyudaGeneral extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda_general);

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
            LinearLayout auxTexto = new LinearLayout(this);
            auxTexto.setOrientation(LinearLayout.VERTICAL);
            auxTexto.setBackgroundResource(R.drawable.customborder2);
            //El nombre de la formula
            TextView textNombre = new TextView(this);
            textNombre.setText(cursorAyuda.getString(0));
            textNombre.setBackgroundResource(R.drawable.customborder);
            auxTexto.addView(textNombre);
            //La referencia bibliográfica
            TextView textBibliografia = new TextView(this);
            textBibliografia.setText(cursorAyuda.getString(1));
            auxTexto.addView(textBibliografia);
            lm.addView(auxTexto);
            cursorAyuda.moveToNext();
        }

        cursorAyuda.close();
        db.close();



    }
}
