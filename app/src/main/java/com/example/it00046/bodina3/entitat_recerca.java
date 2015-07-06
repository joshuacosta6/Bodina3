package com.example.it00046.bodina3;

import android.app.Activity;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.it00046.bodina3.Classes.DAO.DAOEntitats;
import com.example.it00046.bodina3.Classes.ExpandAnimation;


public class entitat_recerca extends Activity {

    private ListView g_LVW_searchResults;
    private View g_LIN_ToolbarAnterior = null;
    private int g_Posicio = -1;
    private Context Jo = this;

    @Override
    protected void onCreate(Bundle p_savedInstanceState) {
        super.onCreate(p_savedInstanceState);
        setContentView(R.layout.entitat_recerca);

        setupSearchView();

        g_LVW_searchResults.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, final View view,
                                        int position, long id) {
                return true;
            }
        });

        g_LVW_searchResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                ImageView l_IMA_Icon;
                TextView l_TXT_NomAnterior = null, l_TXT_Nom;
                View l_LIN_Toolbar;

                if (g_Posicio != position) {
                    l_IMA_Icon = (ImageView) view.findViewById(R.id.LiniaLVWRecercaEntitatsIMAIcona);
                    // Desmarquem el que hi havia marcat
                    if (l_TXT_NomAnterior != null) {
                        // Recuperem color i amaguem seleccio
                        l_TXT_NomAnterior.setBackgroundResource(R.color.blue);
                        l_IMA_Icon.setVisibility(View.GONE);
                        // El colapsem
                        ((LinearLayout.LayoutParams) g_LIN_ToolbarAnterior.getLayoutParams()).bottomMargin = -80;
                        g_LIN_ToolbarAnterior.setVisibility(View.GONE);
                    }
                    // Modifiquem el color de fons de la linia
                    l_TXT_Nom = (TextView)view.findViewById(R.id.LiniaLVWLlistaInvitacionsTXTNom);
                    l_TXT_Nom.setBackgroundResource(R.color.green);
                    l_IMA_Icon.setVisibility(View.VISIBLE);
                    // Apuntem en quina linia estem (per si desprès l'usuari selecciona l'entitat)
                    g_Posicio = position;
                    l_LIN_Toolbar = view.findViewById(R.id.LiniaLVWRecercaEntitatsLINToolbar);
                    // Definim l'animació del item
                    ExpandAnimation l_expandAni = new ExpandAnimation(l_LIN_Toolbar, 100);
                    l_LIN_Toolbar.startAnimation(l_expandAni);
                    l_TXT_NomAnterior = l_TXT_Nom;
                    g_LIN_ToolbarAnterior = l_LIN_Toolbar;
                }
                else{
                    // Ens seleccionan
                    Intent l_resultIntent = new Intent();
                    int l_Parametre = g_Posicio +1;

                    l_resultIntent.putExtra("Seleccio", l_Parametre);
                    setResult(Activity.RESULT_OK, l_resultIntent);
                    finish();
                }
            }

        });
    }

    private void setupSearchView() {
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) findViewById(R.id.entitat_recercaSVW);
        SearchableInfo searchableInfo = searchManager.getSearchableInfo(getComponentName());
        searchView.setSearchableInfo(searchableInfo);
        g_LVW_searchResults = (ListView) findViewById(R.id.entitat_recercaLVW);
        // Events del searchview
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub
                //Toast.makeText(activity, String.valueOf(hasFocus),Toast.LENGTH_SHORT).show();
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // TODO Auto-generated method stub

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Recerquem a partir de 3 caracters
                if (newText.length() > 3) {
                    g_LVW_searchResults.setVisibility(View.VISIBLE);
                    DAOEntitats.Llegir("", g_LVW_searchResults, Jo, R.layout.LiniaLVWRecercaEntitats);
                } else {
                    g_LVW_searchResults.setVisibility(View.INVISIBLE);
                }
                return false;
            }
        });
    }
    // Aquesta funció es cridada pels elements de la llista quan seleccionem un element de la
    // llista
    public void btnEntitatRecerca_Acceptar(View view) {
        // Retornem les dades de l'entitat seleccionada gracies a l_Posicio
        Intent l_resultIntent = new Intent();
        int l_Parametre = g_Posicio +1;

        l_resultIntent.putExtra("Seleccio", l_Parametre);
        setResult(Activity.RESULT_OK, l_resultIntent);
        this.finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem p_item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int l_id = p_item.getItemId();

        //noinspection SimplifiableIfStatement
        if (l_id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(p_item);
    }
    /*

    //     Codi d'exemple per recercar en els contactes....

    private TextView resultText;

    @Override
    protected void onNewIntent(Intent intent) {
        if (ContactsContract.Intents.SEARCH_SUGGESTION_CLICKED.equals(intent.getAction())) {
            //handles suggestion clicked query
            String displayName = getDisplayNameForContact(intent);
            //resultText.setText(displayName);
        } else if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            // handles a search query
            String query = intent.getStringExtra(SearchManager.QUERY);
            //resultText.setText("should search for query: '" + query + "'...");
        }
    }

    private String getDisplayNameForContact(Intent intent) {
        Cursor phoneCursor = getContentResolver().query(intent.getData(), null, null, null, null);
        phoneCursor.moveToFirst();
        int idDisplayName = phoneCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
        String name = phoneCursor.getString(idDisplayName);
        phoneCursor.close();
        return name;
    }
    */
}
