package com.example.leave_application_nmims;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Faculty1 extends AppCompatActivity {
    private RecyclerView recyclerView;
    private HolidayAdapter adapter;
    private List<HolidayItem> holidayList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty1);

        recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        prepareHolidayData();
        adapter = new HolidayAdapter(holidayList);
        recyclerView.setAdapter(adapter);

    }

    private void prepareHolidayData() {
        holidayList = new ArrayList<>();


        // Add holiday items
        holidayList.add(new HolidayItem("January", getAllJanuaryHolidays()));

    }

    private String formatHoliday(String date, String name) {
        return date + " - " + name;
    }
    private String getAllJanuaryHolidays() {
        return formatHoliday("1st January", "New Year's Day") + "\n" +
                formatHoliday("14th January", "Pongal/Makar Sankranti") + "\n" +
                formatHoliday("26th January", "Republic Day (National Holiday)");
    }
}