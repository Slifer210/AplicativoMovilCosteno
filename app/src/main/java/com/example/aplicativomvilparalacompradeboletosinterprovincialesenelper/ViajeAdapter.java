package com.example.aplicativomvilparalacompradeboletosinterprovincialesenelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.aplicativomvilparalacompradeboletosinterprovincialesenelper.Entidades.Viaje;
import com.example.aplicativomvilparalacompradeboletosinterprovincialesenelper.R;
import java.util.List;

public class ViajeAdapter extends RecyclerView.Adapter<ViajeAdapter.ViajeViewHolder> {

    private List<Viaje> listaViajes;

    public ViajeAdapter(List<Viaje> listaViajes) {
        this.listaViajes = listaViajes;
    }

    @NonNull
    @Override
    public ViajeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflar el layout del item_viajes
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_viajes, parent, false);
        return new ViajeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViajeViewHolder holder, int position) {
        Viaje viaje = listaViajes.get(position);

        if (viaje.getBus() != null && viaje.getBus().getNombre() != null) {
            holder.busTextView.setText(viaje.getBus().getNombre());
        } else {
            holder.busTextView.setText("Bus no disponible");
        }
        if (viaje.getHoraSalida() != null) {
            holder.horaSalidaTextView.setText(viaje.getHoraSalida());
        } else {
            holder.horaSalidaTextView.setText("Hora de salida no disponible");
        }

        if (viaje.getHoraLlegada() != null) {
            holder.horaLlegadaTextView.setText(viaje.getHoraLlegada());
        } else {
            holder.horaLlegadaTextView.setText("Hora de llegada no disponible");
        }

        if (viaje.getRuta() != null && viaje.getRuta().getDuracion() != null) {
            holder.duracionTextView.setText(viaje.getRuta().getDuracion());
        } else {
            holder.duracionTextView.setText("Duración no disponible");
        }
    }

    @Override
    public int getItemCount() {
        return listaViajes.size();
    }

    public static class ViajeViewHolder extends RecyclerView.ViewHolder {

        TextView busTextView, horaSalidaTextView, horaLlegadaTextView, precioTextView, duracionTextView, asientosDisponiblesTextView;

        public ViajeViewHolder(@NonNull View itemView) {
            super(itemView);
            busTextView = itemView.findViewById(R.id.bus);
            horaSalidaTextView = itemView.findViewById(R.id.horaSalida);
            horaLlegadaTextView = itemView.findViewById(R.id.horaLlegada);
            precioTextView = itemView.findViewById(R.id.precio);
            duracionTextView = itemView.findViewById(R.id.duracion);
            asientosDisponiblesTextView = itemView.findViewById(R.id.asientos_disponibles);
        }
    }
}
