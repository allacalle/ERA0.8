package com.example.alfonso.era04b;



import android.content.Context;
import android.test.InstrumentationTestCase;

import com.example.alfonso.era04b.Clases.Formula;

public class TestAclaramientoCreatinina extends InstrumentationTestCase {

    public void testResultadoAclaramientoCreatinina () throws Exception
    {
        double expected ;
        double reality ;
        String idFormula ="";
        idFormula = "10";
        //IMPORTANTE como llamar al contexto desde el TEST
        Context context = this.getInstrumentation().getTargetContext().getApplicationContext();
        final Formula formulaActual = new Formula(idFormula, context);


        //Asignamos los valores que se han introducido por la pantalla

        //Calculos para una formula de hombres

        //Que usa el siguiente vector
        String vectorResultados [] = new String[4];
        vectorResultados[0] = "50";
        vectorResultados[1] = "76";
        vectorResultados[2] = "5";
        //Valor hombre
        vectorResultados[3] = "127";

        formulaActual.introducirValoresFormula(vectorResultados);

        formulaActual.calcularFormula();

        expected = 19;
        reality = Float.parseFloat(formulaActual.getResultado().getValor()) ;

        //Ahora lo redondeamos para que sea como expected
        reality = Math.round(reality*100.0)/100.0;

        assertEquals(expected, reality);


        //Calculos para una formula de mujeres

        //Que usa el siguiente vector
        vectorResultados[0] = "50";
        vectorResultados[1] = "66";
        vectorResultados[2] = "6";
        //Valor mujer
        vectorResultados[3] = "128";

        formulaActual.introducirValoresFormula(vectorResultados);

        formulaActual.calcularFormula();

        expected = 11.69;
        reality = Float.parseFloat(formulaActual.getResultado().getValor()) ;

        //Ahora lo redondeamos para que sea como expected
        reality = Math.round(reality*100.0)/100.0;

        assertEquals(expected, reality);


    }
}



