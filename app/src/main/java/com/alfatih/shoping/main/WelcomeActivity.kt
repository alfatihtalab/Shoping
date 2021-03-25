package com.alfatih.shoping.main

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.alfatih.shoping.R
import com.alfatih.shoping.home.HomeActivity
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class WelcomeActivity : AppCompatActivity() {
    val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        // making the status bar transparent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ) {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                // Successfully signed in
                val user = FirebaseAuth.getInstance().currentUser
                //checking the document user
                val docRef = db.collection("users").document(user!!.uid)
                docRef.get()
                    .addOnSuccessListener { document ->
                        if (document != null) {
                            Toast.makeText(applicationContext,
                                "document is found",
                                Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, HomeActivity::class.java))
                        } else {
                            Toast.makeText(applicationContext,
                                "no such document",
                                Toast.LENGTH_SHORT).show()
                            findNavController(R.id.nav_host_fragment).navigate(
                                R.id.action_welcome_to_accountTypeFragment
                            )
                        }
                    }
                    .addOnFailureListener { exception ->
                        Toast.makeText(applicationContext,
                            "exception: $exception",
                            Toast.LENGTH_SHORT).show()
                    }


            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                val error =response?.getError()?.getErrorCode().toString()
                //and handle the error.
                // ...
                Toast.makeText(this,error,Toast.LENGTH_SHORT).show()
            }
        }
    }
    companion object {

        private const val RC_SIGN_IN = 123
    }

}