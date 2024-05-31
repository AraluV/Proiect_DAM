package com.example.a1098_vladralucaalecsandra;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import com.example.a1098_vladralucaalecsandra.pachet.Nota;
import com.example.a1098_vladralucaalecsandra.pachet.NotaAdapter;
import com.example.a1098_vladralucaalecsandra.pachet.RoomBD;

import java.util.ArrayList;

public class ListaNote extends AppCompatActivity {
    private ListView listView;
    private RoomBD roomBD;
    private NotaAdapter notaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_note);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listView = findViewById(R.id.lv_lista_note);

        roomBD = Room.databaseBuilder(getApplicationContext(), RoomBD.class, "roomBD").build();

        populeazaNote();
    }

    private void populeazaNote() {
        new Thread(() -> {
            ArrayList<Nota> note = (ArrayList<Nota>) roomBD.notaDAO().getNote();
            runOnUiThread(() -> {
                notaAdapter = new NotaAdapter(getApplicationContext(), note);
                listView.setAdapter(notaAdapter);
            });
        }).start();
    }
}