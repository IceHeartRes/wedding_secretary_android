package com.resident.weddingsecretary;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Experts extends AppCompatActivity {
    static final private List<String> specialityNames = new ArrayList<>();

    static {
        specialityNames.add("Фотографы");
        specialityNames.add("Видеографы");
        specialityNames.add("Организаторы");
        specialityNames.add("Ведущие");
        specialityNames.add("Регистраторы");
        specialityNames.add("Цветы");
        specialityNames.add("Оформление");
        specialityNames.add("Наряды");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experts);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        setTitle("Специалисты");

        TableLayout table = findViewById(R.id.specialty_table);
        for (int i = 0; i < Math.ceil(specialityNames.size() / 3.0); i++) {
            TableRow tableRow = new TableRow(this);
            tableRow.setWeightSum(3);
            for (int j = 0; j < 3; j++) {
                int pos = i * 3 + j;
                if (pos < specialityNames.size()) {
                    LinearLayout inflate = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.speciality_map_item, null);
                    LinearLayout layout = inflate.findViewById(R.id.speciality_layout);

                    TextView specialityName = layout.findViewById(R.id.speciality_name);
                    final String text = specialityNames.get(pos);
                    specialityName.setText(text);
                    layout.findViewById(R.id.image).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            SpecialityItem.start(Experts.this,text);
                        }
                    });
                    tableRow.addView(inflate);
                }
            }
            table.addView(tableRow);
        }
    }
}
