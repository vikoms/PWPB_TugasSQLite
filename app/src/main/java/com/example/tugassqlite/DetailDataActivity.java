package com.example.tugassqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class DetailDataActivity extends AppCompatActivity {
    EditText nomor,nama,gender,ttl,alamat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data);

        nomor = findViewById(R.id.txtNomorDetail);
        nama = findViewById(R.id.txtNamaDetail);
        gender = findViewById(R.id.txtJenkelDetail);
        ttl = findViewById(R.id.txtTanggalLahirDetail);
        alamat = findViewById(R.id.txtAlamatDetail);

        nama.setText(getIntent().getStringExtra("nama"));
        nomor.setText(getIntent().getIntExtra("nomor",0)+"");
        gender.setText(getIntent().getStringExtra("jenkel"));
        ttl.setText(getIntent().getStringExtra("ttl"));
        alamat.setText(getIntent().getStringExtra("alamat"));
    }
}
