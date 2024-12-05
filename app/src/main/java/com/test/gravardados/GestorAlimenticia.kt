import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.test.gravardados.R

class GestorAlimenticio : AppCompatActivity() {

    private lateinit var spinnerMenuOptions: Spinner
    private lateinit var recyclerViewMenuItems: RecyclerView
    private lateinit var menuItemsAdapter: MenuItemsAdapter
    private val firestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gestor_alimenticia)

        spinnerMenuOptions = findViewById(R.id.spinner_menu_options)
        recyclerViewMenuItems = findViewById(R.id.recycler_view_menu_items)

        // Configure the RecyclerView programmatically
        val gridLayoutManager = GridLayoutManager(this, 2)
        recyclerViewMenuItems.layoutManager = gridLayoutManager
        menuItemsAdapter = MenuItemsAdapter()
        recyclerViewMenuItems.adapter = menuItemsAdapter

        // Set up Spinner
        val optionsArray = resources.getStringArray(R.array.menu_options_array)
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, optionsArray)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerMenuOptions.adapter = spinnerAdapter

        // Set up Spinner selection listener
        spinnerMenuOptions.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedOption = optionsArray[position]
                fetchMenuItems(selectedOption)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }
    }

    private fun fetchMenuItems(option: String) {
        firestore.collection("menu_items")
            .whereEqualTo("selo", option)
            .get()
            .addOnSuccessListener { documents ->
                val menuItems = documents.map { it.toObject(MenuItem::class.java) }
                menuItemsAdapter.submitList(menuItems)
            }
            .addOnFailureListener { exception ->
                // Handle error
            }
    }
}




//Esse?

//package com.test.gravardados
//
//import android.content.Intent
//import android.os.Bundle
//import android.util.Log
//import android.widget.ArrayAdapter
//import android.widget.AutoCompleteTextView
//import android.widget.Button
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import com.google.firebase.firestore.FirebaseFirestore
//import com.google.firebase.firestore.SetOptions
//import com.test.gravardados.adapter.FoodItem
//import com.test.gravardados.databinding.ActivityGestorAlimenticiaBinding
//import java.text.SimpleDateFormat
//import java.util.*
//
//class GestorAlimenticia : AppCompatActivity() {
//
//    lateinit var binding: ActivityGestorAlimenticiaBinding
//    private val db = FirebaseFirestore.getInstance()
//
//    val snackItems = arrayOf("Cedo", "Manhã", "Tarde")
//    private lateinit var autoCompleteTextView: AutoCompleteTextView
//    private lateinit var adapterItems: ArrayAdapter<String>
//
//    // Variável para armazenar o período selecionado
//    private var selectedPeriod: String? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityGestorAlimenticiaBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        val menuresbutton: Button = findViewById(R.id.menures_button)
//        menuresbutton.setOnClickListener {
//            val navegarTelaMenu = Intent(this, Menu::class.java)
//            startActivity(navegarTelaMenu)
//        }
//
//        // Configuração do AutoCompleteTextView
//        autoCompleteTextView = findViewById(com.test.gravardados.R.id.option_period)
//        adapterItems = ArrayAdapter(
//            this,
//            com.test.gravardados.R.layout.list_item,
//            snackItems
//        )
//        autoCompleteTextView.threshold = 1
//        autoCompleteTextView.dropDownHeight = 465
//        autoCompleteTextView.setAdapter(adapterItems)
//
//        // Captura o item selecionado
//        autoCompleteTextView.setOnItemClickListener { adapterView, _, position, _ ->
//            selectedPeriod = adapterView.getItemAtPosition(position).toString()
//            Toast.makeText(this, "Item: $selectedPeriod", Toast.LENGTH_SHORT).show()
//        }
//
//        // Preencher automaticamente o campo de data com a data atual
//        val currentDate = getCurrentDate()
//        binding.editDate.setText(currentDate)  // Preenche o campo de data com a data atual
//
//        // Configuração do botão "Enviar"
//        binding.btnEnviar.setOnClickListener {
//            // Captura a data do campo editDate (já preenchido automaticamente)
//            val optionperiod = binding.editDate.text.toString().trim()
//
//            // Verifica se o período foi selecionado
//            if (optionperiod.isNotEmpty() && selectedPeriod != null) {
//                val title = binding.editTitleFood.text.toString()
//                val description = binding.editDescriptionFood.text.toString()
//
//                // Criação do mapa de dados
//                val newFood = mapOf(
//                    "name" to title,
//                    "categoria" to description
//                )
//
//                // Mapa com o período como chave
//                val periodo = mapOf(
//                    selectedPeriod!! to newFood
//                )
//
//                // Salvando os dados no Firestore
//                db.collection("ADM")
//                    .document(optionperiod)  // Usa a data fornecida automaticamente
//                    .set(periodo, SetOptions.merge())
//                    .addOnSuccessListener {
//                        Log.d("TAG", "DocumentSnapshot updated successfully.")
//                        Toast.makeText(this, "Dados salvos com sucesso!", Toast.LENGTH_SHORT).show()
//                    }
//                    .addOnFailureListener { e ->
//                        Log.w("TAG", "Error updating document", e)
//                        Toast.makeText(this, "Erro ao salvar dados.", Toast.LENGTH_SHORT).show()
//                    }
//            } else {
//                // Se não houver período selecionado
//                Toast.makeText(this, "Preencha todos os campos corretamente", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//        // Fetch and display data from Firestore in real-time
//        fetchDataFromFirestore()
//    }
//
//    private fun fetchDataFromFirestore() {
//        val foodList = mutableListOf<FoodItem>()
//
//        // Listen for real-time updates in the "ADM" collection
//        db.collection("ADM")
//            .addSnapshotListener { snapshot, e ->
//                if (e != null) {
//                    Log.w("TAG", "Error listening to Firestore changes", e)
//                    Toast.makeText(this, "Erro ao ouvir as alterações.", Toast.LENGTH_SHORT).show()
//                    return@addSnapshotListener
//                }
//
//                if (snapshot != null) {
//                    foodList.clear() // Clear the list before adding updated data
//
//                    // Process each document in the snapshot
//                    for (document in snapshot.documents) {
//                        val date = document.id // The document ID is the date (e.g., "04-12-2024")
//                        val data = document.data
//
//                        // Check if data is not null
//                        if (data != null) {
//                            // Loop through the categories (keys) in the document
//                            data.forEach { (category, categoryData) ->
//                                // Check if categoryData is a map and contains the "name" and "categoria" fields
//                                if (categoryData is Map<*, *>) {
//                                    val name = categoryData["name"]?.toString() ?: "Unknown"
//                                    val categoryValue = categoryData["categoria"]?.toString() ?: "Unknown"
//
//                                    // Add the extracted data to the list
//                                    foodList.add(FoodItem(categoryValue, name))
//                                }
//                            }
//                        }
//                    }
//
//                    // Update the RecyclerView with the new data
//                    // Removed as per user's request
//                } else {
//                    Log.d("TAG", "No data found.")
//                }
//            }
//    }
//
//    // Função para obter a data atual formatada
//    private fun getCurrentDate(): String {
//        val calendar = Calendar.getInstance()
//        val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
//        return dateFormat.format(calendar.time)
//    }
//}












