package com.resident.weddingsecretary;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class AddCalendarEvent extends AppCompatActivity {
    private static CompactCalendarView compactCalendar;
    private Calendar dateAndTime = Calendar.getInstance();
    private String[] data = {"Встреча", "Примерка", "Покупка", "Событие"};
    private TextView date;
    private TextView time;
    public String type = data[data.length - 1];
    TextView address;
    TextView comment;

    static void start(Context context, CompactCalendarView compactCalendar) {
        AddCalendarEvent.compactCalendar = compactCalendar;
        context.startActivity(new Intent(context, AddCalendarEvent.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        List<String> objects = Arrays.asList(data);
        HintAdapter adapter = new HintAdapter<>(this, objects, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final Spinner spinner = findViewById(R.id.event_type);
        spinner.setAdapter(adapter);
        spinner.setSelection(adapter.getCount());

//        spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                type = data[position];
//            }
//        });

        address = findViewById(R.id.address);
        comment = findViewById(R.id.comments);

        date = findViewById(R.id.date);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate(v);
            }
        });

        time = findViewById(R.id.time);
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTime(v);
            }
        });

        ImageView add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalendarEvent calendarEvent = new CalendarEvent(type, dateAndTime.getTimeInMillis(), address.getText().toString(), comment.getText().toString());
                Event ev1 = new Event(Color.RED, dateAndTime.getTimeInMillis(), calendarEvent);
                compactCalendar.addEvent(ev1);
                finish();
            }
        });
    }

    // отображаем диалоговое окно для выбора даты
    public void setDate(View v) {
        new DatePickerDialog(this, d,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    // отображаем диалоговое окно для выбора времени
    public void setTime(View v) {
        new TimePickerDialog(this, t,
                dateAndTime.get(Calendar.HOUR_OF_DAY),
                dateAndTime.get(Calendar.MINUTE), true)
                .show();
    }

    // установка обработчика выбора времени
    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateAndTime.set(Calendar.MINUTE, minute);
            time.setText(DateUtils.formatDateTime(AddCalendarEvent.this,
                    dateAndTime.getTimeInMillis(),
                    DateUtils.FORMAT_SHOW_TIME));
        }
    };

    // установка обработчика выбора даты
    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            date.setText(DateUtils.formatDateTime(AddCalendarEvent.this,
                    dateAndTime.getTimeInMillis(),
                    DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR));
        }
    };

    @Override
    public void onBackPressed() {
        compactCalendar=null;
        finish();
    }
}
