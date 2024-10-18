package id.ac.ub.papb.recycler1;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.MahasiswaViewHolder> {
    private static final String TAG = "MahasiswaAdapter"; // Tambahkan TAG di sini
    LayoutInflater inflater;
    Context _context;
    ArrayList<Mahasiswa> data;

    public MahasiswaAdapter(Context _context, ArrayList<Mahasiswa> data) {
        this._context = _context;
        this.data = data;
        this.inflater = LayoutInflater.from(this._context);
    }

    @NonNull
    @Override
    public MahasiswaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row, parent, false);
        return new MahasiswaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MahasiswaViewHolder holder, int position) {
        Mahasiswa mhs = data.get(position);
        Log.d(TAG, "data " + position);  // Menggunakan TAG yang baru didefinisikan
        Log.d(TAG, "nim " + mhs.nim);
        Log.d(TAG, "nama " + mhs.nama);

        holder.tvNim.setText(mhs.nim);
        holder.tvNama.setText(mhs.nama);
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "Jumlah data " + data.size());
        return data.size();
    }
    public void addMahasiswa(Mahasiswa mhs) {
        data.add(mhs);
        notifyItemInserted(data.size() - 1); // memberitahu adapter bahwa item baru telah ditambahkan
        Log.d("MahasiswaAdapter", "Data added: " + mhs.nama); // log tambahan
    }

    class MahasiswaViewHolder extends RecyclerView.ViewHolder {
        TextView tvNim;
        TextView tvNama;

        public MahasiswaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNim = itemView.findViewById(R.id.tvNim);
            tvNama = itemView.findViewById(R.id.tvNama);
        }
    }
}
