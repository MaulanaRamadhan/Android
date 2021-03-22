package umn.ac.id.uas_mobile_musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class Halaman_Login extends AppCompatActivity {
    private ImageButton button;
    private EditText username;
    private EditText password;
    private Button login;
    private TextView info;
    private int counter=5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman__login);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        button = (ImageButton) findViewById(R.id.button_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });

        username = (EditText)findViewById(R.id.editText_username);
        password = (EditText)findViewById(R.id.editText_password);
        login = (Button)findViewById(R.id.button_login);
        info = (TextView)findViewById(R.id.textView_info);

        info.setText("Number of Attempts Remaining : 5");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(username.getText().toString(), password.getText().toString());
            }
        });
    }

    private void validate(String userName, String userPassword){
        if((userName.equals("uasmobile")) && (userPassword.equals("uasmobilegenap"))){
            Intent intent = new Intent(Halaman_Login.this, Halaman_DaftarLagu.class);
            startActivity(intent);
        }
        else{
            counter--;
            info.setText("Number of Attempts Remaining : " + String.valueOf(counter));
            if(counter == 0){
                login.setEnabled(false);
            }
        }
    }
    public void back() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}