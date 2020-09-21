package com.elcio.testsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //nome do banco
        String banco = getString(R.string.banco_name);

        try {

            //cria o banco
            SQLiteDatabase database = openOrCreateDatabase(banco, MODE_PRIVATE, null);

            //cria a tabela
            String sql = "CREATE TABLE IF NOT EXISTS pessoa (nome VARCHAR(256), idade INT(3))";
            database.execSQL(sql);

            //insere dados
            String sqlInsert = "INSERT INTO pessoa(nome, idade) VALUES('maria', 30)";
            database.execSQL(sqlInsert);

            String sqlSelect = "SELECT * FROM pessoa";
            Cursor cursor = database.rawQuery(sqlSelect, null);

            int indiceNome = cursor.getColumnIndex("nome");
            int indiceIdade = cursor.getColumnIndex("idade");

            cursor.moveToFirst();
            while (cursor != null){
                Log.i("MINHA MENSAGEM",cursor.getString(indiceNome));
                cursor.moveToNext();
            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }


}
