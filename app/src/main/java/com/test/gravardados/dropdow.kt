package com.test.gravardados

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class dropdow : AppCompatActivity() {

    val snackItems = arrayOf("Cedo", "Manhã", "Tarde")
    private lateinit var autoCompleteTextView: AutoCompleteTextView
    private lateinit var adapterItems: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dropdow)


        // Inicializando o AutoCompleteTextView e o ArrayAdapter
        autoCompleteTextView = findViewById(R.id.auto_complete_txt)
//        val items = listOf("Item1", "Item2", "Item3") // Lista de itens para o adaptador
        adapterItems =
            ArrayAdapter(this, R.layout.list_item, /*listOf("Item1", "Item2", "Item3")*/snackItems)

        autoCompleteTextView.threshold = 1 // Exibe a lista de opções ao digitar 1 caractere
// Para ajustar a altura do dropdown - cuidado, isso pode não funcionar como esperado em todos os casos
        autoCompleteTextView.dropDownHeight = 465 // Ajuste a altura conforme necessário


        // Definindo o adaptador no AutoCompleteTextView(Mostra barra de opções navbar)
        autoCompleteTextView.setAdapter(adapterItems)

        // Configurando o listener para o clique no item(alert)
        autoCompleteTextView.setOnItemClickListener { adapterView, View, position, id ->
            val item = adapterView.getItemAtPosition(position).toString()
            Toast.makeText(this, "Item: $item", Toast.LENGTH_SHORT).show()
//                  mudar centro do dropdown do resultado
            val textView = autoCompleteTextView as TextView
            textView.textSize = 16f  // Alterando o tamanho da fonte do item selecionado
//                list_item.findViewById<TextView>(com.facebook.R.id.list_item)
        }
    }
}

//val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, snackItems)
//autoCompleteTextView.setAdapter(adapter)
//autoCompleteTextView.threshold = 1 // Exibe a lista de opções ao digitar 1 caractere
//
//// Para ajustar a altura do dropdown - cuidado, isso pode não funcionar como esperado em todos os casos
//autoCompleteTextView.dropDownHeight = 500 // Ajuste a altura conforme necessário

//        spinner.post(Runnable {
//            try {
//                val popup: Field = Spinner::class.java.getDeclaredField("mPopup")
//                popup.setAccessible(true)
//                val popUpObject: Any = popup.get(spinner)
//                val popupClass = Class.forName(popUpObject.javaClass.name)
//                val setHeight: Method =
//                    popupClass.getDeclaredMethod("setHeight", Int::class.javaPrimitiveType)
//                setHeight.setAccessible(true)
//
//                // Ajuste a altura do popup com base no número de itens ou outro critério
//                setHeight.invoke(
//                    popUpObject,
//                    600
//                ) // Aqui 600 é a altura em pixels (ajuste conforme necessário)
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        })
//        autoCompleteTextView.post {
//            try {
//                val popupField = autoCompleteTextView::class.java.getDeclaredField("mPopup")
//                popupField.isAccessible = true
//                val popupObject = popupField.get(autoCompleteTextView)
//                val popupClass = popupObject.javaClass
//                val setHeightMethod = popupClass.getDeclaredMethod("setHeight", Int::class.java)
//                setHeightMethod.isAccessible = true
//
//                // Ajuste a altura do popup com base no número de itens ou outro critério
//                setHeightMethod.invoke(popupObject, 1) // 600 pixels - ajuste conforme necessário
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }


//        val spinner: Spinner = findViewById(R.id.base_dropdown)

//        val adapter = ArrayAdapter.createFromResource(
//            this,
//            R.array.snack_items,
//            android.R.layout.simple_spinner_item
//        )

        // Modificando o tamanho da fonte do item selecionado
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//
//        val textView = spinner.selectedView as TextView
//        textView.textSize = 14f  // Alterando o tamanho da fonte do item selecionado
//
//        spinner.adapter = adapter






//import android.os.Bundle
//import android.view.View
//import android.widget.AdapterView
//import android.widget.ArrayAdapter
//import android.widget.Spinner
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//
//class MainActivity : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        // Obtém o Spinner pelo ID
//        val spinner: Spinner = findViewById(R.id.spinner)
//
//        // Cria o ArrayAdapter para preencher os itens do Spinner com o array do resources
//        val adapter = ArrayAdapter.createFromResource(
//            this,
//            R.array.spinner_items,  // Referência correta para o array no strings.xml
//            android.R.layout.simple_spinner_item
//        )
//
//        // Define o layout que será usado quando o Spinner estiver "aberto" (dropdown)
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//
//        // Atribui o adapter ao Spinner
//        spinner.adapter = adapter
//
//        // Configura o listener para capturar a seleção
//        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(
//                parentView: AdapterView<*>,
//                selectedItemView: View?,
//                position: Int,
//                id: Long
//            ) {
//                val selectedItem = parentView.getItemAtPosition(position).toString()
//                // Exibe um Toast com a opção selecionada
//                Toast.makeText(this@MainActivity, "Selecionado: $selectedItem", Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onNothingSelected(parentView: AdapterView<*>) {
//                // Caso nada seja selecionado
//            }
//        }
//    }
//}



//import android.os.Bundle
//import android.widget.ArrayAdapter
//import android.widget.Spinner
//import androidx.appcompat.app.AppCompatActivity
//
//class MainActivity : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        // Obtém o Spinner pelo ID
//        val spinner: Spinner = findViewById(R.id.spinner)
//
//        // Cria o ArrayAdapter para preencher os itens do Spinner com o array do resources
//        val adapter = ArrayAdapter.createFromResource(
//            this,
//            R.array.snack_items,  // Nome do array no strings.xml
//            android.R.layout.simple_spinner_item
//        )
//
//        // Define o layout para o dropdown
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//
//        // Atribui o adapter ao Spinner
//        spinner.adapter = adapter
//    }
//}


//val snackItems = listOf("Cedo", "Manhã", "Tarde")
//val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, snackItems)
//adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//spinner.adapter = adapter
