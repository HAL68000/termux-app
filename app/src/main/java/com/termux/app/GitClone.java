package com.termux.app;
import static com.termux.shared.termux.TermuxConstants.TERMUX_GITHUB_USER_REPO;
import static com.termux.shared.termux.TermuxConstants.TERMUX_GITHUB_USER_SCRIPTS_FOLDER;
import static com.termux.shared.termux.TermuxConstants.TERMUX_GITHUB_USER_SCRIPTS_FOLDER_INSTALLER;
import static com.termux.shared.termux.TermuxConstants.TERMUX_GITHUB_USER_SCRIPTS_FOLDER_START;

import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;

import org.eclipse.jgit.api.Git;
import java.io.File;
import java.nio.file.*;

public class GitClone {

    public void clone_repo() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    File cloneDir = new File(TERMUX_GITHUB_USER_SCRIPTS_FOLDER);
                    Git.cloneRepository()
                        .setURI(TERMUX_GITHUB_USER_REPO)
                        .setDirectory(cloneDir)
                        .call();
                    makeShellScriptsExecutable(new File(TERMUX_GITHUB_USER_SCRIPTS_FOLDER_INSTALLER));
                    makeShellScriptsExecutable(new File(TERMUX_GITHUB_USER_SCRIPTS_FOLDER_START));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void makeShellScriptsExecutable(File directory) {
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        makeShellScriptsExecutable(file);
                    }
                    else if (file.isFile() && file.getName().endsWith(".sh")) {
                        boolean success = file.setExecutable(true);
                        if (success) {
                            Log.i("GitClone","File reso eseguibile: " + file.getAbsolutePath());
                        } else {
                            Log.e("GitClone","Errore nel rendere eseguibile: " + file.getAbsolutePath());
                        }
                    }
                }
            }
        }
    }
}
