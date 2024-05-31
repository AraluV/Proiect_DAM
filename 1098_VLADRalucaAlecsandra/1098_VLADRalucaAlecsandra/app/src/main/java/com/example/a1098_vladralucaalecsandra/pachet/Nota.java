package com.example.a1098_vladralucaalecsandra.pachet;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "Nota", foreignKeys = @ForeignKey(entity = Informatie.class, parentColumns = "cheie", childColumns = "cheieInformatie"))
public class Nota {
    @ColumnInfo(name = "cheie")
    @PrimaryKey(autoGenerate = true)
    private int cheie;
    @ColumnInfo(name = "cheieInformatie")
    private int cheieInformatie;
    @ColumnInfo(name = "mesaj")
    private String mesaj;

    public Nota(int cheieInformatie, String mesaj) {
        this.cheieInformatie = cheieInformatie;
        this.mesaj = mesaj;
    }

    public int getCheie() {
        return cheie;
    }

    public void setCheie(int cheie) {
        this.cheie = cheie;
    }

    public int getCheieInformatie() {
        return cheieInformatie;
    }

    public void setCheieInformatie(int cheieInformatie) {
        this.cheieInformatie = cheieInformatie;
    }

    public String getMesaj() {
        return mesaj;
    }

    public void setMesaj(String mesaj) {
        this.mesaj = mesaj;
    }
}
