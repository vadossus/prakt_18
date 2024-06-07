package com.bignerdranch.android.application_18

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class activity_add_task : AppCompatActivity() {
    lateinit var title: EditText
    lateinit var subtitle: EditText
    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)
        title = findViewById(R.id.title)
        subtitle = findViewById(R.id.subtitle)
        button = findViewById(R.id.add_task)

        button.setOnClickListener {
            addTask()
        }
    }

    fun back_to_tasks(view: View) {
        val intent = Intent(this,activity_task::class.java)
        startActivity(intent)
    }

    private fun addTask() {
        val title_text = title.text.toString()
        val subtitle_text = subtitle.text.toString()
        val task_item = "$title_text\n$subtitle_text"
        val activityTask = Intent(this, activity_task::class.java)
        activityTask.putExtra("task_item", task_item)
        setResult(RESULT_OK, activityTask)
        Toast.makeText(this,"Задача добавлена",Toast.LENGTH_SHORT).show()
        finish()
    }
}