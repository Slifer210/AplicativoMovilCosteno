package com.example.aplicativomvilparalacompradeboletosinterprovincialesenelper;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.aplicativomvilparalacompradeboletosinterprovincialesenelper.Entidades.Bus;
import com.example.aplicativomvilparalacompradeboletosinterprovincialesenelper.Entidades.Ruta;
import com.example.aplicativomvilparalacompradeboletosinterprovincialesenelper.Entidades.Viaje;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListarActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ViajeAdapter viajeAdapter;
    private List<Viaje> listaViajes;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listar_viajes);

        // Referencias a las vistas para mostrar ciudad y fecha
        TextView ciudadSalidaView = findViewById(R.id.ciudad_salida);
        TextView ciudadDestinoView = findViewById(R.id.ciudad_destino);
        TextView fechaViajeView = findViewById(R.id.fecha_viaje);

        recyclerView = findViewById(R.id.lst1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        requestQueue = Volley.newRequestQueue(this);

        int idRuta = getIntent().getIntExtra("idRuta", -1);

        if (idRuta != -1) {
            String url = Conexion.apiUrl + "/viaje/listar?idRuta=" + idRuta;
            cargarViajes(url, ciudadSalidaView, ciudadDestinoView, fechaViajeView);
        } else {
            Log.e("ListarActivity", "idRuta no encontrado en el Intent");
        }
    }

    private void cargarViajes(String url, TextView ciudadSalidaView, TextView ciudadDestinoView, TextView fechaViajeView) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        listaViajes = new ArrayList<>();

                        try {
                            if (response.length() > 0) {
                                for (int i = 0; i < response.length(); i++) {
                                    JSONObject viajeObj = response.getJSONObject(i);
                                    int idViaje = viajeObj.getInt("idViaje");

                                    // Obtener ciudad de salida y destino directamente de la respuesta JSON
                                    JSONObject rutaObj = viajeObj.getJSONObject("ruta");
                                    String ciudadSalida = rutaObj.getJSONObject("terminalOrigen").getString("departamento");
                                    String ciudadDestino = rutaObj.getJSONObject("terminalDestino").getString("departamento");

                                    String fechaSalidaStr = viajeObj.getString("fechaSalida");
                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                    Date fechaSalida = sdf.parse(fechaSalidaStr);

                                    String horaSalida = viajeObj.getString("horaSalida");
                                    String horaLlegada = viajeObj.getString("horaLlegada");

                                    JSONObject busObj = viajeObj.getJSONObject("bus");
                                    String nombreBus = busObj.getString("nombre");
                                    Bus bus = new Bus(nombreBus);

                                    Ruta ruta = new Ruta(ciudadSalida, ciudadDestino, rutaObj.getString("duracion"));

                                    Viaje viaje = new Viaje(idViaje, bus, fechaSalida, horaSalida, horaLlegada, ruta);

                                    listaViajes.add(viaje);

                                    // Actualizar la primera vez con los datos del primer viaje
                                    if (i == 0) {
                                        ciudadSalidaView.setText(ciudadSalida);
                                        ciudadDestinoView.setText(ciudadDestino);
                                        SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MMM-yyyy EEEE");
                                        fechaViajeView.setText(outputFormat.format(fechaSalida));
                                    }
                                }

                                viajeAdapter = new ViajeAdapter(listaViajes);
                                recyclerView.setAdapter(viajeAdapter);
                            } else {
                                Log.d("ListarActivity", "No se encontraron viajes.");
                            }

                        } catch (JSONException | java.text.ParseException e) {
                            Log.e("ListarActivity", "Error al procesar la respuesta JSON: " + e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("ListarActivity", "Error en la solicitud: " + error.getMessage());
                    }
                }
        );

        requestQueue.add(jsonArrayRequest);
    }


}

