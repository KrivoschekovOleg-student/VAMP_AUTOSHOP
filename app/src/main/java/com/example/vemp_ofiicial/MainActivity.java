package com.example.vemp_ofiicial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Auto> autos = new ArrayList<>();
    ArrayList<Auto> selectedAuto = new ArrayList<>();
    AutoAdapter autoAdapter;
    ListView autosList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setInitialDate();

        autosList = findViewById(R.id.autoList);

        autoAdapter = new AutoAdapter(this, R.layout.item_list, autos);
        autosList.setAdapter(autoAdapter);

        autosList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Auto auto = autoAdapter.getItem(position);
                if (autosList.isItemChecked(position)) {
                    selectedAuto.add(auto);
                } else {
                    selectedAuto.remove(auto);
                }
            }
        });
    }

    private void setInitialDate() {
        autos.add(new Auto("Bentley Bentayga", "Среднеразмерный кроссовер сегмента «суперлюкс», выпускающийся британской компанией Bentley Motors.", R.drawable.bently, " "));
        autos.add(new Auto("BMW i8", "BMW Vision Efficient Dynamics, i8 — это полноприводное двухдверное купе.", R.drawable.bmw, " "));
        autos.add(new Auto("Bugatti Veyron", "Гиперкар компании Bugatti, производившийся с 2005 по 2015 год.", R.drawable.bugatty, " "));
        autos.add(new Auto("Genesis G80", "Genesis G80 сочетает в себе передовые технологии безопасности с элегантным и динамичным дизайном.", R.drawable.genesis, " "));
    }
    public void remove(View view) {
        selectedAuto = autoAdapter.getSelectedAutos();
        for (int i = 0; i < selectedAuto.size(); i++) {
            autos.remove(selectedAuto.get(i));
        }
        autosList.clearChoices();
        selectedAuto.clear();
        autoAdapter.notifyDataSetChanged();
    }

    public void addItem(View view) {
        EditText userName = findViewById(R.id.userName);
        EditText userName2 = findViewById(R.id.userName2);
        String auto = userName.getText().toString();
        String auto2 = userName2.getText().toString();
        if (!auto.isEmpty()) {
            autos.add(new Auto(userName.getText().toString(), userName2.getText().toString(), R.drawable.none, " "));
            userName.setText("");
            userName2.setText("");
            autoAdapter.notifyDataSetChanged();
        } else {
            Toast toast = Toast.makeText(this, "Вы не ввели модель авто", Toast.LENGTH_LONG);
            toast.show();
        }
    }
}