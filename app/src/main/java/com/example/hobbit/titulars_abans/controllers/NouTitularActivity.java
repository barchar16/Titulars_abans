package com.example.hobbit.titulars_abans.controllers;

        import android.app.Activity;
        import android.database.sqlite.SQLiteDatabase;
        import android.os.Bundle;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

        import com.example.hobbit.titulars_abans.R;
        import com.example.hobbit.titulars_abans.model.business.Titular;
        import com.example.hobbit.titulars_abans.model.persistence.TitularsConversor;
        import com.example.hobbit.titulars_abans.model.persistence.TitularsSQLiteHelper;

/*
 * Activitat que permet introduir un nou titular a la BD
 */
public class NouTitularActivity extends Activity {
    private EditText txtTitol;
    private EditText txtSubtitol;
    private Button btnOk;
    private Button btnCancel;

    /**
     * Mètode que s'executa en crear l'activitat
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nou_titular);

        txtTitol = (EditText) findViewById(R.id.txtTitol);
        txtSubtitol = (EditText) findViewById(R.id.txtSubtitol);
        btnOk = (Button) findViewById(R.id.btnOk);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        btnOk.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                desar();
            }
        });

        btnCancel.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }

    /**
     * Mètode que desa les dades introduïdes per l'usuari
     */
    private void desar() {
        try {
            TitularsSQLiteHelper titHelper = new  TitularsSQLiteHelper(this, "Titulars.db", null, 2);
            TitularsConversor titularsConv = new TitularsConversor(titHelper);
            // obtenir l'objecte BD
            SQLiteDatabase db = titHelper.getWritableDatabase();
            // Si s'ha obert correctament la BD
            if(db != null) {
                // esborrar tots els registres de la taula
                titularsConv.save(new Titular(0,txtTitol.getText().toString(), txtSubtitol.getText().toString()));
                Toast.makeText(this, "A pasat per aqui", Toast.LENGTH_LONG).show();
                setResult(RESULT_OK);
            }
        }
        catch(Exception e) {
            setResult(RESULT_CANCELED);
        }
        finally {
            finish();
        }
    }
}