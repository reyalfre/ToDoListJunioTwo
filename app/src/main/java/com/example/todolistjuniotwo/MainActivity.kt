package com.example.todolistjuniotwo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import com.example.todolistjuniotwo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.action_home -> {
                    val fragmentTrans = supportFragmentManager.beginTransaction()
                    fragmentTrans.replace(R.id.containerFragment, HomeFragment())
                    fragmentTrans.commit()
                    true

                }

                R.id.action_categoria -> {
                    val fragmentTrans = supportFragmentManager.beginTransaction()
                    fragmentTrans.replace(R.id.containerFragment, CategoriaFragment())
                    fragmentTrans.commit()
                    true
                    /*
                    supportFragmentManager.commit{
                        setReorderingAllowed(true)
                        replace<CategoriaFragment>(R.id.containerFragment)
                        }
                        true
                    */

                }
                else-> false
            }
        }

    }
}