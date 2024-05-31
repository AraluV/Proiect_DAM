package com.example.a1098_vladralucaalecsandra.pachet;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.a1098_vladralucaalecsandra.R;

import java.util.List;

public class InformatieAdapter extends ArrayAdapter<Informatie> {
    public InformatieAdapter(Context context, List<Informatie> object) {
        super(context, 0 , object);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adaptor_informatie, parent, false);
        Informatie informatie = getItem(position);
        TextView enunt = convertView.findViewById(R.id.enunt_informatie_textView);
        enunt.setText(informatie.getEnunt());
        TextView mesaj = convertView.findViewById(R.id.mesaj_informatie_textView);
        mesaj.setText(informatie.getMesaj());
        if(position % 2 ==0)
            convertView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.para));
        else convertView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.impara));

        return convertView;
    }
}
