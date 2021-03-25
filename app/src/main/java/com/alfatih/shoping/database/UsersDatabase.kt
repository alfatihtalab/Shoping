package com.alfatih.shoping.database

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

open class UsersDatabase() : AppCompatActivity() {
    val db = Firebase.firestore
    // Create a new user with a first and last name


    open fun addCollection(
        collection: String,
        item: java.util.HashMap<String, out Any>,
        context: Context,
    ) {
        db.collection(collection)
            .add(item)
            .addOnSuccessListener { documentReference ->
                //Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")

                Toast.makeText(context, "done", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show()

                //Log.w(TAG, "Error adding document", e)
            }
    }

    open fun addUser(userName: String, occupation: String) {
        db.collection("users")
            .document(userName)
    }


}