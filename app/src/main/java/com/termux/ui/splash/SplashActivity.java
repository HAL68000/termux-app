package com.termux.ui.splash;
import android.os.Handler;
import android.os.Looper;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.os.Build;
import android.app.PendingIntent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.termux.R;
import com.termux.app.RunCommandService;
import com.termux.app.TermuxActivity;
import com.termux.terminal.TerminalSession;
import com.termux.terminal.TerminalSessionClient;
import com.termux.shared.shell.command.ExecutionCommand;
import com.termux.shared.termux.shell.command.runner.terminal.TermuxSession;
import com.termux.shared.shell.command.environment.AndroidShellEnvironment;
import com.termux.shared.termux.TermuxConstants.TERMUX_APP.RUN_COMMAND_SERVICE;
import java.util.Arrays;
import java.util.HashMap;
import com.termux.shared.termux.TermuxConstants;
import com.termux.shared.termux.TermuxConstants.TERMUX_APP.RUN_COMMAND_SERVICE;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // 1. Trova il pulsante tramite il suo ID
//        Button install_ubuntu = findViewById(R.id.install_ubuntu);
        Button button = findViewById(R.id.start_terminal_button);
        Button install_node_red = findViewById(R.id.install_node_red);
        Button run_node_red = findViewById(R.id.run_node_red);

        // 2. Imposta un listener di click sul pulsante
//        install_ubuntu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Handler handler = new Handler(Looper.getMainLooper());
//                startTerminalSession("/data/data/com.termux/files/usr/bin/bash", new String[]{"/data/data/com.termux/files/home/setup.sh"});
//            }
//        });
        install_node_red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler handler = new Handler(Looper.getMainLooper());
                startTerminalSession("/data/data/com.termux/files/usr/bin/bash", new String[]{"/data/data/com.termux/files/home/sample_script.sh"});
            }
        });
        run_node_red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler handler = new Handler(Looper.getMainLooper());
                startTerminalSession("/data/data/com.termux/files/usr/bin/bash", new String[]{"/data/data/com.termux/files/home/sample_script.sh"});
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashActivity.this, TermuxActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void startTerminalSession(String command, String[]arguments) {
        Intent intent = new Intent();
        intent.setClassName("com.termux", "com.termux.app.RunCommandService");
        intent.setAction("com.termux.RUN_COMMAND");
        intent.putExtra("com.termux.RUN_COMMAND_PATH", command);
        intent.putExtra("com.termux.RUN_COMMAND_ARGUMENTS", arguments);
        intent.putExtra("com.termux.RUN_COMMAND_WORKDIR", "/data/data/com.termux/files/home");
        intent.putExtra("com.termux.RUN_COMMAND_BACKGROUND", false);
        intent.putExtra("com.termux.RUN_COMMAND_SESSION_ACTION", "0");
        startService(intent);

    }
//    private void startTerminalSession() {
//        Intent intent = new Intent();
//        intent.setClassName("com.termux", "com.termux.app.RunCommandService");
//        intent.setAction("com.termux.RUN_COMMAND");
//        intent.putExtra("com.termux.RUN_COMMAND_PATH", "/data/data/com.termux/files/usr/bin/pkg");
//        intent.putExtra("com.termux.RUN_COMMAND_ARGUMENTS", new String[]{"update"});
//        intent.putExtra("com.termux.RUN_COMMAND_WORKDIR", "/data/data/com.termux/files/home");
//        intent.putExtra("com.termux.RUN_COMMAND_BACKGROUND", false);
//        intent.putExtra("com.termux.RUN_COMMAND_SESSION_ACTION", "0");
//        startService(intent);
//    }
}
