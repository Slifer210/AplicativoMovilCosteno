package com.example.aplicativomvilparalacompradeboletosinterprovincialesenelper.Acceso;

import static com.example.aplicativomvilparalacompradeboletosinterprovincialesenelper.Conexion.apiUrl;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.aplicativomvilparalacompradeboletosinterprovincialesenelper.HomeActivity;
import com.example.aplicativomvilparalacompradeboletosinterprovincialesenelper.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class inicio extends AppCompatActivity {

    private EditText et_email, et_password;
    private Button btn_login;
    private TextView tv_registrar, tv_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_inicio);

        final TextView tvBienvenido = findViewById(R.id.tv_bienvenido);
        final LinearLayout layoutLogin = findViewById(R.id.layout_login);

        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        btn_login = findViewById(R.id.btn_login);
        tv_registrar = findViewById(R.id.tv_register);
        tv_password = findViewById(R.id.tv_forgot_password);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tvBienvenido.setVisibility(View.GONE);
                layoutLogin.setVisibility(View.VISIBLE);
            }
        }, 100);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarSesion();
            }
        });

        tv_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentReg = new Intent(inicio.this, Registrar.class);
                inicio.this.startActivity(intentReg);
            }
        });

        tv_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentPass = new Intent(inicio.this, RecuperarContra.class);
                inicio.this.startActivity(intentPass);
            }
        });
    }

    private void iniciarSesion() {
        String correo = et_email.getText().toString().trim();
        String password = et_password.getText().toString().trim();

        if (TextUtils.isEmpty(correo)) {
            et_email.setError("Ingrese su correo");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            et_password.setError("Ingrese su contraseña");
            return;
        }

        JSONObject jsonRequest = new JSONObject();
        try {
            jsonRequest.put("correo", correo);
            jsonRequest.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String urlIngresar = apiUrl + "/ingresar";

        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, urlIngresar, jsonRequest,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String token = response.getString("token");
                            guardarToken(token);

                            Log.d("Token", "Token de sesión: " + token);

                            obtenerUsuarioActual(token);

                            Toast.makeText(inicio.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(inicio.this, HomeActivity.class);
                            startActivity(intent);
                            finish();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(inicio.this, "Error en la respuesta del servidor", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(inicio.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        queue.add(request);
    }

    private void guardarToken(String token) {
        getSharedPreferences("mi_app", MODE_PRIVATE)
                .edit()
                .putString("token", token)
                .apply();
    }


    // Método para obtener el usuario actual
    private void obtenerUsuarioActual(String token) {
        String urlUsuarioActual = apiUrl + "/actual-usuario";

        // Crear un nuevo RequestQueue para la nueva solicitud
        RequestQueue queue = Volley.newRequestQueue(this);

        // Configurar la solicitud
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, urlUsuarioActual, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String estadoCivil = response.getString("estadoCivil");
                            String direccion = response.getString("direccion");
                            String fechaNac = response.getString("fechaNac");
                            String numTel = response.getString("numTel");
                            Log.d("estadoCivil", "Estado Civil: " + estadoCivil);
                            Log.d("direccion", "Direccion: " + direccion);
                            Log.d("fechaNac", "Fecha Nac: " + fechaNac);
                            Log.d("numTel", "Num Telf: " + numTel);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("UsuarioError", "Error al obtener el usuario actual");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("UsuarioError", "Error al obtener el usuario actual: " + error.getMessage());
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + token); // Agregar el token en los encabezados
                return headers;
            }
        };

        queue.add(request);
    }
}
