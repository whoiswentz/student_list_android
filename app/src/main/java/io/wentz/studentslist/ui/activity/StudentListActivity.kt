package io.wentz.studentslist.ui.activity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView.AdapterContextMenuInfo
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.wentz.studentslist.R
import io.wentz.studentslist.daos.StudentDAO
import io.wentz.studentslist.models.Student

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
class StudentListActivity : AppCompatActivity() {
    private lateinit var actionBar: Toolbar
    private lateinit var studentListView: ListView
    private lateinit var createStudentFab: FloatingActionButton
    private lateinit var listAdapter: ArrayAdapter<Student>

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

        configureListAdapter(studentListView)
        configureStudentListView()
        configureStudentListLongClick()
        registerForContextMenu(studentListView)
    }

    override fun onResume() {
        super.onResume()
        listAdapter.clear()
        listAdapter.addAll(studentDao.all())
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.activity_student_list_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.activity_student_list_menu_remove -> {
                val menuItem = item.menuInfo as AdapterContextMenuInfo
                val student = listAdapter.getItem(menuItem.position)
                removeStudent(student!!)
            }
        }
        return super.onContextItemSelected(item)
    }

    private fun configureListAdapter(studentList: ListView) {
        listAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1)
        studentListView.adapter = listAdapter
    }

    private fun configureStudentListLongClick() {
        studentListView.setOnItemLongClickListener { parent, view, position, id ->
            val student = parent.getItemAtPosition(position) as Student
            false
        }
    }

    private fun removeStudent(student: Student) {
        studentDao.remove(student)
        listAdapter.remove(student)
    }

    private fun configureStudentListView() {
        studentListView.setOnItemClickListener { parent, view, position, id ->
            val student = parent.getItemAtPosition(position) as Student
            val studentFormIntent = Intent(this, StudentFormActivity::class.java)
            studentFormIntent.putExtra("student", student)
            startActivity(studentFormIntent)
        }
    }
}