//package com.test.gravardados
//
//import android.content.Intent
//import android.os.Bundle
//import android.util.Log
//import android.widget.ArrayAdapter
//import android.widget.AutoCompleteTextView
//import android.widget.Button
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.google.firebase.firestore.FirebaseFirestore
//import com.google.firebase.firestore.SetOptions
//import com.test.gravardados.adapter.FoodItem
//import com.test.gravardados.databinding.ActivityGestorAlimenticiaBinding
//import java.text.SimpleDateFormat
//import java.util.*
//
//class GestorAlimenticia : AppCompatActivity() {
//
//    lateinit var binding: ActivityGestorAlimenticiaBinding
//    private val db = FirebaseFirestore.getInstance()
//
//    val snackItems = arrayOf("Cedo", "Manhã", "Tarde")
//    private lateinit var autoCompleteTextView: AutoCompleteTextView
//    private lateinit var adapterItems: ArrayAdapter<String>
//
//    // Variável para armazenar o período selecionado
//    private var selectedPeriod: String? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityGestorAlimenticiaBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        val menuresbutton: Button = findViewById(R.id.menures_button)
//        menuresbutton.setOnClickListener {
//            val navegarTelaMenu = Intent(this, Menu::class.java)
//            startActivity(navegarTelaMenu)
//        }
//
//
//        // Configuração do AutoCompleteTextView
//        autoCompleteTextView = findViewById(com.test.gravardados.R.id.option_period)
//        adapterItems = ArrayAdapter(
//            this,
//            com.test.gravardados.R.layout.list_item,
//            snackItems
//        )
//        autoCompleteTextView.threshold = 1
//        autoCompleteTextView.dropDownHeight = 465
//        autoCompleteTextView.setAdapter(adapterItems)
//
//        // Captura o item selecionado
//        autoCompleteTextView.setOnItemClickListener { adapterView, _, position, _ ->
//            selectedPeriod = adapterView.getItemAtPosition(position).toString()
//            Toast.makeText(this, "Item: $selectedPeriod", Toast.LENGTH_SHORT).show()
//        }
//
//        // Preencher automaticamente o campo de data com a data atual
//        val currentDate = getCurrentDate()
//        binding.editDate.setText(currentDate)  // Preenche o campo de data com a data atual
//
//        // Configuração do botão "Enviar"
//        binding.btnEnviar.setOnClickListener {
//            // Captura a data do campo editDate (já preenchido automaticamente)
//            val optionperiod = binding.editDate.text.toString().trim()
//
//            // Verifica se o período foi selecionado
//            if (optionperiod.isNotEmpty() && selectedPeriod != null) {
//                val title = binding.editTitleFood.text.toString()
//                val description = binding.editDescriptionFood.text.toString()
//
//                // Criação do mapa de dados
//                val newFood = mapOf(
//                    "name" to title,
//                    "categoria" to description
//                )
//
//                // Mapa com o período como chave
//                val periodo = mapOf(
//                    selectedPeriod!! to newFood
//                )
//
//                // Salvando os dados no Firestore
//                db.collection("ADM")
//                    .document(optionperiod)  // Usa a data fornecida automaticamente
//                    .set(periodo, SetOptions.merge())
//                    .addOnSuccessListener {
//                        Log.d("TAG", "DocumentSnapshot updated successfully.")
//                        Toast.makeText(this, "Dados salvos com sucesso!", Toast.LENGTH_SHORT).show()
//                    }
//                    .addOnFailureListener { e ->
//                        Log.w("TAG", "Error updating document", e)
//                        Toast.makeText(this, "Erro ao salvar dados.", Toast.LENGTH_SHORT).show()
//                    }
//            } else {
//                // Se não houver período selecionado
//                Toast.makeText(this, "Preencha todos os campos corretamente", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//        // Configuração do RecyclerView(Setup)
//        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//
//        // Buscar e exibir dados do Firestore em (real-time)
//        fetchDataFromFirestore()
//    }
//
//    private fun fetchDataFromFirestore() {
//        val foodList = mutableListOf<FoodItem>()
//
//        // Ouça atualizações em tempo real na coleção "ADM"
//        db.collection("ADM")
//            .addSnapshotListener { snapshot, e ->
//                if (e != null) {
//                    Log.w("TAG", "Error listening to Firestore changes", e)
//                    Toast.makeText(this, "Erro ao ouvir as alterações.", Toast.LENGTH_SHORT).show()
//                    return@addSnapshotListener
//                }
//
//                if (snapshot != null) {
//                    foodList.clear() // Limpe a lista antes de adicionar dados atualizados
//
//                    // Processe cada documento no instantâneo
//                    for (document in snapshot.documents) {
//                        val date = document.id //O ID do documento é a data (por exemplo, "04-12-2024")
//                        val data = document.data
//
//                        // Verifique se os dados não são nulos
//                        if (data != null) {
//                            // Percorrer as categorias (keys) no documento
//                            data.forEach { (category, categoryData) ->
//                                // Verifique se a categoria Dados é um mapa e contém os campos "nome" e "categoria"
//                                if (categoryData is Map<*, *>) {
//                                    val name = categoryData["name"]?.toString() ?: "Unknown"
//                                    val categoryValue = categoryData["categoria"]?.toString() ?: "Unknown"
//
//                                    // Adicione os dados extraídos à lista
//                                    foodList.add(FoodItem(categoryValue, name))
//                                }
//                            }
//                        }
//                    }
//                } else {
//                    Log.d("TAG", "No data found.")
//                }
//            }
//    }
//
//    // Função para obter a data atual formatada
//    private fun getCurrentDate(): String {
//        val calendar = Calendar.getInstance()
//        val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
//        return dateFormat.format(calendar.time)
//    }
//}


