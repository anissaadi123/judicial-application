package com.example.projecttdm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.IOError

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var appDb = AppDatabase.getDatabase(this.applicationContext)

        var test = Arret(0,"123456","123456",true)
        GlobalScope.launch (Dispatchers.Main){
             appDb.arretDao().insert(test)
        }
        setContentView(R.layout.activity_main)

        window.decorView.layoutDirection = View.LAYOUT_DIRECTION_RTL

        replaceFragment(Home())

        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> replaceFragment(Home())
                R.id.favori -> replaceFragment(Favori())
                R.id.json -> replaceFragment(Json())

            }
            true
        }


    }

    private fun replaceFragment(fragment:Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }

}