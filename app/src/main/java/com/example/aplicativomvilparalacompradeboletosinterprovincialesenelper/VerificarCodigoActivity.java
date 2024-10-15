package com.example.aplicativomvilparalacompradeboletosinterprovincialesenelper;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class VerificarCodigoActivity extends AppCompatActivity {

    EditText et_codigo;
    Button btn_validar_codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verificar_codigo);

        et_codigo = findViewById(R.id.et_codigo);
        btn_validar_codigo = findViewById(R.id.btn_validar_codigo);

        btn_validar_codigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarCodigo();
            }
        });
    }

    private void validarCodigo() {
        String email = getIntent().getStringExtra("email"); // Obtén el email de la actividad anterior
        String codigo = et_codigo.getText().toString().trim();

        if (TextUtils.isEmpty(codigo)) {
            et_codigo.setError("Ingrese el código");
            return;
        }

        // Crear objeto JSON con el email y código
        JSONObject jsonRequest = new JSONObject();
        try {
            jsonRequest.put("email", email);
            jsonRequest.put("codigo", codigo);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String url = "http://192.168.1.5:8080/cliente/validarCodigo"; // URL de tu backend

        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonRequest,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(VerificarCodigoActivity.this, "Código validado con éxito", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(VerificarCodigoActivity.this, inicio.class);
                        startActivity(intent);
                        finish();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(VerificarCodigoActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(request);
    }
}
