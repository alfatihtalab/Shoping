package com.alfatih.shoping.home

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.SearchEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import com.alfatih.shoping.R
import com.alfatih.shoping.databinding.ActivityHomeBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHomeBinding

    var TIME = 5000 //5000 ms (5 Seconds)
    val authUI = FirebaseAuth.getInstance(FirebaseApp.getInstance())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        val fragmentTest = FragmentTest()



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val searchItem = menu?.findItem(R.id.action_search)
        //val searchView = searchItem?.actionView as SearchView
        // Define the listener
        val expandListener = object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
                // Do something when action item collapses
                return true // Return true to collapse action view
            }

            override fun onMenuItemActionExpand(item: MenuItem): Boolean {
                // Do something when expanded
                return true // Return true to expand action view
            }
        }

        // Get the MenuItem for the action item
        val actionMenuItem = menu?.findItem(R.id.action_shopping)

        // Assign the listener to that action item
        actionMenuItem?.setOnActionExpandListener(expandListener)

        // Any other things you have to do when creating the options menu...

        return true

    }
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_shopping -> {
            // User chose the "Settings" item, show the app settings UI...
            true
        }

        R.id.action_search -> {
            // User chose the "Favorite" action, mark the current item
            // as a favorite...
            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }

}