package com.example.hobbit.titulars_abans.model.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Classe que est�n les funcionalitats de SQLiteOpenHelper
 * 
 * @author Marc Nicolau (marc.nicolau@gmail.com)
 *
 */
public class TitularsSQLiteHelper extends SQLiteOpenHelper {
        // Sent�ncia SQL per crear la taula de Titulars
        private final String SQL_CREATE_TITULARS = "CREATE TABLE Titulars(" +
                                                        "       codi INTEGER PRIMARY KEY, " +
                                                        "       titol TEXT, " +
                                                        "       subtitol TEXT)";

        /**
         * Constructor amb par�metres
         * @param context el context de l'aplicaci�
         * @param nom el nom de la base de dades a crear
         * @param factory s'utilitza per crear objectes cursor, o null per defecte
         * @param versio n�mero de versi� de la BD. Si �s m�s gran que la versi� actual, es far� un 
         *                               Upgrade; si �s menor es far� un Downgrade
         */
        public TitularsSQLiteHelper(Context context, String nom, CursorFactory factory, int versio) {
                super(context, nom, factory, versio);
        }

        /**
         * Event que es produeix quan s'ha de crear la BD
         */
        @Override
        public void onCreate(SQLiteDatabase db) {
                // S'executen les sent�ncies SQL de creaci� de la BD    
                db.execSQL(SQL_CREATE_TITULARS);
                db.execSQL("insert into Titulars(codi,titol,subtitol) values (1,'titulo','subtitulo')");
        }

        /**
         * Event que es produeix quan s'ha d'actualitzar la BD a una versi� superior
         */
        @Override
        public void onUpgrade(SQLiteDatabase db, int versioAnterior, int versioNova) {
                // NOTA: Per simplificar l'exemple, aqu� s'utilitza directament
                // l'opci� d'eliminar la taula anterior i tornar-la a crear buida
                // amb la nova estructura.
                // No obstant, el m�s habitual �s migrar les dades de la taula antiga
                // a la nova, per la qual cosa aquest m�tode hauria de fer m�s coses.

                // S'elimina la versi� anterior de la taula
                db.execSQL("DROP TABLE IF EXISTS Titulars");
                // Es crea la nova versi� de la taula
                db.execSQL(SQL_CREATE_TITULARS);
        }
}