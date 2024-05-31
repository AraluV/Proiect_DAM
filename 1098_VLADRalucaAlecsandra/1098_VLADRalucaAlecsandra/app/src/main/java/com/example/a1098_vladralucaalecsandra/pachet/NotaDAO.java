package com.example.a1098_vladralucaalecsandra.pachet;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NotaDAO {
    @Insert
    public void adaugaNota(Nota nota);

    @Query("SELECT I.cheie, N.cheieInformatie, N.mesaj FROM Nota AS N INNER JOIN Informatie AS I ON I.cheie = N.cheieInformatie")
    public List<Nota> getNote();
}
