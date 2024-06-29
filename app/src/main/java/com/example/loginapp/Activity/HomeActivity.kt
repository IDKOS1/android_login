package com.example.loginapp.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.loginapp.Class.User
import com.example.loginapp.R

class HomeActivity : AppCompatActivity() {

    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val imageList = listOf(
            R.drawable.ggom2,
            R.drawable.ggom3,
            R.drawable.ggom4,
            R.drawable.ggom5,
            R.drawable.ggom6
        )

        val mainImage = findViewById<ImageView>(R.id.imageView)
        val randomImage = imageList.random()
        mainImage.setImageResource(randomImage)

        val user = intent.getParcelableExtra("userData", User::class.java)

        val profileId = findViewById<TextView>(R.id.tv_id)
        val profileName = findViewById<TextView>(R.id.tv_name)
        val profileAge = findViewById<TextView>(R.id.tv_age)
        val profileNickname = findViewById<TextView>(R.id.tv_nickname)

        profileId.text = user?.id.toString()
        profileName.text = user?.name
        profileAge.text = user?.age.toString()
        profileNickname.text = user?.nickname

        val goBack = findViewById<Button>(R.id.bt_goback)

        goBack.setOnClickListener {
            finish()
        }
    }
}