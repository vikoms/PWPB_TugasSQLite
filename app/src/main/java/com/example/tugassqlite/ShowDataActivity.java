package com.example.tugassqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tugassqlite.Adapter.SiswaAdapter;
import com.example.tugassqlite.Helper.DatabaseHelper;
import com.example.tugassqlite.Models.Siswa;

import java.util.List;

public class ShowDataActivity extends AppCompatActivity implements SiswaAdapter.OnUserClickListener {

    RecyclerView recyclerview;
    RecyclerView.LayoutManager layoutManager;

    List<Siswa> siswaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        recyclerview = findViewById(R.id.container);
        layoutManager = new LinearLayoutManager(ShowDataActivity.this);
        recyclerview.setLayoutManager(layoutManager);

        setupRecyclerView();
    }


    public void setupRecyclerView() {
        DatabaseHelper db = new DatabaseHelper(ShowDataActivity.this);
        siswaList = db.selectUserData();
        SiswaAdapter adapter = new SiswaAdapter(siswaList, this);
        recyclerview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onUserClick(Siswa currentSiswa, String action) {
        if (action == "Lihat") {
            Intent lihat = new Intent(ShowDataActivity.this, DetailDataActivity.class);
            lihat.putExtra("nomor", currentSiswa.getNomor());
            lihat.putExtra("nama", currentSiswa.getNama());
            lihat.putExtra("ttl", currentSiswa.getTanggal_lahir());
            lihat.putExtra("jenkel", currentSiswa.getJenis_kelamin());
            lihat.putExtra("alamat", currentSiswa.getAlamat());
            startActivity(lihat);
        } else if (action == "Delete") {
            DatabaseHelper db = new DatabaseHelper(ShowDataActivity.this);
            db.delete(currentSiswa.getNomor());
            setupRecyclerView();
        } else if(action == "Update") {
            Intent update = new Intent(ShowDataActivity.this, InputDataActivity.class);
            update.putExtra("nomor", currentSiswa.getNomor());
            update.putExtra("nama", currentSiswa.getNama());
            update.putExtra("ttl", currentSiswa.getTanggal_lahir());
            update.putExtra("jenkel", currentSiswa.getJenis_kelamin());
            update.putExtra("alamat", currentSiswa.getAlamat());
            startActivity(update);
        }
    }
}

