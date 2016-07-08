package com.example.alfonso.era04b;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.alfonso.era04b.Clases.FormulasSQLiteHelper;

public class MainActivity extends AppCompatActivity {

    boolean prioridadVacia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Abrimos la base de datos 'DbEra' en modo escritura


        FormulasSQLiteHelper usdbh =
                new FormulasSQLiteHelper(this, "DbEra", null, 1);

        SQLiteDatabase db = usdbh.getWritableDatabase();

        if (db != null) {
            Cursor c = db.rawQuery(" SELECT  COUNT(*) FROM Prioridad  ", null);
            c.moveToFirst();
            String nCampos = c.getString(0);


            if (nCampos.equals("0")) {

                prioridadVacia = true;
            }

            /*

            c = db.rawQuery(" SELECT  COUNT(*) FROM Formulas  ", null);
            c.moveToFirst();
            nCampos = c.getString(0);
            if (nCampos.equals("0")) {

                //Creamos la base de datos con la consulta SQL.


//Child-Pugh
//Formula
                db.execSQL("INSERT INTO Formulas (IdFormula,NombreCompleto,Abreviatura,Tipo,Bibliografia) VALUES ('1','Child- Pugh','Child-Pugh','escala','Fauci AS, Kasper DL, Braunwald E, Hauser SL, Longo DL, Jameson JL, Loscalzo J, editors.Harrison. Principios de Medicina Interna.2011.18e.Vol 1.New York: McGraw Hill. ');");


//Parametros
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro,Medida,Minimo,Maximo) VALUES ('1','Billirumina','1','numero','mg/100 ml','0','150');");

                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro,Medida,Minimo,Maximo) VALUES ('2','Albumina serica','1','numero','g/l','0','100');");

                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro,Medida,Minimo,Maximo) VALUES ('3','Tiempo de protombina','1','numero','segundos','0','100');");

                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro)  VALUES ('4','Ascitis','1','lista');");

                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro)  VALUES ('5','Encefalopatia de origen hepatico','1','lista');");

//Resultado
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro)  VALUES ('6','ChildPugh','1','resultado');");

//Criterios de puntuación
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('1','1','x < 2','1');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('2','1','x>= 2 && x<=3','2');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('3','1','x> 3','3');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('4','2','x > 35','1');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('5','2','x >= 30 && x <= 35','2');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('6','2','x < 30','3');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('7','3','x >= 1 && x <= 3','1');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('8','3','x >= 4 && x <= 6','2');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('9','3','x > 6','3');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('10','4','Ninguna','1');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('11','4','Control facil','2');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('12','4','Control inadecuado','3');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('13','5','Ninguna','1');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('14','5','Minima','2');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('15','5','Fase avanzada','3');");
//Resultado
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('16','6','x >= 5 && x <= 6','Clase A. La supervivencia al cabo de 1 año es del 100%');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('17','6','x >= 7 && x <= 9','Clase B. La supervivencia al cabo de 1 año es del 81%');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('18','6','x >= 10 && x <= 15','Clase C. La supervivencia al cabo de 1 año es del 45%.');");


//Glasgow, Escala de Coma

//Formula
                db.execSQL("INSERT INTO Formulas (IdFormula,NombreCompleto,Abreviatura,Tipo,Bibliografia) VALUES ('2','Glasgow, Escala de Coma','Glasgow, Escala de Coma','escala','Luis Jiménez Murillo,Francisco Javier Montero Pérez;Medicina de urgencias y emergencias. 2015. 5 edición. p. 822. ISBN: 978-84-9022-454-0 .');");
//Parámetros
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro) VALUES ('7','Ojos,apertura','2','lista');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro) VALUES ('8','Verbal(mejor)','2','lista');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro)  VALUES ('9','Motor(mejor)','2','lista');");
//Resultado
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro)  VALUES ('10','Glasgow','2','resultado');");
                //Criterios de puntuación
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('19','7','De forma espontanea','4');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('20','7','Tras una orden verbal','3');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('21','7','Al estimulo doloroso','2');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('22','7','No los abre','1');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('23','8','Orientado','5');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('24','8','Conversacion  confusa','4');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('25','8','Palabras inapropiadas','3');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('26','8','Sonido incomprensibles','2');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('27','8','Ninguna','1');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('28','9','Obedece la orden','6');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('29','9','Localiza el estimulo','5');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('30','9','Retira','4');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('31','9','Flexion anormal','3');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('32','9','Extension','2');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('33','9','Ninguna','1');");
//Resultado
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('34','10','x >= 14 && x <= 15','Traumatismo craneoencefalico leve.');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('35','10','x >= 9 && x <= 13','Traumatismo craneoencefalico moderado.');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('36','10','x < 9','Traumatismo craneoencefalico grave.');");


//CHA2DS2-VASc, Escala de Riesgo de AVC por Fibrilación Atrial

//Formula
                db.execSQL("INSERT INTO Formulas (IdFormula,NombreCompleto,Abreviatura,Tipo,Bibliografia) VALUES ('3',' CHA2DS2-VASc, Escala de Riesgo de AVC por Fibrilación Atrial','CHA2DS2-VASc','escala','Luis Jiménez Murillo,Francisco Javier Montero Pérez;Medicina de urgencias y emergencias. 2015. 5 edición. p. 166. ISBN: 978-84-9022-454-0 .');");
//Parámetros
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro)  VALUES ('11','Insuficiencia cardiaca congestiva/disfuncion ventricular izquierda','3','logico');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro)  VALUES ('12','Hipertensión arterial','3','logico');");
//Hipertensión lleva tilde tenedlo en cuenta para probar el correcto uso de las tildes
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro,Medida,Minimo,Maximo) VALUES ('13','Edad','3','numero','años','0','120');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro)  VALUES ('14','Diabetes mellitus','3','logico');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro)  VALUES ('15','Accidente cerebrovascular/tromboembolia','3','logico');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro)  VALUES ('16','Enfermedad vascular','3','logico');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro)  VALUES ('17','Sexo femenino','3','logico');");

//Resultado
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro)  VALUES ('18','CHA2DS2-VASc','3','resultado');");
//Criterios de puntuación
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('37','11','Insuficiencia cardíaca congestiva/disfuncion ventricular izquierda','1');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('38','12','Hipertensión arterial','1');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('39','13','x >= 75','2');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('40','13','x >= 65 && x <= 74','1');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('41','13','x <65','0');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('42','14','Diabetes mellitus','1');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('43','15','Accidente cerebrovascular/tromboembolia','2');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('44','16','Enfermedad vascular','1');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('45','17','Sexo femenino','1');");
//Resultado
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('46','18','x== 0','Riesgo Bajo. No precisa anticoagulacion.');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('47','18','x == 1','Riesgo Moderado. Considerar antiagregacion o anticoagulacion');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('48','18','x >= 2','Riesgo Moderado-Alto. Anticoagulacion, salvo contraindicacion.');");


//Pneumonia Severity Index diagnostico inicial

//Formula
                db.execSQL("INSERT INTO Formulas (IdFormula,NombreCompleto,Abreviatura,Tipo,Bibliografia) VALUES ('4','Pneumonia Severity Index diagnostico inicial','PSI inicial','escala','Fine, MJ; Auble, TE; Yealy, DM; Hanusa, BH; Weissfeld, LA; Singer, DE; Coley, CM; Marrie, TJ; Kapoor, WN; et al. (Jan 1997).A prediction rule to identify low-risk patients with community-acquired pneumonia. N Engl J Med 336 (4): 243–250. doi:10.1056/NEJM199701233360402');");
                //Parámetros
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro)  VALUES ('19','Edad > 50 años','4','logico');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro)  VALUES ('20','Enfermedad neoplasica','4','logico');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro)  VALUES ('21','Enfermedad hepatica','4','logico');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro)  VALUES ('22','Insuficiencia cardiaca','4','logico');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro)  VALUES ('23','Enfermedad cerebrobascular','4','logico');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro)  VALUES ('24','Enfermedad renal','4','logico');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro)  VALUES ('25','Confusion','4','logico');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro)  VALUES ('26','Tension arterial sistotica < 90 mmHg','4','logico');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro)  VALUES ('27','Frecuencia cardiaca >= 125/minuto','4','logico');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro)  VALUES ('28','Frecuencia respiratoria >= 30/minuto','4','logico');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro)  VALUES ('29','Tª axilar <35 o >40 ºC','4','logico');");

//Resultado
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro)  VALUES ('30','PSI-Inicial','4','resultado');");
//Criterios de puntuación
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('49','19','Edad > 50 años','1');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('50','20','Enfermedad neoplasica','1');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('51','21','Enfermedad hepatica','1');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('52','22','Insuficiencia cardiaca','1');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('53','23','Enfermedad cerebrobascular','1');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('54','24','Enfermedad renal','1');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('55','25','Confusion','1');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('56','26','Tension arterial sistotica < 90 mmHg','1');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('57','27','Frecuencia cardiaca >= 125/minuto','1');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('58','28','Frecuencia respiratoria >= 30/minuto','1');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('59','29','Tª axilar <35 o >40 ºC','1');");


//Resultado
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('60','30','x >= 1','El paciente muestra signos de riesgo. Si quiere calcular la puntuacion del PSI utlice la formula de PSI puntuacion.');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('61','30','x <1','Bajo I. Mortalidad 0.1% . Domicilio.');");




//Pneumonia Severity Index puntuacion

//Formula
                db.execSQL("INSERT INTO Formulas (IdFormula,NombreCompleto,Abreviatura,Tipo,Bibliografia) VALUES ('5','Pneumonia Severity Index puntuacion','PSI puntuacion','escala','Fine, MJ; Auble, TE; Yealy, DM; Hanusa, BH; Weissfeld, LA; Singer, DE; Coley, CM; Marrie, TJ; Kapoor, WN; et al. (Jan 1997).A prediction rule to identify low-risk patients with community-acquired pneumonia. N Engl J Med 336 (4): 243–250. doi:10.1056/NEJM199701233360402');");
//Parámetros
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro)  VALUES ('31','Sexo femenino','5','logico');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro,Medida,Minimo,Maximo) VALUES ('32','Edad','5','numero','años','0','120');");

                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro)  VALUES ('33','Procedente de residencia','5','logico');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro)  VALUES ('34','Enfermedad neoplastica','5','logico');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro)  VALUES ('35','Enfermedad hepatica','5','logico');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro)  VALUES ('36','Insuficiencia cardiaca congestiva','5','logico');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro)  VALUES ('37','Enfermedad cerebrovascular','5','logico');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro)  VALUES ('38','Enfermedad renal','5','logico');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro)  VALUES ('39','Tension arterial sistolica < 90 mmHg','5','logico');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro)  VALUES ('40','Frecuecia cardiaca >= 125/minuto','5','logico');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro)  VALUES ('41','Frecuencia respiratoria >= 30/minuto','5','logico');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro)  VALUES ('42','Tª axilar <35 o >40ºC','5','logico');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro)  VALUES ('43','Ph arterial < 7.35','5','logico');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro)  VALUES ('44','PO2 < 60 mmHg o Sat O2 <90%','5','logico');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro)  VALUES ('45','Urea >64 mg/dl o Cr > 1.5 mg/dl','5','logico');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro)  VALUES ('46','Sodio <130 mEq/l','5','logico');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro)  VALUES ('47','Glucemia > 250 mg/dl','5','logico');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro)  VALUES ('48','Hematocrito <30%','5','logico');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro)  VALUES ('49','Derrame pleural','5','logico');");

//Resultado
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro)  VALUES ('50','PSI-Puntuacion','5','resultado');");

//Criterios de puntuación
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('62','31','Sexo femenino','-10');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('63','32','Edad','x');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('64','33','Procedente de residencia','10');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('65','34','Enfermedad neoplastica','30');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('66','35','Enfermedad hepatica','20');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('67','36','Insuficiencia cardiaca congestiva','10');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('68','37','Enfermedad cerebrovascular','10');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('69','38','Enfermedad renal','10');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('70','39','Tension arterial sistolica < 90 mmHg','20');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('71','40','Frecuecia cardiaca >= 125/minuto','10');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('72','41','Frecuencia respiratoria >= 30/minuto','20');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('73','42','Tª axilar <35 o >40ºC','15');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('74','43','Ph arterial < 7.35','30');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('75','44','PO2 < 60 mmHg o Sat O2 <90%','10');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('76','45','Urea >64 mg/dl o Cr > 1.5 mg/dl','20');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('77','46','Sodio <130 mEq/l','20');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('78','47','Glucemia > 250 mg/dl','10');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('79','48','Hematocrito <30%','10');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('80','49','Derrame pleural','10');");

//Resultado
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('81','50','x <51','Bajo I. Mortalidad 0.1% . Domicilio.');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('82','50','x >= 51 &&  x <= 70','Bajo II.Mortalidad 0.6% . Domicilio. (algunos observacion 24 horas y reevaluacion)');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('83','50','x >= 71 &&  x <= 90','Bajo III.Mortalidad 2.8%. Observacion o unidad corta estancia (UCE) 24-72 h y reevaluacion.');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('84','50','x > 90 &&  x <= 130','Medio IV.Mortalidad 8.2%. Ingreso hospitalario (planta o UCI)');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('85','50','x > 130','Alto V. Ingreso hospitalario (planta o UCI)');");


//TIMI Puntuacion de riesgo

//Formula
                db.execSQL("INSERT INTO Formulas (IdFormula,NombreCompleto,Abreviatura,Tipo,Bibliografia) VALUES ('6','The Thrombolysis In Myocardial Infarction (TIMI)','TIMI','escala','Morrow DA1, Antman EM, Charlesworth A, Cairns R, Murphy SA, de Lemos JA, Giugliano RP, McCabe CH, Braunwald E, TIMI risk score for ST-elevation myocardial infarction: A convenient, bedside, clinical score for risk assessment at presentation: An intravenous nPA for treatment of infarcting myocardium early II trial substudy, Clinical Investigation and Reports, 2000; 102: 2031-2037 doi: 10.1161/01.CIR.102.17.2031');");
//Parámetros
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro,Medida,Minimo,Maximo) VALUES ('51','Edad','6','numero','años','0','120');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro) VALUES ('52','Diabetes meditus o  hipertensión arterial  o angina','6','logico');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro) VALUES ('53','Presión arterial sistólica <100mmHg','6','logico');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro) VALUES ('54','Frecuencia cardiaca >100 lat/min','6','logico');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro) VALUES ('55','Killip lll  o  lV','6','logico');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro) VALUES ('56','Peso <67 kg','6','logico');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro) VALUES ('57','Infarto agudo de miocardio anterior (IAM) o bloqueo de rama izquierda (BRI)','6','logico');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro) VALUES ('58','Retraso en la terapia >4 h','6','logico');");
//Resultado
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro) VALUES ('59','TIMI Puntuacion de riesgo','6','resultado');");
//Criterios de puntuación
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('86','51','x >= 75','3');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('87','51','x >= 65 && x <= 74','2');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('88','51','x <65','0');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('89','52','Diabetes meditus o  hipertensión arterial  o angina','1');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('90','53','Presión arterial sistólica <100mmHg','3');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('91','54','Frecuencia cardiaca >100 lat/min','2');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('92','55','Killip lll  o  lV','2');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('93','56','Peso <67 kg','1');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('94','57','Infarto agudo de miocardio anterior (IAM) o bloqueo de rama izquierda (BRI)','1');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('95','58','Retraso en la terapia >4 h','1');");
//Resultado
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('96','59','x == 0',' Mortalidad en 30 días del 0.8%');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('97','59','x == 1','Mortalidad en 30 días del  1.6%');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('98','59','x == 2',' Mortalidad en 30 días del 2.2%');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('99','59','x ==3','Mortalidad en 30 días del 4.4%');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('100','59','x ==  4','Mortalidad en 30 días del 7.3%');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('101','59','x ==  5','Mortalidad en 30 días del 12.4%');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('102','59','x == 6','Mortalidad en 30 días del 16.1%');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('103','59','x ==  7','Mortalidad en 30 días del 23.4%');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('104','59','x == 8','Mortalidad en 30 días del 26.8%');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('105','59','x  >8','Mortalidad en 30 días del 35.9%');");




//Wells Score para Embolismo Pulmonar

//Formula
                db.execSQL("INSERT INTO Formulas (IdFormula,NombreCompleto,Abreviatura,Tipo,Bibliografia) VALUES ('7','Wells Score para Embolismo Pulmonar','Wells Score para Embolismo Pulmonar','escala','Luis Jiménez Murillo,Francisco Javier Montero Pérez;Medicina de urgencias y emergencias. 2015. 5 edición. p. 241. ISBN: 978-84-9022-454-0 .');");
//Parámetros
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro)  VALUES ('60','Historia previa de ETV','7','logico');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro)  VALUES ('61','Frecuencia cardiaca 1,5 >100 lat/min','7','logico');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro)  VALUES ('62','Cirugia reciente/ 1,5 inmovilizacion','7','logico');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro)  VALUES ('63','Hemoptisis','7','logico');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro)  VALUES ('64','Cancer no controlado','7','logico');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro)  VALUES ('65','Signos clinicos de TVP','7','logico');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro)  VALUES ('66','Otra alternativa diagnostica es menos probable que TEP','7','logico');");
//Resultado
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro)  VALUES ('67','Wells Score para Embolismo Pulmonar','7','resultado');");

//Criterios de puntuación
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('106','60','Historia previa de ETV','1.5');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('107','61','Frecuencia cardiaca >100 lat/min','1.5');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('108','62','Cirugia reciente/inmovilizacion','1.5');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('109','63','Hemoptisis','1');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('110','64','Cancer no controlado','1');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('111','65','Signos clinicos de TVP','3');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('112','66','Otra alternativa diagnostica es menos probable que TEP','3');");

//Resulado
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('113','67','x <2','Probabilidad baja de  tromboembolia pulmonar.');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('114','67','x >=2 && x <= 6','Probabilidad intermedia de tromboembolia pulmonar.');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('115','67','x >= 7','Probabidad alta de tromboembolia pulmonar.');");


//Wells, Score para Trombosis Venosa Profunda

//Formula
                db.execSQL("INSERT INTO Formulas (IdFormula,NombreCompleto,Abreviatura,Tipo,Bibliografia) VALUES ('8','Wells, Score para Trombosis Venosa Profunda','Wells, Score para Trombosis Venosa Profunda','escala','Luis Jiménez Murillo,Francisco Javier Montero Pérez;Medicina de urgencias y emergencias. 2015. 5 edición. p. 229. ISBN: 978-84-9022-454-0 .');");
//Parámetros
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro)  VALUES ('68','Cancer activo (tratamiento en los ultimos 6 meses o en tratamiento paliativo)','8','logico');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro)  VALUES ('69','Parálisis, paresia o inmovilización reciente de miembros inferiores','8','logico');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro)  VALUES ('70','Encamamiento de 3 días (o mayor) o cirugía con anestesia general','8','logico');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro)  VALUES ('71','Hinchazon de toda la pierna','8','logico');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro)  VALUES ('72','Hinchazon de la pantorilla  de 3 cm  (o mayor) respecto a la pierna asintomática','8','logico');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro)  VALUES ('73','Edema con fovea en la pierna sintomática','8','logico');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro)  VALUES ('74','Venas superficiales colaterales (no varicosas)','8','logico');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro)  VALUES ('75','Diagnóstico alternativo al menos tan probable como trombosis venosa profunda','8','logico');");
//Resultado
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro)  VALUES ('76','Wells Score para Trombosis Venosa Profunda','8','resultado');");
//Criterios de puntuación
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('116','68','Cancer activo (tratamiento en los ultimos 6 meses o en tratamiento paliativo)','1');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('117','69','Parálisis, paresia o inmovilización reciente de miembros inferiores','1');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('118','70','Encamamiento de 3 días (o mayor) o cirugía con anestesia general','1');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('119','71','Hinchazon de toda la pierna','1');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('120','72','Hinchazon de la pantorilla  de 3 cm  (o mayor) respecto a la pierna asintomática','1');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('121','73','Edema con fovea en la pierna sintomática','1');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('122','74','Venas superficiales colaterales (no varicosas)','1');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('123','75','Diagnóstico alternativo al menos tan probable como trombosis venosa profunda','-2');");

//Resultado
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('124','76','x <= 0','Probabilidad baja de  trombosis venosa profunda');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('125','76','x >0 && x <= 2','Probabilidad intermedia de trombosis venosa profunda');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('126','76','x >= 3','Probabilidad alta de trombosis venosa profunda');");




//BUN:Creatinina (S) relación

//Formula
                db.execSQL("INSERT INTO Formulas (IdFormula,NombreCompleto,Abreviatura,Tipo,Expresion,Bibliografia) VALUES ('9','BUN:Creatinina (S) relación ','BUN','general','Urea/2.14','Luis Jiménez Murillo,Francisco Javier Montero Pérez;Medicina de urgencias y emergencias. 2015. 5 edición. p. 34. ISBN: 978-84-9022-454-0 . ');");
//Parámetros
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro,Medida,Minimo,Maximo) VALUES ('77','Urea','9','numero','mg','0','1000');");
//Resultado
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro,Medida)  VALUES ('78','BUN','9','resultado','mg');");

//Aclaramiento de creatinina, formula de CockcroftGault:

//Formula
                db.execSQL("INSERT INTO Formulas (IdFormula,NombreCompleto,Abreviatura,Tipo,Bibliografia) VALUES ('10','Aclaramiento de creatinina, formula de Cockcroft-Gault ','Aclaramiento de creatinina','general','Luis Jiménez Murillo,Francisco Javier Montero Pérez;Medicina de urgencias y emergencias. 2015. 5 edición. p. 34. ISBN: 978-84-9022-454-0 .');");
//Parámetros
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro,Medida,Minimo,Maximo) VALUES ('79','Edad','10','numero','años','0','120');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro,Medida,Minimo,Maximo) VALUES ('80','Peso','10','numero','kg','25','400');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro,Medida,Minimo,Maximo) VALUES ('81','Crp','10','numero','mg/dl','0','10');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro)  VALUES ('82','Sexo','10','lista');");
//Resultado
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro,Medida)  VALUES ('83','Aclaramiento de la creatinina','10','resultado','ml/min');");
//Criterios de puntuación
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('127','82','Hombre','((140-Edad)*Peso)/(Crp*72)');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('128','82','Mujer','((140-Edad)*Peso)/(Crp*72)*0.85');");


//Agua Libre, Exceso

//Formula
                db.execSQL("INSERT INTO Formulas (IdFormula,NombreCompleto,Abreviatura,Tipo,Expresion,Bibliografia) VALUES ('11','Agua Libre, Exceso ','Agua Libre, Exceso','general','(Agua/100)*Peso*(1- (Sodio/140))','Luis Jiménez Murillo,Francisco Javier Montero Pérez;Medicina de urgencias y emergencias. 2015. 5 edición. p. 457. ISBN: 978-84-9022-454-0 .');");
//Parámetros
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro,Medida,Minimo,Maximo) VALUES ('84','Agua','11','numero','%','0','100');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro,Medida,Minimo,Maximo) VALUES ('85','Peso','11','numero','kg','25','400');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro,Medida,Minimo,Maximo) VALUES ('86','Sodio','11','numero','mEq/l','80','200');");
//Resultado
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro,Medida) VALUES ('87','Agua libre, exceso','11','resultado','l');");

//Agua Corporal, Déficit

//Formula
                db.execSQL("INSERT INTO Formulas (IdFormula,NombreCompleto,Abreviatura,Tipo,Expresion,Bibliografia) VALUES ('12','Agua Corporal, Deficit ','Agua Corporal, Deficit ','general','(Agua/100)*Peso*((Sodio/140)-1)','Luis Jiménez Murillo,Francisco Javier Montero Pérez;Medicina de urgencias y emergencias. 2015. 5 edición. p. 457. ISBN: 978-84-9022-454-0 .');");
//Parámetros
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro,Medida,Minimo,Maximo) VALUES ('88','Agua','12','numero','%','0','100');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro,Medida,Minimo,Maximo) VALUES ('89','Peso','12','numero','kg','25','400');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro,Medida,Minimo,Maximo) VALUES ('90','Sodio','12','numero','mEq/l','80','200');");
                //Resultado
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro,Medida) VALUES ('91','Agua corporal,deficit','12','resultado','l');");

//Cloro, Deficit
//Formula
                db.execSQL("INSERT INTO Formulas (IdFormula,NombreCompleto,Abreviatura,Tipo,Expresion,Bibliografia) VALUES ('13','Cloro, Deficit','Cloro, Deficit ','general','Peso*(Cloro_Volumen_de_Distribucion/100)*(Cloro_deseado  - Cloro_medido )','Fauci AS, Kasper DL, Braunwald E, Hauser SL, Longo DL, Jameson JL, Loscalzo J, editors.Harrison. Principios de Medicina Interna.2011.18e.Vol 1.New York: McGraw Hill. ');");
//Parámetros
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro,Medida,Minimo,Maximo) VALUES ('92','Peso','13','numero','kg','25','400');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro,Medida,Minimo,Maximo) VALUES ('93','Cloro_deseado','13','numero','mEq/l','50','150');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro,Medida,Minimo,Maximo) VALUES ('94','Cloro_medido','13','numero','mEq/l','50','150');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro,Medida,Minimo,Maximo)  VALUES ('95','Cloro_Volumen_de_Distribucion','13','numero','%','0','100');");
//Resultado
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro,Medida)  VALUES ('96','Cloro,deficit','13','resultado','mEq');");

//Superficie Corporal Total

//Formula
                db.execSQL("INSERT INTO Formulas (IdFormula,NombreCompleto,Abreviatura,Tipo,Expresion,Bibliografia) VALUES ('14','Superficie Corporal Total','Superficie Corporal Total ','general','(Peso^0.425 * Estatura^0.725) * 0.007184','Fauci AS, Kasper DL, Braunwald E, Hauser SL, Longo DL, Jameson JL, Loscalzo J, editors.Harrison. Principios de Medicina Interna.2011.18e.Vol 1.New York: McGraw Hill. ');");
//Parámetros
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro,Medida,Minimo,Maximo) VALUES ('97','Peso','14','numero','kg','25','400');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro,Medida,Minimo,Maximo)  VALUES ('98','Estatura','14','numero','cm','100','250');");
//Resultado
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro,Medida)  VALUES ('99','Superficie Corporal Total','14','resultado','metros cuadrados');");

//Gasto de Energía Basal

//Formula
                db.execSQL("INSERT INTO Formulas (IdFormula,NombreCompleto,Abreviatura,Tipo,Expresion,Bibliografia) VALUES ('15','Gasto de Energía Basal','Gasto de Energía Basal','general','','Fauci AS, Kasper DL, Braunwald E, Hauser SL, Longo DL, Jameson JL, Loscalzo J, editors.Harrison. Principios de Medicina Interna.2011.18e.Vol 1.New York: McGraw Hill. ');");
//Parámetros
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro,Medida,Minimo,Maximo) VALUES ('100','Peso','15','numero','kg','25','400');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro,Medida,Minimo,Maximo) VALUES ('101','Estatura','15','numero','cm','100','250');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro,Medida,Minimo,Maximo)  VALUES ('102','Edad','15','numero','años','0','120');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro)  VALUES ('103','Sexo','15','lista');");
//Resultado
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro,Medida)  VALUES ('104','Gasto de energía basal','15','resultado','Kcal/dia');");
//Cambiar esto en la base de datos
//Criterios de puntuación
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('129','103','Hombre','66.47 +(13.75*Peso) + (5.00*Estatura) -(6.76*Edad) ');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('130','103','Mujer','65.51 + (9.56 *Peso) + (1.85*Estatura) - (4.68*Edad)');");



//Osmolaridad calculada

//Formula
                db.execSQL("INSERT INTO Formulas (IdFormula,NombreCompleto,Abreviatura,Tipo,Expresion,Bibliografia) VALUES ('16','Osmolaridad','Osmolaridad','general','(2*(Sodio +Potasio) )+(Glucemia/18) + (Urea/6)','Luis Jiménez Murillo,Francisco Javier Montero Pérez;Medicina de urgencias y emergencias. 2015. 5 edición. p. 7. ISBN: 978-84-9022-454-0 .');");
//Parámetros
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro,Medida,Minimo,Maximo)VALUES ('105','Sodio','16','numero','mEq/l','80','200');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro,Medida,Minimo,Maximo) VALUES ('106','Potasio','16','numero','mEq/l','0','20');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro,Medida,Minimo,Maximo) VALUES ('107','Glucemia','16','numero','mg/dl','0','1000');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro,Medida,Minimo,Maximo) VALUES ('108','Urea','16','numero','mg/dl','0','1000');");
//Resultado
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro,Medida)  VALUES ('109','Osmolaridad','16','resultado','mOsm/l');");


//Agua Corporal Total

//Formula
                db.execSQL("INSERT INTO Formulas (IdFormula,NombreCompleto,Abreviatura,Tipo,Expresion,Bibliografia) VALUES ('17','Agua Corporal Total','Agua Corporal Total','general','(Agua/100)*Peso','Luis Jiménez Murillo,Francisco Javier Montero Pérez;Medicina de urgencias y emergencias. 2015. 5 edición. p. 457. ISBN: 978-84-9022-454-0 .');");
//Parámetros
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro,Medida,Minimo,Maximo) VALUES ('110','Peso','17','numero','kg','25','400');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro,Medida,Minimo,Maximo)  VALUES ('111','Agua','17','numero','%','0','100');");
//Resultado
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro,Medida)  VALUES ('112','Agua corporal total','17','resultado','l');");


//Bicarbonato, Exceso

//Formula
                db.execSQL("INSERT INTO Formulas (IdFormula,NombreCompleto,Abreviatura,Tipo,Expresion,Bibliografia) VALUES ('18','Bicarbonato, Exceso','Bicarbonato, Exceso','general','Peso* (Bicarbonato_VD/100)*(Bicarbonato_medido - Bicarbonato_deseado)','Luis Jiménez Murillo,Francisco Javier Montero Pérez;Medicina de urgencias y emergencias. 2015. 5 edición. p. 70. ISBN: 978-84-9022-454-0 .');");
//Parámetros
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro,Medida,Minimo,Maximo) VALUES ('113','Bicarbonato_medido','18','numero','mEq/l','0','200');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro,Medida,Minimo,Maximo) VALUES ('114','Bicarbonato_deseado','18','numero','mEq/l','0','200');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro,Medida,Minimo,Maximo) VALUES ('115','Bicarbonato_VD','18','numero','%','0','100');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro,Medida,Minimo,Maximo)  VALUES ('116','Peso','18','numero','kg','25','400');");
//Resultado
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro,Medida)  VALUES ('117','Bicarbonato exceso','18','resultado','mEq');");

//Bicarbonato, Deficit

//Formula
                db.execSQL("INSERT INTO Formulas (IdFormula,NombreCompleto,Abreviatura,Tipo,Expresion,Bibliografia) VALUES ('19','Bicarbonato, Deficit','Bicarbonato, Deficit','general','Peso*(Bicarbonato_VD/100)*( Bicarbonato_deseado - Bicarbonato_medido )','Luis Jiménez Murillo,Francisco Javier Montero Pérez;Medicina de urgencias y emergencias. 2015. 5 edición. p. 457. ISBN: 978-84-9022-454-0 .');");
//Parámetros
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro,Medida,Minimo,Maximo) VALUES ('118','Bicarbonato_medido','19','numero','mEq/l','0','200');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro,Medida,Minimo,Maximo) VALUES ('119','Bicarbonato_deseado','19','numero','mEq/l','0','200');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro,Medida,Minimo,Maximo) VALUES ('120','Bicarbonato_VD','19','numero','%','0','100');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro,Medida,Minimo,Maximo)  VALUES ('121','Peso','19','numero','kg','25','400');");
//Resultado
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro,Medida)  VALUES ('122','Bicarbonato deficit','19','resultado','mEq');");

//Gradiente A-a de O2

//Formula
                db.execSQL("INSERT INTO Formulas (IdFormula,NombreCompleto,Abreviatura,Tipo,Expresion,Bibliografia) VALUES ('20','Gradiente Alveolo arterial de oxigeno','Gradiente A-a de O2 ','general',' ((Fraccion_inspirada_de_oxigeno/100)*(Presion_barometrica - Vapor_de_agua)) - (Tension_arterial_de_CO2/Cociente_respiratorio) - Tension_arterial_de_Oxigeno','José Gregorio Soto Campos , Manual de diagnósticoy terapéutica en neumología,  ERGON, 2006, ISBN:9788484734093');");
//Parámetros
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro,Medida,Minimo,Maximo) VALUES ('123','Fraccion_inspirada_de_oxigeno','20','numero','fraccion','0','1');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro,Medida,Minimo,Maximo) VALUES ('124','Presion_barometrica','20','numero','mmHg','0','200');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro,Medida,Minimo,Maximo) VALUES ('125','Vapor_de_agua','20','numero','mmHg','0','150');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro,Medida,Minimo,Maximo) VALUES ('126','Cociente_respiratorio','20','numero','fraccion','0','1');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro,Medida,Minimo,Maximo) VALUES ('127','Tension_arterial_de_CO2','20','numero','mmHg','0','200');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro,Medida,Minimo,Maximo)  VALUES ('128','Tension_arterial_de_Oxigeno','20','numero','mmHg','0','760');");
//Resultado
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro,Medida)  VALUES ('129','Gradiente A-a de O2 ','20','resultado','mmHg');");

//Tension Inspirada de Oxígeno

//Formula
                db.execSQL("INSERT INTO Formulas (IdFormula,NombreCompleto,Abreviatura,Tipo,Expresion,Bibliografia) VALUES ('21','Tension Inspirada de Oxígeno ','Tension Inspirada de Oxígeno ','general','Fraccion_inspirada_de_oxigeno*(Presion_barometrica - Vapor_de_agua)','Luis Jiménez Murillo,Francisco Javier Montero Pérez;Medicina de urgencias y emergencias. 2015. 5 edición. p. 59. ISBN: 978-84-9022-454-0 .');");
//Parámetros
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro,Medida,Minimo,Maximo) VALUES ('130','Fraccion_inspirada_de_oxigeno','21','numero','fraccion','0','1');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro,Medida,Minimo,Maximo) VALUES ('131','Presion_barometrica','21','numero','mmHg','0','760');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro,Medida,Minimo,Maximo)  VALUES ('132','Vapor_de_agua','21','numero','mmHg','0','150');");
//Resultado
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro,Medida)  VALUES ('133','Tension Inspirada de Oxigeno','21','resultado','mmHg');");

//Cálculo de la insulinizacion: dosis de insulina (DI) al día.

//Formula
                db.execSQL("INSERT INTO Formulas (IdFormula,NombreCompleto,Abreviatura,Tipo,Expresion,Bibliografia) VALUES ('22','Cálculo de la insulinizacion: dosis de insulina (DI) al día.','Dosis de insulina al día','general','','Luis Jiménez Murillo,Francisco Javier Montero Pérez;Medicina de urgencias y emergencias. 2015. 5 edición. p. 437. ISBN: 978-84-9022-454-0 .');");
//Parámetros
//Parametro en blanco
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro,Medida,Minimo,Maximo) VALUES ('134','Peso','22','numero','kg','25','400');");
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro,Medida,Minimo,Maximo) VALUES ('135','Edad','22','lista','años','0','120');");

//Resultado
                db.execSQL("INSERT INTO Parametros (IdParametro,NombreParametro,IdFormula,TipoParametro,Medida)  VALUES ('136','Dosis de insulina','22','resultado','Unidades de insulina');");
//Criterios de puntuación
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('131','135','Joven','0.5*Peso');");
                db.execSQL("INSERT INTO CriteriosPuntuacion (IdCriterioPuntuacion,IdParametro,Criterio,Puntuacion) VALUES ('132','135','Edad avanzada','0.3*Peso');");



            }
            */



            db.close();


            if (prioridadVacia) {
                Intent intent =
                        new Intent(MainActivity.this, Bienvenida.class);

                startActivity(intent);

            } else {

                Intent intent =
                        new Intent(MainActivity.this, Inicio.class);

                startActivity(intent);
            }


        }
    }
}