//import android.os.Bundle
//import android.util.Log
//import android.widget.Button
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import com.google.firebase.firestore.FirebaseFirestore
//import com.google.firebase.firestore.SetOptions
//import com.test.gravardados.R
//import com.test.gravardados.adapter.FoodAdapter
//import com.test.gravardados.adapter.FoodItem
//import com.test.gravardados.databinding.ActivityGestorAlimenticiaBinding
//
//class GestorAlimenticia : AppCompatActivity() {
//
//    lateinit var binding: ActivityGestorAlimenticiaBinding
//    private val db = FirebaseFirestore.getInstance()
//
//    private var selectedDate: String? = null  // Variável para armazenar a data selecionada
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityGestorAlimenticiaBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        // Botão para alterar para 01-12-2024
//        val btnDate01: Button = findViewById(R.id.btnDate01)
//        btnDate01.setOnClickListener {
//            selectedDate = "01-12-2024"  // Atualiza a data selecionada para 01-12-2024
//            binding.editDate.setText(selectedDate)  // Atualiza o campo de data na UI
//            fetchDataFromFirestore(selectedDate!!)  // Atualiza os dados com a data selecionada
//        }
//
//        // Botão para alterar para 05-12-2024
//        val btnDate05: Button = findViewById(R.id.btnDate05)
//        btnDate05.setOnClickListener {
//            selectedDate = "05-12-2024"  // Atualiza a data selecionada para 05-12-2024
//            binding.editDate.setText(selectedDate)  // Atualiza o campo de data na UI
//            fetchDataFromFirestore(selectedDate!!)  // Atualiza os dados com a data selecionada
//        }
//
//        // Configuração do botão "Enviar" para salvar os dados
//        binding.btnEnviar.setOnClickListener {
//            val selectedPeriod = binding.optionPeriod.text.toString()
//            val title = binding.editTitleFood.text.toString()
//            val description = binding.editDescriptionFood.text.toString()
//
//            if (selectedDate != null && selectedPeriod.isNotEmpty()) {
//                val newFood = mapOf(
//                    "name" to title,
//                    "categoria" to description
//                )
//
//                val periodo = mapOf(
//                    selectedPeriod to newFood
//                )
//
//                // Atualiza o Firestore com a data e o período selecionados
//                db.collection("ADM")
//                    .document(selectedDate!!)  // Usa a data selecionada
//                    .set(periodo, SetOptions.merge()) // Atualiza a coleção com os dados
//                    .addOnSuccessListener {
//                        Log.d("TAG", "Dados salvos com sucesso.")
//                        Toast.makeText(this, "Dados salvos com sucesso!", Toast.LENGTH_SHORT).show()
//                    }
//                    .addOnFailureListener { e ->
//                        Log.w("TAG", "Erro ao salvar dados", e)
//                        Toast.makeText(this, "Erro ao salvar dados.", Toast.LENGTH_SHORT).show()
//                    }
//            } else {
//                Toast.makeText(this, "Preencha todos os campos corretamente.", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//
//    // Função para pegar os dados do Firestore e atualizar a RecyclerView
//    private fun fetchDataFromFirestore(date: String) {
//        val foodList = mutableListOf<FoodItem>()
//
//        // Acessa a coleção ADM e busca o documento com a data selecionada
//        db.collection("ADM")
//            .document(date)  // Filtra por data
//            .get()
//            .addOnSuccessListener { document ->
//                if (document != null) {
//                    val data = document.data
//
//                    if (data != null) {
//                        data.forEach { (category, categoryData) ->
//                            if (categoryData is Map<*, *>) {
//                                val name = categoryData["name"]?.toString() ?: "Desconhecido"
//                                val categoryValue = categoryData["categoria"]?.toString() ?: "Desconhecido"
//                                foodList.add(FoodItem(categoryValue, name))
//                            }
//                        }
//                    }
//                }
//                // Atualiza o RecyclerView
//                val adapter = FoodAdapter(foodList)
//                binding.recyclerView.adapter = adapter
//            }
//            .addOnFailureListener { e ->
//                Log.w("TAG", "Erro ao acessar os dados: ", e)
//                Toast.makeText(this, "Erro ao acessar os dados.", Toast.LENGTH_SHORT).show()
//    }
//}
//}

//esse

