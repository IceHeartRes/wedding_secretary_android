package com.resident.weddingsecretary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Personal extends AppCompatActivity {

    public static final String DESCRIPTION = "Беру дорого, фоторграфирую плохо, делаю долго, на свадьбу опаздываю, оппаратуру забываю. Звоните, заказывайте, будет плохо.";
    public static final int PHOTO_COUNT = 16;
    public static final int ITEMS_IN_LINE = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);

        TableLayout image_table = findViewById(R.id.image_table);


        for (int i = 0; i < Math.ceil((float) PHOTO_COUNT / ITEMS_IN_LINE); i++) {
            TableRow tableRow = new TableRow(this);
            tableRow.setWeightSum(3);

            for (int j = 0; j < ITEMS_IN_LINE; j++) {
                int pos = i * ITEMS_IN_LINE + j;
                if (pos < PHOTO_COUNT) {
                    LinearLayout inflate = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.image_view, null);
                    tableRow.addView(inflate);
                }
            }
            image_table.addView(tableRow);
        }
    }
}
