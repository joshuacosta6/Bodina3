package com.example.it00046.bodina3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.it00046.bodina3.Classes.CanvasAuto;
import com.example.it00046.bodina3.Classes.Globals;

/**
 * Created by it00046 on 29/09/2015.
 */
public class salons_client_alta extends ActionBarActivity {
    private CanvasAuto g_Canvas;
    private int g_Amplada = 0, g_Alsada = 0;
    private Context Jo = this;
    static final int g_RQC_SALONS_CLIENT_PLANOL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SeekBar l_SEK_Amplada, l_SEK_Alsada;
        final TextView l_TXT_Amplada, l_TXT_Alsada, l_TXT_Nom;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.salons_client_alta);
        // Recuperem els controls
        l_TXT_Nom = (TextView) findViewById(R.id.SalonsClientAltaTXTNom);
        l_SEK_Amplada = (SeekBar) findViewById(R.id.SalonsClientAltaSEKAmplada);
        l_SEK_Alsada = (SeekBar) findViewById(R.id.SalonsClientAltaSEKAlsada);
        l_TXT_Amplada = (TextView) findViewById(R.id.SalonsClientAltaLITAmplada);
        l_TXT_Alsada = (TextView) findViewById(R.id.SalonsClientAltaLITAlsada);
        g_Canvas = (CanvasAuto) findViewById(R.id.SalonsClientAltaVIWDrawing);
        // Codi de les seekbars
        l_SEK_Amplada.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int l_AmpladaTriada = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                l_AmpladaTriada = progresValue;
                g_Amplada = l_AmpladaTriada;
                l_TXT_Amplada.setText(l_AmpladaTriada + " " + Globals.g_Native.getString(R.string.meters));
                if (g_Alsada != 0){
                    g_Canvas.Dibuixa(g_Amplada * 5, g_Alsada * 5);
                }
                else{
                    // Pintem una linia
                    g_Canvas.Dibuixa(g_Amplada * 5, 5);
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //Toast.makeText(getApplicationContext(), "Started tracking seekbar", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //l_TXT_Amplada.setText(seekBar.getMax());
                g_Amplada = l_AmpladaTriada;
            }
        });
        l_SEK_Alsada.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int l_AlsadaTriada = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                l_AlsadaTriada = progresValue;
                g_Alsada = l_AlsadaTriada;
                l_TXT_Alsada.setText(l_AlsadaTriada + " " + Globals.g_Native.getString(R.string.meters));
                if (g_Amplada != 0) {
                    g_Canvas.Dibuixa(g_Amplada * 5, g_Alsada * 5);
                } else {
                    // Pintem una linia
                    g_Canvas.Dibuixa(5, g_Alsada * 5);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                g_Alsada = l_AlsadaTriada;
            }
        });
        // Control de enrera/cancel·lacio
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_close_white_48dp);
    }

    // Funcio per obrir la finestra de recerca de entitats
    public void btnAPlanolOnClick(View view){
        Intent intent = new Intent(Jo, salons_client_planol.class);
        startActivityForResult(intent, g_RQC_SALONS_CLIENT_PLANOL);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.salons_client_alta, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem p_Item) {
        Intent l_Intent;
        int l_id = p_Item.getItemId();

        return super.onOptionsItemSelected(p_Item);
    }

}
