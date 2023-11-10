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
        var view = convertView
        val viewHolder: ViewHolder

        if (view == null) {
            view = LayoutInflater
                .from(context)
                .inflate(R.layout.item_student, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            viewHolder = view.tag as ViewHolder
        }

        val student = getItem(position)

        viewHolder.itemStudentName.text = student.name
        viewHolder.itemStudentPhone.text = student.phone

        return view!!
    }

    fun clear() = items.clear()
    fun addAll(students: List<Student>) = items.addAll(students)
    fun remove(student: Student) = items.remove(student)

    private class ViewHolder(view: View) {
        val itemStudentName: TextView = view.findViewById(R.id.item_student_name)
        val itemStudentPhone: TextView = view.findViewById(R.id.item_student_phone)
    }
}