package com.example.aplicativomvilparalacompradeboletosinterprovincialesenelper;

import static com.example.aplicativomvilparalacompradeboletosinterprovincialesenelper.Conexion.apiUrl;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.aplicativomvilparalacompradeboletosinterprovincialesenelper.Entidades.Administrador;
import com.example.aplicativomvilparalacompradeboletosinterprovincialesenelper.Entidades.Bus;
import com.example.aplicativomvilparalacompradeboletosinterprovincialesenelper.Entidades.Chofer;
import com.example.aplicativomvilparalacompradeboletosinterprovincialesenelper.Entidades.Ruta;
import com.example.aplicativomvilparalacompradeboletosinterprovincialesenelper.Entidades.Terminal;
import com.example.aplicativomvilparalacompradeboletosinterprovincialesenelper.Entidades.Viaje;
import com.example.aplicativomvilparalacompradeboletosinterprovincialesenelper.Vistas.EditarPerfil;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class HomeActivity extends AppCompatActivity implements DatePickerFragment.DatePickerListener {
    private Spinner spinnerOrigen,spinnerDestino;
    private RequestQueue requestQueue;
    private EditText etDate ,etDateLlegada;
    private EditText selectedDateField; // Variable para identificar el EditText seleccionado

    private Button buscar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        spinnerOrigen = findViewById(R.id.spinnerOrigen);
        spinnerDestino = findViewById(R.id.spinnerDestino);
        etDate = findViewById(R.id.tvFechaSalida);
        etDateLlegada = findViewById(R.id.tvFechaRetorno);
        buscar=findViewById(R.id.btnBuscar);
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscaViaje();
            }
        });

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedDateField = etDate; // Asigna etDate como el EditText seleccionado
                showDatePickerDialog();
            }
        });

        etDateLlegada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedDateField = etDateLlegada; // Asigna etDateLlegada como el EditText seleccionado
                showDatePickerDialog();
            }
        });



        // Inicializa la cola de solicitudes de Volley
        requestQueue = Volley.newRequestQueue(this);

        // URL de tu backend
        String url = apiUrl +"/terminal/listar";

        // Realiza la solicitud al backend
        cargarDatos(url,spinnerOrigen);
        cargarDatos(url,spinnerDestino);


