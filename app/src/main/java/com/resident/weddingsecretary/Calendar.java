package com.resident.weddingsecretary;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Calendar extends AppCompatActivity {

    private CompactCalendarView compactCalendar;
    private Date calendarDateClicked = new Date();
    private TextView eventCount;
    private static CalendarEvent calendarEvent;

    static {
        calendarEvent = new CalendarEvent("qqq", new Date().getTime(), "улица", "комментарий");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        compactCalendar = findViewById(R.id.calendar);
        compactCalendar.setUseThreeLetterAbbreviation(true);

        Event ev1 = new Event(Color.RED, new Date().getTime() - 86400000L, calendarEvent);
        compactCalendar.addEvent(ev1);

        eventCount = findViewById(R.id.event_count);
        initEvents(new Date());

        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                calendarDateClicked = dateClicked;
                initEvents(dateClicked);
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
            }
        });

        ImageView addEvent = findViewById(R.id.add_event);
        addEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddCalendarEvent.start(Calendar.this, compactCalendar);
            }
        });

        ImageView viewEvent = findViewById(R.id.view_event);
        viewEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Event> events = compactCalendar.getEvents(calendarDateClicked);
                List<CalendarEvent> calendarEvents = new ArrayList<>();
                for (Event event : events) {
                    calendarEvents.add((CalendarEvent) event.getData());
                }

                CalendarEvents.start(Calendar.this, calendarEvents);
            }
        });
    }

    private void initEvents(Date dateClicked) {
        int size = compactCalendar.getEvents(dateClicked).size();
        View viewById = findViewById(R.id.view_event);
        if (size > 0) {
            eventCount.setText(String.format("Назначено событий: %s", size));
            viewById.setVisibility(View.VISIBLE);
        } else {
            eventCount.setText("Событий не назначено");
            viewById.setVisibility(View.GONE);
        }
    }

}
