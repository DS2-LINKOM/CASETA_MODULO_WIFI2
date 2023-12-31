package mx.linkom.caseta_modulo_wifi2;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;



public class NavegadorActivity extends mx.linkom.caseta_modulo_wifi2.Menu2 {

    private mx.linkom.caseta_modulo_wifi2.Configuracion Conf;
    private FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navegador);

        fAuth = FirebaseAuth.getInstance();

        Conf = new mx.linkom.caseta_modulo_wifi2.Configuracion(this);

        if(Conf.getNoti().equals("PLUMA ENTRADA")){

            Intent i = new Intent(getApplication(), RegistroPlumasEntradasActivity.class);
            startActivity(i);
            finish();

        }else if(Conf.getNoti().equals("PLUMA SALIDA")){

            Intent i = new Intent(getApplication(), RegistroPlumasSalidasActivity.class);
            startActivity(i);
            finish();

        }else if(Conf.getNoti().equals("ERROR")){

            Intent i = new Intent(getApplication(), DashboardVigilante.class);
            startActivity(i);
            finish();

        }


    }

    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main2, menu);

        return true;
    }


    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), mx.linkom.caseta_modulo_wifi2.DashboardVigilante.class);
        startActivity(intent);
    }
}