package com.test.gravardados

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.firestore
import com.test.gravardados.databinding.ActivityDatabaseBinding

class database : AppCompatActivity() {

    lateinit var binding: ActivityDatabaseBinding
    private val db = FirebaseFirestore.getInstance()
    lateinit var tal : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*        setContentView(R.layout.activity_database) // Defina seu layout aqui*/
        binding = ActivityDatabaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.enviarmerenda.setOnClickListener {
            val db = Firebase.firestore

            val foodDate = "10-12-2024"

            val newFood =  mapOf(
                "categoria" to "bolacha",
                "img" to "url_da_bollcha.jpg",
                "name" to "paodequeijo",
                "calorie" to 800
            )
            val periodo =  mapOf("manhã" to newFood)

            db.collection("ADM")
                .document(foodDate)
                .set(periodo, SetOptions.merge())
                .addOnSuccessListener {
                    Log.d("TAG", "DocumentSnapshot updated successfully.")
                }
                .addOnFailureListener { e ->
                    Log.w("TAG", "Error updating document", e)
                }
        }
    }
}
