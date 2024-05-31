package com.example.a1098_vladralucaalecsandra.pachet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.a1098_vladralucaalecsandra.R;

import java.util.List;

public class NotaAdapter extends ArrayAdapter<Nota> {
    public NotaAdapter(Context context, List<Nota> object) {
        super(context, 0, object);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adaptor_nota, parent, false);
        Nota nota = getItem(position);
        TextView enunt = convertView.findViewById(R.id.enunt_nota_textView);
        enunt.setText(String.valueOf(nota.getCheieInformatie()));
        TextView mesaj = convertView.findViewById(R.id.mesaj_nota_textView);
        mesaj.setText(nota.getMesaj());

        return convertView;
    }
}
