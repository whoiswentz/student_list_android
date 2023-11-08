package io.wentz.studentslist.daos

import io.wentz.studentslist.models.Student
import java.util.UUID

class StudentDAO {
    companion object {
        private val students = HashMap<UUID, Student>()
    }

    fun save(student: Student) {
        students[student.id] = student
    }

    fun all(): List<Student> = students.values.toList()

    fun get(i: Int) = students.values.toList()[i]
}