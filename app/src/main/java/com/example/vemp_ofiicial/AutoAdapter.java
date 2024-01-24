package com.example.vemp_ofiicial;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AutoAdapter extends  ArrayAdapter<Auto>{

    private LayoutInflater inflater;
    private int layout;
    private ArrayList<Auto> autos;
    public ArrayList<Auto> getSelectedAutos() {
        return autos;
    }
    AutoAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Auto> autos) {
        super(context, resource, autos);
        this.autos = autos;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder viewHolder;
        if(convertView==null){
            convertView = inflater.inflate(this.layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Auto auto = autos.get(position);

        viewHolder.imageAuto.setImageResource(auto.getImageResourse());
        viewHolder.nameView.setText(auto.getName());
        viewHolder.opisanieView.setText(auto.getOpisanie());

        viewHolder.countView.setText(auto.getUnit() + auto.getCount());

        viewHolder.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = auto.getCount()+1;
                auto.setCount(count);
                viewHolder.countView.setText(auto.getUnit() + count);
                if (count > 0) {
                    viewHolder.removeButton.setText("—");
                }
            }
        });
        viewHolder.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = auto.getCount()-1;
                if (count < 0) {
                    showDeleteConfirmationDialog(auto);
                } else {
                    auto.setCount(count);
                    viewHolder.countView.setText(auto.getUnit() + count);
                    if (count == 0) {
                        viewHolder.removeButton.setText("×");
                    }
                }
            }
        });
        return convertView;
    }
    private void showDeleteConfirmationDialog(final Auto auto) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Подтверждение удаления")
                .setMessage("Вы уверены, что хотите удалить этот элемент?")
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Удаление элемента из списка
                        autos.remove(auto);
                        notifyDataSetChanged(); // Обновляем адаптер после удаления
                    }
                })
                .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Отмена удаления
                        dialog.dismiss();
                    }
                });
        builder.create().show();
    }
    public static class ViewHolder{
        Button addButton, removeButton;
        TextView countView, nameView, opisanieView;
        ImageView imageAuto;
        ViewHolder(View view){
            addButton = view.findViewById(R.id.plus);
            removeButton = view.findViewById(R.id.minus);
            countView = view.findViewById(R.id.count);
            imageAuto = view.findViewById(R.id.imageAuto);
            nameView = view.findViewById(R.id.name);
            opisanieView = view.findViewById(R.id.opisanie);
        }
    }

}
