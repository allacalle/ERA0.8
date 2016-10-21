package com.example.alfonso.era04b;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ReportarError extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportar_error);

        // Hacemos referencia al boton BtnSolicitarFormula
        Button btnReportarError = (Button) findViewById(R.id.BtnReportarError) ;


        final EditText etxTipoError = (EditText) findViewById(R.id.EtxTipoError);
        final EditText etxDescripcion = (EditText) findViewById(R.id.EtxDescripcion);

        btnReportarError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textoMensaje = "Tipo de Error:" + etxTipoError.getText().toString() + "\n"  + "Descripcion:" +  etxDescripcion.getText().toString()  ;


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



    }
}
