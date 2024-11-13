package com.example.aplicativomvilparalacompradeboletosinterprovincialesenelper;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private DatePickerListener listener;

    // Interfaz para pasar la fecha seleccionada
    public interface DatePickerListener {
        void onDateSet(int day, int month, int year);
    }

    // Establece el listener
    public void setDatePickerListener(DatePickerListener listener) {
        this.listener = listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Obtiene la fecha actual
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Crea el DatePickerDialog con el día, mes y año actuales
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        // Llama al listener cuando se selecciona una fecha
        if (listener != null) {
            listener.onDateSet(dayOfMonth, month, year);
        }
    }
}
