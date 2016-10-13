package com.example.alfonso.era04b.Clases;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Alfonso on 20/10/2015.
 * Ultima modificación: 23/08/2016

 */




public class Formula
{
    private Integer IdFormula;
    private String tipoFormula;
    private String nombreCompleto;
    private String abreviatura;
    private String expresion;
    private String bibliografia;
    private  Parametro [] parametros;
    private Parametro resultado;

    /* Para evaluar luego el resultado sera necesario

        La id de la formula que se calculo para encontrarla.
        El resultado de la formula.
        Los criterios para evaluar ese resultado
        Los posibles valores que puede tener ese resultado
        El valor final que tiene el resultado despues de haber sido evaluado.
     */


    /*

    PROC Constructor DEV int
    REQUIERE: Que existe la base de datos, que exista una formula con el identificador pasado por parametro.
    MODIFICAobjeto: Crea un objeto de tipo Formula.
    EFECTOS: El objeto tipo formula es creado. Sus atributos toman valores de la base de datos si es necesario.

     */

    public Formula(String idFormula, Context context)
    {


        //Abrir la base de datos y buscar la formula que coincide con la id .
        FormulasSQLiteHelper usdbh =
                new FormulasSQLiteHelper(context ,"DbEra", null, 1);
        SQLiteDatabase db = usdbh.getReadableDatabase();

        //Inicializo el curso para recorrer las formulas
        Cursor cursorFormulas = db.rawQuery("SELECT NombreCompleto,Abreviatura,Expresion,Tipo,Bibliografia FROM Formulas  WHERE IdFormula = '" + idFormula + "'  ", null);
        cursorFormulas.moveToFirst();

        //Rellenamos los atributos de la formula.
        //Aignamos el id
        setIdFormula(Integer.parseInt(idFormula));

        //Asignamos el nombre completo
        setNombreCompleto(cursorFormulas.getString(0));
        //Asignamos la abreviatura
        setAbreviatura(cursorFormulas.getString(1));

        //Asignamo la expresion
        setExpresion(cursorFormulas.getString(2));

        //Asignamos el tipo de la formula
        setTipoFormula(cursorFormulas.getString(3));

        //Asignamos la bibliografia
        setBibliografia(cursorFormulas.getString(4));

        //Cerramos el cursor de Formulas
        cursorFormulas.close();

        //Antes de continuar debemos crear el campo del resultado que es un Parametro que necesita una consulta especial
        Cursor cursorResultado = db.rawQuery("SELECT IdParametro FROM Parametros  WHERE TipoParametro =='resultado' AND IdFormula='"+ getIdFormula() +"' ", null);
        cursorResultado.moveToFirst();
        Parametro parametroResultado = new Parametro(cursorResultado.getString(0),context);
        setResultado(parametroResultado);
        cursorResultado.close();


        //Se crea para cada formula un parametro , para ello seleccionamos de la tabla de parametros los parametros cuyo IdFormula
        // es el IdFormula de la formula creada.
        Cursor cursorParametros = db.rawQuery("SELECT IdParametro FROM Parametros  WHERE IdFormula = '" + idFormula + "' AND TipoParametro<>'resultado' ", null);
        cursorParametros.moveToFirst();
        int numeroParametros =cursorParametros.getCount();
        db.close();

        //Inicializamos el vector de parametros
        parametros = new Parametro[numeroParametros];

        for(int i=0; i < numeroParametros; i++)
        {
            //Invocamos al constructor de la clase parametro y creamos un parametro
            Parametro parametroActual = new Parametro(cursorParametros.getString(0),context);
            //Pasamos al siguiente parametro
            cursorParametros.moveToNext();

            //Asignamos la posicion i del vector al parametro creado.
            parametros[i] = parametroActual;

        }


    }
    //Aqui empiezan los getter y setters sencillos de la clase.

    //Devuelve en una cadena el tipo de Formula. Los tipos pueden ser dos: escala y general.
    public String getTipoFormula()
    {
        return tipoFormula;
    }


    //Devuelve el nombre de la formula
    public String getNombreCompleto()
    {
        return nombreCompleto;
    }


    //Devuelve la abreviatura de la formula.
    public String getAbreviatura() {
        return abreviatura;
    }


     /*
        Devuelve la expresión de la fórmula. Por ejemplo una expresión podría ser. "a + b +c". Si el tipo de formula es escala
        no existe el campo expresión.
     */
    public String getExpresion() {
        return expresion;
    }


     //Se agrega un tipo a la formula. Los tipos pueden ser generico y escala.
    public void setTipoFormula(String tipoFormula) {
        this.tipoFormula = tipoFormula;
    }


