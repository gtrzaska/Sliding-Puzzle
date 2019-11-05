package com.example.projekt;


import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;


/**
 * Generowanie planszy, mechanika gry
 */
public class PlanszaActivity extends AppCompatActivity {
    /**
     * stan gry 0- w toku 1-zakończona
     */
    int koniec = 0;
    DatabaseHelper mDatabaseHelper;

    private Integer p00,show=0,obr_caly;
    /**
     * zmianna do której będzie przypisana tablica z dobrze ułożonymi puzzlami
     */
    private Integer[][] Ar1 ;
    /**
     * tablica zawierająca puzzle dla obrazu 1 3x3
     */
    private Integer[][] ArrO1v3 = new Integer[][]  {{R.drawable.o1cz1v3, R.drawable.o1cz2v3, R.drawable.o1cz3v3},
            {R.drawable.o1cz4v3, R.drawable.o1cz5v3, R.drawable.o1cz6v3},
            {R.drawable.o1cz7v3, R.drawable.o1cz8v3, R.drawable.o1cz9v3}};
    /**
     * tablica zawierająca puzzle dla obrazu 1 4x4
     */
    private Integer[][] ArrO1v4 = new Integer[][]  {{R.drawable.o1cz1v4b, R.drawable.o1cz2v4, R.drawable.o1cz3v4,R.drawable.o1cz4v4},
            {R.drawable.o1cz5v4, R.drawable.o1cz6v4, R.drawable.o1cz7v4,R.drawable.o1cz8v4},
            {R.drawable.o1cz9v4, R.drawable.o1cz10v4, R.drawable.o1cz11v4,R.drawable.o1cz12v4},
            {R.drawable.o1cz13v4, R.drawable.o1cz14v4, R.drawable.o1cz15v4,R.drawable.o1cz16v4}};
    /**
     * tablica zawierająca puzzle dla obrazu 1 5x5
     */
    private Integer[][] ArrO1v5 = new Integer[][]  {{R.drawable.o1cz1v5b, R.drawable.o1cz2v5, R.drawable.o1cz3v5,R.drawable.o1cz4v5,R.drawable.o1cz5v5},
            {R.drawable.o1cz6v5, R.drawable.o1cz7v5, R.drawable.o1cz8v5,R.drawable.o1cz9v5,R.drawable.o1cz10v5},
            {R.drawable.o1cz11v5, R.drawable.o1cz12v5, R.drawable.o1cz13v5,R.drawable.o1cz14v5,R.drawable.o1cz15v5},
            {R.drawable.o1cz16v5, R.drawable.o1cz17v5, R.drawable.o1cz18v5,R.drawable.o1cz19v5,R.drawable.o1cz20v5},
            {R.drawable.o1cz21v5, R.drawable.o1cz22v5, R.drawable.o1cz23v5,R.drawable.o1cz24v5,R.drawable.o1cz25v5}};
    /**
     * tablica zawierająca puzzle dla obrazu 2 3x3
     */
    private Integer[][] ArrO2v3 = new Integer[][]  {{R.drawable.o2cz1v3b, R.drawable.o2cz2v3, R.drawable.o2cz3v3},
            {R.drawable.o2cz4v3, R.drawable.o2cz5v3, R.drawable.o2cz6v3},
            {R.drawable.o2cz7v3, R.drawable.o2cz8v3, R.drawable.o2cz9v3}};
    /**
     * tablica zawierająca puzzle dla obrazu 2 4x4
     */
    private Integer[][] ArrO2v4 = new Integer[][]  {{R.drawable.o2cz1v4b, R.drawable.o2cz2v4, R.drawable.o2cz3v4,R.drawable.o2cz4v4},
            {R.drawable.o2cz5v4, R.drawable.o2cz6v4, R.drawable.o2cz7v4,R.drawable.o2cz8v4},
            {R.drawable.o2cz9v4, R.drawable.o2cz10v4, R.drawable.o2cz11v4,R.drawable.o2cz12v4},
            {R.drawable.o2cz13v4, R.drawable.o2cz14v4, R.drawable.o2cz15v4,R.drawable.o2cz16v4}};
    /**
     * tablica zawierająca puzzle dla obrazu 2 5x5
     */
    private Integer[][] ArrO2v5 = new Integer[][]  {{R.drawable.o2cz1v5b, R.drawable.o2cz2v5, R.drawable.o2cz3v5,R.drawable.o2cz4v5,R.drawable.o2cz5v5},
            {R.drawable.o2cz6v5, R.drawable.o2cz7v5, R.drawable.o2cz8v5,R.drawable.o2cz9v5,R.drawable.o2cz10v5},
            {R.drawable.o2cz11v5, R.drawable.o2cz12v5, R.drawable.o2cz13v5,R.drawable.o2cz14v5,R.drawable.o2cz15v5},
            {R.drawable.o2cz16v5, R.drawable.o2cz17v5, R.drawable.o2cz18v5,R.drawable.o2cz19v5,R.drawable.o2cz20v5},
            {R.drawable.o2cz21v5, R.drawable.o2cz22v5, R.drawable.o2cz23v5,R.drawable.o2cz24v5,R.drawable.o2cz25v5}};
    /**
     * tablica zawierająca puzzle dla obrazu 3 3x3
     */
    private Integer[][] ArrO3v3 = new Integer[][]  {{R.drawable.o3cz1v3b, R.drawable.o3cz2v3, R.drawable.o3cz3v3},
            {R.drawable.o3cz4v3, R.drawable.o3cz5v3, R.drawable.o3cz6v3},
            {R.drawable.o3cz7v3, R.drawable.o3cz8v3, R.drawable.o3cz9v3}};
    /**
     * tablica zawierająca puzzle dla obrazu 3 4x4
     */
    private Integer[][] ArrO3v4 = new Integer[][]  {{R.drawable.o3cz1v4b, R.drawable.o3cz2v4, R.drawable.o3cz3v4,R.drawable.o3cz4v4},
            {R.drawable.o3cz5v4, R.drawable.o3cz6v4, R.drawable.o3cz7v4,R.drawable.o3cz8v4},
            {R.drawable.o3cz9v4, R.drawable.o3cz10v4, R.drawable.o3cz11v4,R.drawable.o3cz12v4},
            {R.drawable.o3cz13v4, R.drawable.o3cz14v4, R.drawable.o3cz15v4,R.drawable.o3cz16v4}};
    /**
     * tablica zawierająca puzzle dla obrazu 3 5x5
     */
    private Integer[][] ArrO3v5 = new Integer[][]  {{R.drawable.o3cz1v5b, R.drawable.o3cz3v5, R.drawable.o3cz3v5,R.drawable.o3cz4v5,R.drawable.o3cz5v5},
            {R.drawable.o3cz6v5, R.drawable.o3cz7v5, R.drawable.o3cz8v5,R.drawable.o3cz9v5,R.drawable.o3cz10v5},
            {R.drawable.o3cz11v5, R.drawable.o3cz12v5, R.drawable.o3cz13v5,R.drawable.o3cz14v5,R.drawable.o3cz15v5},
            {R.drawable.o3cz16v5, R.drawable.o3cz17v5, R.drawable.o3cz18v5,R.drawable.o3cz19v5,R.drawable.o3cz20v5},
            {R.drawable.o3cz21v5, R.drawable.o3cz22v5, R.drawable.o3cz23v5,R.drawable.o3cz24v5,R.drawable.o3cz25v5}};
    /**
     * tablica zawierająca puzzle dla obrazu 4 3x3
     */
    private Integer[][] ArrO4v3 = new Integer[][]  {{R.drawable.o4cz1v3b, R.drawable.o4cz2v3, R.drawable.o4cz3v3},
            {R.drawable.o4cz4v3, R.drawable.o4cz5v3, R.drawable.o4cz6v3},
            {R.drawable.o4cz7v3, R.drawable.o4cz8v3, R.drawable.o4cz9v3}};
    /**
     * tablica zawierająca puzzle dla obrazu 4 4x4
     */
    private Integer[][] ArrO4v4 = new Integer[][]  {{R.drawable.o4cz1v4b, R.drawable.o4cz2v4, R.drawable.o4cz3v4,R.drawable.o4cz4v4},
            {R.drawable.o4cz5v4, R.drawable.o4cz6v4, R.drawable.o4cz7v4,R.drawable.o4cz8v4},
            {R.drawable.o4cz9v4, R.drawable.o4cz10v4, R.drawable.o4cz11v4,R.drawable.o4cz12v4},
            {R.drawable.o4cz13v4, R.drawable.o4cz14v4, R.drawable.o4cz15v4,R.drawable.o4cz16v4}};
    /**
     * tablica zawierająca puzzle dla obrazu 4 5x5
     */
    private Integer[][] ArrO4v5 = new Integer[][]  {{R.drawable.o4cz1v5b, R.drawable.o4cz3v5, R.drawable.o4cz3v5,R.drawable.o4cz4v5,R.drawable.o4cz5v5},
            {R.drawable.o4cz6v5, R.drawable.o4cz7v5, R.drawable.o4cz8v5,R.drawable.o4cz9v5,R.drawable.o4cz10v5},
            {R.drawable.o4cz11v5, R.drawable.o4cz12v5, R.drawable.o4cz13v5,R.drawable.o4cz14v5,R.drawable.o4cz15v5},
            {R.drawable.o4cz16v5, R.drawable.o4cz17v5, R.drawable.o4cz18v5,R.drawable.o4cz19v5,R.drawable.o4cz20v5},
            {R.drawable.o4cz21v5, R.drawable.o4cz22v5, R.drawable.o4cz23v5,R.drawable.o4cz24v5,R.drawable.o4cz25v5}};

