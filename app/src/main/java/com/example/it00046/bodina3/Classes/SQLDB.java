package com.example.it00046.bodina3.Classes;

/**
 * Created by it00046 on 13/02/2015.
 */
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.it00046.bodina3.Classes.DAO.DAOTaulesClient;
import com.example.it00046.bodina3.Classes.DAO.DAOTipusCelebracions;
import com.example.it00046.bodina3.Classes.Entitats.TipusCelebracio;
import com.example.it00046.bodina3.R;

public class SQLDB extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = Globals.g_Native.getString(R.string.DBName);

    public SQLDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String l_Create;

        // Definim les taules de la BBDD local
        // 1- Client
        l_Create = String.format(Globals.g_Native.getString(R.string.TClientCreate),
                Globals.g_Native.getString(R.string.TClient),
                Globals.g_Native.getString(R.string.TClient_Codi),
                Globals.g_Native.getString(R.string.TClient_eMail),
                Globals.g_Native.getString(R.string.TClient_Nom),
                Globals.g_Native.getString(R.string.TClient_Pais),
                Globals.g_Native.getString(R.string.TClient_Contacte),
                Globals.g_Native.getString(R.string.TClient_DataAlta),
                Globals.g_Native.getString(R.string.TClient_Idioma),
                Globals.g_Native.getString(R.string.TClient_Actualitzat));
        db.execSQL(l_Create);
        // 2- SalonsClient
        l_Create = String.format(Globals.g_Native.getString(R.string.TSalonsClientCreate),
                Globals.g_Native.getString(R.string.TSalonsClient),
                Globals.g_Native.getString(R.string.TSalonsClient_Codi),
                Globals.g_Native.getString(R.string.TSalonsClient_Nom),
                Globals.g_Native.getString(R.string.TSalonsClient_Descripcio),
                Globals.g_Native.getString(R.string.TSalonsClient_Capacitat),
                Globals.g_Native.getString(R.string.TSalonsClient_UnitatsPlanol),
                Globals.g_Native.getString(R.string.TSalonsClient_EscalaPlanol),
                Globals.g_Native.getString(R.string.TSalonsClient_UnitatMesura),
                Globals.g_Native.getString(R.string.TSalonsClient_Estat));
        db.execSQL(l_Create);
        // 3- Tipus de celebracio
        l_Create = String.format(Globals.g_Native.getString(R.string.TTipusCelebracioCreate),
                Globals.g_Native.getString(R.string.TTipusCelebracio),
                Globals.g_Native.getString(R.string.TTipusCelebracio_Codi),
                Globals.g_Native.getString(R.string.TTipusCelebracio_Descripcio));
        db.execSQL(l_Create);
        // 4- Planols
        l_Create = String.format(Globals.g_Native.getString(R.string.TPlanolsCreate),
                Globals.g_Native.getString(R.string.TPlanols),
                Globals.g_Native.getString(R.string.TPlanols_CodiSalo),
                Globals.g_Native.getString(R.string.TPlanols_Tipus),
                Globals.g_Native.getString(R.string.TPlanols_OrigenX),
                Globals.g_Native.getString(R.string.TPlanols_OrigenY),
                Globals.g_Native.getString(R.string.TPlanols_DestiX),
                Globals.g_Native.getString(R.string.TPlanols_DestiY),
                Globals.g_Native.getString(R.string.TPlanols_CurvaX),
                Globals.g_Native.getString(R.string.TPlanols_CurvaY),
                Globals.g_Native.getString(R.string.TPlanols_Texte));
        db.execSQL(l_Create);
        // 5- Celebracions Client
        l_Create = String.format(Globals.g_Native.getString(R.string.TCelebracionsClientCreate),
                Globals.g_Native.getString(R.string.TCelebracionsClient),
                Globals.g_Native.getString(R.string.TCelebracionsClient_Codi),
                Globals.g_Native.getString(R.string.TCelebracionsClient_CodiSalo),
                Globals.g_Native.getString(R.string.TCelebracionsClient_Tipus),
                Globals.g_Native.getString(R.string.TCelebracionsClient_Descripcio),
                Globals.g_Native.getString(R.string.TCelebracionsClient_Convidats),
                Globals.g_Native.getString(R.string.TCelebracionsClient_Data),
                Globals.g_Native.getString(R.string.TCelebracionsClient_Hora),
                Globals.g_Native.getString(R.string.TCelebracionsClient_Lloc),
                Globals.g_Native.getString(R.string.TCelebracionsClient_Contacte),
                Globals.g_Native.getString(R.string.TCelebracionsClient_Estat));
        db.execSQL(l_Create);
        // 6- Distribucions
        l_Create = String.format(Globals.g_Native.getString(R.string.TDistribucionsCreate),
                Globals.g_Native.getString(R.string.TDistribucions),
                Globals.g_Native.getString(R.string.TDistribucions_CodiCelebracio),
                Globals.g_Native.getString(R.string.TDistribucions_CodiDistribucio),
                Globals.g_Native.getString(R.string.TDistribucions_Nom),
                Globals.g_Native.getString(R.string.TDistribucions_DataAlta),
                Globals.g_Native.getString(R.string.TDistribucions_DataModificacio),
                Globals.g_Native.getString(R.string.TDistribucions_Estat));
        db.execSQL(l_Create);
        // 7- Distribucio Taules
        l_Create = String.format(Globals.g_Native.getString(R.string.TDistribucioTaulesCreate),
                Globals.g_Native.getString(R.string.TDistribucioTaules),
                Globals.g_Native.getString(R.string.TDistribucioTaules_CodiCelebracio),
                Globals.g_Native.getString(R.string.TDistribucioTaules_CodiDistribucio),
                Globals.g_Native.getString(R.string.TDistribucioTaules_NumTaula),
                Globals.g_Native.getString(R.string.TDistribucioTaules_CodiTaula),
                Globals.g_Native.getString(R.string.TDistribucioTaules_Descripcio),
                Globals.g_Native.getString(R.string.TDistribucioTaules_PosicioX),
                Globals.g_Native.getString(R.string.TDistribucioTaules_PosicioY));
        db.execSQL(l_Create);
        // 8- Distribucio Convidats
        l_Create = String.format(Globals.g_Native.getString(R.string.TDistribucioConvidatsCreate),
                Globals.g_Native.getString(R.string.TDistribucioConvidats),
                Globals.g_Native.getString(R.string.TDistribucioConvidats_CodiCelebracio),
                Globals.g_Native.getString(R.string.TDistribucioConvidats_CodiDistribucio),
                Globals.g_Native.getString(R.string.TDistribucioConvidats_CodiConvidat),
                Globals.g_Native.getString(R.string.TDistribucioConvidats_PosicioX),
                Globals.g_Native.getString(R.string.TDistribucioConvidats_PosicioY),
                Globals.g_Native.getString(R.string.TDistribucioConvidats_NumTaula));
        db.execSQL(l_Create);
        // 9- Categories de convidats
        l_Create = String.format(Globals.g_Native.getString(R.string.TCategoriesConvidatsCreate),
                Globals.g_Native.getString(R.string.TCategoriesConvidats),
                Globals.g_Native.getString(R.string.TCategoriesConvidats_Codi),
                Globals.g_Native.getString(R.string.TCategoriesConvidats_CodiCelebracio),
                Globals.g_Native.getString(R.string.TCategoriesConvidats_Descripcio));
        db.execSQL(l_Create);
        // 10- Menus de convidats
        l_Create = String.format(Globals.g_Native.getString(R.string.TMenusConvidatsCreate),
                Globals.g_Native.getString(R.string.TMenusConvidats),
                Globals.g_Native.getString(R.string.TMenusConvidats_Codi),
                Globals.g_Native.getString(R.string.TMenusConvidats_Descripcio));
        db.execSQL(l_Create);
        // 11- Relacions de convidats
        l_Create = String.format(Globals.g_Native.getString(R.string.TRelacionsConvidatsCreate),
                Globals.g_Native.getString(R.string.TRelacionsConvidats),
                Globals.g_Native.getString(R.string.TRelacionsConvidats_Codi),
                Globals.g_Native.getString(R.string.TRelacionsConvidats_CodiCelebracio),
                Globals.g_Native.getString(R.string.TRelacionsConvidats_TipusRelacio),
                Globals.g_Native.getString(R.string.TRelacionsConvidats_Descripcio));
        db.execSQL(l_Create);
        // 12- Convidats
        l_Create = String.format(Globals.g_Native.getString(R.string.TConvidatsCreate),
                Globals.g_Native.getString(R.string.TConvidats),
                Globals.g_Native.getString(R.string.TConvidats_CodiCelebracio),
                Globals.g_Native.getString(R.string.TConvidats_Codi),
                Globals.g_Native.getString(R.string.TConvidats_Nom),
                Globals.g_Native.getString(R.string.TConvidats_Tipus),
                Globals.g_Native.getString(R.string.TConvidats_CodiParella),
                Globals.g_Native.getString(R.string.TConvidats_Adresa),
                Globals.g_Native.getString(R.string.TConvidats_Contacte),
                Globals.g_Native.getString(R.string.TConvidats_Telefon),
                Globals.g_Native.getString(R.string.TConvidats_eMail),
                Globals.g_Native.getString(R.string.TConvidats_Confirmat),
                Globals.g_Native.getString(R.string.TConvidats_Avisat),
                Globals.g_Native.getString(R.string.TConvidats_Transport),
                Globals.g_Native.getString(R.string.TConvidats_CodiMenu),
                Globals.g_Native.getString(R.string.TConvidats_CodiCategoria1),
                Globals.g_Native.getString(R.string.TConvidats_CodiCategoria2),
                Globals.g_Native.getString(R.string.TConvidats_CodiRelacio1),
                Globals.g_Native.getString(R.string.TConvidats_Comentari),
                Globals.g_Native.getString(R.string.TConvidats_Estat));
        db.execSQL(l_Create);
        // 13- Taules
        l_Create = String.format(Globals.g_Native.getString(R.string.TTaulesClientCreate),
                Globals.g_Native.getString(R.string.TTaulesClient),
                Globals.g_Native.getString(R.string.TTaulesClient_Codi),
                Globals.g_Native.getString(R.string.TTaulesClient_Tipus),
                Globals.g_Native.getString(R.string.TTaulesClient_Descripcio),
                Globals.g_Native.getString(R.string.TTaulesClient_MaxPersones),
                Globals.g_Native.getString(R.string.TTaulesClient_AmpladaDiametre),
                Globals.g_Native.getString(R.string.TTaulesClient_Llargada),
                Globals.g_Native.getString(R.string.TTaulesClient_Estat));
        db.execSQL(l_Create);
        //
        // Si executem aixó es que hem creat la BBDD i no hi han dades, de moment:
        Globals.g_NoHiHanDades = true;
        Globals.g_BD_ValorsInicials = true;
        //
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Aqui expresem el canvis (no se si podria ser necessari trespassar dades i tal
        // per evitar problema en l'usuari
        String l_DROP = String.format(Globals.g_Native.getString(R.string.TDrop), Globals.g_Native.getString(R.string.TClient));
        db.execSQL(l_DROP);
        l_DROP = String.format(Globals.g_Native.getString(R.string.TDrop), Globals.g_Native.getString(R.string.TSalonsClient));
        db.execSQL(l_DROP);
        l_DROP = String.format(Globals.g_Native.getString(R.string.TDrop), Globals.g_Native.getString(R.string.TTipusCelebracio));
        db.execSQL(l_DROP);
        l_DROP = String.format(Globals.g_Native.getString(R.string.TDrop), Globals.g_Native.getString(R.string.TPlanols));
        db.execSQL(l_DROP);
        l_DROP = String.format(Globals.g_Native.getString(R.string.TDrop), Globals.g_Native.getString(R.string.TCelebracionsClient));
        db.execSQL(l_DROP);
        l_DROP = String.format(Globals.g_Native.getString(R.string.TDrop), Globals.g_Native.getString(R.string.TDistribucions));
        db.execSQL(l_DROP);
        l_DROP = String.format(Globals.g_Native.getString(R.string.TDrop), Globals.g_Native.getString(R.string.TDistribucioTaules));
        db.execSQL(l_DROP);
        l_DROP = String.format(Globals.g_Native.getString(R.string.TDrop), Globals.g_Native.getString(R.string.TDistribucioConvidats));
        db.execSQL(l_DROP);
        l_DROP = String.format(Globals.g_Native.getString(R.string.TDrop), Globals.g_Native.getString(R.string.TCategoriesConvidats));
        db.execSQL(l_DROP);
        l_DROP = String.format(Globals.g_Native.getString(R.string.TDrop), Globals.g_Native.getString(R.string.TMenusConvidats));
        db.execSQL(l_DROP);
        l_DROP = String.format(Globals.g_Native.getString(R.string.TDrop), Globals.g_Native.getString(R.string.TRelacionsConvidats));
        db.execSQL(l_DROP);
        l_DROP = String.format(Globals.g_Native.getString(R.string.TDrop), Globals.g_Native.getString(R.string.TConvidats));
        db.execSQL(l_DROP);
        l_DROP = String.format(Globals.g_Native.getString(R.string.TDrop), Globals.g_Native.getString(R.string.TTaulesClient));
        db.execSQL(l_DROP);
        // Tornem a definir les taules
        this.onCreate(db);
    }

}