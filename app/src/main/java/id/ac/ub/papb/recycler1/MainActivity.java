package id.ac.ub.papb.recycler1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.database.Cursor;
import android.widget.Toast;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv1;
    Button btnAddData;
    MahasiswaAdapter adapter;
    ArrayList<Mahasiswa> data;
    EditText etNim, etNama;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv1 = findViewById(R.id.rv1);
        btnAddData = findViewById(R.id.bt1);
        etNim = findViewById(R.id.etNim);
        etNama = findViewById(R.id.editTextTextPersonName2);
        dbHelper = new DBHelper(this);

        data = new ArrayList<>();
        loadData(); // load data dari SQLite ke RecyclerView

        adapter = new MahasiswaAdapter(this, data);
        rv1.setAdapter(adapter);
        rv1.setLayoutManager(new LinearLayoutManager(this));

        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nimInput = etNim.getText().toString();
                String namaInput = etNama.getText().toString();

                if (!nimInput.isEmpty() && !namaInput.isEmpty()) {
                    boolean isInserted = dbHelper.insertData(nimInput, namaInput);

                    if (isInserted) {
                        Mahasiswa newMahasiswa = new Mahasiswa();
                        newMahasiswa.nim = nimInput;
                        newMahasiswa.nama = namaInput;

                        data.add(newMahasiswa);
                        adapter.notifyItemInserted(data.size() - 1);

                        etNim.setText("");
                        etNama.setText("");

                        Toast.makeText(MainActivity.this, "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Gagal menambahkan data", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Harap isi NIM dan Nama", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loadData() {
        Cursor res = dbHelper.getAllData();
        if (res.getCount() == 0) {
            Toast.makeText(this, "Tidak ada data", Toast.LENGTH_SHORT).show();
            return;
        }

        while (res.moveToNext()) {
            Mahasiswa mhs = new Mahasiswa();
            mhs.nim = res.getString(1); // kolom NIM
            mhs.nama = res.getString(2); // kolom Nama
            data.add(mhs);
        }
    }
}