//package com.test.gravardados
//
//import android.content.Intent
//import android.os.Bundle
//import android.util.Log
//import android.widget.ArrayAdapter
//import android.widget.AutoCompleteTextView
//import android.widget.Button
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.google.firebase.firestore.FirebaseFirestore
//import com.google.firebase.firestore.SetOptions
//import com.test.gravardados.adapter.FoodAdapter
//import com.test.gravardados.adapter.FoodItem
//import com.test.gravardados.databinding.ActivityGestorAlimenticiaBinding
//import java.text.SimpleDateFormat
//import java.util.*
//
//class GestorAlimenticia : AppCompatActivity() {
//
//    lateinit var binding: ActivityGestorAlimenticiaBinding
//    private val db = FirebaseFirestore.getInstance()
//
//    val snackItems = arrayOf("Cedo", "Manhã", "Tarde")
//    private lateinit var autoCompleteTextView: AutoCompleteTextView
//    private lateinit var adapterItems: ArrayAdapter<String>
//
//    // Variável para armazenar o período selecionado
//    private var selectedPeriod: String? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityGestorAlimenticiaBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        val menuresbutton: Button = findViewById(R.id.menures_button)
//        menuresbutton.setOnClickListener {
//            val navegarTelaMenu = Intent(this, Menu::class.java)
//            startActivity(navegarTelaMenu)
//        }
//
//
//        // Configuração do AutoCompleteTextView
//        autoCompleteTextView = findViewById(com.test.gravardados.R.id.option_period)
//        adapterItems = ArrayAdapter(
//            this,
//            com.test.gravardados.R.layout.list_item,
//            snackItems
//        )
//        autoCompleteTextView.threshold = 1
//        autoCompleteTextView.dropDownHeight = 465
//        autoCompleteTextView.setAdapter(adapterItems)
//
//        // Captura o item selecionado
//        autoCompleteTextView.setOnItemClickListener { adapterView, _, position, _ ->
//            selectedPeriod = adapterView.getItemAtPosition(position).toString()
//            Toast.makeText(this, "Item: $selectedPeriod", Toast.LENGTH_SHORT).show()
//        }
//
//        // Preencher automaticamente o campo de data com a data atual
//        val currentDate = getCurrentDate()
//        binding.editDate.setText(currentDate)  // Preenche o campo de data com a data atual
//
//        // Configuração do botão "Enviar"
//        binding.btnEnviar.setOnClickListener {
//            // Captura a data do campo editDate (já preenchido automaticamente)
//            val optionperiod = binding.editDate.text.toString().trim()
//
//            // Verifica se o período foi selecionado
//            if (optionperiod.isNotEmpty() && selectedPeriod != null) {
//                val title = binding.editTitleFood.text.toString()
//                val description = binding.editDescriptionFood.text.toString()
//
//                // Criação do mapa de dados
//                val newFood = mapOf(
//                    "name" to title,
//                    "categoria" to description
//                )
//
//                // Mapa com o período como chave
//                val periodo = mapOf(
//                    selectedPeriod!! to newFood
//                )
//
//                // Salvando os dados no Firestore
//                db.collection("ADM")
//                    .document(optionperiod)  // Usa a data fornecida automaticamente
//                    .set(periodo, SetOptions.merge())
//                    .addOnSuccessListener {
//                        Log.d("TAG", "DocumentSnapshot updated successfully.")
//                        Toast.makeText(this, "Dados salvos com sucesso!", Toast.LENGTH_SHORT).show()
//                    }
//                    .addOnFailureListener { e ->
//                        Log.w("TAG", "Error updating document", e)
//                        Toast.makeText(this, "Erro ao salvar dados.", Toast.LENGTH_SHORT).show()
//                    }
//            } else {
//                // Se não houver período selecionado
//                Toast.makeText(this, "Preencha todos os campos corretamente", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//        // RecyclerView Setup
//        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//
//        // Fetch and display data from Firestore in real-time
//        fetchDataFromFirestore()
//    }
//
//    private fun fetchDataFromFirestore() {
//        val foodList = mutableListOf<FoodItem>()
//
//        // Listen for real-time updates in the "ADM" collection
//        db.collection("ADM")
//            .addSnapshotListener { snapshot, e ->
//                if (e != null) {
//                    Log.w("TAG", "Error listening to Firestore changes", e)
//                    Toast.makeText(this, "Erro ao ouvir as alterações.", Toast.LENGTH_SHORT).show()
//                    return@addSnapshotListener
//                }
//
//                if (snapshot != null) {
//                    foodList.clear() // Clear the list before adding updated data
//
//                    // Process each document in the snapshot
//                    for (document in snapshot.documents) {
//                        val date = document.id // The document ID is the date (e.g., "04-12-2024")
//                        val data = document.data
//
//                        // Check if data is not null
//                        if (data != null) {
//                            // Loop through the categories (keys) in the document
//                            data.forEach { (category, categoryData) ->
//                                // Check if categoryData is a map and contains the "name" and "categoria" fields
//                                if (categoryData is Map<*, *>) {
//                                    val name = categoryData["name"]?.toString() ?: "Unknown"
//                                    val categoryValue = categoryData["categoria"]?.toString() ?: "Unknown"
//
//                                    // Add the extracted data to the list
//                                    foodList.add(FoodItem(categoryValue, name))
//                                }
//                            }
//                        }
//                    }
//
//                    // Update the RecyclerView with the new data
//                    val adapter = FoodAdapter(foodList)
//                    binding.recyclerView.adapter = adapter
//                } else {
//                    Log.d("TAG", "No data found.")
//                }
//            }
//    }
//
//    // Função para obter a data atual formatada
//    private fun getCurrentDate(): String {
//        val calendar = Calendar.getInstance()
//        val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
//        return dateFormat.format(calendar.time)
//    }
//}







//import android.os.Bundle
//import android.util.Log
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.google.firebase.firestore.FirebaseFirestore
//import com.test.gravardados.adapter.FoodAdapter
//import com.test.gravardados.adapter.FoodItem
//import com.test.gravardados.databinding.ActivityGestorAlimenticiaBinding
//
//class GestorAlimenticia : AppCompatActivity() {
//
//    lateinit var binding: ActivityGestorAlimenticiaBinding
//    private val db = FirebaseFirestore.getInstance()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityGestorAlimenticiaBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        // Configurar RecyclerView
//        val foodList = mutableListOf<FoodItem>()
//        val recyclerView = binding.recyclerView
//        val foodAdapter = FoodAdapter(foodList)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.adapter = foodAdapter
//
//        // Recuperar dados do Firestore
//        db.collection("ADM")
//            .addSnapshotListener { snapshot, exception ->
//                if (exception != null) {
//                    Log.w("TAG", "Error fetching data from Firestore", exception)
//                    Toast.makeText(this, "Erro ao buscar dados.", Toast.LENGTH_SHORT).show()
//                    return@addSnapshotListener
//                }
//
//                if (snapshot != null) {
//                    foodList.clear() // Limpa a lista antes de adicionar novos dados
//
//                    // Processa cada documento (onde cada documento é uma data)
//                    for (document in snapshot.documents) {
//                        val date = document.id // O ID do documento é a data (Ex: "04-12-2024")
//                        val data = document.data
//
//                        // Verifica se os dados não são nulos
//                        if (data != null) {
//                            // Loop pelas categorias e dados dentro do documento
//                            data.forEach { (category, categoryData) ->
//                                // Verifica se o dado da categoria é um mapa
//                                if (categoryData is Map<*, *>) {
//                                    val name = categoryData["name"]?.toString() ?: "Unknown"
//                                    val categoryValue = categoryData["categoria"]?.toString() ?: "Unknown"
//
//                                    // Adiciona à lista de alimentos
//                                    foodList.add(FoodItem(categoryValue, name))
//                                }
//                            }
//                        }
//                    }
//
//                    // Atualiza o RecyclerView com os novos dados
//                    foodAdapter.notifyDataSetChanged()
//                }
//            }
//    }
//}



/*funciona, so que editbase não funcinciona
funciona, so que editbase não funcinciona*/

