package com.example.projekt;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;


import java.util.ArrayList;


/**
 * Klasa wyswietlające liste wyników
 */
public class ListDataActivity extends AppCompatActivity {


    DatabaseHelper mDatabaseHelper;
    /**
     * wybrany poziom
     */
    int poziom;
    private ListView mListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        poziom = getIntent().getExtras().getInt("POZ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
        mListView = (ListView) findViewById(R.id.listView);
        mDatabaseHelper = new DatabaseHelper(this);

        createListView();
    }

    /**
     * Tworzenie listy
     */
    private void createListView() {

        mListView.setAdapter(null);
        Cursor data = mDatabaseHelper.getData(poziom);
        ArrayList<String> listData = new ArrayList<>();
        while(data.moveToNext()){

            listData.add(data.getString(1)+ " czas: "+data.getString(2)+"sek");
        }

        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        mListView.setAdapter(adapter);

    }
    /**
     *Funkcja obsługująca kliknięcia
     * @param view
     *      kliknięty obiekt na ekranie
     */
    public void onClick(View view)
    {
        Intent intent2 = new Intent(ListDataActivity.this, ListDataActivity.class);

        switch (view.getId()) {
            case R.id.bt3v3:
                poziom =3;
                createListView();
                break;
            case R.id.bt4v4:
                poziom =4;
                createListView();
                break;
            case R.id.bt5v5:
                poziom =5;
                createListView();
                break;
            case R.id.imageView:
                Intent intent = new Intent(ListDataActivity.this, MainActivity.class);
               startActivity(intent);
                break;


        }
    }

}