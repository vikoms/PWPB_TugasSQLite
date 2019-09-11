package com.example.tugassqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tugassqlite.Helper.DatabaseHelper;
import com.example.tugassqlite.Models.Siswa;

public class InputDataActivity extends AppCompatActivity {

    EditText edtNomor,edtNama,edtTTL,edtGender,edtAlamat;
    TextView txtSimpan;
    String nama,ttl,gender,alamat;
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



        txtSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nomor = Integer.parseInt(edtNomor.getText().toString());
                nama = edtNama.getText().toString();
                ttl = edtTTL.getText().toString();
                gender = edtGender.getText().toString();
                alamat = edtAlamat.getText().toString();

                DatabaseHelper db = new DatabaseHelper(InputDataActivity.this);
                Siswa siswa = new Siswa();
                siswa.setNomor(nomor);
                siswa.setNama(nama);
                siswa.setTanggal_lahir(ttl);
                siswa.setJenis_kelamin(gender);
                siswa.setAlamat(alamat);
                db.insert(siswa);
                Toast.makeText(InputDataActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
