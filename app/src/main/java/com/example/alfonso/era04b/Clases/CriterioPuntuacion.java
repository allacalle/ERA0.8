package com.example.alfonso.era04b.Clases;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Alfonso on 11/03/2016.
 */
public class CriterioPuntuacion
{
    private Integer IdCriterioPuntuacion;
    private String Criterio;
    private String puntuacion;

    //Constructor de la clase
    public CriterioPuntuacion (String idCriterioPuntuacion, Context context) {

        //Abrir la base de datos y buscar el criterio de puntuacion que coincide con la id

        FormulasSQLiteHelper usdbh =
                new FormulasSQLiteHelper(context ,"DbEra", null, 1);
        SQLiteDatabase db = usdbh.getReadableDatabase();

        //Inicializo el curso para recorrer los criterios de puntuacion
        Cursor cursorCriteriosPuntuacion = db.rawQuery("SELECT Criterio,Puntuacion FROM CriteriosPuntuacion  WHERE IdCriterioPuntuacion = '" + idCriterioPuntuacion + "'  ", null);
        cursorCriteriosPuntuacion.moveToFirst();

        //Asignamos el id del criterio de puntuacion
        setIdCriterioPuntuacion(Integer.parseInt(idCriterioPuntuacion));
        //Asignamos el criterio
        setCriterio(cursorCriteriosPuntuacion.getString(0));

        //Asignamos la puntuacion
        setPuntuacion(cursorCriteriosPuntuacion.getString(1));

        cursorCriteriosPuntuacion.close();
        db.close();

    }

    public Integer getIdCriterioPuntuacion() {
        return IdCriterioPuntuacion;
    }

    public String getCriterio() {
        return Criterio;
    }

    public String getPuntuacion() {
        return puntuacion;
    }

    public void setCriterio(String criterio) {
        Criterio = criterio;
    }

    public void setIdCriterioPuntuacion(Integer idCriterioPuntuacion) {
        IdCriterioPuntuacion = idCriterioPuntuacion;
    }

    public void setPuntuacion(String puntuacion) {
        this.puntuacion = puntuacion;
    }


}


//Clase donde se implementara la clase de criterio de puntuación.

//Cada parametro puede contener varios criterios de puntuacion que  ayudan a asignar una puntuación a ese parametro.

//No todos los parametros tienen criterio de puntuacion, pero todos los parametros de una formula de escala lo suelen tener casi siempre


