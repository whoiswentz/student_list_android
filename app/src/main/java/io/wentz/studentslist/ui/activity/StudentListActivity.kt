package io.wentz.studentslist.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.widget.Toolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.wentz.studentslist.R
import io.wentz.studentslist.daos.StudentDAO

class StudentListActivity : AppCompatActivity() {
    private lateinit var actionBar: Toolbar
    private lateinit var studentListView: ListView
    private lateinit var createStudentFab: FloatingActionButton

    private val studentDao = StudentDAO()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_student_list)
        actionBar = findViewById(R.id.my_toolbar)
        actionBar.title = "Student List"
        setSupportActionBar(actionBar)

        studentListView = findViewById(R.id.student_list_view)
        createStudentFab = findViewById(R.id.create_student_fab)

        createStudentFab.setOnClickListener {
            startActivity(Intent(this, StudentFormActivity::class.java))
        }

        configureStudentListView()
    }

    override fun onResume() {
        super.onResume()
        studentListView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, studentDao.all())
    }

    private fun configureStudentListView() {
        studentListView.setOnItemClickListener { parent, view, position, id ->
            val student = studentDao.get(position)
            val studentFormIntent = Intent(this, StudentFormActivity::class.java)
            studentFormIntent.putExtra("student", student)
            startActivity(studentFormIntent)
        }
    }
}