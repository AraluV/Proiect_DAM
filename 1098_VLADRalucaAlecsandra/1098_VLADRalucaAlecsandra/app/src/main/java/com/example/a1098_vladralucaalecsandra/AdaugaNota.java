package com.example.a1098_vladralucaalecsandra;


import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import com.example.a1098_vladralucaalecsandra.pachet.Informatie;
import com.example.a1098_vladralucaalecsandra.pachet.Nota;
import com.example.a1098_vladralucaalecsandra.pachet.RoomBD;

import java.util.ArrayList;

public class AdaugaNota extends AppCompatActivity {

    private RoomBD roomBD;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adauga_nota);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        roomBD = Room.databaseBuilder(getApplicationContext(), RoomBD.class, "roomBD").build();

        spinner = findViewById(R.id.enunturi_spinner);

        populeazaSpinner();

        Button buton_anuleaza = findViewById(R.id.inchide_nota_button);
        buton_anuleaza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button buton_salveaza = findViewById(R.id.salveaza_nota_button);
        buton_salveaza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Informatie informatie = (Informatie) spinner.getSelectedItem();
                EditText mesaj = findViewById(R.id.mesaj_editTextTextMultiLine);
                Nota nota = new Nota(informatie.getCheie(), mesaj.getText().toString());
                salveazaNota(nota);
                finish();
            }
        });

    }

    private void populeazaSpinner() {
        new Thread(() -> {
            ArrayList<Informatie> informatii = (ArrayList<Informatie>) roomBD.informatieDAO().getInformatii();
            runOnUiThread(() -> {
                ArrayAdapter<Informatie> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, informatii);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
            });
        }).start();
    }

    private void salveazaNota(Nota nota) {
        new Thread(() -> roomBD.notaDAO().adaugaNota(nota)).start();
    }
}