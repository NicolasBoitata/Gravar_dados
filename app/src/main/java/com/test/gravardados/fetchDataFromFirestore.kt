package com.test.gravardados

import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.test.gravardados.adapter.FoodItem

// Função auxiliar para buscar dados do Firestore
fun fetchDataFromFirestore(db: FirebaseFirestore, foodList: MutableList<FoodItem>, onDataFetched: (List<FoodItem>) -> Unit): ListenerRegistration {
    // Ouvir alterações em tempo real na coleção "ADM"
    return db.collection("ADM")
        .addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w("TAG", "Error listening to Firestore changes", e)
                return@addSnapshotListener
            }

            if (snapshot != null) {
                foodList.clear() // Limpa a lista antes de adicionar os dados atualizados

                // Processa cada documento no snapshot
                for (document in snapshot.documents) {
                    val data = document.data
                    if (data != null) {
                        // Para cada categoria dentro do documento
                        data.forEach { (_, categoryData) ->
                            if (categoryData is Map<*, *>) {
                                val name = categoryData["name"]?.toString() ?: "Unknown"
                                val categoryValue = categoryData["categoria"]?.toString() ?: "Unknown"
                                foodList.add(FoodItem(categoryValue, name))
                            }
                        }
                    }
                }

                // Chama o callback passando os dados atualizados
                onDataFetched(foodList)
            }
        }
}
