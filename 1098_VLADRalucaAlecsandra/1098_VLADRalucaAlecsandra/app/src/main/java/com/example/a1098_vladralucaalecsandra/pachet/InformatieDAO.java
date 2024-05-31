package com.example.a1098_vladralucaalecsandra.pachet;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface InformatieDAO {
    @Insert
    public void adaugaInformatie(Informatie informatie);

    @Query("SELECT * FROM Informatie WHERE cheie = :cheie")
    public Informatie getInformatieDupaCheie(int cheie);

    @Query("SELECT * FROM Informatie ORDER BY data DESC")
    public List<Informatie> getInformatii();
}
