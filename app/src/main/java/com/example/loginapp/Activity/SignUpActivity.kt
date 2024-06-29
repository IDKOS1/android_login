package com.example.loginapp.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.loginapp.Class.User
import com.example.loginapp.Class.UserData
import com.example.loginapp.R

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.signup)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val username = findViewById<EditText>(R.id.et_username)
        val userid = findViewById<EditText>(R.id.et_id)
        val password = findViewById<EditText>(R.id.et_password)
        val nickname = findViewById<EditText>(R.id.et_mbti)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val goLogin = findViewById<TextView>(R.id.tv_login)
        val signupButton = findViewById<TextView>(R.id.bt_signup)

        val numberPicker = findViewById<NumberPicker>(R.id.number_picker)
        numberPicker.maxValue = 100
        numberPicker.wrapSelectorWheel

        var gender: Boolean? = null
        // 성별 선택시 gender 변수 변경
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radioMale -> {
                    // "남"이 선택된 경우
                    gender = true
                }

                R.id.radioFemale -> {
                    // "여"가 선택된 경우
                    gender = false
                }
            }
        }

        signupButton.setOnClickListener {
            if (userid.text.toString().isEmpty() ||
                password.text.toString().isEmpty() ||
                nickname.text.toString().isEmpty() ||
                username.text.toString().isEmpty()
                ) {
                showToast("미입력된 정보가 있습니다.")
            } else if (gender == null) {
                showToast("성별을 선택해주세요.")
            } else if (numberPicker.value == 0) {
                showToast("나이를 선택해 주세요.")
            } else {
                val intent = Intent(this, SignInActivity::class.java)
                val user = User(
                    userid.text.toString(),
                    password.text.toString(),
                    username.text.toString(),
                    numberPicker.value,
                    gender!!,
                    nickname.text.toString()
                )
                UserData.userList[userid.text.toString()] = user
                intent.putExtra("user", user)
                setResult(RESULT_OK, intent)
                finish()
            }
        }

        goLogin.setOnClickListener {
            finish()
        }
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}