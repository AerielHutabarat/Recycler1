package id.ac.ub.papb.recycler1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv1;
    Button btnAddData;
    MahasiswaAdapter adapter;
    ArrayList<Mahasiswa> data;
    EditText etNim, etNama;
    public static String TAG = "RV1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv1 = findViewById(R.id.rv1);
        btnAddData = findViewById(R.id.bt1);
        etNim = findViewById(R.id.etNim);
        etNama = findViewById(R.id.editTextTextPersonName2);

        data = getData(); // Inisialisasi data awal
        adapter = new MahasiswaAdapter(this, data);
        rv1.setAdapter(adapter);
        rv1.setLayoutManager(new LinearLayoutManager(this));

        // Event listener untuk tombol tambah data
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nimInput = etNim.getText().toString();
                String namaInput = etNama.getText().toString();

                if (!nimInput.isEmpty() && !namaInput.isEmpty()) {
                    // Tambahkan data baru
                    Mahasiswa newMahasiswa = new Mahasiswa();
                    newMahasiswa.nim = nimInput;
                    newMahasiswa.nama = namaInput;

                    // Tambahkan ke data dan beri tahu adapter bahwa data telah berubah
                    data.add(newMahasiswa);
                    adapter.notifyItemInserted(data.size() - 1); // Perbarui RecyclerView

                    // Kosongkan input setelah disimpan
                    etNim.setText("");
                    etNama.setText("");

                    // Berikan feedback ke pengguna
                    Toast.makeText(MainActivity.this, "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                } else {
                    // Berikan peringatan jika input kosong
                    Toast.makeText(MainActivity.this, "Harap isi NIM dan Nama", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public ArrayList<Mahasiswa> getData() {
        ArrayList<Mahasiswa> data = new ArrayList<>();
        List<String> nim = Arrays.asList(getResources().getStringArray(R.array.nim));
        List<String> nama = Arrays.asList(getResources().getStringArray(R.array.nama));
        for (int i = 0; i < nim.size(); i++) {
            Mahasiswa mhs = new Mahasiswa();
            mhs.nim = nim.get(i);
            mhs.nama = nama.get(i);
            Log.d(TAG, "getData " + mhs.nim);
            data.add(mhs);
        }
        return data;
    }
}
