package com.example.aplicativomvilparalacompradeboletosinterprovincialesenelper.Acceso;

import static com.example.aplicativomvilparalacompradeboletosinterprovincialesenelper.Conexion.apiUrl;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.aplicativomvilparalacompradeboletosinterprovincialesenelper.Entidades.Cliente;
import com.example.aplicativomvilparalacompradeboletosinterprovincialesenelper.Entidades.EstadoCliente;
import com.example.aplicativomvilparalacompradeboletosinterprovincialesenelper.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class Registrar extends AppCompatActivity {
    TextView tv_iniciar;
    EditText et_dni, et_apellido, et_nombre, et_email, et_contrasena, et_confirm_contrasena;
    Button btn_registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        tv_iniciar = findViewById(R.id.tv_iniciar_sesion);
        et_dni = findViewById(R.id.et_dni);
        et_apellido = findViewById(R.id.et_apellido);
        et_nombre = findViewById(R.id.et_nombre);
        et_email = findViewById(R.id.et_email);
        et_contrasena = findViewById(R.id.et_contrasena);
        et_confirm_contrasena = findViewById(R.id.et_confirm_contrasena);
        btn_registrar = findViewById(R.id.btn_registrar);

        et_dni.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 8) { // Cuando se ingresan 8 dígitos
                    consultarDNI(s.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

        tv_iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentIni = new Intent(Registrar.this, inicio.class);
                startActivity(intentIni);
            }
        });

        et_dni.addTextChangedListener(new RegistroTextWatcher(et_dni));
        et_apellido.addTextChangedListener(new RegistroTextWatcher(et_apellido));
        et_nombre.addTextChangedListener(new RegistroTextWatcher(et_nombre));
        et_email.addTextChangedListener(new RegistroTextWatcher(et_email));
        et_contrasena.addTextChangedListener(new RegistroTextWatcher(et_contrasena));
        et_confirm_contrasena.addTextChangedListener(new RegistroTextWatcher(et_confirm_contrasena));

        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Desactiva el botón de inmediato al hacer clic
                if (!btn_registrar.isEnabled()) {
                    return;  // Si el botón ya está deshabilitado, no ejecuta el código
                }
                btn_registrar.setEnabled(false);

                // Valida que todos los campos estén correctos antes de registrar
                if (validarCampos()) {
                    registrarUsuario(); // Llama a registrarUsuario si la validación es correcta
                } else {
                    btn_registrar.setEnabled(true); // Reactiva el botón si la validación falla
                }
            }
        });
    }

    private class RegistroTextWatcher implements TextWatcher {

        private View view;

        private RegistroTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            int id = view.getId();

            if (id == R.id.et_dni) {
                validarCampoDni();
            } else if (id == R.id.et_apellido) {
                validarCampoApellido();
            } else if (id == R.id.et_nombre) {
                validarCampoNombre();
            } else if (id == R.id.et_email) {
                validarCampoEmail();
            } else if (id == R.id.et_contrasena) {
                validarCampoContrasena();
            } else if (id == R.id.et_confirm_contrasena) {
                validarCampoConfirmarContrasena();
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {}
    }

    private boolean validarCampos() {
        return validarCampoDni() && validarCampoApellido() && validarCampoNombre() &&
                validarCampoEmail() && validarCampoContrasena() && validarCampoConfirmarContrasena();
    }

    private boolean validarCampoDni() {
        String dni = et_dni.getText().toString().trim();
        if (TextUtils.isEmpty(dni)) {
            et_dni.setError("Ingrese su DNI");
            return false;
        }
        if (dni.length() != 8) {
            et_dni.setError("El DNI debe tener 8 dígitos");
            return false;
        }
        et_dni.setError(null);
        return true;
    }

    private boolean validarCampoApellido() {
        String apellido = et_apellido.getText().toString().trim();
        if (TextUtils.isEmpty(apellido)) {
            et_apellido.setError("Ingrese su apellido");
            return false;
        }
        et_apellido.setError(null);
        return true;
    }

    private boolean validarCampoNombre() {
        String nombre = et_nombre.getText().toString().trim();
        if (TextUtils.isEmpty(nombre)) {
            et_nombre.setError("Ingrese su nombre");
            return false;
        }
        et_nombre.setError(null);
        return true;
    }

    private boolean validarCampoEmail() {
        String email = et_email.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            et_email.setError("Ingrese su correo");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            et_email.setError("Ingrese un correo válido");
            return false;
        }
        et_email.setError(null);
        return true;
    }

    private boolean validarCampoContrasena() {
        String contrasena = et_contrasena.getText().toString().trim();
        if (TextUtils.isEmpty(contrasena)) {
            et_contrasena.setError("Ingrese su contraseña");
            return false;
        }
        et_contrasena.setError(null);
        return true;
    }

    private boolean validarCampoConfirmarContrasena() {
        String contrasena = et_contrasena.getText().toString().trim();
        String confirmarContrasena = et_confirm_contrasena.getText().toString().trim();
        if (TextUtils.isEmpty(confirmarContrasena)) {
            et_confirm_contrasena.setError("Confirme su contraseña");
            return false;
        } else if (!contrasena.equals(confirmarContrasena)) {
            et_confirm_contrasena.setError("Las contraseñas no coinciden");
            return false;
        }
        et_confirm_contrasena.setError(null);
        return true;
    }

    private void registrarUsuario() {
        Log.d("RegistrarUsuario", "Función llamada");
        if (validarCampos()) {

            EstadoCliente estadoCliente = new EstadoCliente(1, "Activo");
            Cliente cliente = new Cliente(
                    0,
                    Integer.parseInt(et_dni.getText().toString()),
                    et_nombre.getText().toString(),
                    et_apellido.getText().toString(),
                    "Soltero",
                    "Dirección",
                    "123456789",
                    new Date(),
                    new Date(),
                    et_email.getText().toString(),
                    et_contrasena.getText().toString(),
                    estadoCliente
            );

            JSONObject jsonCliente = new JSONObject();
            try {

                jsonCliente.put("numDocumento", cliente.getNumDocumento());
                jsonCliente.put("apellidos", cliente.getApellidos());
                jsonCliente.put("nombres", cliente.getNombres());
                jsonCliente.put("correo", cliente.getCorreo());
                jsonCliente.put("password", cliente.getPassword());

                JSONObject jsonEstadoCliente = new JSONObject();
                jsonEstadoCliente.put("idEstadoCliente", cliente.getEstadoCliente().getIdEstadoCliente());
                jsonEstadoCliente.put("estado", cliente.getEstadoCliente().getEstado());
                jsonCliente.put("estadoCliente", jsonEstadoCliente);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            String urlEnviarCodigo = apiUrl + "/cliente/enviarCodigo";

            RequestQueue queue = Volley.newRequestQueue(this);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, urlEnviarCodigo, jsonCliente,
                    response -> {
                        btn_registrar.setEnabled(true);
                        Toast.makeText(Registrar.this, "Código enviado al correo", Toast.LENGTH_SHORT).show();

                        Intent intentVerificar = new Intent(Registrar.this, VerificarCodigoActivity.class);
                        intentVerificar.putExtra("email", et_email.getText().toString());
                        startActivity(intentVerificar);
                    }, error -> {
                        btn_registrar.setEnabled(true);
                        if (error.networkResponse != null && error.networkResponse.statusCode == 409) {
                            Toast.makeText(Registrar.this, "El correo ya ha sido utilizado.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Registrar.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
            queue.add(request);
        }
    }


    private void consultarDNI(String dni) {
        String urlConsultarDni = apiUrl + "/consultar-dni?dni=" + dni;
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, urlConsultarDni, null,
                response -> {
                    try {
                        // Obtener los valores del JSON que devuelve el backend
                        String apellido = response.getString("apellidoPaterno") + " " + response.getString("apellidoMaterno");
                        String nombre = response.getString("nombres");

                        // Establecer los valores en los campos correspondientes
                        et_apellido.setText(apellido);
                        et_nombre.setText(nombre);

                        // Hacer que los EditTexts sean no editables
                        et_apellido.setEnabled(false);
                        et_nombre.setEnabled(false);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(Registrar.this, "Error al procesar la respuesta", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    Toast.makeText(Registrar.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                });
        queue.add(request);
    }

}
