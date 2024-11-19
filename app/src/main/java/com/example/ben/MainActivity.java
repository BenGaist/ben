package com.example.ben;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btn = findViewById(R.id.button);
        EditText editTxt = findViewById(R.id.edit);
        TextView txt = findViewById(R.id.hello);
        String name = String.valueOf(editTxt.getText());

        SharedPreferences sharedPreferences = getSharedPreferences("testUp",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        txt.setText(sharedPreferences.getString("username","###"));
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("are you sure")
                        .setMessage("are you really really sure")
                        .setPositiveButton("ok",null)
                        .setNegativeButton("dont", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity.this, "no", Toast.LENGTH_LONG).show();
                            }
                        }).show();


                    String name1 = editTxt.getText().toString();
                    editor.putString("username", name1);
                    editor.apply();
                    txt.setText(sharedPreferences.getString("username", "###"));

            }
    });


    }
}