package com.termux.app;

import android.content.Context;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

public class AssetReader {
    public static String readAssetFile(Context context, String fileName) {
        StringBuilder text = new StringBuilder();
        try {
            // Ottieni l'input stream del file asset
            InputStream is = context.getAssets().open(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            // Leggi il file riga per riga
            while ((line = reader.readLine()) != null) {
                text.append(line).append("\n");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text.toString();
    }
    // Metodo per copiare il file e renderlo eseguibile
    public static void copyAssetToTermuxAndSetExecutable(Context context, String assetFileName, String outputFilePath) {
        try {
            // Verifica se la directory di destinazione esiste, se no creala
            File outFile = new File(outputFilePath);
            File parentDir = outFile.getParentFile();
            if (!parentDir.exists()) {
                boolean dirCreated = parentDir.mkdirs();  // Crea la directory
                if (!dirCreated) {
                    System.out.println("Impossibile creare la directory di destinazione.");
                    return;
                }
            }

            // Legge il file dagli asset
            InputStream is = context.getAssets().open(assetFileName);
            OutputStream os = new FileOutputStream(outFile);

            // Copia il contenuto del file
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }

            // Chiude gli stream
            os.flush();
            os.close();
            is.close();

            // Rende lo script eseguibile
            if (outFile.setExecutable(true)) {
                System.out.println("File copiato e reso eseguibile con successo.");
            } else {
                System.out.println("Impossibile rendere il file eseguibile.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
