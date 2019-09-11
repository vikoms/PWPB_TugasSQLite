package com.example.tugassqlite.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tugassqlite.Models.Siswa;
import com.example.tugassqlite.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SiswaAdapter extends RecyclerView.Adapter<SiswaAdapter.viewHolder> {

    List<Siswa> siswaList;
    Context context;

    public SiswaAdapter(List<Siswa> siswaList) {
        this.siswaList = siswaList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_data,parent,false);
        viewHolder holder= new viewHolder(view);
        context = parent.getContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        final Siswa siswa = siswaList.get(position);
        holder.nama.setText(siswa.getNama());
    }

    @Override
    public int getItemCount() {
        return siswaList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView nama;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            nama = itemView.findViewById(R.id.txtNama);
        }
    }
}
