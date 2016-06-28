package com.example.alfonso.era04b.Clases;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Alfonso on 20/10/2015.
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
    EFECTOS: El objeto tipo formula es creado. Sus atributos toman valores si es necesario.

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

    //Constructor de copia
        /*

    PROC contarPametros() DEV int
    REQUIERE:
    MODIFICA:
    EFECTOS: Muestra el numero de parametros de una formula

     */

    //Hacer

    /*
    PROC getTipoFormula() DEV String
    REQUIERE:Que exista un objeto formula.
    MODIFICA:
    EFECTOS: Devuelve en una cadena el tipo de Formula. Los tipos pueden ser dos: escala y general.
     */
    public String getTipoFormula()
    {
        return tipoFormula;
    }





    /*

   PROC getTipoFormula() DEV String
   REQUIERE:Que exista un objeto formula.
   MODIFICA:
   EFECTOS: Devuelve en una cadena el tipo de Formula. Los tipos pueden ser dos: escala y general.

    */
    public String getNombreCompleto()
    {
        return nombreCompleto;
    }

         /*

    PROC getTipoFormula() DEV String
    REQUIERE:Que exista un objeto formula.
    MODIFICA:
    EFECTOS: Devuelve en una cadena el tipo de Formula. Los tipos pueden ser dos: escala y general.

     */

    /*

PROC getTipoFormula() DEV String
REQUIERE:Que exista un objeto formula.
MODIFICA:
EFECTOS: Devuelve en una cadena el tipo de Formula. Los tipos pueden ser dos: escala y general.

*/
    public String getAbreviatura() {
        return abreviatura;
    }

    /*

PROC getTipoFormula() DEV String
REQUIERE:Que exista un objeto formula.
MODIFICA:
EFECTOS: Devuelve en una cadena el tipo de Formula. Los tipos pueden ser dos: escala y general.

*/
    public String getExpresion() {
        return expresion;
    }


    /*

PROC getTipoFormula() DEV String
REQUIERE:Que exista un objeto formula.
MODIFICA:
EFECTOS: Devuelve en una cadena el tipo de Formula. Los tipos pueden ser dos: escala y general.

*/
    public void setTipoFormula(String tipoFormula) {
        this.tipoFormula = tipoFormula;
    }

    /*

PROC getTipoFormula() DEV String
REQUIERE:Que exista un objeto formula.
MODIFICA:
EFECTOS: Devuelve en una cadena el tipo de Formula. Los tipos pueden ser dos: escala y general.

*/
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    /*

PROC getTipoFormula() DEV String
REQUIERE:Que exista un objeto formula.
MODIFICA:
EFECTOS: Devuelve en una cadena el tipo de Formula. Los tipos pueden ser dos: escala y general.

*/
    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }
    /*

PROC getTipoFormula() DEV String
REQUIERE:Que exista un objeto formula.
MODIFICA:
EFECTOS: Devuelve en una cadena el tipo de Formula. Los tipos pueden ser dos: escala y general.

*/


    public void setExpresion(String expresion) {
        this.expresion = expresion;
    }


    /*

PROC getTipoFormula() DEV String
REQUIERE:Que exista un objeto formula.
MODIFICA:
EFECTOS: Devuelve en una cadena el tipo de Formula. Los tipos pueden ser dos: escala y general.

*/

    public Parametro[] getParametros() {
        return parametros;
    }
    /*

PROC getTipoFormula() DEV String
REQUIERE:Que exista un objeto formula.
MODIFICA:
EFECTOS: Devuelve en una cadena el tipo de Formula. Los tipos pueden ser dos: escala y general.

*/


    public void setParametros(Parametro[] parametros) {
        this.parametros = parametros;
    }

        /*

PROC getTipoFormula() DEV String
REQUIERE:Que exista un objeto formula.
MODIFICA:
EFECTOS: Devuelve en una cadena el tipo de Formula. Los tipos pueden ser dos: escala y general.

*/


    public Parametro getResultado() {
        return resultado;
    }

    /*

PROC getTipoFormula() DEV String
REQUIERE:Que exista un objeto formula.
MODIFICA:
EFECTOS: Devuelve en una cadena el tipo de Formula. Los tipos pueden ser dos: escala y general.

*/


    public void setIdFormula(Integer idFormula) {
        IdFormula = idFormula;
    }
    /*

PROC getTipoFormula() DEV String
REQUIERE:Que exista un objeto formula.
MODIFICA:
EFECTOS: Devuelve en una cadena el tipo de Formula. Los tipos pueden ser dos: escala y general.

*/

    public Integer getIdFormula() {
        return IdFormula;
    }
    /*

PROC getTipoFormula() DEV String
REQUIERE:Que exista un objeto formula.
MODIFICA:
EFECTOS: Devuelve en una cadena el tipo de Formula. Los tipos pueden ser dos: escala y general.

*/

    public int buscarPosicionDeParametro (Integer idParametro)
    {
        return 1;
    }
    /*

PROC getTipoFormula() DEV String
REQUIERE:Que exista un objeto formula.
MODIFICA:
EFECTOS: Devuelve en una cadena el tipo de Formula. Los tipos pueden ser dos: escala y general.

*/

    public Parametro getParametro (Integer posicion)
    {
        return parametros[posicion];
    }

    //Aqui acaban los getter y setter sencillos de la clase


    /*

    PROC contarPametros() DEV int
    REQUIERE:
    MODIFICA:
    EFECTOS: Muestra el numero de parametros de una formula

     */



    public int contarParametros()
    {
        return parametros.length ;
    }

    public void setResultado(Parametro resultado) {
        this.resultado = resultado;

    }

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

    public String calcularFormulaGeneral(){
        String resultado ="";

        Expression expression = new Expression(getExpresion());


        for (int i = 0; i < contarParametros(); i++) {
            if(i == 0 )
                expression.with(getParametro(i).getNombre(), getParametro(i).getValor());
            else
                expression.and(getParametro(i).getNombre(), getParametro(i).getValor());
        }

        //Si el parametro resultado tiene

        resultado =  expression.eval().toString() ;

        //Si el parametro resultado tiene criterios de puntuacion con su id hay que evaluar el resultado.
        if (getResultado().contarCriterios() > 0)
            resultado =  evaluarResultado(resultado);


        getResultado().setValor(resultado);

        return resultado;
    }

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
        if (getResultado().contarCriterios() > 0)
            resultado = evaluarResultado(resultado);

        getResultado().setValor(resultado);

        return resultado;
    }

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

    public boolean introducirValoresFormula (String vectorResultados[] )
    {
        for (int i =0 ;i < contarParametros(); i++)
        {
            /*
            //El valor del parametro dependera del tipo de este.
            //Tipo numero: El valor es el numero que se necesita para calcular la formula.
            //Tipo lista: Es el identificador del criterio de puntuacion que contiene el valor del parametro
            //Tipo logico: Si es 1 significa que esta checked si es 0 significa que no lo esta. En caso de estar chequeado
            //el valor del parametro sera el valor del criterio que tenga el idParametro de este campo.
            */
            int posicionCriterio;
            String  valorCriterio;


            switch (getParametro(i).getTipo())
            {
                case ("numero"):
                    getParametro(i).setValor(vectorResultados[i]);
                    if (getTipoFormula().equals("escala"))
                        getParametro(i).setValor(getParametro(i).evaluarPuntuacion(vectorResultados[i]));

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

    public String getBibliografia() {
        return bibliografia;
    }

    public void setBibliografia(String bibliografia) {
        this.bibliografia = bibliografia;
    }
}



