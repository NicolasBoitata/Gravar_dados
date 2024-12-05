//package com.test.gravardados
//
//import android.os.Bundle
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.google.firebase.firestore.FirebaseFirestore
//import com.test.gravardados.adapter.FoodAdapter
//import com.test.gravardados.adapter.FoodItem
//import com.test.gravardados.databinding.FragmentFoodListBinding
//
//class FoodListFragment : Fragment() {
//
//    // Declare a reference to the binding class
//    private var _binding: FragmentFoodListBinding? = null
//    private val binding get() = _binding!!
//
//    private val db = FirebaseFirestore.getInstance()
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout and set up the binding
//        _binding = FragmentFoodListBinding.inflate(inflater, container, false)
//
//        // RecyclerView setup
//        val recyclerView: RecyclerView = binding.recyclerView
//        recyclerView.layoutManager = LinearLayoutManager(requireContext())
//
//        // List to store the food items
//        val foodList = mutableListOf<FoodItem>()
//
//        // Fetch data from Firestore
//        fetchDataFromFirestore(db, foodList) { updatedFoodList ->
//            // Update the RecyclerView with the fetched data
//            val adapter = FoodAdapter(updatedFoodList)
//            recyclerView.adapter = adapter
//        }
//
//        // Return the root view of the binding
//        return binding.root
//    }
//
//    // Function to fetch data from Firestore
//    private fun fetchDataFromFirestore(db: FirebaseFirestore, foodList: MutableList<FoodItem>, onDataFetched: (List<FoodItem>) -> Unit) {
//        db.collection("ADM")
//            .addSnapshotListener { snapshot, e ->
//                if (e != null) {
//                    Log.w("TAG", "Error listening to Firestore changes", e)
//                    return@addSnapshotListener
//                }
//
//                if (snapshot != null) {
//                    foodList.clear()
//
//                    for (document in snapshot.documents) {
//                        val data = document.data
//                        if (data != null) {
//                            data.forEach { (_, categoryData) ->
//                                if (categoryData is Map<*, *>) {
//                                    val name = categoryData["name"]?.toString() ?: "Unknown"
//                                    val categoryValue = categoryData["categoria"]?.toString() ?: "Unknown"
//                                    foodList.add(FoodItem(categoryValue, name))
//                                }
//                            }
//                        }
//                    }
//
//                    onDataFetched(foodList)
//                }
//            }
//    }
//
//    // Clear the binding when the fragment's view is destroyed to avoid memory leaks
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//}





//package com.test.gravardados
//
//import android.os.Bundle
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.google.firebase.firestore.FirebaseFirestore
//import com.test.gravardados.adapter.FoodAdapter
//import com.test.gravardados.adapter.FoodItem
//import com.test.gravardados.databinding.FragmentFoodListBinding
//
//class FoodListFragment : Fragment() {
//
//    private lateinit var binding: FragmentFoodListBinding
//    private val db = FirebaseFirestore.getInstance()
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        binding = FragmentFoodListBinding.inflate(inflater, container, false)
//
//        // RecyclerView Setup
//        val recyclerView: RecyclerView = binding.recyclerView
//        recyclerView.layoutManager = LinearLayoutManager(requireContext())
//
//        // Lista de itens de comida
//        val foodList = mutableListOf<FoodItem>()
//
//        // Chama a função auxiliar para buscar dados do Firestore
//        fetchDataFromFirestore(db, foodList) { updatedFoodList ->
//            // Atualiza o RecyclerView com os dados obtidos
//            val adapter = FoodAdapter(updatedFoodList)
//            recyclerView.adapter = adapter
//        }
//
//        return binding.root
//    }
//
//    // Função auxiliar para buscar dados do Firestore
//    private fun fetchDataFromFirestore(db: FirebaseFirestore, foodList: MutableList<FoodItem>, onDataFetched: (List<FoodItem>) -> Unit) {
//        // Ouvir alterações em tempo real na coleção "ADM"
//        db.collection("ADM")
//            .addSnapshotListener { snapshot, e ->
//                if (e != null) {
//                    Log.w("TAG", "Error listening to Firestore changes", e)
//                    return@addSnapshotListener
//                }
//
//                if (snapshot != null) {
//                    foodList.clear() // Limpa a lista antes de adicionar os dados atualizados
//
//                    // Processa cada documento no snapshot
//                    for (document in snapshot.documents) {
//                        val data = document.data
//                        if (data != null) {
//                            // Para cada categoria dentro do documento
//                            data.forEach { (_, categoryData) ->
//                                if (categoryData is Map<*, *>) {
//                                    val name = categoryData["name"]?.toString() ?: "Unknown"
//                                    val categoryValue = categoryData["categoria"]?.toString() ?: "Unknown"
//                                    foodList.add(FoodItem(categoryValue, name))
//                                }
//                            }
//                        }
//                    }
//
//                    // Chama o callback passando os dados atualizados
//                    onDataFetched(foodList)
//                }
//            }
//    }
//}
