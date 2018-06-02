package com.resident.weddingsecretary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Articles extends AppCompatActivity {


    public static final List<String> STRINGS;

    static {
        STRINGS = new ArrayList<>();
        STRINGS.add("Статья 1.Статья 1.Статья 1.Статья 1.");
        STRINGS.add("Статья 2.Статья 2.Статья 2.Статья 2.");
        STRINGS.add("Статья 3.Статья 3.Статья 3.Статья 3.");
        STRINGS.add("Статья 4.Статья 4.Статья 4.Статья 4.");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);

        LinearLayout articleList = findViewById(R.id.article_list);

        for (final String articleHeader : STRINGS) {
            LinearLayout inflate = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.article_item, null);
            TextView text = inflate.findViewById(R.id.article_header);
            text.setText(articleHeader);

            ImageView image = inflate.findViewById(R.id.article_image);
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Article.start(Articles.this, articleHeader);
                }
            });
            articleList.addView(inflate);
        }

    }

}
