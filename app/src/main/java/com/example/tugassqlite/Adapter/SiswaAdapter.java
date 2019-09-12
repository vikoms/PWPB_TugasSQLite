package com.example.tugassqlite.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tugassqlite.DetailDataActivity;
import com.example.tugassqlite.Helper.DatabaseHelper;
import com.example.tugassqlite.Models.Siswa;
import com.example.tugassqlite.R;
import com.example.tugassqlite.ShowDataActivity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SiswaAdapter extends RecyclerView.Adapter<SiswaAdapter.viewHolder> {

    List<Siswa> siswaList;
    Context context;
    OnUserClickListener listener;
    public SiswaAdapter(List<Siswa> siswaList, OnUserClickListener listener) {
        this.siswaList = siswaList;
        this.listener = listener;
    }


    public interface OnUserClickListener{
        void onUserClick(Siswa currentSiswa, String action);
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

        holder.nama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                View dialogView = LayoutInflater.from(context).inflate(R.layout.alert_dialog,null);
                dialog.setView(dialogView);

                TextView lihat = dialogView.findViewById(R.id.lihatData);
                TextView update = dialogView.findViewById(R.id.updateData);
                TextView delete = dialogView.findViewById(R.id.deleteData);


                final AlertDialog alertDialog = dialog.create();
                alertDialog.show();

                lihat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.onUserClick(siswa,"Lihat");
                        alertDialog.dismiss();
                    }
                });

                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.onUserClick(siswa,"Delete");
                        alertDialog.dismiss();
                }
                });
                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.onUserClick(siswa,"Update");
                        alertDialog.dismiss();
                    }
                });


            }
        });

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
