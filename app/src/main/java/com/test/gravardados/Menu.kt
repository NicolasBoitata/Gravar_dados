//package com.test.gravardados
//
//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
//import androidx.fragment.app.FragmentTransaction
//import com.test.gravardados.databinding.ActivityMenuBinding
//
//class Menu : AppCompatActivity() {
//
//    private lateinit var binding: ActivityMenuBinding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMenuBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        // Adiciona o fragmento FoodListFragment no layout da atividade
//        if (savedInstanceState == null) {
//            val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
//            fragmentTransaction.replace(R.id.fragmentContainer, FoodListFragment()) // Usando o ID do container onde o fragmento será adicionado
//            fragmentTransaction.commit()
//        }
//    }
//}




package com.test.gravardados

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.test.gravardados.adapter.FoodAdapter
import com.test.gravardados.adapter.FoodItem
import com.test.gravardados.databinding.ActivityMenuBinding

class Menu : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // RecyclerView Setup
        val recyclerView: RecyclerView = binding.recyclerView // Certifique-se de ter um RecyclerView no seu layout activity_menu.xml
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Lista de itens de comida
        val foodList = mutableListOf<FoodItem>()

        // Chama a função auxiliar para buscar dados do Firestore
        fetchDataFromFirestore(db, foodList) { updatedFoodList ->
            // Atualiza o RecyclerView com os dados obtidos
            val adapter = FoodAdapter(updatedFoodList)
            binding.recyclerView.adapter = adapter
        }
    }
}




//package com.test.gravardados;
//
//import android.os.Bundle;
//import android.util.Log
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.google.firebase.firestore.FirebaseFirestore
//import com.test.gravardados.adapter.FoodAdapter
//import com.test.gravardados.adapter.FoodItem
//import com.test.gravardados.databinding.ActivityMenuBinding
//
//class Menu : AppCompatActivity() {
//
//    private lateinit var binding: ActivityMenuBinding
//    private val db = FirebaseFirestore.getInstance()
//
//    override fun onCreate(savedInstanceState:Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_menu)
//        binding = ActivityMenuBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//            // RecyclerView Setup
//            val recyclerView: RecyclerView = binding.recyclerView // Certifique-se de ter um RecyclerView no seu layout activity_menu.xml
//            recyclerView.layoutManager = LinearLayoutManager(this)
//
//            // Fetch and display data from Firestore in real-time
//            fetchDataFromFirestore()
//        }
//
//        private fun fetchDataFromFirestore() {
//            val foodList = mutableListOf<FoodItem>()
//
//            // Listen for real-time updates in the "ADM" collection
//            db.collection("ADM")
//                .addSnapshotListener { snapshot, e ->
//                    if (e != null) {
//                        Log.w("TAG", "Error listening to Firestore changes", e)
//                        Toast.makeText(this, "Erro ao ouvir as alterações.", Toast.LENGTH_SHORT).show()
//                        return@addSnapshotListener
//                    }
//
//                    if (snapshot != null) {
//                        foodList.clear() // Clear the list before adding updated data
//
//                        // Process each document in the snapshot
//                        for (document in snapshot.documents) {
//                            val date = document.id // The document ID is the date (e.g., "04-12-2024")
//                            val data = document.data
//
//                            // Check if data is not null
//                            if (data != null) {
//                                // Loop through the categories (keys) in the document
//                                data.forEach { (category, categoryData) ->
//                                    // Check if categoryData is a map and contains the "name" and "categoria" fields
//                                    if (categoryData is Map<*, *>) {
//                                        val name = categoryData["name"]?.toString() ?: "Unknown"
//                                        val categoryValue = categoryData["categoria"]?.toString() ?: "Unknown"
//
//                                        // Add the extracted data to the list
//                                        foodList.add(FoodItem(categoryValue, name))
//                                    }
//                                }
//                            }
//                        }
//
//                        // Update the RecyclerView with the new data
//                        val adapter = FoodAdapter(foodList)
//                        binding.recyclerView.adapter = adapter
//                    } else {
//                        Log.d("TAG", "No data found.")
//                    }
//                }
//        }
//    }
