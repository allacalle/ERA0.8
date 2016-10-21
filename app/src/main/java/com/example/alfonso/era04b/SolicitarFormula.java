package com.example.alfonso.era04b;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SolicitarFormula extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitar_formula);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Hacemos referencia al boton BtnSolicitarFormula
        Button btnSolicitarFormula = (Button) findViewById(R.id.BtnSolicitarFormula) ;


        final EditText etxNombre = (EditText) findViewById(R.id.EtxNombre);
        final EditText etxReferencia = (EditText) findViewById(R.id.EtxReferencia);
        final EditText etxLink = (EditText) findViewById(R.id.EtxLink);





        btnSolicitarFormula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textoMensaje = "Nombre Formula:" + etxNombre.getText().toString() + "\n"  + "Referencia:" +  etxReferencia.getText().toString() + "\n" + "Link" +  etxLink.getText().toString() ;


                String[] to = { "eracontacto@yopmail.com", "eracontacto@yopmail.com" };
                String[] cc = { "eracontacto@yopmail.com" };
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
                emailIntent.putExtra(Intent.EXTRA_CC, cc);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Solicitar Formula");
                emailIntent.putExtra(Intent.EXTRA_TEXT, textoMensaje);
                emailIntent.setType("message/rfc822");
                startActivity(Intent.createChooser(emailIntent, "Email "));

            }
        });


        /*
        */



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