//package com.test.gravardados
//
//import android.os.Bundle
//import android.util.Log
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.google.firebase.firestore.FirebaseFirestore
//import com.google.firebase.firestore.QuerySnapshot
//import com.test.gravardados.adapter.FoodAdapter
//import com.test.gravardados.adapter.FoodItem
//import com.test.gravardados.databinding.ActivityGestorAlimenticiaBinding
//
//class GestorAlimenticia : AppCompatActivity() {
//
//    lateinit var binding: ActivityGestorAlimenticiaBinding
//    private val db = FirebaseFirestore.getInstance()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityGestorAlimenticiaBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        // RecyclerView Setup
//        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//
//        // Fetch and display data from Firestore in real-time
//        fetchDataFromFirestore()
//    }
//
//    private fun fetchDataFromFirestore() {
//        val foodList = mutableListOf<FoodItem>()
//
//        // Listen for real-time updates in the "ADM" collection
//        db.collection("ADM")
//            .addSnapshotListener { snapshot, e ->
//                if (e != null) {
//                    Log.w("TAG", "Error listening to Firestore changes", e)
//                    Toast.makeText(this, "Erro ao ouvir as alterações.", Toast.LENGTH_SHORT).show()
//                    return@addSnapshotListener
//                }
//
//                if (snapshot != null) {
//                    foodList.clear() // Clear the list before adding updated data
//
//                    // Process each document in the snapshot
//                    for (document in snapshot.documents) {
//                        val date = document.id // The document ID is the date (e.g., "04-12-2024")
//                        val data = document.data
//
//                        // Check if data is not null
//                        if (data != null) {
//                            // Loop through the categories (keys) in the document
//                            data.forEach { (category, categoryData) ->
//                                // Check if categoryData is a map and contains the "name" and "categoria" fields
//                                if (categoryData is Map<*, *>) {
//                                    val name = categoryData["name"]?.toString() ?: "Unknown"
//                                    val categoryValue = categoryData["categoria"]?.toString() ?: "Unknown"
//
//                                    // Add the extracted data to the list
//                                    foodList.add(FoodItem(categoryValue, name))
//                                }
//                            }
//                        }
//                    }
//
//                    // Update the RecyclerView with the new data
//                    val adapter = FoodAdapter(foodList)
//                    binding.recyclerView.adapter = adapter
//                } else {
//                    Log.d("TAG", "No data found.")
//                }
//            }
//    }

    // Function to fetch data from Firestore with real-time updates
//    private fun fetchDataFromFirestore() {
//        val foodList = mutableListOf<FoodItem>()
//
//        // Listen for real-time updates in the "ADM" collection
//        db.collection("ADM")
//            .addSnapshotListener { snapshot, e ->
//                if (e != null) {
//                    Log.w("TAG", "Error listening to Firestore changes", e)
//                    Toast.makeText(this, "Erro ao ouvir as alterações.", Toast.LENGTH_SHORT).show()
//                    return@addSnapshotListener
//                }
//
//                if (snapshot != null) {
//                    foodList.clear() // Clear the list before adding updated data
//
//                    // Process each document in the snapshot
//                    for (document in snapshot.documents) {
//                        val date = document.id // The document ID is the date (e.g., "04-12-2024")
//                        val data = document.data
//
//                        // Loop through the categories and extract their data
//                        data?.forEach { (category, categoryData) ->
//                            // Each category is a map with "name" and "categoria"
//                            val categoryName = categoryData as Map<*, *>
//                            val name = categoryName["name"]?.toString() ?: "Unknown"
//                            val categoryValue = categoryName["categoria"]?.toString() ?: "Unknown"
//
//                            // Add the extracted data to the list
//                            foodList.add(FoodItem(categoryValue, name))
//                        }
//                    }
//
//                    // Update the RecyclerView with the new data
//                    val adapter = FoodAdapter(foodList)
//                    binding.recyclerView.adapter = adapter
//                } else {
//                    Log.d("TAG", "No data found.")
//                }
//            }
//    }
//}






/*Mais ou menos Bom, o poblema é que pega no android não no firestore
Mais ou menos Bom, o poblema é que pega no android não no firestore*/

//package com.test.gravardados
//
//import android.os.Bundle
//import android.util.Log
//import android.view.View
//import android.widget.ArrayAdapter
//import android.widget.AutoCompleteTextView
//import android.widget.TextView
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import com.google.firebase.firestore.FirebaseFirestore
//import com.google.firebase.firestore.SetOptions
//import com.test.gravardados.databinding.ActivityGestorAlimenticiaBinding
//import java.text.SimpleDateFormat
//import java.util.*
//
//class GestorAlimenticia : AppCompatActivity() {
//
//    lateinit var binding: ActivityGestorAlimenticiaBinding
//    private val db = FirebaseFirestore.getInstance()
//
//    val snackItems = arrayOf("Cedo", "Manhã", "Tarde")
//    private lateinit var autoCompleteTextView: AutoCompleteTextView
//    private lateinit var adapterItems: ArrayAdapter<String>
//
//    private var selectedPeriod: String? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityGestorAlimenticiaBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        autoCompleteTextView = findViewById(com.test.gravardados.R.id.option_period)
//        adapterItems = ArrayAdapter(
//            this,
//            com.test.gravardados.R.layout.list_item,
//            snackItems
//        )
//        autoCompleteTextView.threshold = 1
//        autoCompleteTextView.dropDownHeight = 465
//        autoCompleteTextView.setAdapter(adapterItems)
//
//        autoCompleteTextView.setOnItemClickListener { adapterView, _, position, _ ->
//            selectedPeriod = adapterView.getItemAtPosition(position).toString()
//            Toast.makeText(this, "Item: $selectedPeriod", Toast.LENGTH_SHORT).show()
//        }
//
//        val currentDate = getCurrentDate()
//        binding.editDate.setText(currentDate)
//
//        // Load data from Firestore when the activity is created
//        loadDataFromFirestore(currentDate)
//
//        binding.btnEnviar.setOnClickListener {
//            val optionperiod = binding.editDate.text.toString().trim()
//
//            if (optionperiod.isNotEmpty() && selectedPeriod != null) {
//                val title = binding.editTitleFood.text.toString()
//                val description = binding.editDescriptionFood.text.toString()
//
//                val newFood = mapOf(
//                    "name" to title,
//                    "categoria" to description
//                )
//
//                val periodo = mapOf(
//                    selectedPeriod!! to newFood
//                )
//
//                db.collection("ADM")
//                    .document(optionperiod)
//                    .set(periodo, SetOptions.merge())
//                    .addOnSuccessListener {
//                        Log.d("TAG", "DocumentSnapshot updated successfully.")
//                        Toast.makeText(this, "Dados salvos com sucesso!", Toast.LENGTH_SHORT).show()
//                        // After saving, reload the data to show updated content
//                        loadDataFromFirestore(optionperiod)
//                    }
//                    .addOnFailureListener { e ->
//                        Log.w("TAG", "Error updating document", e)
//                        Toast.makeText(this, "Erro ao salvar dados.", Toast.LENGTH_SHORT).show()
//                    }
//            } else {
//                Toast.makeText(this, "Preencha todos os campos corretamente", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//
//    // Function to get current date
//    private fun getCurrentDate(): String {
//        val calendar = Calendar.getInstance()
//        val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
//        return dateFormat.format(calendar.time)
//    }
//
//    // Function to load data from Firestore and display it
//    private fun loadDataFromFirestore(date: String) {
//        db.collection("ADM")
//            .document(date)
//            .get()
//            .addOnSuccessListener { document ->
//                if (document != null && document.exists()) {
//                    val data = document.data
//                    if (data != null) {
//                        // Assuming the structure of the Firestore document is:
//                        // { "Cedo": { "name": "Breakfast", "categoria": "Fruit" }, "Tarde": { ... } }
//
//                        val periodData = data[selectedPeriod]
//                        if (periodData != null) {
//                            val periodName = (periodData as Map<*, *>)["name"]
//                            val periodCategory = periodData["categoria"]
//                            // Show the data in a TextView
//                            val displayText = "Name: $periodName\nCategory: $periodCategory"
//                            val textView = findViewById<TextView>(R.id.textViewData)
//                            textView.text = displayText
//                        }
//                    }
//                } else {
//                    Toast.makeText(this, "No data found for the selected date", Toast.LENGTH_SHORT).show()
//                }
//            }
//            .addOnFailureListener { e ->
//                Log.w("TAG", "Error getting document", e)
//                Toast.makeText(this, "Error retrieving data", Toast.LENGTH_SHORT).show()
//            }
//    }
//}



