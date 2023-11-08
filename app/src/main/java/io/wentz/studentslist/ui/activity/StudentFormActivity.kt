package io.wentz.studentslist.ui.activity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import io.wentz.studentslist.R
import io.wentz.studentslist.daos.StudentDAO
import io.wentz.studentslist.models.Student

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
class StudentFormActivity : AppCompatActivity() {
    private lateinit var nameField: EditText
    private lateinit var phoneField: EditText
    private lateinit var emailField: EditText
    private lateinit var saveButton: Button
    private lateinit var actionBar: Toolbar

    private val studentDao = StudentDAO()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_form)

        nameField = findViewById(R.id.student_form_name)
        phoneField = findViewById(R.id.student_form_phone)
        emailField = findViewById(R.id.student_form_email)
        actionBar = findViewById(R.id.my_toolbar)

        val student = intent.getSerializableExtra("student", Student::class.java)
        if (student != null) {
            actionBar.title = "Update student"
            nameField.setText(student.name)
            phoneField.setText(student.phone)
            emailField.setText(student.email)
        } else {
            actionBar.title = "Create new student"
            nameField.setText("")
            phoneField.setText("")
            emailField.setText("")
        }

        setSupportActionBar(actionBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupSaveButton()
    }
    
    private fun setupSaveButton() {
        saveButton = findViewById(R.id.student_form_create_button)
        saveButton.setOnClickListener {
            saveStudent()
            finish()
        }
    }

    private fun saveStudent() {
        val name = nameField.text.toString()
        val phone = phoneField.text.toString()
        val email = emailField.text.toString()

        val student = intent.getSerializableExtra("student", Student::class.java)
        val studentToSave = if (student != null) {
            Student(id = student.id, name = name, phone = phone, email = email)
        } else {
            Student(name = name, phone = phone, email = email)
        }

        studentDao.save(studentToSave)
    }
}