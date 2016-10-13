package com.example.alfonso.era04b;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SolicitarFormula extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitar_formula);
    }
}


/*

En esta pantalla aparece un pequeño formulario donde el usuario rellena.

Campo de Texto : Nombre de Formula.

Referencia Bibliográfica

Link:

Boton enviar

--> Cuando se pulse el boton enviar aparece el programa de correo y automanticamente se escribe el mensaje

El cidgo para mandarla es parecido a este.

Intent i = new Intent(Intent.ACTION_SEND);
i.setType("message/rfc822");
i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"recipient@example.com"});
i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
i.putExtra(Intent.EXTRA_TEXT   , "body of email");
try {
    startActivity(Intent.createChooser(i, "Send mail..."));
} catch (android.content.ActivityNotFoundException ex) {
    Toast.makeText(MyActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
}


Basicamente se debe meter el mensaje en una cadena de texto separado con sus saltos de lineas correspondiente en meterlo
en la cadena de body. Ya que se va a hacer asi se deberian cambiar los reportar error para que siga el mismo formato.

**Se puede poner una mensaje diciendo que para utilizar esta funcionalidad debe tener un cliente de correo instalado en su movil
* y conexion a internet.

 */