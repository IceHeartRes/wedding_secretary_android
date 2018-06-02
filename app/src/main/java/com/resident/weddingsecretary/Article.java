package com.resident.weddingsecretary;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Article extends AppCompatActivity {

    public static final int PHOTO_COUNT = 16;
    public static final int ITEMS_IN_LINE = 3;

    public static final String ARTICLE_NAME = "name";

    public   static void start(Context context, String name){
      Intent intent = new Intent(context, Article.class);
      intent.putExtra(ARTICLE_NAME, name);
      context.startActivity(intent);
  }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        String name = getIntent().getStringExtra(ARTICLE_NAME);
        TextView articleHeader = findViewById(R.id.atricle_header);
        articleHeader.setText(name);

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