//package com.test.gravardados
//
//import android.content.Intent
//import android.os.Bundle
//import android.util.Log
//import android.widget.ArrayAdapter
//import android.widget.AutoCompleteTextView
//import android.widget.Button
//import android.widget.TextView
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import com.google.firebase.firestore.FirebaseFirestore
//import com.google.firebase.firestore.SetOptions
//import com.test.gravardados.databinding.ActivityGestorAlimenticiaBinding
//
//class GestorAlimenticia : AppCompatActivity() {
//
//    lateinit var binding: ActivityGestorAlimenticiaBinding
//    private val db = FirebaseFirestore.getInstance()
//
//    val snackItems = arrayOf("Cedo", "Manhã", "Tarde")
//    private lateinit var autoCompleteTextView: AutoCompleteTextView
//    private lateinit var adapterItems: ArrayAdapter<String>
//
//    // Variável para armazenar o período selecionado
//    private var selectedPeriod: String? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityGestorAlimenticiaBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//
//        val menuresbutton: Button = findViewById(R.id.menures_button)
//        menuresbutton.setOnClickListener {
//            val navegarTelaMenu = Intent(this, Menu::class.java)
//            startActivity(navegarTelaMenu)
//        }
//
//
//        // Configuração do AutoCompleteTextView
//        autoCompleteTextView = findViewById(com.test.gravardados.R.id.option_period)
//        adapterItems = ArrayAdapter(
//            this,
//            com.test.gravardados.R.layout.list_item,
//            snackItems
//        )
//        autoCompleteTextView.threshold = 1
//        autoCompleteTextView.dropDownHeight = 465
//        autoCompleteTextView.setAdapter(adapterItems)
//
//        // Captura o item selecionado
//        autoCompleteTextView.setOnItemClickListener { adapterView, _, position, _ ->
//            selectedPeriod = adapterView.getItemAtPosition(position).toString()
//            Toast.makeText(this, "Item: $selectedPeriod", Toast.LENGTH_SHORT).show()
//        }
//
//        // Chama o método da classe Menu para obter a data atual
//        val currentDate = Menu.getCurrentDate()  // Preenche o campo de data com a data atual
//        binding.editDate.setText(currentDate)  // Define a data no campo
//
//        // Buscar os dados do Firestore para a data 04-12-2024
//        val targetDate = "04-12-2024" // Data que queremos buscar
//        fetchDataFromFirestore(targetDate)
//
//        // Configuração do botão "Enviar"
//        binding.btnEnviar.setOnClickListener {
//            // Captura a data do campo editDate (já preenchido automaticamente)
//            val optionperiod = binding.editDate.text.toString().trim()
//
//            // Verifica se o período foi selecionado
//            if (optionperiod.isNotEmpty() && selectedPeriod != null) {
//                val title = binding.editTitleFood.text.toString()
//                val description = binding.editDescriptionFood.text.toString()
//
//                // Criação do mapa de dados
//                val newFood = mapOf(
//                    "name" to title,
//                    "categoria" to description
//                )
//
//                // Mapa com o período como chave
//                val periodo = mapOf(
//                    selectedPeriod!! to newFood
//                )
//
//                // Salvando os dados no Firestore
//                db.collection("ADM")
//                    .document(optionperiod)  // Usa a data fornecida automaticamente
//                    .set(periodo, SetOptions.merge())
//                    .addOnSuccessListener {
//                        Log.d("TAG", "DocumentSnapshot updated successfully.")
//                        Toast.makeText(this, "Dados salvos com sucesso!", Toast.LENGTH_SHORT).show()
//                    }
//                    .addOnFailureListener { e ->
//                        Log.w("TAG", "Error updating document", e)
//                        Toast.makeText(this, "Erro ao salvar dados.", Toast.LENGTH_SHORT).show()
//                    }
//            } else {
//                // Se não houver período selecionado
//                Toast.makeText(this, "Preencha todos os campos corretamente", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//
//    // Função para buscar os dados do Firestore
//    private fun fetchDataFromFirestore(date: String) {
//        db.collection("ADM")
//            .document(date)  // Buscar pelo documento com a data específica
//            .get()
//            .addOnSuccessListener { document ->
//                if (document.exists()) {
//                    val title = document.getString("title") // Pegue o campo "title"
//                    val description = document.getString("description") // Pegue o campo "description"
//
//                    // Atualize os campos na interface com os dados do Firestore
//                    binding.editTitleFood.setText(title ?: "")
//                    binding.editDescriptionFood.setText(description ?: "")
//                } else {
//                    Toast.makeText(this, "Documento não encontrado para a data $date", Toast.LENGTH_SHORT).show()
//                }
//            }
//            .addOnFailureListener { e ->
//                Log.w("TAG", "Error getting document", e)
//                Toast.makeText(this, "Erro ao buscar dados.", Toast.LENGTH_SHORT).show()
//            }
//    }
//}






/*ESSE DAQUI É O MELHOR
ESSE DAQUI É O MELHOR
ESSE DAQUI É O MELHOR*/

