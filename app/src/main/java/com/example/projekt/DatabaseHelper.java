package com.example.projekt;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Klasa w której będą się znajdowały działania na bazie
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    /**
     * nazwa tabeli
     */
    private static final String TABLE_NAME = "rekord";
    /**
     * pierwsza kolumna - id
     */
    private static final String COL1 = "ID";
    /**
     * dtuga kolumna - nazwa gracza
     */
    private static final String COL2 = "nazwa";
    /**
     * trzecia kolumna czask ułożenia
     */
    private static final String COL3 = "czas";

    /**
     * czwarta kolumna - plansza
     */
    private static final String COL4 = "plansza";

    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    /**
     * tworzenie tabeli w bazie
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COL2 + " TEXT," + COL3 + " REAL," + COL4 + " INTEGER)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    /**
     * @param nazwa
     *      nazwa gracza
     * @param czas
     *      czas ułozenia puzzli
     * @param plansza
     * wybrana plansza
     * @return
     *      zwraca true gdy doda dane do tabeli i false jak nie doda danych
     */
    public boolean addData(String nazwa, double czas, int plansza) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, nazwa);
        contentValues.put(COL3, czas);
        contentValues.put(COL4, plansza);

        Log.d("bd", "dodano:  " + nazwa + " " + czas + " " + plansza + " do " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }


    /**
     * pobiera dane z tabeli
     * @param poziom
     *  wybrany poziom
     * @return
     *      zwraca dane
     */
    public Cursor getData(int poziom) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME +" WHERE plansza = "+ poziom +" ORDER BY czas ASC";
        Cursor data = db.rawQuery(query, null);
        return data;
    }
}