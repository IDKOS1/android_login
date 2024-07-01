package com.example.loginapp.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.loginapp.Class.User
import com.example.loginapp.Class.UserData
import com.example.loginapp.R

class SignInActivity : AppCompatActivity() {
    private lateinit var userId: EditText
    private lateinit var userPassword: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.signin_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        userId = findViewById(R.id.et_id)
        userPassword = findViewById(R.id.et_password)

        val getSignUp =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == RESULT_OK) {
                    val user = it.data?.getParcelableExtra("user", User::class.java)
                    if (user != null) {
                        userId.setText(user.id)
                        userPassword.setText(user.password)
                    }
                    if(UserData.userList.isNotEmpty()) {
                        val singleTone = findViewById<TextView>(R.id.singleTone)
                        singleTone.text = UserData.userList.toString()
                    }
                }
            }


        val signInButton = findViewById<Button>(R.id.bt_signin)
        val signUpButton = findViewById<Button>(R.id.bt_signup)


        // 로그인 버튼
        signInButton.setOnClickListener {
            val id = userId.text.toString()
            val password = userPassword.text.toString()
            if (id.isBlank() || password.isEmpty()) {
                showToast("아이디 혹은 비밀번호를 입력해 주세요.")
            } else if (id in UserData.userList) {
                if (UserData.userList[id]?.password == password) {
                    val intent = Intent(this, HomeActivity::class.java)
                    intent.putExtra("userData", UserData.userList[id])
                    startActivity(intent)
                } else {
                    showToast("비밀번호가 일치하지 않습니다.")
                }
            } else {
                showToast("아이디 혹은 비밀번호를 확인해 주세요.")
            }
        }

        // 회원 가입 버튼
        signUpButton.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            getSignUp.launch(intent)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


}