    //Se agrega un nombre completo a la fórmula.
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }


    //Se agrega una abreviatura a la formula
    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }


    /*Se agrega una expresión a la formula.Por ejemplo una expresión podría ser. "a + b +c". Si el tipo de formula es escala
    no existe el campo expresión.
    */
    public void setExpresion(String expresion) {
        this.expresion = expresion;
    }


    //Devuelve un vector de dato Tipo Parametro.
    public Parametro[] getParametros() {
        return parametros;
    }

    //Copia un Tipo de dato Parametro en los Parametros de esta formula.
    public void setParametros(Parametro[] parametros) {
        this.parametros = parametros;
    }

    //Devuelve el campo llamado Resultado.
    public Parametro getResultado() {
        return resultado;
    }


    //Asigna un identificador a la formula.
    public void setIdFormula(Integer idFormula) {
        IdFormula = idFormula;
    }


    //Obtiene el identificador de la formula
    public Integer getIdFormula() {
        return IdFormula;
    }


    //Devuelve el contenido de la posición en el vector pedida como parametro.
    public Parametro getParametro (Integer posicion)
    {
        return parametros[posicion];
    }



    //Muestra el numero de parametros de una formula
    public int contarParametros()
    {
        return parametros.length ;
    }

    public void setResultado(Parametro resultado) {
        this.resultado = resultado;

    }

    /*
       PROC calcularFormula
       REQUIERE: Tener todos los datos necesarios para poder hacer un calculo de la formula.
       MODIFICA:
       EFECTOS: Selecciona dependiendo de la formula calcularFormulaGeneral o calcularFormulaEscala.

        */
    public String calcularFormula ()
    {
        String resultado ="";

        switch (getTipoFormula()){
            case "general":
                resultado =   calcularFormulaGeneral();
                break;
            case "escala":
                resultado = calcularFormulaEscala();
                break;
        }
        return resultado;
    }

    /*
         PROC calcularFormulaGeneral
         REQUIERE: Tener todos los datos necesarios para poder hacer un calculo de la formula. Ser una formula de tipo general.
         MODIFICA: Campo resultado de la formula.
         EFECTOS: Resuelve una formula de tipo general y muestra su resultado en pantalla.

          */
    public String calcularFormulaGeneral(){
        String resultado ="";

        Expression expression = new Expression(getExpresion());


        for (int i = 0; i < contarParametros(); i++) {
            if(i == 0 )
                expression.with(getParametro(i).getNombre(), getParametro(i).getValor());
            else
                expression.and(getParametro(i).getNombre(), getParametro(i).getValor());
        }



        //Convertimos el bigDecimal de Expresion en String utilizando toPlainString para que muestre el número sin notación científica.
        resultado =  expression.eval().toPlainString() ;



        //Si el parametro resultado tiene criterios de puntuacion con su id hay que evaluar el resultado.
        if (getResultado().contarCriterios() > 0) {
            //Guardamos el resultado numerico
            String auxResultado = resultado;
            //colocamos el resultado numerico junto al interpretado
            resultado = evaluarResultado(resultado) + " " + auxResultado ; ;
        }

        //TODO Deberia agregar la puntuación del resultado y en la medida que se trata y meterlo en el resultado antes de mandarlo.


        getResultado().setValor(resultado);

        return resultado;
    }

       /*
         PROC calcularFormulaEscala
         REQUIERE: Tener todos los datos necesarios para poder hacer un calculo de la formula. Ser una formula de tipo general.
         MODIFICA: Campo resultado de la formula.
         EFECTOS: Resuelve una formula de tipo escala y muestra su resultado en pantalla.

          */

    public String calcularFormulaEscala() {
        String resultado = "";
        float contador = 0;

        //Sumamos todas las puntuaciones
        for (int i = 0; i < contarParametros(); i++) {
            contador = contador + Float.parseFloat(getParametro(i).getValor());
        }

        resultado = resultado + contador;
        getResultado().setPuntuacionEscala(contador);

        //Si el parametro resultado tiene criterios de puntuacion con su id hay que evaluar el resultado.
        if (getResultado().contarCriterios() > 0) {
            resultado = evaluarResultado(resultado) + "\n Puntuación:" + contador;
        }

        getResultado().setValor(resultado);

        return resultado;
    }

       /*
         PROC evaluarResultado
         REQUIERE: Formula con criterios de puntuacion. Una puntuación que es el resultado a evaluar.
         MODIFICA: Campo resultado de la formula.
         EFECTOS: Interpreta un criterio de puntuación para conseguir una puntuación.

          */

    public String evaluarResultado (String puntuacion)
    {
        String resultado="";

        for(int i =0; i < getResultado().contarCriterios() ; i++ ) {

            Expression evaluador = new Expression(getResultado().getCriterioPuntuacion(i).getCriterio());
            evaluador.with("x", puntuacion);

            if (evaluador.eval().toString().equals("1"))
                resultado = getResultado().getCriterioPuntuacion(i).getPuntuacion();

        }

        return resultado;
    }
       /*
         PROC introducirValoresFormula
         REQUIERE: vectorResultados[]
         MODIFICA: Campos valor de los parametros de la formula.
            //El valor del parametro dependera del tipo de este.
            //Tipo numero: El valor es el numero que se necesita para calcular la formula.
            //Tipo lista: Es el identificador del criterio de puntuacion que contiene el valor del parametro
            //Tipo logico: Si es 1 significa que esta checked si es 0 significa que no lo esta. En caso de estar chequeado
            //el valor del parametro sera el valor del criterio que tenga el idParametro de este campo.
         EFECTOS: La formula obtiene valores para poder utilizar el metodo calcularFormula

          */

    public boolean introducirValoresFormula (String vectorResultados[] )
    {
        for (int i =0 ;i < contarParametros(); i++)
        {
            int posicionCriterio;
            String  valorCriterio;


            switch (getParametro(i).getTipo())
            {
                case ("numero"):
                    if ( Float.parseFloat(vectorResultados[i]) <= getParametro(i).getValorMaximo() && Float.parseFloat(vectorResultados[i]) >= getParametro(i).getValorMinimo())
                    {
                        getParametro(i).setValor(vectorResultados[i]);
                        if (getTipoFormula().equals("escala"))
                            getParametro(i).setValor(getParametro(i).evaluarPuntuacion(vectorResultados[i]));

                    }
                    else
                    {
                        return false;
                    }
                    break;
                case ("lista"):
                    //Buscamos la posicion del criterio de puntuacion
                    posicionCriterio = getParametro(i).buscarPosicionDeCriterio(Integer.parseInt(vectorResultados[i])) ;
                    valorCriterio = getParametro(i).getCriterioPuntuacion(posicionCriterio).getPuntuacion() ;
                    getParametro(i).setValor(valorCriterio);
                    //Si la formula general este seleccionable es el que crea la expresion.
                    if (getTipoFormula().equals("general"))
                        setExpresion(valorCriterio);

                    break;
                case ("logico"):
                    if (vectorResultados[i].equals("1") )
                    {
                        posicionCriterio = getParametro(i).buscarPosicionDeCriterio();
                        valorCriterio = getParametro(i).getCriterioPuntuacion(posicionCriterio).getPuntuacion() ;
                        getParametro(i).setValor(valorCriterio);
                    }
                    else
                        getParametro(i).setValor("0");
                    break;
            }
        }

        return true;
    }

       /*
         PROC introducirRecientes
         REQUIERE: El resultado de una formula calculada.
         MODIFICA: Tabla recientes de la base de datos.
         EFECTOS: Introduce la ultima formula utilizada como formula Reciente agregando el id de la formula y la fecha a la tabla Recientes
          */

    public boolean introducirRecientes (Integer idFormula, String resultado,  Context context)
    {

        //Abrir la base de datos y buscar la formula que coincide con la id .

        FormulasSQLiteHelper usdbh =
                new FormulasSQLiteHelper(context ,"DbEra", null, 1);
        SQLiteDatabase db = usdbh.getWritableDatabase();

        //vemos si hay una formula con esa id
        Cursor c = db.rawQuery("SELECT COUNT (IdFormula) FROM Recientes  WHERE IdFormula = '" + idFormula + "'  ", null);
        c.moveToFirst();
        //Cogemos el identificador de la formula
        int numeroFormulas = c.getInt(0);
        c.close();

        //Cogemos la fecha del sistema y le ponemos en formato dd/mm/aaaa hora:minuto:segundo
        SimpleDateFormat curFormater = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        //Si existe la borramos e insertamos la id con la fecha actual en la tabla Recientes
        if (numeroFormulas != 0 ) {
            db.execSQL("DELETE FROM Recientes WHERE IdFormula = '" + idFormula + "' ;");
            db.execSQL("INSERT INTO Recientes (IdFormula,Fecha,Resultado)  VALUES ('"+ idFormula +"','"+ curFormater.format(date) +"','"+ resultado +"' );");

        }
        //Sino insertamos la id con la fecha actual en la tabla Recientes
        else
        {
            db.execSQL("INSERT INTO Recientes (IdFormula,Fecha,Resultado)  VALUES ('"+ idFormula +"','"+ curFormater.format(date) +"','"+ resultado +"' );");
        }

        c.close();
        db.close();


        return true;
    }

    //Agrega la bibliografia a una formula
    public String getBibliografia() {
        return bibliografia;
    }

    //Obtiene la bibliografía de una formula.
    public void setBibliografia(String bibliografia) {
        this.bibliografia = bibliografia;
    }
}