//package com.test.gravardados
//
//import android.os.Bundle
//import android.util.Log
//import android.view.View
//import android.widget.ArrayAdapter
//import android.widget.AutoCompleteTextView
//import android.widget.TextView
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import com.google.firebase.firestore.FirebaseFirestore
//import com.google.firebase.firestore.SetOptions
//import com.test.gravardados.databinding.ActivityGestorAlimenticiaBinding
//import java.text.SimpleDateFormat
//import java.util.*
//
//class GestorAlimenticia : AppCompatActivity() {
//
//    lateinit var binding: ActivityGestorAlimenticiaBinding
//    private val db = FirebaseFirestore.getInstance()
//
//    val snackItems = arrayOf("Cedo", "Manhã", "Tarde")
//    private lateinit var autoCompleteTextView: AutoCompleteTextView
//    private lateinit var adapterItems: ArrayAdapter<String>
//
//    // Variável para armazenar o período selecionado
//    private var selectedPeriod: String? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityGestorAlimenticiaBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        // Configuração do AutoCompleteTextView
//        autoCompleteTextView = findViewById(com.test.gravardados.R.id.option_period)
//        adapterItems = ArrayAdapter(
//            this,
//            com.test.gravardados.R.layout.list_item,
//            snackItems
//        )
//        autoCompleteTextView.threshold = 1
//        autoCompleteTextView.dropDownHeight = 465
//        autoCompleteTextView.setAdapter(adapterItems)
//
//        // Captura o item selecionado
//        autoCompleteTextView.setOnItemClickListener { adapterView, _, position, _ ->
//            selectedPeriod = adapterView.getItemAtPosition(position).toString()
//            Toast.makeText(this, "Item: $selectedPeriod", Toast.LENGTH_SHORT).show()
//        }
//
//        // Preencher automaticamente o campo de data com a data atual
//        val currentDate = getCurrentDate()
//        binding.editDate.setText(currentDate)  // Preenche o campo de data com a data atual
//
//        // Configuração do botão "Enviar"
//        binding.btnEnviar.setOnClickListener {
//            // Captura a data do campo editDate (já preenchido automaticamente)
//            val optionperiod = binding.editDate.text.toString().trim()
//
//            // Verifica se o período foi selecionado
//            if (optionperiod.isNotEmpty() && selectedPeriod != null) {
//                val title = binding.editTitleFood.text.toString()
//                val description = binding.editDescriptionFood.text.toString()
//
//                // Criação do mapa de dados
//                val newFood = mapOf(
//                    "name" to title,
//                    "categoria" to description
//                )
//
//                // Mapa com o período como chave
//                val periodo = mapOf(
//                    selectedPeriod!! to newFood
//                )
//
//                // Salvando os dados no Firestore
//                db.collection("ADM")
//                    .document(optionperiod)  // Usa a data fornecida automaticamente
//                    .set(periodo, SetOptions.merge())
//                    .addOnSuccessListener {
//                        Log.d("TAG", "DocumentSnapshot updated successfully.")
//                        Toast.makeText(this, "Dados salvos com sucesso!", Toast.LENGTH_SHORT).show()
//                    }
//                    .addOnFailureListener { e ->
//                        Log.w("TAG", "Error updating document", e)
//                        Toast.makeText(this, "Erro ao salvar dados.", Toast.LENGTH_SHORT).show()
//                    }
//            } else {
//                // Se não houver período selecionado
//                Toast.makeText(this, "Preencha todos os campos corretamente", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//
//    // Função para obter a data atual formatada
//    private fun getCurrentDate(): String {
//        val calendar = Calendar.getInstance()
//        val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
//        return dateFormat.format(calendar.time)
//    }
//}




//package com.test.gravardados
//
//import android.os.Bundle
//import android.util.Log
//import android.view.View
//import android.widget.ArrayAdapter
//import android.widget.AutoCompleteTextView
//import android.widget.TextView
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import com.google.firebase.firestore.FirebaseFirestore
//import com.google.firebase.firestore.SetOptions
//import com.test.gravardados.databinding.ActivityGestorAlimenticiaBinding
//
//class GestorAlimenticia : AppCompatActivity() {
//
//    lateinit var binding: ActivityGestorAlimenticiaBinding
//    private val db = FirebaseFirestore.getInstance()
//
//    val snackItems = arrayOf("Cedo", "Manhã", "Tarde")
//    private lateinit var autoCompleteTextView: AutoCompleteTextView
//    private lateinit var adapterItems: ArrayAdapter<String>
//
//    // Variável para armazenar o período selecionado
//    private var selectedPeriod: String? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityGestorAlimenticiaBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        // Configuração do AutoCompleteTextView
//        autoCompleteTextView = findViewById(com.test.gravardados.R.id.option_period)
//        adapterItems = ArrayAdapter(
//            this,
//            com.test.gravardados.R.layout.list_item,
//            snackItems
//        )
//        autoCompleteTextView.threshold = 1
//        autoCompleteTextView.dropDownHeight = 465
//        autoCompleteTextView.setAdapter(adapterItems)
//
//        // Captura o item selecionado
//        autoCompleteTextView.setOnItemClickListener { adapterView, _, position, _ ->
//            selectedPeriod = adapterView.getItemAtPosition(position).toString()
//            Toast.makeText(this, "Item: $selectedPeriod", Toast.LENGTH_SHORT).show()
//        }
//
//        // Configuração do botão "Enviar"
//        binding.btnEnviar.setOnClickListener {
//            // Captura a data digitada pelo usuário
//            val optionperiod = binding.editDate.text.toString().trim()  // Captura a data do campo editDate
//
//            // Verifica se a data foi preenchida e o período foi selecionado
//            if (optionperiod.isNotEmpty() && selectedPeriod != null) {
//                val title = binding.editTitleFood.text.toString()
//                val description = binding.editDescriptionFood.text.toString()
//
//                // Criação do mapa de dados
//                val newFood = mapOf(
//                    "name" to title,
//                    "categoria" to description
//                )
//
//                // Mapa com o período como chave
//                val periodo = mapOf(
//                    selectedPeriod!! to newFood
//                )
//
//                // Salvando os dados no Firestore
//                db.collection("ADM")
//                    .document(optionperiod)  // Usa a data fornecida pelo usuário
//                    .set(periodo, SetOptions.merge())
//                    .addOnSuccessListener {
//                        Log.d("TAG", "DocumentSnapshot updated successfully.")
//                        Toast.makeText(this, "Dados salvos com sucesso!", Toast.LENGTH_SHORT).show()
//                    }
//                    .addOnFailureListener { e ->
//                        Log.w("TAG", "Error updating document", e)
//                        Toast.makeText(this, "Erro ao salvar dados.", Toast.LENGTH_SHORT).show()
//                    }
//            } else {
//                // Se não houver data ou período selecionado
//                Toast.makeText(this, "Preencha todos os campos corretamente", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//}






