package com.unam.unica.cursos.frafments;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AuxSQL extends SQLiteOpenHelper {
    String sqlTabla1 = "CREATE TABLE Ingresos(codigo integer primary key AUTOINCREMENT, tipo text, cantidad real, anho integer, mes integer, dia integer)";
    String sqlTabla2 = "CREATE TABLE Gastos(codigo integer primary key AUTOINCREMENT, tipo text, cantidad real, anho integer, mes integer, dia integer)";

    public AuxSQL(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlTabla1);
        db.execSQL(sqlTabla2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Ingresos");
        db.execSQL("DROP TABLE IF EXISTS Gastos");
        db.execSQL(sqlTabla1);
        db.execSQL(sqlTabla2);
    }
}
