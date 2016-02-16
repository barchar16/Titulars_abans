package com.example.hobbit.titulars_abans.controllers;

import android.app.Activity;
        import android.os.Bundle;
import android.content.Intent;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
import android.widget.ListView;
        import android.widget.TextView;
        import android.widget.Toast;

import com.example.hobbit.titulars_abans.R;
import com.example.hobbit.titulars_abans.model.persistence.TitularsConversor;
import com.example.hobbit.titulars_abans.model.persistence.TitularsSQLiteHelper;


/**
 * Activitat que mostra un ListView de titulars a partir de les
 * dades d'una base de dades SQLite
 *
 * @author Marc Nicolau Reixach (marc.nicolau@gmail.com)
 *
 */

public class MainActivity extends Activity {

    final static int ADD_TITULAR = 1;

    private Cursor titulars;
    private TitularsAdapter adapter;
    private TitularsSQLiteHelper titHelper;
    private TitularsConversor titularsConv;
    private ListView llista;
    private TextView lblNoData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // recuperar els controls
        lblNoData = (TextView) findViewById(R.id.lblNoData);
        llista = (ListView)findViewById(R.id.lstTitulars);

        // vincular el menú contextual a la llista
        registerForContextMenu(llista);

        // crear l'objecte que crea la connexió amb la BD
        titHelper = new  TitularsSQLiteHelper(this, "Titulars", null, 2);
        // obtenir l'objecte BD
        SQLiteDatabase db = titHelper.getWritableDatabase();
        titularsConv = new TitularsConversor(titHelper);

        // Si s'ha obert correctament la BD
        if(db != null) {
            Toast.makeText(this, "refresh", Toast.LENGTH_SHORT).show();
            // actualitzar la llista
            refreshData();
            // Tancar la base de dades
            db.close();
        }
    }

    /*
     * Torna a executar la consulta i a enllaçar les dades
     */
    void refreshData() {
        titulars = titularsConv.getAll();
        adapter = new TitularsAdapter(this, titulars);
        llista.setAdapter(adapter);

        if(titulars.getCount() == 0) {
            Toast.makeText(this, "no", Toast.LENGTH_SHORT).show();
            lblNoData.setVisibility(lblNoData.VISIBLE);
            llista.setVisibility(llista.INVISIBLE);
        }
        else {
            Toast.makeText(this, "si", Toast.LENGTH_SHORT).show();
            lblNoData.setVisibility(lblNoData.INVISIBLE);
            llista.setVisibility(llista.VISIBLE);
        }
    }

    /**
     * Crea el menú d'opcions de l'aplicació
     */
  /*  public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }*/

    /**
     * Respon a l'event d'haver escollit una opció del menú de l'app
     */
   /* public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
                case R.id.mnuAdd:
                        Intent i = new Intent(this, NouTitularActivity.class);
                        startActivityForResult(i, ADD_TITULAR);
                        return true;
            case R.id.mnuSortir :
                this.finish();
                return true;
        }
        return false;
    }*/

    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        // si s'ha tancat l'activitat ADD_TITULAR i ha anat bé
        if( requestCode == ADD_TITULAR && resultCode == RESULT_OK) {
            refreshData();
        }
    }

    /**
     * Crea el menú contextual
     */
 /*   public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contextual, menu);
    }*/

    /**
     * Respon a l'event d'haver escollit una opció del menú contextual
     */
 /*   public boolean onContextItemSelected(MenuItem item) {
        AdapterContextMenuInfo info = (AdapterContextMenuInfo)item.getMenuInfo();

        switch (item.getItemId()) {
                case R.id.mnuVeureDades:
                        // mostrar les dades de l'element escollit
                        Toast.makeText(this, adapter.getItem(info.position).getTitol(), Toast.LENGTH_LONG).show();
                        return true;
                case R.id.mnuEsborrar:
                        // esborrar l'element escollit
                        titularsConv.remove(adapter.getItem(info.position));
                        // actualitzar la llista
                        refreshData();
                        // mostrar missatge
                        Toast.makeText(this, "S'ha esborrat el titular!", Toast.LENGTH_LONG).show();
                        return true;
                default: break;
        }
        return false;
    }*/
}
