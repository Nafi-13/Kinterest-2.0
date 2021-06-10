package com.example.tutorials

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_profile.*


class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        bottomNavigationView2.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.miHome -> {
                    Intent(this,MainActivity::class.java).also { startActivity(it) }

                }

                R.id.miProfile ->{
                    Intent(this,ProfileActivity::class.java).also { startActivity(it) }

                }
            }
            true
        }




    }

}