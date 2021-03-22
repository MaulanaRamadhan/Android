package umn.ac.id.uas_mobile_musicplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;


public class Halaman_DaftarLagu extends AppCompatActivity {
    private static final String TAG = "-";
    private ImageButton button;
    private static final int MY_PERMISSION_REQUEST = 1;
    private SongAdapter mAdapter;
    private ArrayList<SongInfo> songList;
    ArrayList<Integer> arrayList;
    RecyclerView recycler;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman__daftar_lagu);
        initializeViews();
        getSongList();
        songList = new ArrayList<>();

        recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mAdapter = new SongAdapter(getApplicationContext(), songList, new SongAdapter.RecyclerItemClickListener() {
            @Override
            public void onClickListener(SongInfo song, int position) {
                Toast.makeText(Halaman_DaftarLagu.this, song.getSongName(), Toast.LENGTH_SHORT).show();
            }
        });
        recycler.setAdapter(mAdapter);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        button = (ImageButton) findViewById(R.id.button_option);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(Halaman_DaftarLagu.this, v);
                popup.setOnMenuItemClickListener(Halaman_DaftarLagu.this::onMenuItemClick);
                popup.inflate(R.menu.popup_menu);
                popup.show();
            }
        });
        Intent intent = new Intent(this, Popup.class);
        startActivity(intent);
    }

    private void getSongList() {
                ArrayList<SongInfo> songs = new ArrayList<>();
                Log.d(TAG, "onSuccess: " + songs);
                songList.addAll(songs);
                mAdapter.notifyDataSetChanged();
            }

    private void initializeViews() {
        recycler = (RecyclerView) findViewById(R.id.recyclerView);
    }

    public void getMusic(){
        ContentResolver contentResolver = getContentResolver();
        Uri songUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor songCursor = contentResolver.query(songUri, null, null, null, null);

        if (songCursor != null && songCursor.moveToFirst()){
            int songName = songCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);

            do {
                String currentName = songCursor.getString(songName);
                arrayList.add(songName);
            }while(songCursor.moveToNext());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissons, int[] grantResults){
        switch (requestCode){
            case MY_PERMISSION_REQUEST: {
                if(grantResults.length > 0 && grantResults [0] == PackageManager.PERMISSION_GRANTED){
                    if (ContextCompat.checkSelfPermission(Halaman_DaftarLagu.this,
                            Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(this, "Permission Granted!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "No Permission Granted!", Toast.LENGTH_SHORT).show();
                    finish();
                }
                return;
            }
        }
    }

    public boolean onMenuItemClick(MenuItem item) {
        Toast.makeText(this, "Selected Item: " +item.getTitle(), Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.profile:
                Intent intent2 = new Intent(this, Halaman_Profil.class);
                startActivity(intent2);
                return true;
            case R.id.logout:
                Intent intent3 = new Intent(this, MainActivity.class);
                startActivity(intent3);
                return true;
            default:
                return false;
        }
    }
}