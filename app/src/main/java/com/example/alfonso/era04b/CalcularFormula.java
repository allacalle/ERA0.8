package com.example.alfonso.era04b;


/**
 * Created by Alfonso on 18/03/2016.
 * Ultima modificación: 20/07/2016

 */

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.example.alfonso.era04b.Clases.Formula;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.ArrayList;
import java.util.List;

public class CalcularFormula extends AppCompatActivity {

    String cadenaPrueba = "";
    int indiceCampoIncorrecto = 0 ;
    boolean existenCamposEdit = false;
    String idFormula;
    String nombreFormula;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcular_formula);
        //Boton atras
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final LinearLayout lm = (LinearLayout) findViewById(R.id.LytContenedor);
        Button BtnRecientes = (Button) findViewById(R.id.BtnRecientes) ;
        Button BtnInicio = (Button) findViewById(R.id.BtnInicio) ;
        //Button BtnAyuda = (Button) findViewById(R.id.BtnAyuda) ;


        //Lo primero es capturar la abreviatura de la formula que se nos pasa en un bundle
        //Recuperamos la información pasada en el intent
        Bundle bundle = this.getIntent().getExtras();
        //Construimos el mensaje a mostrar
        final String valorRecibido = bundle.getString("IdFormula");
        idFormula = valorRecibido;

        //Se crea una formula con el id que nos han pasado.
        //Se crea una formula con el id que nos han pasado.
        final Formula formulaActual = new Formula(idFormula, getApplicationContext());
        //Sacamos el nombre de la formula para futuros usos.
        nombreFormula = formulaActual.getAbreviatura();
        //Parametro parametro = new Parametro("2" , getApplicationContext());
        //Lista para los editText
        final List<EditText> allEds = new ArrayList<EditText>();
        //Lista para los checkbox
        final List<CheckBox> allChs = new ArrayList<CheckBox>();
        //Lista para los radial group
        final List<RadioGroup> allRgs = new ArrayList<RadioGroup>();

        //Se crea un parametro auxiliar para cuestiones de diseño con el TextView y el EditText
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);

        LinearLayout.LayoutParams param2 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT, 3.0f);




        //antes de nada creamos un layaout con un label de nombre de la formula y a su derecho un boton de información.
        LinearLayout layoutCabecera = new LinearLayout(this);
        layoutCabecera.setOrientation(LinearLayout.HORIZONTAL);
        TextView nombreDeFormula = new TextView(this);
        nombreDeFormula.setText(formulaActual.getNombreCompleto());
        nombreDeFormula.setLayoutParams(param);
        nombreDeFormula.setGravity(Gravity.CENTER_HORIZONTAL);
        Button btnAyuda = new Button(this);
        btnAyuda.setText("Ayuda");
        btnAyuda.setLayoutParams(param);
        layoutCabecera.addView(nombreDeFormula);
        layoutCabecera.setBackgroundResource(R.drawable.customborder);



        //tenemos que ver si alguno de los parametros es numero para mostrar o no el boton ayuda.
        for (int i = 0; i < formulaActual.contarParametros(); i++) {
            if (formulaActual.getParametro(i).getTipo().equals("numero"))
                existenCamposEdit = true;
        }

        /*
        //Solo mostramos el boton ayuda si existen campos editables en el formulario
        if(existenCamposEdit)
            layoutCabecera.addView(btnAyuda);
         */
        lm.addView(layoutCabecera);


        for (int i = 0; i < formulaActual.contarParametros(); i++) {
            //Creamos un linear layout para cada iteraccón , en el estarán todos los elementos
            //No queremos mostrar los parametros de tipo resultado.
            LinearLayout layoutDeParametro = new LinearLayout(this);
            layoutDeParametro.setOrientation(LinearLayout.HORIZONTAL);
            layoutDeParametro.setBackgroundColor(Color.parseColor("#D5F8F8"));
            //Primero le creamos una caja de texto
            layoutDeParametro.setBackgroundResource(R.drawable.customborder2);
            TextView nombreDeParametro = new TextView(this);
            //Ponemos el nombre del parametro en negrita
            nombreDeParametro.setTypeface(null, Typeface.BOLD);
            //nombreDeParametro.setText(formulaActual.getParametro(i).getNombre());

            if (formulaActual.getParametro(i).getMedida() != null)
                nombreDeParametro.setText("" +formulaActual.getParametro(i).getNombre()+ "(" +formulaActual.getParametro(i).getMedida()+ ")." );
            else
                nombreDeParametro.setText(formulaActual.getParametro(i).getNombre());

            nombreDeParametro.setLayoutParams(param);
            layoutDeParametro.addView(nombreDeParametro);
            //Ahora dependiendo del tipo de parametro creamos un elemento u otro

            switch (formulaActual.getParametro(i).getTipo()) {
                //Si es del tipo numero se crea una caja de texto
                case "numero":
                    EditText valorNumeroParametro = new EditText(this);
                    valorNumeroParametro.setLayoutParams(param2);
                    valorNumeroParametro.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
                    valorNumeroParametro.setInputType(InputType.TYPE_CLASS_PHONE);
                    //valorNumeroParametro.setId(formulaActual.getParametro(i).getIdParametro());
                    allEds.add(valorNumeroParametro);
                    layoutDeParametro.addView(valorNumeroParametro);
                    break;
                //Se crea un checbox para cada uno de los criterios de puntuacion
                case "logico":
                    CheckBox valorLogicoParametro = new CheckBox(this);
                    valorLogicoParametro.setLayoutParams(param2);
                    //valorLogicoParametro.setId(formulaActual.getParametro(i).getIdParametro());
                    allChs.add(valorLogicoParametro);
                    layoutDeParametro.addView(valorLogicoParametro);
                    break;

                //Se crea un radial group con un radial button para cada una de las opciones
                case "lista":
                    RadioGroup valorListaParametro = new RadioGroup(this);
                    valorListaParametro.setLayoutParams(param);
                    for (int j = 0; j < formulaActual.getParametro(i).contarCriterios(); j++) {
                        RadioButton opcion = new RadioButton(this);
                        //Asignamos a cada uno de los botones de opcion como su id la de su criterio para poder encontrarlos luego
                        opcion.setId(formulaActual.getParametro(i).getCriterioPuntuacion(j).getIdCriterioPuntuacion());
                        opcion.setText(formulaActual.getParametro(i).getCriterioPuntuacion(j).getCriterio());
                        valorListaParametro.addView(opcion);
                    }
                    allRgs.add(valorListaParametro);
                    layoutDeParametro.addView(valorListaParametro);

                    break;
            }

            lm.addView(layoutDeParametro);

        }

        /* Se creará un botón que al pulsarlo:
        1 Evalue que todos los datos introducidos sean correctos (Valores Prohibidos, minimos, maximos,
        seleccionar al menos una opcion los radio button)
        2 Almacenara todos los valores introducidos en un vector que pasaremos a la siguiente pantalla para realizar los calculos.
         2.1 Para coger los datos debemos acceder a los almacenes donde estan. Para ello sera necesario
          Crear un bucle que vaya entrando a cada uno de los list<> dependiendo del tipo de datos de cada almacen
          Como son 3 tipos distintos de list<> necesitamos 3 variables para ir almacenando la posicion actual de cada iterador.
          Cuando tengamos todos los valores podemos crear una cadena que contenta idParametro: valorParametro; IdParametro:valor...

         */

        final Button btnCalcular = new Button(this);
        btnCalcular.setText("Calcular");
        btnCalcular.setBackgroundResource(R.drawable.seleccion);
        btnCalcular.setTextColor(Color.parseColor("#FFFFFF"));
        lm.addView(btnCalcular);




        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();

        alertDialog.setButton("Continuar..", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // here you can add functions
            }
        });

        final TextView textoResultado = new TextView(this);
        textoResultado.setTextSize(30);
        lm.addView(textoResultado);



        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Necesitamos 3 iteradores uno para cada tipo de list<>
                int iEdit = 0;
                int iCheck = 0;
                int iRadio = 0;
                String aux = "";
                cadenaPrueba = "";
                //Creamos una variable bool que sera true cuando todos los campos tengan valores correctos
                boolean camposCorrectos = true;
                //Creamos una varable donde almacenaremos el indice del parametro incorrecto para despues mostrarlo en pantalla.
                indiceCampoIncorrecto = -1;
                //Vamos a meter todos los resultados en un vector de cadenas
                String[] vectorResultados = new String[formulaActual.contarParametros()];
                //Vamos a extraer los valores introducidos

                //Creamos una variable String para colocar un mensaje de texto en los errores.
                String mensajeError = "";


                //Contamos el numero de parametros -1 porque no estamos interesados en el parametro resultado
                for (int i = 0; i < formulaActual.contarParametros(); i++) {
                    //Necesitamos 3 iteradores uno para cada tipo de list<>
                    switch (formulaActual.getParametro(i).getTipo()) {
                        case "numero":
                            vectorResultados[i] = allEds.get(iEdit).getText().toString();
                            iEdit++;
                            //Si el valor no es numerico
                            if (!isNumeric(vectorResultados[i])) {
                                //Como no es un numero ya hay un campo incorrecto.
                                camposCorrectos = false;
                                //Guardamos el indice del campo incorrecto para mostrarlo mas adelante.
                                indiceCampoIncorrecto = i;
                                mensajeError = "El parametro " +formulaActual.getParametro(i).getNombre() + " es incorrecto, debe introducir un numero valido";

                                //Comprobamos que el decimal este separado por una coma en vez de un punto.
                                if (esComaElDecimal(vectorResultados[i]))
                                {
                                    mensajeError = "El parametro " +formulaActual.getParametro(i).getNombre() + " es incorrecto, los numeros decimales deben ir separados por puntos" ;
                                }
                            }

                            //Si el valor es numero pero es menor que el valor numero es incorrecto
                            else if (Float.parseFloat(vectorResultados[i]) < formulaActual.getParametro(i).getValorMinimo()) {
                                camposCorrectos = false;
                                indiceCampoIncorrecto = i;
                                mensajeError = " El parametro "  +formulaActual.getParametro(i).getNombre() +  " es incorrecto, el numero introducido debe ser mayor o igual a " + formulaActual.getParametro(i).getValorMinimo() ;
                            }

                            //Si es mayor que el valor maximo es incorrecto
                            else if (Float.parseFloat(vectorResultados[i]) > formulaActual.getParametro(i).getValorMaximo()) {
                                camposCorrectos = false;
                                indiceCampoIncorrecto = i;
                                mensajeError = "El parametro " +formulaActual.getParametro(i).getNombre() + " es incorrecto, el numero introducido debe ser menor o igual a " + formulaActual.getParametro(i).getValorMaximo() ;

                            }


                            break;
                        case "lista":
                            aux = "" + allRgs.get(iRadio).getCheckedRadioButtonId();
                            vectorResultados[i] = aux;
                            if (aux.equals("-1")) {
                                camposCorrectos = false;
                                indiceCampoIncorrecto = i;
                                mensajeError = "El parametro " +formulaActual.getParametro(i).getNombre() +  " es incorrecto, debe seleccionar una opción";
                            }

                            iRadio++;
                            //Sino ha seleccionado ninguna opcion es incorrecto

                            break;
                        case "logico":
                            if (allChs.get(iCheck).isChecked())
                                vectorResultados[i] = "1";
                            else
                                vectorResultados[i] = "0";
                            iCheck++;
                            break;
                    }

                    if (!camposCorrectos) {

                        {

                            alertDialog.setMessage(mensajeError);
                            alertDialog.show();
                            break;

                        }

                    }


                    //Introdicimos los valores de los parametros en nuestra formula.
                    //formulaActual.introducirValoresFormula(vectorResultados);

                    //Calculamos los resultados.
                    // formulaActual.calcularFormula();

                    // textoResultado.setText(cadenaPrueba  + "\n Su resultado es \n" + formulaActual.getResultado().getValor());

                    //Agregamos la formula a recientes
                    //Esta funcion no debe tener parametros salvo el context????
                    // formulaActual.introducirRecientes(formulaActual.getIdFormula() , formulaActual.getResultado().getValor(), getApplicationContext());




                }
                if (camposCorrectos) {
                    //Introdicimos los valores de los parametros en nuestra formula.
                    formulaActual.introducirValoresFormula(vectorResultados);

                    //Calculamos los resultados.
                    formulaActual.calcularFormula();

                    String resultadoCriterios = "";

                    //Vamos a mostrar los criterios y sus puntuaciones para poder evaluar que funciona correctamente.
                    //Bucle para recorrer los parametros
                    for (int i = 0; i < formulaActual.contarParametros(); i++) {

                            resultadoCriterios = resultadoCriterios + formulaActual.getParametro(i).getNombre() + ":" + formulaActual.getParametro(i).getValor() + "\n" ;

                        }



                    //Agregamos la formula a recientes
                    //Esta funcion no debe tener parametros salvo el context????
                    formulaActual.introducirRecientes(formulaActual.getIdFormula() , formulaActual.getResultado().getValor(), getApplicationContext());


                    String resultadoFinal ="";


                    if (formulaActual.getResultado().getMedida() == null)
                        resultadoFinal = formulaActual.getResultado().getValor() ;

                    else {
                        resultadoFinal = formulaActual.getResultado().getValor() + " " + formulaActual.getResultado().getMedida();

                        }


                    alertDialog.setMessage(resultadoFinal);
                    alertDialog.show();
                    //textoResultado.setText(resultadoCriterios);
                    textoResultado.setText(resultadoFinal);




                }



            }
        });

        /*

        assert BtnAyuda != null;

        BtnAyuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Creamos el Intent
                Intent intent =
                        new Intent(CalcularFormula.this, AyudaFormula.class);

                Bundle b = new Bundle();
                b.putString("IdFormula", valorRecibido );


                //Añadimos la información al intent
                intent.putExtras(b);



                //Iniciamos la nueva actividad
                startActivity(intent);
            }
        });

        */

        assert BtnRecientes != null;

        BtnRecientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creamos el Intent
                Intent intent =
                        new Intent(CalcularFormula.this, FormulasRecientes.class);


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
                        new Intent(CalcularFormula.this, Inicio.class);


                //Iniciamos la nueva actividad
                startActivity(intent);

            }
        });



    }



    // Funcion auxiliar que comprueba si una cadena es un numero. Podria ser interesante tenerlas todas en una misma clase
    public boolean isNumeric(String str) {
        return str.matches("^-?[0-9]+([.][0-9]+)?$");
    }

    public boolean esComaElDecimal (String str) {
        return str.matches("^-?[0-9]+([,][0-9]+)?$");
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
            case R.id.error:

                //establecemos el destinatario del mensaje. Que sera nuestra email de contacto.
                String[] to = { "eracontacto@yopmail.com", "eracontacto@yopmail.com" };
                String[] cc = { "eracontacto@yopmail.com" };
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:"));
                //Rellenamos los campos necesarios para mandar el mail
                emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
                emailIntent.putExtra(Intent.EXTRA_CC, cc);
                //Se agrega el nombre de la formula a reportar.
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Reporte ERROR en Formula:"+nombreFormula );
                emailIntent.putExtra(Intent.EXTRA_TEXT, "");
                emailIntent.setType("message/rfc822");
                //Iniciamos la actividad para mandar el mail.
                startActivity(Intent.createChooser(emailIntent, "Email "));

                return true;
            case R.id.ayuda:
                Intent intent =
                        new Intent(CalcularFormula.this, AyudaFormula.class);
                Bundle b = new Bundle();

                //Vamos a pasar el identificador de la formula que es un campo unico .
                b.putString("IdFormula", idFormula );
                //Añadimos la información al intent
                intent.putExtras(b);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.calcular, menu);
        return true;
    }



}


//Se deben crear tres contenedores de datos, para tres tipos de datos numero ( editText), lista radial button y logico checkbox.
//Ademas se debe crear una etiqueta con el nombre de cada parametro.

//Para esto creamos en un bucle cada etiqueta con el nombre del parametro y despues le añadimos dependiendo del tipo del parametro
//un editBox, o varios chebox o radialbuton.

//Se deben recoger todos los datos introducidos por el usuario y meterlos en un vector

//Este vector pasara a otra pagina donde seran calculados.

