package com.example.aplicativomvilparalacompradeboletosinterprovincialesenelper.Acceso;

import static com.example.aplicativomvilparalacompradeboletosinterprovincialesenelper.Conexion.apiUrl;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.aplicativomvilparalacompradeboletosinterprovincialesenelper.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RecuperarContra extends AppCompatActivity {

    private EditText et_correo, et_contra;
    private Button btn_recuperar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recuperar_contra2);

        et_correo = findViewById(R.id.et_correo);
        et_contra = findViewById(R.id.et_nuevaContra);
        btn_recuperar = findViewById(R.id.btn_recuperar);

        btn_recuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cambiarContrasenaSiExiste();
            }
        });
    }

    private void cambiarContrasenaSiExiste() {
        String email = et_correo.getText().toString().trim();
        String newPassword = et_contra.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            et_correo.setError("Ingrese su correo");
            return;
        }

        if (TextUtils.isEmpty(newPassword)) {
            et_contra.setError("Ingrese la nueva contraseña");
            return;
        }

        Map<String, String> datos = new HashMap<>();
        datos.put("correo", email);
        datos.put("password", newPassword);

        JSONObject jsonDatos = new JSONObject(datos);

        String urlRecuperar = apiUrl + "/cliente/cambiarContrasena";

        RequestQueue queue = Volley.newRequestQueue(this);
        btn_recuperar.setEnabled(false);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, urlRecuperar, jsonDatos,
                response -> {
                    btn_recuperar.setEnabled(true);
                    Toast.makeText(RecuperarContra.this, "Contraseña cambiada con éxito.", Toast.LENGTH_SHORT).show();

                     Intent intent = new Intent(RecuperarContra.this, inicio.class);
                     startActivity(intent);
                }, error -> {
            btn_recuperar.setEnabled(true);
            if (error.networkResponse != null && error.networkResponse.statusCode == 404) {
                Toast.makeText(RecuperarContra.this, "El correo no existe.", Toast.LENGTH_SHORT).show();
            } else if (error.networkResponse != null && error.networkResponse.statusCode == 409) {
                Toast.makeText(RecuperarContra.this, "Error al cambiar la contraseña. Intente de nuevo.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(RecuperarContra.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);
    }
}
