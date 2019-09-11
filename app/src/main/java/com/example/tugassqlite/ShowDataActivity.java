package com.example.tugassqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.tugassqlite.Adapter.SiswaAdapter;
import com.example.tugassqlite.Helper.DatabaseHelper;
import com.example.tugassqlite.Models.Siswa;

import java.util.List;

public class ShowDataActivity extends AppCompatActivity {

    RecyclerView recyclerview;
    RecyclerView.LayoutManager layoutManager;

    List<Siswa> siswaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        recyclerview = (RecyclerView) findViewById(R.id.container);
        layoutManager= new LinearLayoutManager(ShowDataActivity.this);
        recyclerview.setLayoutManager(layoutManager);
        setupRecyclerView();
    }


    private void setupRecyclerView() {
        DatabaseHelper db = new DatabaseHelper(ShowDataActivity.this);
        siswaList = db.selectUserData();
        Siswa siswa = new Siswa();
        Toast.makeText(this,siswa.getNama() , Toast.LENGTH_SHORT).show();

        SiswaAdapter adapter = new SiswaAdapter(siswaList);
        recyclerview.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

}
