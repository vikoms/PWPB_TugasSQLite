package com.example.tugassqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tugassqlite.Helper.DatabaseHelper;
import com.example.tugassqlite.Models.Siswa;

public class InputDataActivity extends AppCompatActivity {

    EditText edtNomor, edtNama, edtTTL, edtGender, edtAlamat;
    TextView txtSimpan;
    String nama, ttl, gender, alamat;

    DatabaseHelper db;
    Siswa siswa;
    int nomor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data);

        edtNomor = findViewById(R.id.editTextNomor);
        edtNama = findViewById(R.id.editTextNama);
        edtTTL = findViewById(R.id.editTextTanggalLahir);
        edtGender = findViewById(R.id.editTextJenkel);
        edtAlamat = findViewById(R.id.editTextAlamat);
        txtSimpan = findViewById(R.id.textView);
        db = new DatabaseHelper(InputDataActivity.this);
        siswa = new Siswa();
        nomor = getIntent().getIntExtra("nomor", 0) ;

        if (nomor == 0) {
            edtNama.setText("");
            edtTTL.setText("");
            edtGender.setText("");
            edtAlamat.setText("");
        } else if (nomor != 0) {
            edtNama.setText(getIntent().getStringExtra("nama"));
            edtTTL.setText(getIntent().getStringExtra("ttl"));
            edtGender.setText(getIntent().getStringExtra("jenkel"));
            edtAlamat.setText(getIntent().getStringExtra("alamat"));
        }

        txtSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nomor == 0) {
                    Toast.makeText(InputDataActivity.this, "Data Berhasil Ditambahkan", Toast.LENGTH_SHORT).show();

                    inputData();
                } else if(nomor != 0) {
                    Toast.makeText(InputDataActivity.this, "Data Berhasil Diupdate", Toast.LENGTH_SHORT).show();
                    updateData();
                }
            }
        });


    }


    public void inputData() {
        nama = edtNama.getText().toString();
        ttl = edtTTL.getText().toString();
        gender = edtGender.getText().toString();
        alamat = edtAlamat.getText().toString();


        siswa.setNama(nama);
        siswa.setTanggal_lahir(ttl);
        siswa.setJenis_kelamin(gender);
        siswa.setAlamat(alamat);
        db.insert(siswa);

        edtNama.setText("");
        edtTTL.setText("");
        edtGender.setText("");
        edtAlamat.setText("");
    }

    public void updateData() {


        siswa.setNomor(nomor);
        siswa.setNama(edtNama.getText().toString());
        siswa.setTanggal_lahir(edtTTL.getText().toString());
        siswa.setJenis_kelamin(edtGender.getText().toString());
        siswa.setAlamat(edtAlamat.getText().toString());
        db.update(siswa);
        startActivity(new Intent(InputDataActivity.this, ShowDataActivity.class));
        finish();
    }
}
