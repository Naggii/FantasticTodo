package com.example.fantastictodo

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import com.example.fantastictodo.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    private val todoItemFragment = TodoItemFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val toolbar: Toolbar = binding.toolbar
        toolbar.title = ""
        setSupportActionBar(toolbar)

        val drawer = binding.drawerLayout
        val toggle = ActionBarDrawerToggle(
            this, drawer, toolbar,
            R.string.drawer_open,
            R.string.drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = binding.navigationView
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_todo -> {
                // 二重追加させない
                if (todoItemFragment.isAdded) {
                    val drawer = binding.drawerLayout
                    drawer.closeDrawer(GravityCompat.START)
                    return true
                }
                binding.debugText.isVisible = false
                val transaction = supportFragmentManager.beginTransaction()
                transaction.add(R.id.transition_fragment, todoItemFragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
            R.id.menu_tasks -> {
                // TASK List追加
                Log.d(TAG, "Item 2 Selected!")
            }
            R.id.menu_schedule -> {
                // スケジュール追加
                Log.d(TAG, "Item 3 Selected!")
            }
        }
        val drawer = binding.drawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    companion object {
        var TAG = "MainActivity"
    }
}