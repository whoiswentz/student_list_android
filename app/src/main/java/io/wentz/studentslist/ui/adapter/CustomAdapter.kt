package io.wentz.studentslist.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import io.wentz.studentslist.R
import io.wentz.studentslist.models.Student

class CustomAdapter(
    private val context: Context
) : BaseAdapter() {
    public val items: ArrayList<Student> = arrayListOf()

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Student {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflatedView =
            LayoutInflater.from(context).inflate(R.layout.item_student, parent, false)
        val itemStudentName: TextView = inflatedView.findViewById(R.id.item_student_name)
        val itemStudentPhone: TextView = inflatedView.findViewById(R.id.item_student_phone)

        val student = getItem(position)

        itemStudentName.text = student.name
        itemStudentPhone.text = student.phone

        return inflatedView
    }

    fun clear() = items.clear()
    fun addAll(students: List<Student>) = items.addAll(students)
    fun remove(student: Student) = items.remove(student)
}