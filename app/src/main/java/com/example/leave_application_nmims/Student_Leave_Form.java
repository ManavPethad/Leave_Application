package com.example.leave_application_nmims;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.leave_application_nmims.R;
import com.example.leave_application_nmims.RandomKeyGenerator;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Student_Leave_Form extends AppCompatActivity {
    EditText e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15;
    Button b1;
    CheckBox c1, c2, c3, c4;
    Spinner sp;
    DatabaseReference leaveapp;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_leave_form);
        e1 = findViewById(R.id.name);
        e2 = findViewById(R.id.sap_id);
        e3 = findViewById(R.id.program);
        e4 = findViewById(R.id.semister);
        e5 = findViewById(R.id.hostelroom);
        e6 = findViewById(R.id.selfmail);
        e7 = findViewById(R.id.parentsmail);
        e8 = findViewById(R.id.studentphone);
        e9 = findViewById(R.id.parentsphone);
        e10 = findViewById(R.id.attendance);
        e11 = findViewById(R.id.days);
        e12 = findViewById(R.id.datefrom);
        e13 = findViewById(R.id.dateto);
        e14 = findViewById(R.id.academicdays);
        e15 = findViewById(R.id.reason);
        b1 = findViewById(R.id.submit);
        c1 = findViewById(R.id.sick_leave_checkbox);
        c2 = findViewById(R.id.one_day_leave_checkbox);
        c3 = findViewById(R.id.casual_leave_checkbox);
        c4 = findViewById(R.id.parents_mailed_checkbox);
        sp = findViewById(R.id.names_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.names_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateForm()) {
                    String name, sap_id, program, semister, hostelroom, selfmail, parentsmail, studentphone, parentsphone, attendance, days, datefrom;
                    String dateto, academicdays, reason, faculty;
                    name = e1.getText().toString();
                    sap_id = e2.getText().toString();
                    program = e3.getText().toString();
                    semister = e4.getText().toString();
                    hostelroom = e5.getText().toString();
                    selfmail = e6.getText().toString();
                    parentsmail = e7.getText().toString();
                    studentphone = e8.getText().toString();
                    parentsphone = e9.getText().toString();
                    attendance = e10.getText().toString();
                    days = e11.getText().toString();
                    datefrom = e12.getText().toString();
                    dateto = e13.getText().toString();
                    academicdays = e14.getText().toString();
                    reason = e15.getText().toString();
                    faculty = sp.getSelectedItem().toString();

                    // Create a reference to the Firebase Realtime Database
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference ref = database.getReference("leave_applications");
                    // Push the data to Firebase
                    String key = RandomKeyGenerator.generateRandomKey();
                    ref.child(key).child("name").setValue(name);
                    ref.child(key).child("sap_id").setValue(sap_id);
                    ref.child(key).child("program").setValue(program);
                    ref.child(key).child("semister").setValue(semister);
                    ref.child(key).child("hostelroom").setValue(hostelroom);
                    ref.child(key).child("selfmail").setValue(selfmail);
                    ref.child(key).child("parentsmail").setValue(parentsmail);
                    ref.child(key).child("studentphone").setValue(studentphone);
                    ref.child(key).child("parentsphone").setValue(parentsphone);
                    ref.child(key).child("attendance").setValue(attendance);
                    ref.child(key).child("days").setValue(days);
                    ref.child(key).child("datefrom").setValue(datefrom);
                    ref.child(key).child("dateto").setValue(dateto);
                    ref.child(key).child("academicdays").setValue(academicdays);
                    ref.child(key).child("reason").setValue(reason);
                    ref.child(key).child("faculty").setValue(faculty);
                    Toast.makeText(Student_Leave_Form.this, "Submitted", Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(Student_Leave_Form.this, Main_Home_Page_Dashboard.class);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean validateForm() {
        // Validate each input field according to specified criteria
        String name = e1.getText().toString().trim();
        String sapId = e2.getText().toString().trim();
        String program = e3.getText().toString().trim();
        String semester = e4.getText().toString().trim();
        String hostelRoomNo = e5.getText().toString().trim();
        String selfEmail = e6.getText().toString().trim();
        String parentsEmail = e7.getText().toString().trim();
        String studentPhone = e8.getText().toString().trim();
        String parentsPhone = e9.getText().toString().trim();
        String attendance = e10.getText().toString().trim();
        String totalDays = e11.getText().toString().trim();
        String fromDate = e12.getText().toString().trim();
        String toDate = e13.getText().toString().trim();
        String academicDays = e14.getText().toString().trim();
        String reason = e15.getText().toString().trim();

        // Perform validation checks
        if (name.isEmpty() || !name.matches("[a-zA-Z c]+")) {
            Toast.makeText(this, "Invalid name", Toast.LENGTH_SHORT).show();
            return false;
        } else if (sapId.length() != 11 || !sapId.matches("\\d+")) {
            Toast.makeText(this, "Invalid SAP ID", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!program.equalsIgnoreCase("BTech") && !program.equalsIgnoreCase("MBATech")) {
            Toast.makeText(this, "Program should be BTech or MBATech", Toast.LENGTH_SHORT).show();
            return false;
        } else if (semester.isEmpty() || !semester.matches("[1-7]")) {
            Toast.makeText(this, "Invalid semester", Toast.LENGTH_SHORT).show();
            return false;
        } else if (hostelRoomNo.isEmpty() || hostelRoomNo.length() < 3 || hostelRoomNo.length() > 4 || !hostelRoomNo.matches("\\d+")) {
            Toast.makeText(this, "Invalid hostel room no", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!selfEmail.endsWith("@gmail.com") || !parentsEmail.endsWith("@gmail.com")) {
            Toast.makeText(this, "Email IDs should end with @gmail.com", Toast.LENGTH_SHORT).show();
            return false;
        } else if (studentPhone.isEmpty() || studentPhone.length() != 10 || !studentPhone.matches("\\d+")) {
            Toast.makeText(this, "Invalid student phone number", Toast.LENGTH_SHORT).show();
            return false;
        } else if (parentsPhone.isEmpty() || parentsPhone.length() != 10 || !parentsPhone.matches("\\d+")) {
            Toast.makeText(this, "Invalid parents phone number", Toast.LENGTH_SHORT).show();
            return false;
        } else if (attendance.isEmpty() || !attendance.matches("\\d+")) {
            Toast.makeText(this, "Invalid attendance", Toast.LENGTH_SHORT).show();
            return false;
        } else if (totalDays.isEmpty() || !totalDays.matches("\\d+")) {
            Toast.makeText(this, "Invalid total days", Toast.LENGTH_SHORT).show();
            return false;
        } else if (fromDate.isEmpty() || toDate.isEmpty() || !fromDate.matches("\\d{2}/\\d{2}/\\d{2}") || !toDate.matches("\\d{2}/\\d{2}/\\d{2}")) {
            Toast.makeText(this, "Invalid date format (DD/MM/YY)", Toast.LENGTH_SHORT).show();
            return false;
        } else if (academicDays.isEmpty() || !academicDays.matches("\\d+")) {
            Toast.makeText(this, "Invalid academic days", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!c1.isChecked() && !c2.isChecked() && !c3.isChecked()) {
            Toast.makeText(this, "Select at least one type of leave", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!c4.isChecked()) {
            Toast.makeText(this, "Parents must have mailed to mentor", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!(c1.isChecked() ^ c2.isChecked() ^ c3.isChecked())) {
            Toast.makeText(this, "Select only one type of leave", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true; // Form is valid
    }
}
