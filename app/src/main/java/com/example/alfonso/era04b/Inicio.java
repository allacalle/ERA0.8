package com.example.alfonso.era04b;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.alfonso.era04b.Clases.ArrayAdapterSearchView;
import com.example.alfonso.era04b.Clases.FormulasSQLiteHelper;

/**
 * Created by Alfonso on 18/03/2016.
 * Ultima modificación: 20/07/2016

 */

public class Inicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        //Prueba boton busqueda.





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
        MenuItem searchItem = menu.findItem(R.id.busqueda);
        final ArrayAdapterSearchView searchView = (ArrayAdapterSearchView) MenuItemCompat.getActionView(searchItem);

        //Abrimos la base de datos.

        //Primero se debe abrir la base de datos
        FormulasSQLiteHelper usdbh =
                new FormulasSQLiteHelper(this, "DbEra", null, 1);

        final SQLiteDatabase db = usdbh.getReadableDatabase();

        //creamos un cursor con las abreviaturas de las formulas.
        final Cursor CursorAbreviatura = db.rawQuery(" SELECT  Abreviatura FROM Formulas", null);
        CursorAbreviatura.moveToFirst();


        //Creamos un array de String con la abreviaturas de las formulas
        String [] ArrayAbreviaturas = new String[CursorAbreviatura.getCount()];

        for(int i=0; i < CursorAbreviatura.getCount(); i++)
        {
            ArrayAbreviaturas[i] = CursorAbreviatura.getString(0);
            CursorAbreviatura.moveToNext();
        }

        CursorAbreviatura.close();



        //String[] languages={"Android ","java","IOS","SQL","JDBC","Web services","Androida", "Androito", "Antiestaminico"};
        //Convertir cadena en adapter.
        //final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1 ,languages);

        //Converitmos el array de elementos en una lista de las abreviaturas de las formulas
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.simple_list_item_1 ,ArrayAbreviaturas);


        searchView.setAdapter(adapter);


        searchView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                //Ponemos el texto seleccionado en el searchview
                //searchView.setText(adapter.getItem(position).toString());
                String AbreviaturaSeleccionada = adapter.getItem(position).toString();



                //hacemos una consulta a la bbdd para que nos devuelva la id de la formula con la abreviatura seleccionada.
                Cursor CursorIdFormula = db.rawQuery(" SELECT  IdFormula FROM Formulas WHERE Abreviatura = '" + AbreviaturaSeleccionada + "' ", null);


                //Cremos un nuevo bundle para la actividad pasando la id como parametro IdParametro
                //Creamos el Intent
                Intent intent =
                        new Intent(Inicio.this, CalcularFormula.class);

                //Creamos la información a pasar entre actividades
                Bundle b = new Bundle();
                CursorIdFormula.moveToFirst();
                String cadenaId= "";
                cadenaId = CursorIdFormula.getString(0) ;
                //Vamos a pasar el identificador de la formula que es un campo unico .
                b.putString("IdFormula", cadenaId );

                //Añadimos la información al intent
                intent.putExtras(b);

                //Cerramos el cursor del id y la base de datos.
                CursorIdFormula.close();
                db.close();

                //Iniciamos la nueva actividad
                startActivity(intent);

                //Iniciamos la actividad calcular formula con la id de la formula seleccionada.


            }
        });

        return true;
    }





    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.error:
                //metodoAdd()
                //info.setText("Se presionó Añadir");
                String[] to = { "eracontacto@yopmail.com", "eracontacto@yopmail.com" };
                String[] cc = { "eracontacto@yopmail.com" };
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:"));
                //String[] to = direccionesEmail;
                //String[] cc = copias;
                emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
                emailIntent.putExtra(Intent.EXTRA_CC, cc);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Reporte ERROR General");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "");
                emailIntent.setType("message/rfc822");
                startActivity(Intent.createChooser(emailIntent, "Email "));

                return true;
            case R.id.busqueda:
                //metodoSearch()
                //info.setText("Se presionó Buscar");
                return true;
            case R.id.configuracion:
                Intent intent =
                        new Intent(Inicio.this, CambiarConfiguracion.class);
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

