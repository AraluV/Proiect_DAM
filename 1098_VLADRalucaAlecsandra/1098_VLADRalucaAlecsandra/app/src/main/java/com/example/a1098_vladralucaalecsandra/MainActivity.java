package com.example.a1098_vladralucaalecsandra;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import com.example.a1098_vladralucaalecsandra.pachet.Informatie;
import com.example.a1098_vladralucaalecsandra.pachet.InformatieAdapter;
import com.example.a1098_vladralucaalecsandra.pachet.RoomBD;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RoomBD roomBD;
    private ListView listView;
    private InformatieAdapter informatieAdapter;
    private ArrayList<Informatie> informatii;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listView = findViewById(R.id.lv_mainActivity);
        roomBD = Room.databaseBuilder(getApplicationContext(), RoomBD.class, "roomBD").build();

        SharedPreferences preferences = getSharedPreferences("preferences", MODE_PRIVATE);
        boolean primaInitializare = preferences.getBoolean("primaInitializare", true);
        if (primaInitializare) {
            citireDinJson();
            SharedPreferences.Editor editor = getSharedPreferences("preferences", MODE_PRIVATE).edit();
            editor.putBoolean("primaInitializare", false);
            editor.apply();
        }

        afisareInformatii();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Informatie informatie = informatii.get(position);
                Intent intent = new Intent(MainActivity.this, AfisareInformatie.class);
                intent.putExtra("cheieInformatie", informatie.getCheie());
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.meniu_aplicatie, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.meniu_adaugareNota) {
            Intent intent = new Intent(MainActivity.this, AdaugaNota.class);
            startActivity(intent);
        }
        else if (id == R.id.meniu_vizulizareNote) {
            Intent intent = new Intent(MainActivity.this, ListaNote.class);
            startActivity(intent);
        }
        else if (id == R.id.meniu_despre) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Despre")
                    .setMessage("Aplicatie creata de\n\t\t\tVLAD Raluca Alecsandra\nGrupa 1098 ASE 2024\nVersiunea 1.0")
                    .setPositiveButton("Inchide", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        else super.onOptionsItemSelected(item);
        return true;
    }

    public void citireDinJson() {
        try {
            InputStream inputStream = getApplicationContext().getAssets().open("date.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String json = new String(buffer, StandardCharsets.UTF_8);

            JSONObject jsonObject = new JSONObject(json);
            JSONArray array = jsonObject.getJSONArray("Informatie");

            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                Informatie informatie = new Informatie(object.getString("Enunt"), object.getString("Mesaj"), object.getString("Autor"), object.getString("Data"));
                new Thread(() -> roomBD.informatieDAO().adaugaInformatie(informatie)).start();
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void afisareInformatii() {
        new Thread(() -> {
            informatii = (ArrayList<Informatie>) roomBD.informatieDAO().getInformatii();
            runOnUiThread(() -> {
                informatieAdapter = new InformatieAdapter(getApplicationContext(), informatii);
                listView.setAdapter(informatieAdapter);
            });
        }).start();
    }
}