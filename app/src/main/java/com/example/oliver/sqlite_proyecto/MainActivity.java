package com.example.oliver.sqlite_proyecto;

import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView lista;
    FloatingActionButton fab;
    DBAdapter bd;

    AdaptadorListView adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lista = (ListView) findViewById(R.id.lista);
        fab=(FloatingActionButton)findViewById(R.id.floatingActionButton);
        bd=new DBAdapter(getApplicationContext());
        llenarLista();
    }

    private void llenarLista(){
        bd.open();
/*
        bd.insertContact("Oliver Jasiel Galaviz Arroyo","oljagalavizar@ittepic.edu.mx","311-740-71-11");
        bd.insertContact("Marlene Yael Alvarez Parra","mayaalvarezpa@ittepic.edu.mx","311-260-07-04");
        bd.insertContact("Fernando Samuel Enriquez Hernandez","fesaenriquezhe@ittepic.edu.mx","311-268-43-74");
        bd.insertContact("Cesar Ramses Espino Serrano","ceraespinose@ittepic.edu.mx","323-239-18-02");
        bd.insertContact("Genaro Yair Enciso Luna","geyaencisolu@ittepic.edu.mx","311-139-92-94");
*/
        int n=bd.lengthQuery();

        String [] nombre = new String[n];
        String [] email = new String[n];
        String [] telefono = new String[n];
        //int ids[] = new int[n];

        int i=0;
        Cursor result=bd.getAllContactsAZ();
        result.moveToFirst();
        while (!result.isAfterLast()) {
            int id=result.getInt(0);
            String name=result.getString(1);
            String email1=result.getString(2);
            String phone=result.getString(3);

          //  ids[i]=id;
            nombre[i]=name;
            email[i]=email1;
            telefono[i]=phone;

            i++;

            result.moveToNext();
        }
        result.close();

        adapter = new AdaptadorListView(this, nombre,email,telefono);
        lista.setAdapter(adapter);

        bd.close();
    }
}