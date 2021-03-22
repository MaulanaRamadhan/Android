package umn.ac.id.uas_mobile_musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button_login, button_profil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove Action Bar
        getSupportActionBar().hide();
        //Make it Fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        button_login = (Button) findViewById(R.id.button_login);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHalaman_Login();
            }
        });
        button_profil = (Button) findViewById(R.id.button_profil);
        button_profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHalaman_Profil();
            }
        });
    }
    public void openHalaman_Login() {
        Intent intent = new Intent(this, Halaman_Login.class);
        startActivity(intent);
    }
    public void openHalaman_Profil() {
        Intent intent = new Intent(this, Halaman_Profil.class);
        startActivity(intent);
    }
}