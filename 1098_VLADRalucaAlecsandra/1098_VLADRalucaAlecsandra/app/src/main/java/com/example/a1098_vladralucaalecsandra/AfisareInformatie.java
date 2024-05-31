package com.example.a1098_vladralucaalecsandra;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import com.example.a1098_vladralucaalecsandra.pachet.Informatie;
import com.example.a1098_vladralucaalecsandra.pachet.RoomBD;

public class AfisareInformatie extends AppCompatActivity {

    private RoomBD roomBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_afisare_informatie);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        roomBD = Room.databaseBuilder(getApplicationContext(), RoomBD.class, "roomBD").build();

        int cheie = getIntent().getIntExtra("cheieInformatie", 0);
        cautaInformatieDupaCheie(cheie);

        Button button = findViewById(R.id.button_inchide_afisare_informatie);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void cautaInformatieDupaCheie(int cheie) {
        new Thread(() -> {
            Informatie informatie = roomBD.informatieDAO().getInformatieDupaCheie(cheie);
            runOnUiThread(() -> {
                TextView enunt = findViewById(R.id.afisare_enunt_textView);
                enunt.setText(informatie.getEnunt());
                TextView mesaj = findViewById(R.id.afisare_mesaj_textView);
                mesaj.setText(informatie.getMesaj());
                TextView autor = findViewById(R.id.afisare_autor_textView);
                autor.setText(informatie.getAutor());
                TextView data = findViewById(R.id.afisare_data_textView);
                data.setText(informatie.getData());
            });
        }).start();
    }
}