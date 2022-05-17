package com.cna.coincollection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.cna.coincollection.fragments.DashboardFragment
import com.cna.coincollection.fragments.InfoFragment
import com.cna.coincollection.fragments.SettingsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val dashboardFragment = DashboardFragment()
    private val settingsFragment = SettingsFragment()
    private val infoFragment = InfoFragment()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragment(dashboardFragment) // cand se deschide activitatea , facem inflate la fragmentul dashboard


        val bottom_navigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottom_navigation.setOnNavigationItemSelectedListener { /// Cand apasam pe un element din bottom navigation
            when(it.itemId)
            {
                R.id.ic_dashboard -> replaceFragment(dashboardFragment)
                R.id.ic_info -> replaceFragment(infoFragment)
                R.id.ic_settings -> replaceFragment(settingsFragment)
            }
            true
        }

    }

    private fun replaceFragment(fragment: Fragment)
    {
        if(fragment != null) /// Daca fragmentul exista - nu e nul
        {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container,fragment) /// Replace la fragmentul curent
            transaction.commit()
        }
    }
}