//package com.test.gravardados
//
//import android.os.Bundle
//import android.util.Log
//import android.view.View
//import android.widget.ArrayAdapter
//import android.widget.AutoCompleteTextView
//import android.widget.TextView
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import com.google.firebase.firestore.FirebaseFirestore
//import com.google.firebase.firestore.SetOptions
//import com.test.gravardados.databinding.ActivityGestorAlimenticiaBinding
//
//class GestorAlimenticia : AppCompatActivity() {
//
//    lateinit var binding: ActivityGestorAlimenticiaBinding
//    private val db = FirebaseFirestore.getInstance()
//
//    val snackItems = arrayOf("Cedo", "Manhã", "Tarde")
//    private lateinit var autoCompleteTextView: AutoCompleteTextView
//    private lateinit var adapterItems: ArrayAdapter<String>
//
//    // Variável para armazenar o período selecionado
//    private var selectedPeriod: String? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityGestorAlimenticiaBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        // Configuração do AutoCompleteTextView
//        autoCompleteTextView = findViewById(com.test.gravardados.R.id.option_period)
//        adapterItems = ArrayAdapter(
//            this,
//            com.test.gravardados.R.layout.list_item,
//            snackItems
//        )
//        autoCompleteTextView.threshold = 1
//        autoCompleteTextView.dropDownHeight = 465
//        autoCompleteTextView.setAdapter(adapterItems)
//
//        // Captura o item selecionado
//        autoCompleteTextView.setOnItemClickListener { adapterView, _, position, _ ->
//            selectedPeriod = adapterView.getItemAtPosition(position).toString()
//            Toast.makeText(this, "Item: $selectedPeriod", Toast.LENGTH_SHORT).show()
//        }
//
//        // Configuração do botão "Enviar"
//        binding.btnEnviar.setOnClickListener {
//            // Verifica se o período foi selecionado
//            if (selectedPeriod != null) {
//                val optionperiod = "11-10-2024" // Exemplo de data fixa
//                val title = binding.editTitleFood.text.toString()
//                val description = binding.editDescriptionFood.text.toString()
//
//                // Criação do mapa de dados
//                val newFood = mapOf(
//                    "name" to title,
//                    "categoria" to description
//                )
//
//                // Mapa com o período como chave
//                val periodo = mapOf(
//                    selectedPeriod!! to newFood
//                )
//
//                // Salvando os dados no Firestore
//                db.collection("ADM")
//                    .document(optionperiod)
//                    .set(periodo, SetOptions.merge())
//                    .addOnSuccessListener {
//                        Log.d("TAG", "DocumentSnapshot updated successfully.")
//                        Toast.makeText(this, "Dados salvos com sucesso!", Toast.LENGTH_SHORT).show()
//                    }
//                    .addOnFailureListener { e ->
//                        Log.w("TAG", "Error updating document", e)
//                        Toast.makeText(this, "Erro ao salvar dados.", Toast.LENGTH_SHORT).show()
//                    }
//            } else {
//                Toast.makeText(this, "Selecione um período", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//}




//package com.test.gravardados;
//
//import android.R
//import android.annotation.SuppressLint
//import android.os.Bundle
//import android.util.Log
//import android.view.View
//import android.widget.AdapterView
//import android.widget.ArrayAdapter
//import android.widget.AutoCompleteTextView
//import android.widget.Button
//import android.widget.Spinner
//import android.widget.TextView
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import com.google.firebase.Firebase
//import com.google.firebase.firestore.FirebaseFirestore
//import com.google.firebase.firestore.SetOptions
//import com.google.firebase.firestore.firestore
//import com.test.gravardados.databinding.ActivityGestorAlimenticiaBinding
//
//
//class GestorAlimenticia : AppCompatActivity() {
//
//    lateinit var binding: ActivityGestorAlimenticiaBinding
//    private val db = FirebaseFirestore.getInstance()
////    var snackitems: Array<String> = arrayOf("Item 1", "Item 2", "Item 3")
//
//    val snackItems = arrayOf("Cedo", "Manhã", "Tarde")
//    private lateinit var autoCompleteTextView: AutoCompleteTextView
//    private lateinit var adapterItems: ArrayAdapter<String>
//
////    val btnenviar : Button = findViewById(com.test.gravardados.R.id.btn_enviar)
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityGestorAlimenticiaBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        binding.btnEnviar.setOnClickListener {
//            val db = Firebase.firestore
//
//            val optionperiod = "10-10-2024"
//
//            val title = binding.editTitleFood.text.toString()
//            val description = binding.editDescriptionFood.text.toString()
//
//            val newFood =  mapOf(
//                "name" to title,
//                "categoria" to description
//            )
//
//            val periodo =  mapOf(snackItems to newFood)
//            db.collection("ADM")
//                .document(optionperiod)
//                .set(periodo, SetOptions.merge())
//                .addOnSuccessListener {
//                    Log.d("TAG", "DocumentSnapshot updated successfully.")
//                }
//                .addOnFailureListener { e ->
//                    Log.w("TAG", "Error updating document", e)
//                }
//        }
//
//        autoCompleteTextView = findViewById(com.test.gravardados.R.id.option_period)
//
//        adapterItems =
//            ArrayAdapter(
//                this,
//                com.test.gravardados.R.layout.list_item,
//                snackItems
//            )
//
//        autoCompleteTextView.threshold = 1
//
//        autoCompleteTextView.dropDownHeight = 465
//
//        autoCompleteTextView.setAdapter(adapterItems)
//
//
//        autoCompleteTextView.setOnItemClickListener { adapterView, View, position, id ->
//            val item = adapterView.getItemAtPosition(position).toString()
//            Toast.makeText(this, "Item: $item", Toast.LENGTH_SHORT).show()
//            val textView = autoCompleteTextView as TextView
//            textView.textSize = 16f
//        }
//    }
//}




//            val spinner: Spinner = findViewById(R.id.view_spinner_snack)
//
//            val snackItems = listOf("Cedo", "Manhã", "Tarde")
//            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, snackItems)
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//            spinner.adapter = adapter
//
//
//            // Usando um ArrayAdapter para preencher os dados do Spinner
////            val adapter = ArrayAdapter.createFromResource(
////                this,
////                R.array.snack_items,
////                android.R.layout.simple_spinner_item)
//
//            // Define o layout de exibição para cada item
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//
//            // Atribui o adaptador ao Spinner
//            spinner.adapter = adapter
//
//            // Configura um listener para capturar seleções
//            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//                override fun onItemSelected(
//                    parentView: AdapterView<*>,
//                    selectedItemView: View?,
//                    position: Int,
//                    id: Long
//                ) {
//                    val selectedItem = parentView.getItemAtPosition(position).toString()
//                    Toast.makeText(this@GestorAlimenticia, "Selecionado: $selectedItem", Toast.LENGTH_SHORT).show()
//                }
//
//                override fun onNothingSelected(parentView: AdapterView<*>) {
//                    // Não há seleção
//                }
//            }
