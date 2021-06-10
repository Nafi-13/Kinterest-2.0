package com.example.tutorials
import android.util.Log
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class RealtimeDatabase {
    private val TAG = "ReadAndWriteSnippets"

    // [START declare_database_ref]
    private var database: DatabaseReference = Firebase.database.reference
    // [END declare_database_ref]

    init {
        // [START initialize_database_ref]
        Log.w("RDB class","init")
        // [END initialize_database_ref]
    }

    // [START rtdb_write_new_user]
    fun writeNewUser(userId: String, name: String, email: String) {
        val user = User(name, email)
        Log.w("RealtimeDB","Writing new user")

        database.child("users").child(userId).setValue(user)
    }

}