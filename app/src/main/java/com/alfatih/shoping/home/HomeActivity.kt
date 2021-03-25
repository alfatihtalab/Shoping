package com.alfatih.shoping.home
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.alfatih.shoping.R
import com.alfatih.shoping.auth.UserFirebase
import com.alfatih.shoping.databinding.ActivityHomeBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    var TIME = 5000 //5000 ms (5 Seconds)
    val authUI = FirebaseAuth.getInstance(FirebaseApp.getInstance())

    // Access a Cloud Firestore instance from your Activity
    val db = Firebase.firestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        //val fragmentTest = FragmentTest()

        val finalHost = NavHostFragment.create(R.navigation.home_nav)
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, finalHost)
            .setPrimaryNavigationFragment(finalHost) // equivalent to app:defaultNavHost="true"
            .commit()

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
        R.id.action_setting ->{
           findNavController(R.id.nav_host_fragment)
               .navigate(R.id.settingFragment)
            true
        }
        R.id.action_sign_out -> {
            val userFirebase = UserFirebase()

            userFirebase.signOut(this,this)
            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }


}