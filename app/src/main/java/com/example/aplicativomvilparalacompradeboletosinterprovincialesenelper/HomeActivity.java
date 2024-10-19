package com.example.aplicativomvilparalacompradeboletosinterprovincialesenelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aplicativomvilparalacompradeboletosinterprovincialesenelper.Vistas.EditarPerfil;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button btnLogout = findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

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
}
