package com.resident.weddingsecretary;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.util.Locale;

public class CalendarEvents extends AppCompatActivity {
    public static final String EVENTS = "events";

    public static void start(Context context, List<CalendarEvent> events) {
        Intent intent = new Intent(context, CalendarEvents.class);
        intent.putExtra(EVENTS, new Gson().toJson(events));
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_events);

        String eventJson = getIntent().getStringExtra(EVENTS);

        Type listType = new TypeToken<ArrayList<CalendarEvent>>() {
        }.getType();
        List<CalendarEvent> events = new Gson().fromJson(eventJson, listType);

        TextView date = findViewById(R.id.date);
        SimpleDateFormat month_date = new SimpleDateFormat("План на dd MMMM", Locale.getDefault());
        String planDate = month_date.format(events.get(0).getTime());
        date.setText(planDate);

        LinearLayout eventsList = findViewById(R.id.event_list);

        for (final CalendarEvent event : events) {
            LinearLayout inflate = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.event_item, null);
            ((TextView) inflate.findViewById(R.id.event_type)).setText(event.getType());

            TextView time = inflate.findViewById(R.id.event_time);
            time.setText(DateUtils.formatDateTime(this,
                    event.getTime(),
                    DateUtils.FORMAT_SHOW_TIME));

            ((TextView) inflate.findViewById(R.id.event_address)).setText(event.getAddress());
            ((TextView) inflate.findViewById(R.id.event_comment)).setText(event.getComment());

            eventsList.addView(inflate);
        }

    }
}
