package io.wentz.studentslist.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import io.wentz.studentslist.R
import io.wentz.studentslist.models.Student

class StudentFormActivity : AppCompatActivity() {
    private lateinit var nameField: EditText
    private lateinit var phoneField: EditText
    private lateinit var emailField: EditText
    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_form)

        nameField = findViewById(R.id.student_form_name)
        phoneField = findViewById(R.id.student_form_phone)
        emailField = findViewById(R.id.student_form_email)

        saveButton = findViewById(R.id.student_form_create_button)
        saveButton.setOnClickListener {
            val name = nameField.text.toString()
            val phone = phoneField.text.toString()
            val email = emailField.text.toString()

            val student = Student(name, phone, email)

            Toast.makeText(this, student.toString(), Toast.LENGTH_LONG).show()
        }
    }
}