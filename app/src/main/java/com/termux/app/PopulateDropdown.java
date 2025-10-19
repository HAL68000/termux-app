package com.termux.app;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.termux.R;
import com.termux.app.terminal.io.TermuxTerminalExtraKeys;
import com.termux.view.TerminalView;
import com.termux.app.terminal.TermuxTerminalViewClient;
import com.termux.app.terminal.TermuxTerminalSessionActivityClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PopulateDropdown  extends AppCompatActivity {

    private final Context context;
    private final TerminalView mTerminalView;
    private final TermuxTerminalViewClient mTermuxTerminalViewClient;
    private final TermuxTerminalSessionActivityClient mTermuxTerminalSessionActivityClient;
    private final Spinner scriptsSpinner;
    // Costruttore per inizializzare il context
    public PopulateDropdown(Context context, TerminalView mTerminalView, TermuxTerminalViewClient mTermuxTerminalViewClient, TermuxTerminalSessionActivityClient mTermuxTerminalSessionActivityClient, Spinner scriptsSpinner) {
        this.context = context;
        this.mTerminalView = mTerminalView;
        this.mTermuxTerminalViewClient = mTermuxTerminalViewClient;
        this.mTermuxTerminalSessionActivityClient = mTermuxTerminalSessionActivityClient;
        this.scriptsSpinner = scriptsSpinner;
    }
    // Metodo per popolare il dropdown
    public void populate(Map<String, String> scriptsMap) {
        Log.i("PopulateDropdown", "populateDropdown");

        // Trova lo spinner dal layout
//        Spinner scriptsSpinner = ((Activity) context).findViewById(R.id.scriptsSpinner);

        // Verifica che lo spinner non sia null
        if (scriptsSpinner == null) {
            Log.e("PopulateDropdown", "Spinner non trovato nel layout");
            return;
        }

        List<String> scriptLabels = new ArrayList<>(scriptsMap.keySet());

        // Crea l'adapter passando il context corretto
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, scriptLabels);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        scriptsSpinner.setAdapter(adapter);

        // Set the action to be performed when an item is selected in the dropdown
        scriptsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedLabel = parent.getItemAtPosition(position).toString();
                String scriptPath = scriptsMap.get(selectedLabel);

                // Esegui lo script selezionato
                if (scriptPath != null && !scriptPath.isEmpty()) {
                    Log.i("PopulateDropdown", "Eseguo script: " + scriptPath);
//                    String command = scriptPath;
                    TermuxTerminalExtraKeys terminalExtraKeys = new  TermuxTerminalExtraKeys((TermuxActivity) context, mTerminalView, mTermuxTerminalViewClient, mTermuxTerminalSessionActivityClient);
                    terminalExtraKeys.onTerminalExtraKeyButtonClick(mTerminalView, scriptPath+"\n", false, false, false, false);

                } else {
                    Log.e("PopulateDropdown", "scriptPath è null");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No action when nothing is selected
            }
        });
    }
}
