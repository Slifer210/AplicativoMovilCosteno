package com.example.aplicativomvilparalacompradeboletosinterprovincialesenelper.Vistas;

import static com.example.aplicativomvilparalacompradeboletosinterprovincialesenelper.Conexion.apiUrl;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.aplicativomvilparalacompradeboletosinterprovincialesenelper.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EditarPerfil extends AppCompatActivity {

    private EditText etDireccion, etNumeroTelefono, etFechaNacimiento, etEstadoCivil;
    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_editar_perfil);

        etDireccion = findViewById(R.id.et_direccion);
        etNumeroTelefono = findViewById(R.id.et_numero_telefono);
        etFechaNacimiento = findViewById(R.id.et_fecha_nacimiento);
        etEstadoCivil = findViewById(R.id.et_estado_civil);
        btnGuardar = findViewById(R.id.btn_guardar);

        // Cargar datos del usuario actual
        cargarDatosUsuario();

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizarPerfil();
            }
        });
    }

    private void cargarDatosUsuario() {
        String urlUsuarioActual = apiUrl + "/actual-usuario";
        String token = getSharedPreferences("mi_app", MODE_PRIVATE).getString("token", "");

        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, urlUsuarioActual, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int idUsuario = response.getInt("idPersona"); // Obtener el ID del usuario
                            String estadoCivil = response.getString("estadoCivil");
                            String direccion = response.getString("direccion");
                            String fechaNac = response.getString("fechaNac");
                            String numTel = response.getString("numTel");

                            // Guardar el ID en SharedPreferences para usarlo más tarde
                            getSharedPreferences("mi_app", MODE_PRIVATE).edit().putInt("idPersona", idUsuario).apply();

                            etEstadoCivil.setText(estadoCivil);
                            etDireccion.setText(direccion);
                            etNumeroTelefono.setText(numTel);
                            etFechaNacimiento.setText(fechaNac);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("UsuarioError", "Error al cargar los datos del usuario");
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
                headers.put("Authorization", "Bearer " + token);
                return headers;
            }
        };
        queue.add(request);
    }


    private void actualizarPerfil() {
        String direccion = etDireccion.getText().toString().trim();
        String numTelefono = etNumeroTelefono.getText().toString().trim();
        String fechaNacimiento = etFechaNacimiento.getText().toString().trim();
        String estadoCivil = etEstadoCivil.getText().toString().trim();

        if (TextUtils.isEmpty(direccion) || TextUtils.isEmpty(numTelefono) || TextUtils.isEmpty(fechaNacimiento)) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Crear el objeto JSON para la actualización
        JSONObject jsonRequest = new JSONObject();
        try {
            jsonRequest.put("direccion", direccion);
            jsonRequest.put("numTel", numTelefono);
            jsonRequest.put("fechaNac", fechaNacimiento);
            jsonRequest.put("estadoCivil", estadoCivil);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Obtener el ID del cliente desde SharedPreferences
        int clienteId = getSharedPreferences("mi_app", MODE_PRIVATE).getInt("idPersona", -1);
        if (clienteId == -1) {
            Toast.makeText(this, "Error: ID de cliente no encontrado", Toast.LENGTH_SHORT).show();
            return;
        }

        // Usar el ID en la URL para la actualización
        String urlActualizar = apiUrl + "/cliente/actualizar/" + clienteId;
        String token = getSharedPreferences("mi_app", MODE_PRIVATE).getString("token", "");

        // Realizar la solicitud de actualización
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, urlActualizar, jsonRequest,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(EditarPerfil.this, "Perfil actualizado con éxito", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(EditarPerfil.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + token);
                return headers;
            }
        };
        queue.add(request);
    }

}
