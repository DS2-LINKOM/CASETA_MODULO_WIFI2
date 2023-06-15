package mx.linkom.caseta_modulo_wifi2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class RegistroPlumasSalidasActivity extends mx.linkom.caseta_modulo_wifi2.Menu2 {
    JSONArray ja1,ja2,ja3,ja4,ja5,ja6;
    Configuracion Conf;
    TextView pluma, usuario,direccion;
    Button Registrar;
    String[] t1;
    String t2;
    TextView nombre;

    LinearLayout autos_placas;
    TextView placas;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registroplumassalidas);
        Conf = new Configuracion(this);
        nombre = (TextView) findViewById(R.id.nombre);
        pluma = (TextView) findViewById(R.id.setPluma);
        usuario = (TextView) findViewById(R.id.setNombre);
        direccion = (TextView) findViewById(R.id.setDireccion);
        Registrar = (Button) findViewById(R.id.registrar);
        autos_placas = (LinearLayout) findViewById(R.id.autos_placas);
        placas = (TextView) findViewById(R.id.setPlacas);
        Detalle();
        Registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Verificar();
            }
        });


    }



    public void Detalle(){
        t1=Conf.getVisita().split("");
        t2=(Conf.getVisita()).substring(1);

        if(t1[0].equals("A")){
            String URL = "https://demoarboledas.privadaarboledas.net/plataforma/casetaV2/controlador/WIFI/plumas_registro_10.php?bd_name="+Conf.getBd()+"&bd_user="+Conf.getBdUsu()+"&bd_pwd="+Conf.getBdCon();
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {


                @Override
                public void onResponse(String response) {

                    response = response.replace("][",",");
                    if (response.length()>0){
                        try {
                            ja5 = new JSONArray(response);
                            pluma();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }, new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("TAG","Error: " + error.toString());
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("id", t2);
                    params.put("id_residencial", Conf.getResid().trim());
                    return params;
                }
            };
            requestQueue.add(stringRequest);
        }else if(t1[0].equals("V")) {

            String URL = "https://demoarboledas.privadaarboledas.net/plataforma/casetaV2/controlador/WIFI/plumas_registro_7.php?bd_name=" + Conf.getBd() + "&bd_user=" + Conf.getBdUsu() + "&bd_pwd=" + Conf.getBdCon();
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {


                @Override
                public void onResponse(String response) {

                    response = response.replace("][", ",");
                    if (response.length() > 0) {
                        try {
                            ja5 = new JSONArray(response);

                            pluma();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("TAG", "Error: " + error.toString());
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("id", t2);
                    params.put("id_residencial", Conf.getResid().trim());
                    return params;
                }
            };
            requestQueue.add(stringRequest);
        }
    }

    public void pluma(){

        String URL = "https://demoarboledas.privadaarboledas.net/plataforma/casetaV2/controlador/WIFI/plumas_registro_8.php?bd_name="+Conf.getBd()+"&bd_user="+Conf.getBdUsu()+"&bd_pwd="+Conf.getBdCon();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {

                response = response.replace("][",",");
                if (response.length()>0){
                    try {
                        ja6 = new JSONArray(response);

                        if(t1[0].equals("A")) {
                            Auto();
                        }else if(t1[0].equals("V")){
                            Visita();
                        }                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG","Error: " + error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_residencial", Conf.getResid().trim());
                try {
                    params.put("id_app",ja5.getString(6).trim());
                    params.put("id_usuario",ja5.getString(7).trim());

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void Auto(){
        String url = "https://demoarboledas.privadaarboledas.net/plataforma/casetaV2/controlador/WIFI/auto6.php?bd_name=" + Conf.getBd() + "&bd_user=" + Conf.getBdUsu() + "&bd_pwd=" + Conf.getBdCon();

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                response = response.replace("][",",");
                if (response.length()>0){
                    try {
                        ja1 = new JSONArray(response);
                        Usuario(ja1.getString(2));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error ", "Id: " + error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("id", t2);
                params.put("id_residencial", Conf.getResid().trim());

                return params;
            }
        };
        requestQueue.add(stringRequest);

    }

    public void Visita(){

        String URL = "https://demoarboledas.privadaarboledas.net/plataforma/casetaV2/controlador/WIFI/vst_php8.php?bd_name="+Conf.getBd()+"&bd_user="+Conf.getBdUsu()+"&bd_pwd="+Conf.getBdCon();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {
                Log.e("TAG","LINKOM ST: " + response);

                response = response.replace("][",",");
                if (response.length()>0){
                    try {
                        ja1 = new JSONArray(response);

                        dtlVisita();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG","Error: " + error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", t2);
                params.put("id_residencial", Conf.getResid().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void dtlVisita(){

        String URL = "https://demoarboledas.privadaarboledas.net/plataforma/casetaV2/controlador/WIFI/plumas_registro_14.php?bd_name="+Conf.getBd()+"&bd_user="+Conf.getBdUsu()+"&bd_pwd="+Conf.getBdCon();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {

                response = response.replace("][",",");
                if (response.length()>0){
                    try {
                        ja4 = new JSONArray(response);

                        Usuario(ja1.getString(2));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG","Error: " + error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                try {
                    params.put("id_visita", ja1.getString(0));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                params.put("id_residencial", Conf.getResid().trim());

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void Usuario(final String IdUsu){ //DATOS USUARIO

        String URL = "https://demoarboledas.privadaarboledas.net/plataforma/casetaV2/controlador/WIFI/vst_php2.php?bd_name="+Conf.getBd()+"&bd_user="+Conf.getBdUsu()+"&bd_pwd="+Conf.getBdCon();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                response = response.replace("][",",");
                if (response.length()>0){
                    try {
                        ja2 = new JSONArray(response);

                        dtlLugar(ja2.getString(0));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG","Error: " + error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("IdUsu", IdUsu.trim());
                params.put("id_residencial", Conf.getResid().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void dtlLugar(final String idUsuario){
        String URLResidencial = "https://demoarboledas.privadaarboledas.net/plataforma/casetaV2/controlador/WIFI/vst_php3.php?bd_name="+Conf.getBd()+"&bd_user="+Conf.getBdUsu()+"&bd_pwd="+Conf.getBdCon();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLResidencial, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                response = response.replace("][", ",");
                if (response.length() > 0) {
                    try {
                        ja3 = new JSONArray(response);

                        Informacion();
                    } catch (JSONException e) {

                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", "Error: " + error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_usuario", idUsuario.trim());
                params.put("id_residencial", Conf.getResid().trim());

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void Informacion(){
        try {
            pluma.setText(ja6.getString(3).trim());
            if(t1[0].equals("A")) {
                nombre.setText("Residente:");
                usuario.setText(ja1.getString(4).trim());
            }else if(t1[0].equals("V")){
                nombre.setText("Visita:");
                usuario.setText(ja1.getString(7).trim());
                autos_placas.setVisibility(View.VISIBLE);
                placas.setText(ja4.getString(9));
            }
            direccion.setText(ja3.getString(0));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void Verificar() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(RegistroPlumasSalidasActivity.this);
        alertDialogBuilder.setTitle("Alerta");
        alertDialogBuilder
                .setMessage("¿ Desea registrar la salida ?")
                .setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        registro();
                    }
                }).create().show();
    }


    public void registro(){
        if (t1[0].equals("A")) {
            String URL = "https://demoarboledas.privadaarboledas.net/plataforma/casetaV2/controlador/WIFI/plumas_registro_13.php?bd_name="+Conf.getBd()+"&bd_user="+Conf.getBdUsu()+"&bd_pwd="+Conf.getBdCon();
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

                @Override
                public void onResponse(String response){
                    if(response.equals("error")){
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(RegistroPlumasSalidasActivity.this);
                        alertDialogBuilder.setTitle("Alerta");
                        alertDialogBuilder
                                .setMessage("Registro No Exitosa")
                                .setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Toast.makeText(getApplicationContext(),"Salida No Registrada", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(getApplicationContext(), DashboardVigilante.class);
                                        startActivity(i);
                                        finish();
                                    }
                                }).create().show();
                    }else {
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(RegistroPlumasSalidasActivity.this);
                        alertDialogBuilder.setTitle("Alerta");
                        alertDialogBuilder
                                .setMessage("Registro Exitoso")
                                .setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Toast.makeText(getApplicationContext(),"Salida  Registrada", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(getApplicationContext(), DashboardVigilante.class);
                                        startActivity(i);
                                        finish();
                                    }
                                }).create().show();

                    }
                }
            }, new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("TAG","Error: " + error.toString());
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String, String> params = new HashMap<>();
                    try {
                        params.put("id", ja5.getString(0).trim());
                        params.put("token", ja2.getString(5).trim());
                        params.put("visita",ja1.getString(4).trim());
                        params.put("texto","ha salido");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    return params;
                }
            };
            requestQueue.add(stringRequest);

        }else if (t1[0].equals("V")) {
            String URL = "https://demoarboledas.privadaarboledas.net/plataforma/casetaV2/controlador/WIFI/plumas_registro_6.php?bd_name="+Conf.getBd()+"&bd_user="+Conf.getBdUsu()+"&bd_pwd="+Conf.getBdCon();
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

                @Override
                public void onResponse(String response){
                    if(response.equals("error")){
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(RegistroPlumasSalidasActivity.this);
                        alertDialogBuilder.setTitle("Alerta");
                        alertDialogBuilder
                                .setMessage("Registro No Exitosa")
                                .setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Toast.makeText(getApplicationContext(),"Salida No Registrada", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(getApplicationContext(), DashboardVigilante.class);
                                        startActivity(i);
                                        finish();
                                    }
                                }).create().show();
                    }else {
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(RegistroPlumasSalidasActivity.this);
                        alertDialogBuilder.setTitle("Alerta");
                        alertDialogBuilder
                                .setMessage("Registro Exitoso")
                                .setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Toast.makeText(getApplicationContext(),"Salida  Registrada", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(getApplicationContext(), DashboardVigilante.class);
                                        startActivity(i);
                                        finish();
                                    }
                                }).create().show();

                    }
                }
            }, new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("TAG","Error: " + error.toString());
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String, String> params = new HashMap<>();
                    try {
                        params.put("id", ja5.getString(0).trim());
                        params.put("token", ja2.getString(5).trim());
                        params.put("visita",ja1.getString(7).trim());
                        params.put("texto","ha salido");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    return params;
                }
            };
            requestQueue.add(stringRequest);
        }


    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), DashboardVigilante.class);
        startActivity(intent);
        finish();
    }

}
