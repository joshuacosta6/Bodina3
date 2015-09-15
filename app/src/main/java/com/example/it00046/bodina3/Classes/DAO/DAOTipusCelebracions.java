package com.example.it00046.bodina3.Classes.DAO;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.it00046.bodina3.Classes.Custom.LVWLlistaTipusCelebracions;
import com.example.it00046.bodina3.Classes.Entitats.TipusCelebracio;
import com.example.it00046.bodina3.Classes.Globals;
import com.example.it00046.bodina3.R;
import com.loopj.android.http.RequestParams;

/**
 * Created by it00046 on 05/08/2015.
 */
public class DAOTipusCelebracions {
    private static final String TAG_TipusCelebracio = Globals.g_Native.getString(R.string.TTipusCelebracio);
    private static final String TAG_Codi = Globals.g_Native.getString(R.string.TTipusCelebracio_Codi);
    private static final String TAG_Descripcio = Globals.g_Native.getString(R.string.TTipusCelebracio_Descripcio);

    ///////////////////////////////////////////////////////////////////////////////////////////////
    // O P E R A T I V A   P U B L I C A
    ///////////////////////////////////////////////////////////////////////////////////////////////
    // Llegim els tipus de celebracio del client
    public static void Llegir(final ListView p_LVW_TipusCelebracions, int p_Layout, final Context p_Context) {
        final ArrayAdapter<TipusCelebracio> l_Llista = new LVWLlistaTipusCelebracions(p_Context, p_Layout);
        Globals.MostrarEspera(p_Context);
        try {
            Cursor l_cursor = Globals.g_DB.query(TAG_TipusCelebracio,
                    Globals.g_Native.getResources().getStringArray(R.array.TTipusCelebracio_Camps),
                    null, // c. selections
                    null, // d. selections args
                    null, // e. group by
                    null, // f. having
                    null, // g. order by
                    null); // h. limit
            if (l_cursor.getCount() > 0) {
                l_cursor.moveToFirst();
                for (int i=0; i < l_cursor.getCount(); i++) {
                    TipusCelebracio l_Tipus = CursorToTipusCelebracio(l_cursor);
                    l_Llista.add(l_Tipus);
                    l_cursor.moveToNext();
                }
                p_LVW_TipusCelebracions.setAdapter(l_Llista);
            }
            Globals.TancarEspera();
        }
        catch(Exception e) {
            Globals.F_Alert(Globals.g_Native.getString(R.string.errorservidor_ProgramError),
                    Globals.g_Native.getString(R.string.error_greu), p_Context);
            Globals.TancarEspera();
        }
    }
    // Afegim un tipus de celebracio
    public static void Afegir(TipusCelebracio p_TipusCelebracio, final Context p_Context, boolean p_Asistit, boolean p_Tancam){
        if (p_Asistit) {
            Globals.MostrarEspera(p_Context);
        }
        try {
            // Calculem el codi del tipus de celebracio

            //
            Globals.g_DB.insert(TAG_TipusCelebracio,
                    null,
                    TipusCelebracioToContentValues(p_TipusCelebracio));
        }
        catch(Exception e) {
            if (p_Asistit) {
                Globals.F_Alert(Globals.g_Native.getString(R.string.errorservidor_ProgramError),
                        Globals.g_Native.getString(R.string.error_greu), p_Context);
                Globals.TancarEspera();
            }
        }
        finally{
            if (p_Asistit) {
                Globals.TancarEspera();
                // Informem de la operativa feta
                Toast.makeText(p_Context,
                        Globals.g_Native.getString(R.string.op_afegir_ok),
                        Toast.LENGTH_LONG).show();
                // Tanquem a qui ens ha cridat
                if (p_Tancam) {
                    Activity l_activity = (Activity) p_Context;
                    l_activity.finish();
                }
            }
        }
    }
    // Modifiquem un tipus de celebracio
    public static void Modificar(TipusCelebracio p_TipusCelebracio, final Context p_Context, boolean p_Tancam){
        Globals.MostrarEspera(p_Context);
        try {
            Globals.g_DB.update(TAG_TipusCelebracio,
                    TipusCelebracioToContentValues(p_TipusCelebracio),
                    TAG_Codi + "= " + p_TipusCelebracio.Codi,
                    null);
        }
        catch(Exception e) {
            Globals.F_Alert(Globals.g_Native.getString(R.string.errorservidor_ProgramError),
                    Globals.g_Native.getString(R.string.error_greu), p_Context);
            Globals.TancarEspera();
        }
        finally{
            Globals.TancarEspera();
            // Informem de la operativa feta
            Toast.makeText(p_Context,
                    Globals.g_Native.getString(R.string.op_modificacio_ok),
                    Toast.LENGTH_LONG).show();
            // Tanquem a qui ens ha cridat
            if (p_Tancam) {
                Activity l_activity = (Activity) p_Context;
                l_activity.finish();
            }
        }
    }
    // Esborrem un tipus de celebracio
    public static boolean Esborrar(int p_Codi, final Context p_Context, boolean p_Tancam){
        boolean l_Resultat = true;

        Globals.MostrarEspera(p_Context);
        try {
            Globals.g_DB.delete(TAG_TipusCelebracio,
                    TAG_Codi + "= " + p_Codi,
                    null);
        }
        catch(Exception e) {
            Globals.F_Alert(Globals.g_Native.getString(R.string.errorservidor_ProgramError),
                    Globals.g_Native.getString(R.string.error_greu), p_Context);
            Globals.TancarEspera();
            l_Resultat = false;
        }
        finally{
            Globals.TancarEspera();
            // Informem de la operativa feta
            Toast.makeText(p_Context,
                    Globals.g_Native.getString(R.string.op_esborrar_ok),
                    Toast.LENGTH_LONG).show();
            // Tanquem a qui ens ha cridat
            if (p_Tancam) {
                Activity l_activity = (Activity) p_Context;
                l_activity.finish();
            }
        }
        return l_Resultat;
    }
    // Definim els tipus inicials de celebracio
    public static void ValorsInicials() {
        TipusCelebracio l_TipusCelebracio;

        l_TipusCelebracio = new TipusCelebracio();
        l_TipusCelebracio.Codi = 0;
        l_TipusCelebracio.Descripcio = Globals.g_Native.getString(R.string.TipusCelebracio0);
        Afegir(l_TipusCelebracio, Globals.g_Native, false, false);
        l_TipusCelebracio.Codi = 1;
        l_TipusCelebracio.Descripcio = Globals.g_Native.getString(R.string.TipusCelebracio1);
        Afegir(l_TipusCelebracio, Globals.g_Native, false, false);
        l_TipusCelebracio.Codi = 2;
        l_TipusCelebracio.Descripcio = Globals.g_Native.getString(R.string.TipusCelebracio2);
        Afegir(l_TipusCelebracio, Globals.g_Native, false, false);
        l_TipusCelebracio.Codi = 3;
        l_TipusCelebracio.Descripcio = Globals.g_Native.getString(R.string.TipusCelebracio3);
        Afegir(l_TipusCelebracio, Globals.g_Native, false, false);
        l_TipusCelebracio.Codi = 4;
        l_TipusCelebracio.Descripcio = Globals.g_Native.getString(R.string.TipusCelebracio4);
        Afegir(l_TipusCelebracio, Globals.g_Native, false, false);
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////
    // Funcions privades
    ///////////////////////////////////////////////////////////////////////////////////////////////
    // Posa les dades del client a contentValue
    private static ContentValues TipusCelebracioToContentValues(TipusCelebracio p_TipusCelebracio) {
        ContentValues l_values = new ContentValues();

        l_values.put(TAG_Codi, p_TipusCelebracio.Codi);
        l_values.put(TAG_Descripcio, p_TipusCelebracio.Descripcio);
        return l_values;
    }
    // Pasa les dades del cursor a TipusCelebracio
    private static TipusCelebracio CursorToTipusCelebracio(Cursor p_cursor){
        TipusCelebracio l_TipusCelebracio = new TipusCelebracio();

        l_TipusCelebracio.Codi = p_cursor.getInt(p_cursor.getColumnIndex(TAG_Codi));
        l_TipusCelebracio.Descripcio = p_cursor.getString(p_cursor.getColumnIndex(TAG_Descripcio));
        return l_TipusCelebracio;
    }
}
