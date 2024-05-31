package com.example.a1098_vladralucaalecsandra.pachet;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Informatie")
public class Informatie {
    @ColumnInfo(name = "cheie")
    @PrimaryKey(autoGenerate = true)
    private int cheie;
    @ColumnInfo(name = "enunt")
    private String enunt;
    @ColumnInfo(name = "mesaj")
    private String mesaj;
    @ColumnInfo(name = "autor")
    private String autor;
    @ColumnInfo(name = "data")
    private String data;

    public Informatie(String enunt, String mesaj, String autor, String data) {
        this.enunt = enunt;
        this.mesaj = mesaj;
        this.autor = autor;
        this.data = data;
    }

    public int getCheie() {
        return cheie;
    }

    public void setCheie(int cheie) {
        this.cheie = cheie;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEnunt() {
        return enunt;
    }

    public void setEnunt(String enunt) {
        this.enunt = enunt;
    }

    public String getMesaj() {
        return mesaj;
    }

    public void setMesaj(String mesaj) {
        this.mesaj = mesaj;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Ignore
    @NonNull
    @Override
    public String toString() {
        return this.enunt;
    }
}
