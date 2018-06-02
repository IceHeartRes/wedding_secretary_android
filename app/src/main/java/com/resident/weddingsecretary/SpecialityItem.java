package com.resident.weddingsecretary;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SpecialityItem extends AppCompatActivity {


    private static final List<String> EXPERT_NAME;
    public static final String SPECIALITY_NAME = "speciality_name";

    static {
        EXPERT_NAME = new ArrayList<>();
        EXPERT_NAME.add("Вася Пупкин");
        EXPERT_NAME.add("Иванов Иван");
        EXPERT_NAME.add("Валерий Подполковников");
    }

    public static void start(Context context, String specialityName) {
        Intent intent = new Intent(context, SpecialityItem.class);
        intent.putExtra(SPECIALITY_NAME, specialityName);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speciality_item);

        Intent intent = getIntent();

        LinearLayout specialityList = findViewById(R.id.list);

        TextView specialityName = findViewById(R.id.speciality_name);
        specialityName.setText(intent.getStringExtra(SPECIALITY_NAME));

        for (String expertName : EXPERT_NAME) {
            LinearLayout inflate = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.speciality_list_item, null);
            TextView expertNameView = inflate.findViewById(R.id.expert_name);
            expertNameView.setText(expertName);
            specialityList.addView(inflate);
            ImageView next = findViewById(R.id.next_button);
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(SpecialityItem.this, Personal.class));
                }
            });
        }

    }
}
