package com.example.it00046.bodina3;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.it00046.bodina3.Classes.Client;
import com.example.it00046.bodina3.Classes.EntitatClient;
import com.example.it00046.bodina3.Classes.Globals;
import com.example.it00046.bodina3.Classes.SQLClientsDAO;
import com.example.it00046.bodina3.Classes.SQLEntitatsClientDAO;
import com.example.it00046.bodina3.Classes.SpinnerClasses.SpnEntitat;
import com.example.it00046.bodina3.Classes.Validacio;
import com.example.it00046.bodina3.Classes.params.Entitat;

import java.util.List;


public class ac_entitat_solicitar extends ActionBarActivity {

    private Spinner lSPN_EntitatsClient;
    private EditText lTXT_Descripcio, lTXT_Contacte, lTXT_eMail;
    private TextView lTextEntitat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ly_entitat_solicitar);
        // Recuperem controls del layout
        // Entrada de dades:
        lTXT_Descripcio = (EditText) findViewById(R.id.TexteEntitatSolicitar_Descripcio);
        lTXT_Contacte = (EditText) findViewById(R.id.TexteEntitatSolicitar_Contacte);
        lTXT_eMail = (EditText) findViewById(R.id.TexteEntitatSolicitar_eMail);
        //  Literals:
        lTextEntitat = (TextView) findViewById(R.id.litEntitatSolicitar_Entitat);
        // Spinners:
        lSPN_EntitatsClient = (Spinner)findViewById(R.id.spinnerEntitatSolicitar_Entitat);
        // Codi per tractar el spinner de les entitats del client
        List <SpnEntitat> l_Entitats = SQLEntitatsClientDAO.LlegirEntitats();
        ArrayAdapter<SpnEntitat> dataAdapter = new ArrayAdapter<SpnEntitat>(this,
                android.R.layout.simple_spinner_item, l_Entitats);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        lSPN_EntitatsClient.setAdapter(dataAdapter);
        /*
        ArrayAdapter<CharSequence> adapter_EntitatsClient = ArrayAdapter.createFromResource(this,R.array.Idioma,android.R.layout.simple_spinner_item);
        adapter_EntitatsClient.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lSPN_EntitatsClient.setAdapter(adapter_EntitatsClient);
        */
        // Codi del Spinner de entitats del client
        lSPN_EntitatsClient.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView parent, View view, int pos, long id) {
                // Esborrem possible error
                lTextEntitat.setError(null);
            }
            @Override
            public void onNothingSelected(AdapterView parent) {
            }
        });
        // Codi de validacio de la finestra (fem servir la clase est�tica Validaci�)
        lTXT_Descripcio.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validacio.hasText(lTXT_Descripcio);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });
        lTXT_Contacte.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validacio.hasText(lTXT_Contacte);
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
        lTXT_eMail.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validacio.isEmailAddress(lTXT_eMail, true);
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
        /*
        // Codi de control del camp de texte (la visibilitat del aspa). Aix� anir� dintre
        // del component que has de crear !!!!
        lTXT_Prova.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    l_BotoEsborrar.setVisibility(View.VISIBLE);
                } else {
                    l_BotoEsborrar.setVisibility(View.INVISIBLE);
                }
            }
        });
        */

    }

    // Funcio interna per validar la finestra
    private boolean ValidarFinestra() {
        boolean ret = true;

        if (!Validacio.hasText(lTXT_Descripcio)) ret = false;
        if (!Validacio.hasText(lTXT_Contacte)) ret = false;
        if (!Validacio.isEmailAddress(lTXT_eMail, true)) ret = false;
        if (lSPN_EntitatsClient.getSelectedItem().toString().equals(Globals.g_Native.getString(R.string.llista_Select))){
            lTextEntitat.setError(Globals.g_Native.getString(R.string.error_CampObligatori));
            ret = false;
        }

        return ret;
    }

    public void btnEntitatSolicitar_Acceptar(View view) {
        EntitatClient l_entitatClient = new EntitatClient();
        Entitat l_entitat = new Entitat();
        SpnEntitat l_Aux;

        // Validem que els camps estiguin informats
        if (ValidarFinestra()) {
            l_entitatClient.DescripcioAssociacio = lTXT_Descripcio.getText().toString();
            l_entitatClient.ContacteAssociacio = lTXT_Contacte.getText().toString();
            l_entitatClient.eMailAssociacio = lTXT_eMail.getText().toString();
            l_Aux = (SpnEntitat) lSPN_EntitatsClient.getSelectedItem();
            l_entitatClient = l_Aux.getId();

            l_entitatClient.CodiEntitat = l_entitat.Codi;
            l_entitatClient.eMailEntitat = l_entitat.eMail;
            l_entitatClient.ContacteEntitat = l_entitat.Contacte;
            l_entitatClient.AdresaEntitat = l_entitat.Adresa;
            l_entitatClient.TelefonEntitat = l_entitat.Telefon;
            l_entitatClient.PaisEntitat = l_entitat.Pais;
            l_entitatClient.EstatEntitat = 1;
            //
            SQLEntitatsClientDAO.Solicitar(l_entitatClient);
            this.finish();
        }
        else{
            Toast.makeText(ac_entitat_solicitar.this,
                    Globals.g_Native.getString(R.string.error_Layout),
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mn_entitat_solicitar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
