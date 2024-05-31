package com.example.a1098_vladralucaalecsandra.pachet;

import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {Informatie.class, Nota.class}, version = 1)
public abstract class RoomBD extends RoomDatabase {
    public abstract InformatieDAO informatieDAO();

    public abstract NotaDAO notaDAO();
}
