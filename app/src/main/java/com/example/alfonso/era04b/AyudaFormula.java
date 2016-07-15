package com.example.alfonso.era04b;

import android.content.Intent;
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

import com.example.alfonso.era04b.Clases.Formula;

import org.w3c.dom.Text;


   /*
       Para cada pagina se debe mostrar

       - El nombre de la formula.
       - Sus parametros, que deben contener
         * Valor minimo (si lo tiene)
         * Valor maximo (si lo tiene)
         * Criterios cada uno con su  Puntuacion
       - Su expresion (si la tiene)
       - Su referencia bibliografica
         */

public class AyudaFormula extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda_formula);
        //Activo el boton atras
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final LinearLayout lm = (LinearLayout) findViewById(R.id.LytContenedor);

        //Recibimos la id de la formula
        Bundle bundle = this.getIntent().getExtras();
        //Construimos el mensaje a mostrar
        final String valorRecibido = bundle.getString("IdFormula");

        final Formula formulaActual = new Formula(valorRecibido, getApplicationContext());

        //Se crea un parametro auxiliar para cuestiones de diseño con el TextView y el EditText
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);


        //EN esta pagina se mostrar la ayuda personalizada para cada formula.

        //Creo un layout auxiliar
        //antes de nada creamos un layaout con un label de nombre de la formula y a su derecho un boton de información.
        LinearLayout layoutCabecera = new LinearLayout(this);
        layoutCabecera.setOrientation(LinearLayout.HORIZONTAL);
        TextView nombreDeFormula = new TextView(this);
        nombreDeFormula.setText(formulaActual.getNombreCompleto());
        nombreDeFormula.setLayoutParams(param);
        Button btnAyuda = new Button(this);
        btnAyuda.setText("Ayuda");
        btnAyuda.setLayoutParams(param);
        layoutCabecera.addView(nombreDeFormula);
        layoutCabecera.setBackgroundResource(R.drawable.customborder);
        assert lm != null;
        lm.addView(layoutCabecera);


        //Iteramos

        for (int i = 0; i < formulaActual.contarParametros(); i++) {

            //Creamos un linear layout para cada iteraccón , en el estarán todos los elementos
            //No queremos mostrar los parametros de tipo resultado.
            LinearLayout layoutDeParametro = new LinearLayout(this);
            layoutDeParametro.setOrientation(LinearLayout.VERTICAL);
            layoutDeParametro.setBackgroundColor(Color.parseColor("#D5F8F8"));
            //Primero le creamos una caja de texto
            layoutDeParametro.setBackgroundResource(R.drawable.customborder2);
            TextView nombreDeParametro = new TextView(this);
            nombreDeParametro.setTypeface(null, Typeface.BOLD);
            if (formulaActual.getParametro(i).getMedida() != null)
                nombreDeParametro.setText("" +formulaActual.getParametro(i).getNombre()+ "(" +formulaActual.getParametro(i).getMedida()+ ")." );
            else
                nombreDeParametro.setText(formulaActual.getParametro(i).getNombre());

            nombreDeParametro.setLayoutParams(param);
            layoutDeParametro.addView(nombreDeParametro);
            //Ahora dependiendo del tipo de parametro creamos un elemento u otro

            if (formulaActual.getParametro(i).getValorMaximo() != formulaActual.getParametro(i).getValorMinimo()) {

                TextView TxtValorMinimo = new TextView(this);
                //Redondeamos el float, no queremos decimales
                String StrValorMinimo = "" + Math.round(formulaActual.getParametro(i).getValorMinimo());
                TxtValorMinimo.setText("Mínimo:" + StrValorMinimo + "\t");
                TextView TxtValorMaximo = new TextView(this);
                String StrValorMaximo = "" + Math.round(formulaActual.getParametro(i).getValorMaximo());
                TxtValorMaximo.setText("Máximo:" + StrValorMaximo + "");
                layoutDeParametro.addView(TxtValorMinimo);
                layoutDeParametro.addView(TxtValorMaximo);
            }
            if (formulaActual.getTipoFormula().equals("escala") ) {

                TextView etiquetaPuntuacion= new TextView(this);
                etiquetaPuntuacion.setTypeface(null, Typeface.BOLD);
                etiquetaPuntuacion.setText("Puntuación");
                etiquetaPuntuacion.setBackgroundResource(R.drawable.customborder2);
                layoutDeParametro.addView(etiquetaPuntuacion);


                for (int j = 0; j < formulaActual.getParametro(i).contarCriterios(); j++) {
                    //Mostramos los criterios de cada parametro con sus puntuaciones
                    LinearLayout layoutDeCriterio = new LinearLayout(this);

                    //Creamos un layout para los criterios.

                    TextView TxtCriterio = new TextView(this);
                    TextView TxTPuntuacion = new TextView(this);
                    TextView TxTPuntuacion2 = new TextView(this);


                    if(formulaActual.getParametro(i).getTipo().equals("logico"))
                    {
                        TxtCriterio.setText("\n" + "Puntos:" + formulaActual.getParametro(i).getCriterioPuntuacion(j).getPuntuacion() );

                    }
                    else {

                        String cadenaCriterio = cambiarFormatoCriterio(formulaActual.getParametro(i).getCriterioPuntuacion(j).getCriterio(), formulaActual.getParametro(i).getNombre() );
                        String puntos = "\n Puntos:";
                        TxtCriterio.setText(cadenaCriterio + puntos + formulaActual.getParametro(i).getCriterioPuntuacion(j).getPuntuacion());
                        //TxTPuntuacion.setText(formulaActual.getParametro(i).getCriterioPuntuacion(j).getPuntuacion());
                    }

                    layoutDeCriterio.addView(TxtCriterio);
                    layoutDeCriterio.setBackgroundResource(R.drawable.customborder3);

                    //layoutDeCriterio.addView(TxTPuntuacion);
                    //layoutDeCriterio.addView(TxTPuntuacion2);
                    layoutDeParametro.addView(layoutDeCriterio);


                }
            }


            lm.addView(layoutDeParametro);

            //Muestra la expresion de la formula si existe.

            //Muestro los parametros (creamos otro layout para esto)
            //Si existen valores minimos y maximos mostramos:
            // Valor comprendido entre ValorMinimo y ValorMaximo
            // Si tinene criterios:
            // Criterio :--> Con su criterio de puntuacion (cambiar && por Y)

            // Por ejemplo  x>= 3 Y < x <= 6  3 puntos.


            //Muestra la expresion de la formula si existe.


            //Muestro la referencia bibliografica


        }


        LinearLayout layoutDeExpresion = new LinearLayout(this);
        layoutDeExpresion.setOrientation(LinearLayout.VERTICAL);
        layoutDeExpresion.setBackgroundColor(Color.parseColor("#D5F8F8"));
        //Primero le creamos una caja de texto
        layoutDeExpresion.setBackgroundResource(R.drawable.customborder2);



        if (formulaActual.getTipoFormula().equals("general"))
        {



            if(formulaActual.getExpresion().equals(""))
            {
                TextView etiquetaFormula = new TextView(this);
                etiquetaFormula.setTypeface(null, Typeface.BOLD);
                etiquetaFormula.setText("Formula");
                layoutDeExpresion.addView(etiquetaFormula);

                for (int i = 0; i < formulaActual.contarParametros(); i++) {

                    //Miramos todos los parametroso

                    //Si algún parametro tiene criterios de puntuacion siendo general, es que puede tener varias expresiones asi que las mostramos mostrando antes el criterio
                    if(formulaActual.getParametro(i).contarCriterios() > 0 )
                    {
                        for (int j = 0; j < formulaActual.getParametro(i).contarCriterios(); j++)
                        {
                            TextView nombreDeExpresion = new TextView(this);
                            //Mostramos todas las posibles expresiones de la formula
                            nombreDeExpresion.setText(formulaActual.getParametro(i).getCriterioPuntuacion(j).getCriterio() + ":" +  formulaActual.getParametro(i).getCriterioPuntuacion(j).getPuntuacion());
                            layoutDeExpresion.addView(nombreDeExpresion);

                        }
                    }


                }
                lm.addView(layoutDeExpresion);
            }


            else
            {
                TextView nombreDeExpresion = new TextView(this);
                nombreDeExpresion.setText(formulaActual.getExpresion());
                layoutDeExpresion.addView(nombreDeExpresion);
                lm.addView(layoutDeExpresion);

            }

        }

        //Mostramos la bibliografia
        LinearLayout layoutBibliografia = new LinearLayout(this);
        layoutBibliografia.setOrientation(LinearLayout.VERTICAL);
        layoutBibliografia.setBackgroundColor(Color.parseColor("#D5F8F8"));
        //Primero le creamos una caja de texto
        layoutBibliografia.setBackgroundResource(R.drawable.customborder2);
        TextView nombreBibliografia = new TextView(this);
        nombreBibliografia.setText(formulaActual.getBibliografia());
        layoutBibliografia.addView(nombreBibliografia);
        lm.addView(layoutBibliografia);

        final Button btnRegresar = new Button(this);
        btnRegresar.setText("Regresar");
        btnRegresar.setBackgroundResource(R.drawable.seleccion);
        lm.addView(btnRegresar);


        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creamos el Intent
               finish();

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



    public String cambiarFormatoCriterio (String original, String nombreParametro)
    {
        String cadenaCambiada = "";

        //Lo primero es separar todos los campos de la cadena separados con sus spacios utilizando la funcion split

        String vectorPalabras [] = original.split(" ");
        int contador = 0;

        //Ahora voy a recorrer los elementos utilizando un bucle cada vez que encuentre una x incremento el contador

        //en este buscle se pretender cambiar los siguientes simbolos por.

        //x --> Primera aparicion: nombreParametro

        //x --> Segunda o posterios aparicion es eliminado  "";

        //&& --> y

        // < --> Menor a

        // > --> Mayor a

        // Menor ó igual a

        //Mayor ó igual a

        // Puntuacion si es 1--> 1 punto.

        //Puntuación si es dipstinto de 1 --> X puntos.


        for(int i =0; i < vectorPalabras.length; i++ )
        {
         switch (vectorPalabras[i])
         {
             case "x" :
                 if (contador < 1) {
                     vectorPalabras[i] = nombreParametro;
                     contador++;
                 }
                 else {
                     vectorPalabras[i] = "";
                     contador++;
                 }
             break;

             case "&&":
                vectorPalabras[i]= "y";
             break;

             case "<":
                 vectorPalabras[i]= "Menor a";
             break;

             case ">":
                 vectorPalabras[i]= "Mayor a";
             break;

             case "<=":
                 vectorPalabras[i]= "Menor ó igual a";
             break;

             case ">=":
                 vectorPalabras[i]= "Mayor ó igual a";
             break;


         }
        }

        for (String vectorPalabra : vectorPalabras) {
            cadenaCambiada = cadenaCambiada + " " + vectorPalabra;
        }



        return cadenaCambiada;
    }

}
