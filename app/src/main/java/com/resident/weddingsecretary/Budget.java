package com.resident.weddingsecretary;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.realm.Realm;
import io.realm.RealmResults;

public class Budget extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

        Realm mRealm = Realm.getInstance(this);
        RealmResults<BudgetItem> budgetItems = mRealm.allObjects(BudgetItem.class);

//        Realm mRealm = Realm.getInstance(this);
//        mRealm.beginTransaction();
//
//        MyBook book = mRealm.createObject(MyBook.class);
//
//        book.setTitle(getTrimmedTitle());
//
//        mRealm.commitTransaction();


        LinearLayout budgetListView = findViewById(R.id.budget_list);
        List<String> sections = getSections(budgetItems);
        for (final String section : sections) {
            LinearLayout budgetSectionView = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.budget_section, null);

            TextView header = budgetSectionView.findViewById(R.id.budget_header);
            header.setText(section);

            List<BudgetItem> budgetItemList = selectForSection(budgetItems, section);
            for (BudgetItem budgetItem : budgetItemList) {
                LinearLayout budgetItemView = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.budget_item, null);

                TextView description = budgetItemView.findViewById(R.id.description);
                description.setText(budgetItem.getDescription());

                TextView plan = budgetItemView.findViewById(R.id.plan);
                description.setText(budgetItem.getDescription());

                TextView fact = budgetItemView.findViewById(R.id.fact);
                description.setText(budgetItem.getDescription());
            }

            budgetListView.addView(budgetSectionView);
        }
    }

    @NonNull
    private List<BudgetItem> selectForSection(RealmResults<BudgetItem> budgetItems, String section) {
        List<BudgetItem> budgetItemList = new ArrayList<>();
        for (final BudgetItem budgetItem : budgetItems) {
            if (Objects.equals(section, budgetItem.getSection())) {
                budgetItemList.add(budgetItem);
            }
        }
        return budgetItemList;
    }

    private List<String> getSections(RealmResults<BudgetItem> budgetItems) {
        List<String> sections = new ArrayList<>();
        for (final BudgetItem budgetItem : budgetItems) {
            String section = budgetItem.getSection();
            if (!sections.contains(section)) {
                sections.add(section);
            }
        }
        return sections;
    }
}
