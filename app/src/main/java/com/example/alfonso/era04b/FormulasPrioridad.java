package com.example.alfonso.era04b;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.alfonso.era04b.Clases.FormulasSQLiteHelper;

public class FormulasPrioridad extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulas_prioridad);
        //Boton atras
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//Recuperamos la información pasada en el intent
        Bundle bundle = this.getIntent().getExtras();
        //Construimos el mensaje a mostrar
        final String valorRecibido = bundle.getString("Prioridad");

        Button BtnRecientes = (Button) findViewById(R.id.BtnRecientes) ;
        Button BtnInicio = (Button) findViewById(R.id.BtnInicio) ;


        //Primero se debe abrir la base de datos
        FormulasSQLiteHelper usdbh =
                new FormulasSQLiteHelper(this, "DbEra", null, 1);

        SQLiteDatabase db = usdbh.getWritableDatabase();

        LinearLayout lm = (LinearLayout) findViewById(R.id.LytContenedor);

        Cursor cursorPrioridad = null;

        switch (valorRecibido)
        {
            case "Alta":
                cursorPrioridad = db.rawQuery(" SELECT F.IdFormula,F.Abreviatura  FROM Formulas F,Prioridad P WHERE F.IdFormula = P.IdFormula AND P.Tipo ='Alta' ", null);
                break;
            case "Media":
                cursorPrioridad = db.rawQuery(" SELECT F.IdFormula,F.Abreviatura  FROM Formulas F,Prioridad P WHERE F.IdFormula = P.IdFormula AND P.Tipo ='Media' ", null);
                break;
            case "Baja" :
                cursorPrioridad = db.rawQuery(" SELECT F.IdFormula,F.Abreviatura  FROM Formulas F,Prioridad P WHERE F.IdFormula = P.IdFormula AND P.Tipo ='Baja' ", null);
                break;


        }



        //Hacemos una consulta para obtener todas las Ids cuya prioridad es alta cursorAlta

        //utilizamos la propiedad JOIN para hacer una consulta sobre 2 tablas que tienen un valor en comun, en este caso la IdFormula
        //Cursor cursorAlta = db.rawQuery(" SELECT F.IdFormula,F.Abreviatura  FROM Formulas F,Prioridad P WHERE F.IdFormula = P.IdFormula AND P.Tipo ='Alta' ", null);
        //Cursor cursorAlta = db.rawQuery("SELECT * FROM Formulas",null);
        cursorPrioridad.moveToFirst();

        //Contamos el numero de formulas
        int numeroFormulas;
        numeroFormulas = cursorPrioridad.getCount();



        for( int i=0; i < numeroFormulas; i++ )
        {

            //Creamos un objeto drawable para dar formato a los elementos auxiliares.
            GradientDrawable drawable = new GradientDrawable();
            drawable.setShape(GradientDrawable.RECTANGLE);
            drawable.setStroke(5, Color.parseColor("#BDBDBD"));

            switch (valorRecibido) {
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


            final Button boton = new Button(this);
            boton.setText(cursorPrioridad.getString(1));
            boton.setId(cursorPrioridad.getInt(0));
            cursorPrioridad.moveToNext();
            //Le aplico el layout
            boton.setBackgroundDrawable(drawable);
            lm.addView(boton);

            boton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Creamos el Intent
                    Intent intent =
                            new Intent(FormulasPrioridad.this, CalcularFormula.class);

                    //Creamos la información a pasar entre actividades
                    Bundle b = new Bundle();
                    String cadenaId= "";
                    cadenaId = cadenaId + boton.getId() ;
                    //Vamos a pasar el identificador de la formula que es un campo unico .
                    b.putString("IdFormula", cadenaId );

                    //Añadimos la información al intent
                    intent.putExtras(b);

                    //Iniciamos la nueva actividad
                    startActivity(intent);

                }
            });



        }

        assert BtnRecientes != null;
        BtnRecientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creamos el Intent
                Intent intent =
                        new Intent(FormulasPrioridad.this, FormulasRecientes.class);


                //Iniciamos la nueva actividad
                startActivity(intent);

            }
        });

        assert BtnInicio != null;
        BtnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creamos el Intent
                Intent intent =
                        new Intent(FormulasPrioridad.this, Inicio.class);


                //Iniciamos la nueva actividad
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
            case R.id.busqueda:
                //metodoSearch()
                //info.setText("Se presionó Buscar");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    //Esta es una pagina filstrada que mostrara una lista de todas las formulas cuya prioridad es Alta

    //Sera una lista de botones que al pulsarlo te llevaran a la formula en si, la pantalla debe ser intuitiva y sin muchas
    // mas elementos que puedan confundir al usuario.






    //Hacemos una consulta de todas las formulas  cursorFormulas

    //Creamos un boton para cada formula

    //Al pulsar en el boton nos llevara a la formula cuyo nombre tiene (el identificador del boton deberia ser el IdFormula

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.prioridad, menu);
        return true;
    }




}

