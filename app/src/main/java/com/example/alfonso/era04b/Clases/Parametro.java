package com.example.alfonso.era04b.Clases;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Alfonso on 20/10/2015.
 * Ultima modificación: 20/07/2016

 */


public class Parametro
{
    private Integer IdParametro;
    private String tipo;
    private String nombre;
    private CriterioPuntuacion [] criterio;
    private String medida;
    private String valor;
    private float ValorMinimo;
    private float ValorMaximo;
    //Este campo almacenara el valor de la suma total de la escala.
    private float PuntuacionEscala ;

       /*
         PROC Parametro (Constructor de la clase)
         REQUIERE: El identificador del parametro.
         MODIFICA:
         EFECTOS: Crea un Parametro obteniendo los datos de la base de datos Parametros.

          */


    public Parametro(String idParametro, Context context) {
        //Abrir la base de datos y buscar la formula que coincide con la id .

        FormulasSQLiteHelper usdbh =
                new FormulasSQLiteHelper(context ,"DbEra", null, 1);
        SQLiteDatabase db = usdbh.getReadableDatabase();

        //Inicializo el curso para recorrer los Parametros
        Cursor cursorParametros = db.rawQuery("SELECT NombreParametro,TipoParametro,Medida,Minimo,Maximo FROM Parametros  WHERE IdParametro = '" + idParametro + "'  ", null);
        cursorParametros.moveToFirst();

        //Asignamos el id del parametro
        setIdParametro(Integer.parseInt(idParametro));

        //Asignamos el nombre del Parametro
        setNombre(cursorParametros.getString(0));

        //Asignamos el tipo del Parametro
        setTipo(cursorParametros.getString(1));

        //Asignamos la medida
        setMedida(cursorParametros.getString(2));

        //En principio se asigna aunque sea NULL ya que no deberia dar problema***

        //Asignamos el valor minimo que puede tener el parametro
        setValorMinimo(cursorParametros.getInt(3));

        //Asignamos el valor maximo que puede tener el parametro
        setValorMaximo(cursorParametros.getInt(4));

        Cursor cursorCriterios = db.rawQuery("SELECT IdCriterioPuntuacion FROM CriteriosPuntuacion  WHERE IdParametro = '" + getIdParametro() + "'  ", null);
        cursorCriterios.moveToFirst();
        int numeroCriterios = cursorCriterios.getCount();
        db.close();


        //Inicializamos el vector de criterios de puntuacion
        criterio = new CriterioPuntuacion[numeroCriterios];



        //Ahora se debe hacer una consulta para encontrar los criterios de puntuacion que tiene este parametro
        if (numeroCriterios == 0)
        {
            //Sino tiene criterios de puntuacion aqui acaba la creación del parametro
            cursorCriterios.close();
        }

        else
        {

            for(int i=0; i < numeroCriterios; i++)
            {

                //Invocamos al constructor de la clase parametro y creamos un parametro
                CriterioPuntuacion criterioActual = new CriterioPuntuacion(cursorCriterios.getString(0),context);
                //Pasamos al siguiente parametro
                cursorCriterios.moveToNext();

                //Asignamos la posicion i del vector al parametro creado.
                criterio[i] = criterioActual;

            }
        }

        //Si tiene algun criterio de puntuacion se crean



    }

    //setters y getters sencillos de la clase



    //Obtiene la medida de un parametro.
    public String getMedida() {
        return medida;
    }

    //Obtiene el nombre de un parametro.
    public String getNombre() {
        return nombre;
    }

    //Obtiene el tipo de un parametro.
    public String getTipo() {
        return tipo;
    }

    //Asigna la medida a un parametro
    public void setMedida(String medida) {
        this.medida = medida;
    }

    //Asigna el nombre a un parametro
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //Asigna el tipo a un parametro
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    //Asigna el id a un parametro.
    public void setIdParametro(Integer idParametro) {
        IdParametro = idParametro;
    }

    //Obtiene el id de un parametro
    public Integer getIdParametro() {
        return IdParametro;
    }

    //Asigna el criterio a un parametro
    public void setCriterio(CriterioPuntuacion[] criterio) {
        this.criterio = criterio;
    }

    //Obtiene el criterio de un parametro
    public CriterioPuntuacion[] getCriterio() {
        return criterio;
    }

    //Obtiene el valor de un parametro
    public String getValor() {
        return valor;
    }

    //Asigna el valor de un parametro.
    public void setValor(String valor) {
        this.valor = valor;
    }

    //Obtiene el criterio de puntuación de un parametro indicandole la posición donde se encuentra.
    public CriterioPuntuacion getCriterioPuntuacion (Integer posicion)
    {
        return criterio[posicion];
    }

    //Obtiene el valor maximo de un parametro
    public float getValorMaximo() {
        return ValorMaximo;
    }

    //Obtiene el valor minimo de un parametro
    public float getValorMinimo() {
        return ValorMinimo;
    }

    //Asigna el valor maximo a un parametro
    public void setValorMaximo(int valorMaximo) {
        ValorMaximo = valorMaximo;
    }

    //Asigna el valor minimo a un parametro
    public void setValorMinimo(int valorMinimo) {
        ValorMinimo = valorMinimo;
    }

    //Obtiene la puntuación de un parametro de una formula de tipo escala.
    public float getPuntuacionEscala() {
        return PuntuacionEscala;
    }

    //Asigna la puntuación a un parametro de una formula de tipo escala
    public void setPuntuacionEscala(float puntuacionEscala) {
        PuntuacionEscala = puntuacionEscala;
    }

    //Valida si tienes el identificador del criterio que quieres buscar
    public int buscarPosicionDeCriterio (Integer idCriTerioPuntuacion) {

        int posicionCriterio=-1;

        for(int i =0; i < contarCriterios(); i++ )
        {
            if (criterio[i].getIdCriterioPuntuacion().equals(idCriTerioPuntuacion))
                posicionCriterio = i;
        }

        return posicionCriterio;
    }

    //Solo es valida para el tipo logico devuelve la primera posicion del vector
    public int buscarPosicionDeCriterio () {

        return 0;
    }


    //Esta funcion recibe un valor para un criterio y devuelve que puntuacion le corresponde con ese valor.
    public String evaluarPuntuacion (String puntuacion)
    {
        String resultado="";

        for(int i =0; i < contarCriterios() ; i++ ) {

            if(getCriterioPuntuacion(i).getPuntuacion().equals("x"))
            {
                resultado = puntuacion;
            }
            else {

                Expression evaluador = new Expression(getCriterioPuntuacion(i).getCriterio());
                evaluador.with("x", puntuacion);

                if (evaluador.eval().toString().equals("1")) {
                    resultado = getCriterioPuntuacion(i).getPuntuacion();
                }
            }
        }

        return resultado;
    }


    //Cuenta los criterios del Parametro.
    public int contarCriterios()
    {
        return criterio.length ;

    }



}