//        Button btnLogout = findViewById(R.id.btn_logout);
//        btnLogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.navInicio) {
                    // Manejar navegación a la actividad de Inicio
                    return true;
                } else if (item.getItemId() == R.id.navNotificaciones) {
                    // Manejar navegación a la actividad de Notificaciones
                    return true;
                } else if (item.getItemId() == R.id.navBoletos) {
                    // Manejar navegación a la actividad de Boletos
                    return true;
                } else if (item.getItemId() == R.id.navPerfil) {
                    // Navegar a la actividad de editar perfil
                    Intent intent = new Intent(HomeActivity.this, EditarPerfil.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });

    }

    private void cargarDatos(String url, Spinner spinner) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        List<String> dataList = new ArrayList<>();
                        try {
                            // Log para verificar la respuesta
                            Log.d("Volley", "Respuesta del backend: " + response.toString());

                            // Convierte el JSONArray en una lista de strings
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                String nombre = jsonObject.getString("nombre"); // Ajusta según el campo que esperas
                                dataList.add(nombre);
                            }

                            // Configura el adaptador para el spinner
                            ArrayAdapter<String> adapter = new ArrayAdapter<>(HomeActivity.this, android.R.layout.simple_spinner_item, dataList);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinner.setAdapter(adapter);

                        } catch (JSONException e) {
                            Log.e("Volley", "Error al procesar la respuesta JSON: " + e.getMessage());
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Maneja el error
                        Log.e("Volley", "Error en la solicitud: " + error.getMessage());
                        error.printStackTrace();
                    }
                }
        );

        // Agrega la solicitud a la cola de Volley
        requestQueue.add(jsonArrayRequest);
    }
    private void showDatePickerDialog() {
        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.setDatePickerListener(this);
        datePickerFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onDateSet(int day, int month, int year) {
        // Establece la fecha en el EditText que fue seleccionado
        if (selectedDateField != null) {
            selectedDateField.setText(day + "/" + (month + 1) + "/" + year);
        }
    }
    private void buscaViaje(){
        // Obtiene los valores seleccionados o ingresados
        String origen = spinnerOrigen.getSelectedItem().toString();
        String destino = spinnerDestino.getSelectedItem().toString();
        String fechaSalida = etDate.getText().toString();
        String fechaRetorno = etDateLlegada.getText().toString();

        // Muestra los valores en el log de Android
        // Log.d("HomeActivity", "Origen: " + origen);
        // Log.d("HomeActivity", "Destino: " + destino);
        // Log.d("HomeActivity", "Fecha de Salida: " + fechaSalida);
        // Log.d("HomeActivity", "Fecha de Retorno: " + fechaRetorno);

        // Busca la ruta por terminales
        buscarRutas();
    }
    private void buscarRutas() {
        String selectedTerminalOrigen = spinnerOrigen.getSelectedItem().toString();
        String selectedTerminalDestino = spinnerDestino.getSelectedItem().toString();

        if (selectedTerminalOrigen.isEmpty() || selectedTerminalDestino.isEmpty()) {
            Log.d("RutaError", "Debe seleccionar una terminal de origen y destino");
            return;
        }

        buscarRutaPorTerminales(selectedTerminalOrigen, selectedTerminalDestino);
    }

    private void buscarRutaPorTerminales(String nombreOrigen, String nombreDestino) {
        String url = apiUrl + "/ruta/buscar?nombreOrigen=" + nombreOrigen + "&nombreDestino=" + nombreDestino;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null,
                response -> {
                    try {
                        int idRuta = response.getInt("idRuta");
                        Log.d("RutaInfo", "ID de Ruta obtenida: " + idRuta);
                        // Navegar a ListarActivity y pasar el idRuta
                        Intent intent = new Intent(HomeActivity.this, ListarActivity.class);
                        intent.putExtra("idRuta", idRuta); // Pasa el idRuta
                        startActivity(intent); // Lanza la actividad
                    } catch (JSONException e) {
                        Log.e("RutaError", "Error al parsear la respuesta JSON: " + e.getMessage());
                    }
                },
                error -> Log.e("RutaError", "Error al buscar ruta: " + error.getMessage())
        );

        requestQueue.add(jsonObjectRequest);
    }

