package io.wentz.studentslist.ui.activity

import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView.AdapterContextMenuInfo
import android.widget.ListView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.wentz.studentslist.R
import io.wentz.studentslist.daos.StudentDAO
import io.wentz.studentslist.models.Student
import io.wentz.studentslist.ui.adapter.CustomAdapter

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
class StudentListActivity : AppCompatActivity() {
    private lateinit var actionBar: Toolbar
    private lateinit var listAdapter: CustomAdapter

    private val studentDao = StudentDAO()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)

        actionBar = findViewById(R.id.my_toolbar)
        actionBar.title = "Student List"
        setSupportActionBar(actionBar)

        configureFab()
        configureList()

        studentDao.save(Student(name = "AAA", phone = "BBBB", email = "CCCC"))
    }

    private fun configureList() {
        val studentListView: ListView = findViewById(R.id.student_list_view)
        configureListAdapter(studentListView)
        configureStudentListView(studentListView)
        registerForContextMenu(studentListView)
    }

    private fun configureFab() {
        val createStudentFab: FloatingActionButton = findViewById(R.id.create_student_fab)
        createStudentFab.setOnClickListener {
            startActivity(Intent(this, StudentFormActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        listAdapter.notifyDataSetChanged()
        listAdapter.clear()
        listAdapter.addAll(studentDao.all())
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.activity_student_list_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.activity_student_list_menu_remove) {
            val menuItem = item.menuInfo as AdapterContextMenuInfo
            val student = listAdapter.getItem(menuItem.position)

            AlertDialog.Builder(this).setTitle("Remove Student")
                .setMessage("Want to remove ${student.name}?")
                .setPositiveButton("Yes") { dialog, which ->
                    removeStudent(student)
                }
                .setNegativeButton("No", null)
                .show()

        }
        return super.onContextItemSelected(item)
    }

    private fun configureListAdapter(studentList: ListView) {
        listAdapter = CustomAdapter(this)
        studentList.adapter = listAdapter
    }

    private fun removeStudent(student: Student) {
        listAdapter.notifyDataSetChanged()
        studentDao.remove(student)
        listAdapter.remove(student)
    }

    private fun configureStudentListView(listView: ListView) {
        listView.setOnItemClickListener { parent, view, position, id ->
            val student = parent.getItemAtPosition(position) as Student
            val studentFormIntent = Intent(this, StudentFormActivity::class.java)
            studentFormIntent.putExtra("student", student)
            startActivity(studentFormIntent)
        }
    }
}

