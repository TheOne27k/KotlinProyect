package com.cibertec.cibertecapp.menu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.cibertec.cibertecapp.R
import com.cibertec.cibertecapp.news.NewsFragment
import com.cibertec.cibertecapp.profile.ProfileFragment
import com.cibertec.cibertecapp.subject.SubjectFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MenuActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val nav_view = findViewById<BottomNavigationView>(R.id.nav_view)
        nav_view.setOnItemSelectedListener {
            when(it.itemId){
                R.id.itemNews -> {
                    val fragment = NewsFragment.newInstance()
                    openFragment(fragment)
                    true
                }
                R.id.itemSubjects -> {
                    val fragment = SubjectFragment.newInstance()
                    openFragment(fragment)
                    true
                }
                R.id.itemProfile -> {
                    val fragment = ProfileFragment.newInstance()
                    openFragment(fragment)
                    true
                }
                else -> false
            }
        }
        nav_view.selectedItemId = R.id.itemNews
    }
    fun openFragment(fragment:Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_menu, fragment)
        transaction.commit()
    }
}