//    private void buscarViajes(int idRuta) {
//        String selectedFechaSalida = etDate.getText().toString();
//        String selectedFechaLlegada = etDateLlegada.getText().toString();
//
//        try {
//            // Extrae solo la parte de la fecha sin la hora ni la zona horaria (yyyy-MM-dd)
//            String fechaSalidaDateOnly = selectedFechaSalida.substring(0, 10); // Extrae "yyyy-MM-dd"
//            String fechaLlegadaDateOnly = selectedFechaLlegada.substring(0, 10); // Extrae "yyyy-MM-dd"
//
//            // Construir la URL con el formato correcto de fecha (sin hora y zona horaria)
//            String url = apiUrl + "/viaje/buscar?fechaSalida=" + fechaSalidaDateOnly + "&fechaLlegada=" + fechaLlegadaDateOnly + "&idRuta=" + idRuta;
//            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
//                    Request.Method.GET, url, null,
//                    response -> {
//                        try {
//                            List<Viaje> viajes = new ArrayList<>();
//                            for (int i = 0; i < response.length(); i++) {
//                                JSONObject viajeObj = response.getJSONObject(i);
//
//                                // Obtener todos los campos del viaje
//                                int idViaje = viajeObj.getInt("idViaje");
//                                String horaSalida = viajeObj.getString("horaSalida");
//                                String horaLlegada = viajeObj.getString("horaLlegada");
//
//                                // Obtener datos relacionados (como Ruta, Bus, Chofer, Administrador)
//                                JSONObject rutaObj = viajeObj.getJSONObject("ruta");
//                                int idRutaResponse = rutaObj.getInt("idRuta");
//                                String estadoRuta = rutaObj.getString("estadoRuta");
//                                double distancia = rutaObj.getDouble("distancia");
//                                String duracion = rutaObj.getString("duracion");
//
//                                // Terminales de Origen y Destino
//                                JSONObject terminalOrigenObj = rutaObj.getJSONObject("terminalOrigen");
//                                Terminal terminalOrigen = new Terminal(
//                                        terminalOrigenObj.getInt("idTerminal"),
//                                        terminalOrigenObj.getString("nombre"),
//                                        terminalOrigenObj.getString("direccion"),
//                                        terminalOrigenObj.getString("departamento"),
//                                        terminalOrigenObj.getString("provincia"),
//                                        terminalOrigenObj.getString("distrito"),
//                                        terminalOrigenObj.getString("coordenadaLatitud"),
//                                        terminalOrigenObj.getString("coordenadaLongitud"),
//                                        terminalOrigenObj.getString("estado")
//                                );
//
//                                JSONObject terminalDestinoObj = rutaObj.getJSONObject("terminalDestino");
//                                Terminal terminalDestino = new Terminal(
//                                        terminalDestinoObj.getInt("idTerminal"),
//                                        terminalDestinoObj.getString("nombre"),
//                                        terminalDestinoObj.getString("direccion"),
//                                        terminalDestinoObj.getString("departamento"),
//                                        terminalDestinoObj.getString("provincia"),
//                                        terminalDestinoObj.getString("distrito"),
//                                        terminalDestinoObj.getString("coordenadaLatitud"),
//                                        terminalDestinoObj.getString("coordenadaLongitud"),
//                                        terminalDestinoObj.getString("estado")
//                                );
//
//                                // Crear el objeto Ruta
//                                Ruta ruta = new Ruta(idRutaResponse, distancia, duracion, estadoRuta, terminalOrigen, terminalDestino, null);
//
//                                // Obtener datos del Bus
//                                JSONObject busObj = viajeObj.getJSONObject("bus");
//                                Bus bus = new Bus(
//                                        busObj.getLong("idBus"),
//                                        busObj.getString("nombre"),
//                                        busObj.getString("placa"),
//                                        busObj.getString("modelo"),
//                                        busObj.getInt("capacidadPiso1"),
//                                        busObj.getInt("capacidadPiso2"),
//                                        null // Agregar el estado del bus si es necesario
//                                );
//
//                                // Obtener datos del Chofer1
//                                JSONObject chofer1Obj = viajeObj.getJSONObject("chofer1");
//                                String fechaLicenciaString = chofer1Obj.getString("fechaLicencia");
//                                Date fechaLicencia = sdf.parse(fechaLicenciaString); // Convertir la fecha de licencia a Date
//
//                                Chofer chofer1 = new Chofer(
//                                        fechaLicencia, // Fecha de licencia
//                                        chofer1Obj.getString("nombre"),
//                                        true // Estado verdadero por defecto
//                                );
//
//                                // Obtener datos del Chofer2
//                                JSONObject chofer2Obj = viajeObj.getJSONObject("chofer2");
//                                String fechaLicencia2String = chofer2Obj.getString("fechaLicencia");
//                                Date fechaLicencia2 = sdf.parse(fechaLicencia2String); // Convertir la fecha de licencia de chofer2 a Date
//
//                                Chofer chofer2 = new Chofer(
//                                        fechaLicencia2, // Fecha de licencia
//                                        chofer2Obj.getString("nombre"),
//                                        true // Estado verdadero por defecto
//                                );
//
//                                // Obtener datos del Administrador
//                                JSONObject administradorObj = viajeObj.getJSONObject("administrador");
//                                Administrador administrador = new Administrador(
//                                        administradorObj.getLong("idAdministrador"),
//                                        administradorObj.getString("correo"),
//                                        administradorObj.getString("password"),
//                                        null // Puedes agregar el estado si es necesario
//                                );
//
//                                // Crear el objeto Viaje
//                                Viaje viaje = new Viaje(
//                                        idViaje, // idViaje
//                                        fechaSalida, // Fecha de salida (ya es un objeto Date)
//                                        fechaLlegada, // Fecha de llegada (ya es un objeto Date)
//                                        horaSalida, // Hora de salida
//                                        horaLlegada, // Hora de llegada
//                                        ruta, // Objeto Ruta
//                                        administrador ,// Objeto Administrador
//                                        bus, // Objeto Bus
//                                        chofer1, // Objeto Chofer1
//                                        chofer2 // Objeto Chofer2
//                                );
//
//                                viajes.add(viaje); // Agregar a la lista de viajes
//                            }
//
//                            Log.d("ViajeInfo", "Datos de viajes obtenidos: " + viajes);
//                            // Aquí podrías actualizar el servicio o navegar a otra actividad
//                        } catch (JSONException e) {
//                            Log.e("ViajeError", "Error al procesar la respuesta JSON de viajes: " + e.getMessage());
//                        } catch (ParseException e) {
//                            throw new RuntimeException(e);
//                        }
//                    },
//                    error -> Log.e("ViajeError", "Error al buscar los viajes: " + error.getMessage())
//            );
//
//            requestQueue.add(jsonArrayRequest);
//        } catch (ParseException e) {
//            Log.e("FechaError", "Error al convertir las fechas: " + e.getMessage());
//        }
//    }
//
private void buscarViajes(int idRuta) {
    String selectedFechaSalida = etDate.getText().toString().trim();  // Eliminar espacios adicionales
    String selectedFechaLlegada = etDateLlegada.getText().toString().trim();  // Eliminar espacios adicionales

    try {
        // Verificar si las fechas están vacías
        if (selectedFechaSalida.isEmpty() || selectedFechaLlegada.isEmpty()) {
            Log.e("FechaError", "Las fechas de salida o llegada están vacías");
            return;
        }

        // Verificar el formato de las fechas
        Log.d("HomeActivity", "Fecha de Salida: " + selectedFechaSalida);
        Log.d("HomeActivity", "Fecha de Retorno: " + selectedFechaLlegada);

        // Convertir las fechas al formato dd/MM/yyyy
        SimpleDateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy");
        originalFormat.setTimeZone(TimeZone.getTimeZone("America/Lima"));

        // Aquí se incluye la hora en el formato yyyy-MM-dd HH:mm:ss
        SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        targetFormat.setTimeZone(TimeZone.getTimeZone("America/Lima"));

        // Intentar parsear las fechas
        Date fechaSalidaDate = originalFormat.parse(selectedFechaSalida);
        Date fechaLlegadaDate = originalFormat.parse(selectedFechaLlegada);

        // Convertir las fechas de Date a String en el formato requerido
        String fechaSalidaISO = targetFormat.format(fechaSalidaDate);
        String fechaLlegadaISO = targetFormat.format(fechaLlegadaDate) ;

        Log.d("HomeActivity", "Fecha de Salida (formateada): " + fechaSalidaISO);
        Log.d("HomeActivity", "Fecha de Retorno (formateada): " + fechaLlegadaISO);

        // Construir la URL
        String url = apiUrl + "/viaje/buscar?fechaSalida=" + fechaSalidaISO + "&fechaLlegada=" + fechaLlegadaISO + "&idRuta=" + idRuta;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null,
                response -> {
                    // Verificar si la respuesta contiene resultados
                    if (response.length() > 0) {
                        Log.d("ViajeInfo", "Se encontraron " + response.length() + " viajes.");
                        // Aquí puedes procesar los datos recibidos, por ejemplo, mostrando los viajes en un RecyclerView o ListView.
                    } else {
                        Log.d("ViajeInfo", "No se encontraron viajes.");
                    }
                },
                error -> Log.e("ViajeError", "Error al buscar los viajes: " + error.getMessage())
        );

        requestQueue.add(jsonArrayRequest);
    } catch (ParseException e) {
        Log.e("FechaError", "Error al convertir las fechas: " + e.getMessage());
    }
}


}

