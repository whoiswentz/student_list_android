package io.wentz.studentslist.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.widget.Toolbar
import io.wentz.studentslist.R

class StudentListActivity : AppCompatActivity() {
    private lateinit var actionBar: Toolbar
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_student_list)

        actionBar = findViewById(R.id.my_toolbar)
        actionBar.title = "Student List"
        setSupportActionBar(actionBar)

        val students = arrayOf("Student 1", "Student 2", "Student 3")
        listView = findViewById(R.id.list_view)
        listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, students)
    }
}