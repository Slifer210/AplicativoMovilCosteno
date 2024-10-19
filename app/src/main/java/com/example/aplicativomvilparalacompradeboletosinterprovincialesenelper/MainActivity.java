package com.example.aplicativomvilparalacompradeboletosinterprovincialesenelper;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aplicativomvilparalacompradeboletosinterprovincialesenelper.Acceso.inicio;

public class MainActivity extends AppCompatActivity {

    public static int tiempo = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Handler to start another activity after a delay
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, inicio.class);
                startActivity(intent);
                finish();
            }
        }, tiempo);
    }
}