    private Integer[][] img = ArrO1v3 ;
    /**
     * pusty puzzel
     */
      int ix ;
    /**
     * rozmiarz planszy
     */
     int poziom = 5;
    /**
     * owybrany braz
     */
    int obraz ;
    /**
     * nazwa gracza
     */
    String  nazwa;

    int seconds,minutes;



    ImageView[][] image= new ImageView[poziom][poziom];
    /**
     *  tu bedzie wyświelany czas
     */
    TextView timerTextView;
    long startTime = 0;


    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
            long millis = System.currentTimeMillis() - startTime;
             seconds = (int) (millis / 1000);
             minutes = seconds / 60;
            seconds = seconds % 60;
            timerTextView =(TextView)findViewById(R.id.tv1);
            timerTextView.setText(String.format("%d:%02d", minutes, seconds));

            timerHandler.postDelayed(this, 500);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
             poziom = getIntent().getExtras().getInt("POZ");
        obraz = getIntent().getExtras().getInt("OBR");
        nazwa = getIntent().getExtras().getString("NAZ");
       Log.d("+=", String.valueOf(poziom));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plansza);

        startTime = System.currentTimeMillis();
        timerHandler.postDelayed(timerRunnable, 0);
        TworzeniePlanszy();
        mDatabaseHelper = new DatabaseHelper(this);
    }

    /**
     * funkcja tworząca planszę
     */
    private void TworzeniePlanszy() {
        if(obraz==1){
            obr_caly = R.drawable.o1c;
           if(poziom == 3) {
               ix = ArrO1v3[0][0];
               img = ArrO1v3;
               Ar1=new Integer[][]  {{R.drawable.o1cz1v3, R.drawable.o1cz2v3, R.drawable.o1cz3v3},
                       {R.drawable.o1cz4v3, R.drawable.o1cz5v3, R.drawable.o1cz6v3},
                       {R.drawable.o1cz7v3, R.drawable.o1cz8v3, R.drawable.o1cz9v3}};
               p00 = R.drawable.o1cz1;
           }
           else if ( poziom == 4) {
             img = ArrO1v4;
             ix = ArrO1v4[0][0];
             Ar1=new Integer[][]  {{R.drawable.o1cz1v4b, R.drawable.o1cz2v4, R.drawable.o1cz3v4,R.drawable.o1cz4v4},
                     {R.drawable.o1cz5v4, R.drawable.o1cz6v4, R.drawable.o1cz7v4,R.drawable.o1cz8v4},
                     {R.drawable.o1cz9v4, R.drawable.o1cz10v4, R.drawable.o1cz11v4,R.drawable.o1cz12v4},
                     {R.drawable.o1cz13v4, R.drawable.o1cz14v4, R.drawable.o1cz15v4,R.drawable.o1cz16v4}};;
             p00 = R.drawable.o1cz1v4;
          }
           else if ( poziom == 5) {
               img = ArrO1v5;
               ix = ArrO1v5[0][0];
               Ar1= new Integer[][]  {{R.drawable.o1cz1v5b, R.drawable.o1cz2v5, R.drawable.o1cz3v5,R.drawable.o1cz4v5,R.drawable.o1cz5v5},
                       {R.drawable.o1cz6v5, R.drawable.o1cz7v5, R.drawable.o1cz8v5,R.drawable.o1cz9v5,R.drawable.o1cz10v5},
                       {R.drawable.o1cz11v5, R.drawable.o1cz12v5, R.drawable.o1cz13v5,R.drawable.o1cz14v5,R.drawable.o1cz15v5},
                       {R.drawable.o1cz16v5, R.drawable.o1cz17v5, R.drawable.o1cz18v5,R.drawable.o1cz19v5,R.drawable.o1cz20v5},
                       {R.drawable.o1cz21v5, R.drawable.o1cz22v5, R.drawable.o1cz23v5,R.drawable.o1cz24v5,R.drawable.o1cz25v5}};;
               p00 = R.drawable.o1cz1v5;
           }
        }
       else if(obraz==2){
            obr_caly = R.drawable.o2c;
            if(poziom == 3) {
                ix = ArrO2v3[0][0];
                img = ArrO2v3;
                Ar1=new Integer[][]  {{R.drawable.o2cz1v3b, R.drawable.o2cz2v3, R.drawable.o2cz3v3},
                        {R.drawable.o2cz4v3, R.drawable.o2cz5v3, R.drawable.o2cz6v3},
                        {R.drawable.o2cz7v3, R.drawable.o2cz8v3, R.drawable.o2cz9v3}};
                p00 = R.drawable.o2cz1v3;
            }
            else if ( poziom == 4) {
                img = ArrO2v4;
                ix = ArrO2v4[0][0];
                Ar1=  new Integer[][]  {{R.drawable.o2cz1v4b, R.drawable.o2cz2v4, R.drawable.o2cz3v4,R.drawable.o2cz4v4},
                        {R.drawable.o2cz5v4, R.drawable.o2cz6v4, R.drawable.o2cz7v4,R.drawable.o2cz8v4},
                        {R.drawable.o2cz9v4, R.drawable.o2cz10v4, R.drawable.o2cz11v4,R.drawable.o2cz12v4},
                        {R.drawable.o2cz13v4, R.drawable.o2cz14v4, R.drawable.o2cz15v4,R.drawable.o2cz16v4}};;
                p00 = R.drawable.o2cz1v4;
            }
            else if ( poziom == 5) {
                img = ArrO2v5;
                ix = ArrO2v5[0][0];
                Ar1=new Integer[][]  {{R.drawable.o2cz1v5b, R.drawable.o2cz2v5, R.drawable.o2cz3v5,R.drawable.o2cz4v5,R.drawable.o2cz5v5},
                        {R.drawable.o2cz6v5, R.drawable.o2cz7v5, R.drawable.o2cz8v5,R.drawable.o2cz9v5,R.drawable.o2cz10v5},
                        {R.drawable.o2cz11v5, R.drawable.o2cz12v5, R.drawable.o2cz13v5,R.drawable.o2cz14v5,R.drawable.o2cz15v5},
                        {R.drawable.o2cz16v5, R.drawable.o2cz17v5, R.drawable.o2cz18v5,R.drawable.o2cz19v5,R.drawable.o2cz20v5},
                        {R.drawable.o2cz21v5, R.drawable.o2cz22v5, R.drawable.o2cz23v5,R.drawable.o2cz24v5,R.drawable.o2cz25v5}};;
                p00 = R.drawable.o2cz1v5;
            }
        }
        else if(obraz==3){
            obr_caly = R.drawable.o3c;
            if(poziom == 3) {
                ix = ArrO3v3[0][0];
                img = ArrO3v3;
                Ar1=new Integer[][]  {{R.drawable.o3cz1v3b, R.drawable.o3cz2v3, R.drawable.o3cz3v3},
                        {R.drawable.o3cz4v3, R.drawable.o3cz5v3, R.drawable.o3cz6v3},
                        {R.drawable.o3cz7v3, R.drawable.o3cz8v3, R.drawable.o3cz9v3}};;
                p00 = R.drawable.o3cz1v3;
            }
            else if ( poziom == 4) {
                img = ArrO3v4;
                ix = ArrO3v4[0][0];
                Ar1= new Integer[][]  {{R.drawable.o3cz1v4b, R.drawable.o3cz2v4, R.drawable.o3cz3v4,R.drawable.o3cz4v4},
                        {R.drawable.o3cz5v4, R.drawable.o3cz6v4, R.drawable.o3cz7v4,R.drawable.o3cz8v4},
                        {R.drawable.o3cz9v4, R.drawable.o3cz10v4, R.drawable.o3cz11v4,R.drawable.o3cz12v4},
                        {R.drawable.o3cz13v4, R.drawable.o3cz14v4, R.drawable.o3cz15v4,R.drawable.o3cz16v4}};;
                p00 = R.drawable.o3cz1v4;
            }
            else if ( poziom == 5) {
                img = ArrO3v5;
                ix = ArrO3v5[0][0];
                Ar1=new Integer[][]  {{R.drawable.o3cz1v5b, R.drawable.o3cz3v5, R.drawable.o3cz3v5,R.drawable.o3cz4v5,R.drawable.o3cz5v5},
                        {R.drawable.o3cz6v5, R.drawable.o3cz7v5, R.drawable.o3cz8v5,R.drawable.o3cz9v5,R.drawable.o3cz10v5},
                        {R.drawable.o3cz11v5, R.drawable.o3cz12v5, R.drawable.o3cz13v5,R.drawable.o3cz14v5,R.drawable.o3cz15v5},
                        {R.drawable.o3cz16v5, R.drawable.o3cz17v5, R.drawable.o3cz18v5,R.drawable.o3cz19v5,R.drawable.o3cz20v5},
                        {R.drawable.o3cz21v5, R.drawable.o3cz22v5, R.drawable.o3cz23v5,R.drawable.o3cz24v5,R.drawable.o3cz25v5}};;
                p00 = R.drawable.o3cz1v5;
            }
        }
        else if(obraz==4){
            obr_caly = R.drawable.o4c;
            if(poziom == 3) {
                ix = ArrO4v3[0][0];
                img = ArrO4v3;
                Ar1=new Integer[][]  {{R.drawable.o4cz1v3b, R.drawable.o4cz2v3, R.drawable.o4cz3v3},
                        {R.drawable.o4cz4v3, R.drawable.o4cz5v3, R.drawable.o4cz6v3},
                        {R.drawable.o4cz7v3, R.drawable.o4cz8v3, R.drawable.o4cz9v3}};;
                p00 = R.drawable.o4cz1v3;
            }
            else if ( poziom == 4) {
                img = ArrO4v4;
                ix = ArrO4v4[0][0];
                Ar1= new Integer[][]  {{R.drawable.o4cz1v4b, R.drawable.o4cz2v4, R.drawable.o4cz3v4,R.drawable.o4cz4v4},
                        {R.drawable.o4cz5v4, R.drawable.o4cz6v4, R.drawable.o4cz7v4,R.drawable.o4cz8v4},
                        {R.drawable.o4cz9v4, R.drawable.o4cz10v4, R.drawable.o4cz11v4,R.drawable.o4cz12v4},
                        {R.drawable.o4cz13v4, R.drawable.o4cz14v4, R.drawable.o4cz15v4,R.drawable.o4cz16v4}};;
                p00 = R.drawable.o4cz1v4;
            }
            else if ( poziom == 5) {
                img = ArrO4v5;
                ix = ArrO4v5[0][0];
                Ar1=new Integer[][]  {{R.drawable.o4cz1v5b, R.drawable.o4cz3v5, R.drawable.o4cz3v5,R.drawable.o4cz4v5,R.drawable.o4cz5v5},
                        {R.drawable.o4cz6v5, R.drawable.o4cz7v5, R.drawable.o4cz8v5,R.drawable.o4cz9v5,R.drawable.o4cz10v5},
                        {R.drawable.o4cz11v5, R.drawable.o4cz12v5, R.drawable.o4cz13v5,R.drawable.o4cz14v5,R.drawable.o4cz15v5},
                        {R.drawable.o4cz16v5, R.drawable.o4cz17v5, R.drawable.o4cz18v5,R.drawable.o4cz19v5,R.drawable.o4cz20v5},
                        {R.drawable.o4cz21v5, R.drawable.o4cz22v5, R.drawable.o4cz23v5,R.drawable.o4cz24v5,R.drawable.o4cz25v5}};
                p00 = R.drawable.o4cz1v5;
            }
        }



        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels/poziom;
        shuffle(img) ;

        int p = 1;
        for (int x= 0; x < poziom; x++)
            for (int y = 0; y < poziom; y++) {
                //   ArrO1v3[x][y] = img[x][y];
                RelativeLayout layout = (RelativeLayout)findViewById(R.id.layout);

                image[x][y] = new ImageView(this);
                image[x][y].setLayoutParams(new android.view.ViewGroup.LayoutParams(width,width));
                image[x][y].setX(width*y);
                image[x][y].setY(width*x);
                image[x][y].setImageResource(img[x][y]);
                layout.addView(image[x][y]);

                final int finalX = x;
                final int finalY = y;
                image[x][y].setOnClickListener(new View.OnClickListener() {


                    /**
                     *Funkcja obsługująca kliknięcia
                     * @param v
                     *      kliknięty obiekt na ekranie
                     */
                    @Override
                    public void onClick(View v) {
                        if (koniec == 0){
                            Log.d("---", img[finalX][finalY] + " " + ix);
                        if (finalX - 1 > -1)
                            if (img[finalX - 1][finalY].equals(ix)) {
                                Log.d("1", String.valueOf(image[finalX][finalY].getDrawable()));

                                image[finalX - 1][finalY].setImageResource(img[finalX][finalY]);
                                image[finalX][finalY].setImageResource(img[finalX - 1][finalY]);
                                img[finalX - 1][finalY] = img[finalX][finalY];
                                img[finalX][finalY] = ix;
                                sprawdzanie();
                            }
                        if (finalY - 1 > -1)
                            if (img[finalX][finalY - 1].equals(ix)) {
                                Log.d("2", String.valueOf(image[finalX][finalY].getDrawable()));

                                image[finalX][finalY - 1].setImageResource(img[finalX][finalY]);
                                image[finalX][finalY].setImageResource(img[finalX][finalY - 1]);
                                img[finalX][finalY - 1] = img[finalX][finalY];
                                img[finalX][finalY] = ix;
                                sprawdzanie();
                            }
                        if (finalX + 1 < poziom)
                            if (img[finalX + 1][finalY].equals(ix)) {
                                Log.d("3", String.valueOf(image[finalX][finalY].getDrawable()));

                                image[finalX + 1][finalY].setImageResource(img[finalX][finalY]);
                                image[finalX][finalY].setImageResource(img[finalX + 1][finalY]);
                                img[finalX + 1][finalY] = img[finalX][finalY];
                                img[finalX][finalY] = ix;
                                sprawdzanie();
                            }
                        if (finalY + 1 < poziom)
                            if (img[finalX][finalY + 1].equals(ix)) {
                                Log.d("3", String.valueOf(image[finalX][finalY].getDrawable()));

                                image[finalX][finalY + 1].setImageResource(img[finalX][finalY]);
                                image[finalX][finalY].setImageResource(img[finalX][finalY + 1]);
                                img[finalX][finalY + 1] = img[finalX][finalY];
                                img[finalX][finalY] = ix;
                                sprawdzanie();
                            }
                    }
                }
                });

                p++;
            }



    }

    /**
     * funkcja sprawdzająca czy skończono grę
     */
    void sprawdzanie(){
        /**
         * zmienna zawierająca ile puzzli jest na dobrej pozycji
         */
        int pom=0;
       TextView tvwin;
        tvwin = (TextView)findViewById(R.id.tvWin);
       // Log.d("win", Ar1[0][0]+"wygrana"+img[1][1]);
        for (int x= 0; x < poziom; x++)
            for (int y = 0; y < poziom; y++) {
                Log.d("def", Ar1[x][y]+"--"+img[x][y]);
            if(Ar1[x][y].toString().equals(img[x][y].toString())){
                pom++;
                }
            else {
             // break;
            }

            if(pom == poziom*poziom){//poziom*poziom
                Log.d("win", "wygrana"+poziom*poziom);
                image[0][0].setImageResource(p00);
               // tvwin.setTextColor(Color.RED);
                koniec=1;
                timerHandler.removeCallbacks(timerRunnable);
                seconds = minutes*60 + seconds;
                ImageView ivp = (ImageView) findViewById(R.id.ivPokaz2);
                ivp.setVisibility(View.VISIBLE);
               AddData(nazwa,seconds,poziom);

            }
            }
   }
    /**
     * funkcja mieszająca tablice zawierającą puzzle
     */
    void shuffle(Integer[][] a) {
        Random random = new Random();

        for (int i = a.length - 1; i > 0; i--) {
            for (int j = a[i].length - 1; j > 0; j--) {
                int m = random.nextInt(i + 1);
                int n = random.nextInt(j + 1);

                int temp = a[i][j];
                a[i][j] = a[m][n];
                a[m][n] = temp;
            }
        }
    }
    /**
     *Funkcja obsługująca kliknięcia
     * @param view
     *      kliknięty obiekt na ekranie
     */
    public void onClick(View view)
    {
        ImageView ivc = (ImageView) findViewById(R.id.ivCalosc);
        switch (view.getId()) {
            case R.id.ivBack:
                Intent intent = new Intent(PlanszaActivity.this, MainActivity.class);
                startActivity(intent);

                break;
            case R.id.ivPokaz:
                if(show==0) {
                    ivc.setImageResource(obr_caly);
                    show=1;
                }
                else if (show==1) {
                    ivc.setImageResource(0);
                    show = 0;
                }
                break;
            case R.id.ivPokaz2:
                Intent intent2 = new Intent(PlanszaActivity.this, ListDataActivity.class);
                intent2.putExtra("POZ", poziom);
                startActivity(intent2);

                break;
                }
    }

    /**
     * funkcja dodająca wynik do bazy danych
     * @param nazwa
     *      nazwa gracza
     * @param czas
     *      czas gry
     * @param plansza
     *      plansza na jakiej grano
     */
    public void AddData(String nazwa,double czas,int plansza) {
        boolean insertData = mDatabaseHelper.addData(nazwa,czas,plansza);

        if (insertData) {
            Log.d("bd", "dodano dane");
        } else {
            Log.d("bd", "nie dodano danych");
        }

    }


}

