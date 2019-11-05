package com.example.projekt;


import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Obsługa menu
 */
public class MainActivity extends AppCompatActivity {
    DatabaseHelper mDatabaseHelper;
    int poziom= 3;
    int obraz= 1;
    String nazwa;
    private EditText tnazwa;
    private Button bt3x3,bt4x4,bt5x5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDatabaseHelper = new DatabaseHelper(this);
        tnazwa = (EditText)findViewById(R.id.etNazwa);
    }

    /**
     *Funkcja obsługująca kliknięcia
     * @param view
     *      kliknięty obiekt na ekranie
     */
    public void onClick(View view)
    {
        final float scale = getResources().getDisplayMetrics().density;
        int width1  = (int) (132 * scale);
        int width2 = (int) (125 * scale);
        int margin = (int) (8* scale);
        /**
         * Przycisk do wyboru planszy 3 na 3
         */
        bt3x3 = (Button)findViewById(R.id.bt3v3);
        /**
         * Przycisk do wyboru planszy 4 na 4
         */
        bt4x4 = (Button)findViewById(R.id.bt4v4);
        /**
         * Przycisk do wyboru planszy 5 na 5
         */
        bt5x5 = (Button)findViewById(R.id.bt5v5);
        /**
         * Piewszy obraz
         */
        ImageView iv1 = (ImageView) findViewById(R.id.ivO1);
        /**
         * Drugi obraz
         */
        ImageView iv2 = (ImageView) findViewById(R.id.ivO2);
        /**
         * Trzeci obraz
         */
        ImageView iv3 = (ImageView) findViewById(R.id.ivO3);
        /**
         * Czwarty obraz
         */
        ImageView iv4 = (ImageView) findViewById(R.id.ivO4);
        /**
         * wielkość wybranego obrazka
         */
        LinearLayout.LayoutParams parm1 = new LinearLayout.LayoutParams(width1,width1);
        /**
         * wielkość niewybranych obrazków
         */
        LinearLayout.LayoutParams parm2 = new LinearLayout.LayoutParams(width2,width2);
        parm1.setMargins(margin, 0, margin, 0);
        parm2.setMargins(margin, 0, margin, 0);



        switch (view.getId()) {
            case R.id.bt3v3:
                poziom =3;
                bt3x3.setTextSize(15);
                bt3x3.setTypeface(null, Typeface.BOLD);
                bt4x4.setTextSize(14);
                bt4x4.setTypeface(null, Typeface.NORMAL);
                bt5x5.setTextSize(14);
                bt5x5.setTypeface(null, Typeface.NORMAL);
                Log.d("-", 3+ ""  );
                break;
            case R.id.bt4v4:
                poziom =4;
                bt4x4.setTextSize(15);
                bt4x4.setTypeface(null, Typeface.BOLD);
                bt3x3.setTextSize(14);
                bt3x3.setTypeface(null, Typeface.NORMAL);
                bt5x5.setTextSize(14);
                bt5x5.setTypeface(null, Typeface.NORMAL);
                Log.d("-", 4+ ""  );
                break;
            case R.id.bt5v5:
                poziom =5;
                bt5x5.setTextSize(15);
                bt5x5.setTypeface(null, Typeface.BOLD);
                bt4x4.setTextSize(14);
                bt4x4.setTypeface(null, Typeface.NORMAL);
                bt3x3.setTextSize(14);
                bt3x3.setTypeface(null, Typeface.NORMAL);
                Log.d("-", 5+ ""  );
                break;

            case R.id.ivO1:
                Log.d("2", "vvvv");
                obraz =1;
                iv1.setLayoutParams(parm1);
                iv2.setLayoutParams(parm2);
                iv3.setLayoutParams(parm2);
                iv4.setLayoutParams(parm2);
                break;
            case R.id.ivO2:
                obraz =2;
                iv2.setLayoutParams(parm1);
                iv1.setLayoutParams(parm2);
                iv3.setLayoutParams(parm2);
                iv4.setLayoutParams(parm2);
                break;
            case R.id.ivO3:
                obraz =3;
                iv3.setLayoutParams(parm1);
                iv1.setLayoutParams(parm2);
                iv2.setLayoutParams(parm2);
                iv4.setLayoutParams(parm2);
                break;
            case R.id.ivO4:
                obraz =4;
                iv4.setLayoutParams(parm1);
                iv1.setLayoutParams(parm2);
                iv2.setLayoutParams(parm2);
                iv3.setLayoutParams(parm2);
                break;
            case R.id.btGraj:
                Intent intent = new Intent(MainActivity.this, PlanszaActivity.class);
                intent.putExtra("POZ", poziom);
                intent.putExtra("OBR", obraz);
                intent.putExtra("NAZ", tnazwa.getText().toString());
                startActivity(intent);

                break;
            case R.id.ivPokaz3:
                Intent intent2 = new Intent(MainActivity.this, ListDataActivity.class);
                intent2.putExtra("POZ", poziom);
                startActivity(intent2);

                break;

        }
    }
}
