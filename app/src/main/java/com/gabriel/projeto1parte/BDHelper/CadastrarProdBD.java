package com.gabriel.projeto1parte.BDHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.gabriel.projeto1parte.model.Cadastro;

import java.util.ArrayList;

public class CadastrarProdBD extends SQLiteOpenHelper {

    private static final String DATABASE = "bdprodutoscad";
    private static final int VERSION = 1;

    public CadastrarProdBD (Context context) {
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {

        String produto = "CREATE TABLE Cadastro(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nomeprod TEXT NOT NULL, quantestoq INTEGER, precoprod INTEGER, local TEXT NOT NULL);";
        bd.execSQL(produto);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String produto = "DROP TABLE IF EXISTS Cadastro";
        db.execSQL(produto);
    }

    public void salvarProdutosCad(Cadastro Cadastro)
    {

        ContentValues values = new ContentValues();

        values.put("nomeprod", Cadastro.getNomeprod());
        values.put("quantestoq", Cadastro.getQuantestoq());
        values.put("precoprod", Cadastro.getPrecoprod());
        values.put("local", Cadastro.getLocal());

        getWritableDatabase().insert("Cadastro", null, values);
    }

    public ArrayList<Cadastro> getLista()
    {

        String [] columns = {"id","nomeprod","quantestoq","precoprod","local"};
        Cursor cursor = getWritableDatabase().query("Cadastro",columns,null, null, null, null, null, null);

        ArrayList<Cadastro> cadastro = new ArrayList<Cadastro>();

        while(cursor.moveToNext()){

            Cadastro cadastros = new Cadastro();
            cadastros.setId(cursor.getLong(0));
            cadastros.setNomeprod(cursor.getString(1));
            cadastros.setQuantestoq(cursor.getInt(2));
            cadastros.setPrecoprod(cursor.getInt(3));
            cadastros.setLocal(cursor.getString(4));

            cadastro.add(cadastros);

        }return cadastro;
    }



}
