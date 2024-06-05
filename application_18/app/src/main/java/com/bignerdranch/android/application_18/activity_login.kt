package com.bignerdranch.android.application_18

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class activity_login : AppCompatActivity() {
    lateinit var login: EditText
    lateinit var password: EditText
    lateinit var pref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        login = findViewById(R.id.login)
        password = findViewById(R.id.password)
        findViewById<View>(R.id.loading_button).setOnClickListener{
            AlertDialog.Builder(this)
                .setMessage("Загрузить данные?")
                .setPositiveButton("Да") { dialog, which ->
                    pref = getPreferences(MODE_PRIVATE)
                    login.setText(pref.getString("login",""))
                    password.setText(pref.getString("password",""))
                }
                .setNegativeButton("Нет") { dialog, which ->
                    dialog.dismiss()
                }
                .show()
        }
    }

    fun handler(view: View) {
        if (view.getId() == R.id.save_button){
            pref = getPreferences(MODE_PRIVATE)
            var ed = pref.edit()
            ed.putString("login",login.getText().toString())
            ed.putString("password",password.getText().toString())
            ed.apply()
        }
    }

    fun next_screen(view: View) {
        if (!login.text.toString().isNullOrEmpty() || !password.text.toString().isNullOrEmpty())
        {
            val intent = Intent(this,activity_task::class.java)
            startActivity(intent)
        }
        else
        {
            val alert = AlertDialog.Builder(this)
            alert.setTitle("Ошибка")
            alert.setMessage("У вас незаполненные поля")
            alert.setPositiveButton("ОК",null)
            alert.create()
            alert.show()

        }
    }
}