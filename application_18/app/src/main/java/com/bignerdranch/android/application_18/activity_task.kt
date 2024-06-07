package com.bignerdranch.android.application_18

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView

class activity_task : AppCompatActivity() {
    lateinit var add_task_button: Button
    lateinit var listView: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)
        add_task_button = findViewById(R.id.button)
        listView = findViewById(R.id.listView)


        add_task_button.setOnClickListener {
            onclick_add_task_button()
        }

        zagruska()
    }

    private fun onclick_add_task_button() {
        val intent = Intent(this, activity_add_task::class.java)
        startActivityForResult(intent, 1)
    }




    fun next_screen2(view: activity_task) {
        val intent = Intent(this,activity_add_task::class.java)
        startActivity(intent)
    }

    private fun zagruska() {
        val sharedPreferences = getSharedPreferences("tasks", Context.MODE_PRIVATE)
        val tasksSize = sharedPreferences.getInt("tasks_size", 0)
        val tasks = mutableListOf<String>()
        for (i in 0 until tasksSize) {
            sharedPreferences.getString("task_$i", "")?.let { tasks.add(it) }
        }
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tasks)
        listView.adapter = adapter
    }

    fun dovabit_zadachy(taskItem: String) {
        val adapter = listView.adapter as ArrayAdapter<String>
        adapter.add(taskItem)
        val tasks = mutableListOf<String>()
        for (i in 0 until adapter.count) {
            adapter.getItem(i)?.let { tasks.add(it) }
        }
        soxranit(tasks)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK) {
            val taskItem = data?.getStringExtra("task_item")
            if (taskItem != null) {
                dovabit_zadachy(taskItem)
            }
        }
    }

    private fun soxranit(tasks: MutableList<String>) {
        val sharedPreferences = getSharedPreferences("tasks", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("tasks_size", tasks.size)
        for (i in 0 until tasks.size) {
            editor.putString("task_$i", tasks[i])
        }
        editor.apply()
    }
}