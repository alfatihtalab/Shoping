package com.alfatih.shoping.auth

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest

open class UserFirebase: AppCompatActivity(){
    var TIME = 5000 //5000 ms (5 Seconds)

    //Get the currently signed-in user
    fun checkCurrentUser(
        user: FirebaseUser?,
        context: Context,
        cls: Class<*>,
        activity: Activity,
    ) {
        if (user != null) {
            //start the main activity
            startActivity(context, cls, activity)
            // User is signed in
        } else {
            Toast.makeText(activity, "please sing in first", Toast.LENGTH_SHORT).show()
//            val providers = arrayListOf(
//                AuthUI.IdpConfig.PhoneBuilder().build(),
//                AuthUI.IdpConfig.GoogleBuilder().build()
//            )
//            activity.findNavController(R.id.nav_host_fragment).navigate(
//                R.id.welcome
//            )

//            // Create and launch sign-in intent
//            activity.startActivityForResult(
//                AuthUI.getInstance()
//                    .createSignInIntentBuilder()
//                    .setAvailableProviders(providers)
//                    .build(), RC_SIGN_IN
//            )
//            // [END auth_fui_create_intent]
//            //userIntentToSecondActivity(context,cls,activity)
//            // No user is signed in
        }
    }
    open fun getProfile(user: FirebaseUser?){
        user?.let {
            // Name, email address, and profile photo Url
            val name = user.displayName
            val email = user.email
            val photoUrl = user.photoUrl

            // Check if user's email is verified
            val emailVerified = user.isEmailVerified

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            val uid = user.uid
        }

    }

    fun updateProfile(
        user: FirebaseUser?,
        context: Context,
        name: String, progressBar: ProgressBar,
        imageView: ImageView,
    ) {

        progressBar.visibility = View.VISIBLE
        val profileUpdates = UserProfileChangeRequest.Builder()
            .setDisplayName(name)
            .setPhotoUri(
                Uri.parse(
                    "https://scontent.fkrt2-2.fna.fbcdn.net/v/t1.0-9/122517209_2754811221289104_8609602280647281667_o.jpg?_nc_cat=105&ccb=3&_nc_sid=09cbfe&_nc_eui2=AeEhJ0IF172x5FjysPk2GbKLvxeQoyQSPbm_F5CjJBI9udoQqg7cYCNa6Dhp-nqeGyLNsRSKTawai-DyJrVBwHo1&_nc_ohc=cU6m9x5B1rIAX-KlPJx&_nc_oc=AQmlJh8ay5FjIiOVs7LNh6LXvYlrR0uPv_8mN-RMnUXLR6hQO4LgzEzMvkoGqXPMSV8&_nc_ht=scontent.fkrt2-2.fna&oh=8458dafe3500eef6c1392409c2faa008&oe=604CDA62"))
            .build()

        user!!.updateProfile(profileUpdates)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    progressBar.visibility = View.GONE
                    //Log.d(TAG, "User profile updated.")
                    imageView.setImageURI(Uri.parse("https://scontent.fkrt2-2.fna.fbcdn.net/v/t1.0-9/122517209_2754811221289104_8609602280647281667_o.jpg?_nc_cat=105&ccb=3&_nc_sid=09cbfe&_nc_eui2=AeEhJ0IF172x5FjysPk2GbKLvxeQoyQSPbm_F5CjJBI9udoQqg7cYCNa6Dhp-nqeGyLNsRSKTawai-DyJrVBwHo1&_nc_ohc=cU6m9x5B1rIAX-KlPJx&_nc_oc=AQmlJh8ay5FjIiOVs7LNh6LXvYlrR0uPv_8mN-RMnUXLR6hQO4LgzEzMvkoGqXPMSV8&_nc_ht=scontent.fkrt2-2.fna&oh=8458dafe3500eef6c1392409c2faa008&oe=604CDA62"))
                    Toast.makeText(context,"good",Toast.LENGTH_SHORT).show()
                }
            }
    }
    // [END auth_fui_result]
    fun updateProfile(user: FirebaseUser?,
                          context: Context, name: String,progressBar: ProgressBar){

        progressBar.visibility = View.VISIBLE
        val profileUpdates = UserProfileChangeRequest.Builder()
            .setDisplayName(name)
            .setPhotoUri(Uri.parse("https://example.com/jane-q-user/profile.jpg"))
            .build()

        user!!.updateProfile(profileUpdates)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    progressBar.visibility = View.GONE
                    //Log.d(TAG, "User profile updated.")
                    Toast.makeText(context,"good",Toast.LENGTH_SHORT).show()
                }
            }
    }
    // [END auth_fui_result]

    open fun signOut(context: Context, activity: Activity) {
        // [START auth_fui_signout]
        AuthUI.getInstance()
            .signOut(context)
            .addOnCompleteListener {
                // ...
                //startActivity(Intent(context,activity))
                activity.finish()
            }
        // [END auth_fui_signout]
    }
    open fun signIn(context: Context,activity: Activity){
        // Choose authentication providers
        val providers = arrayListOf(
            AuthUI.IdpConfig.PhoneBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build())

// Create and launch sign-in intent
        activity.startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build(),
            RC_SIGN_IN)
    }
    open fun deleteUser(context: Context){
        AuthUI.getInstance()
            .delete(context)
            .addOnCompleteListener {
                // ...
            }
    }

    fun startActivity(a: Context, b: Class<*>, activity: Activity):
            Unit {
        activity.startActivity(Intent(a, b))
    }

    val sum: (Int, Int) -> Int = { x: Int, y: Int -> x + y }
    val lambda: (Context, Class<*>, Activity) -> Unit =
        { context, clazz, activity -> activity.startActivity(Intent(context, clazz)) }
    val sum2 = { context: Context, cls: Class<*>, activity: Activity ->
        activity.startActivity(Intent(context, cls))
    }

    companion object {

        private const val RC_SIGN_IN = 123
    }

}

