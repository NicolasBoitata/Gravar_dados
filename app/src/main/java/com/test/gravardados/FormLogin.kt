package com.test.gravardados

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class FormLogin : AppCompatActivity() {

    private lateinit var editNome: EditText
    private lateinit var editEmail: EditText
    private lateinit var editSenha: EditText
    private lateinit var btEntrar: Button
    private val mensagens = arrayOf("preencha todos os campos", "Login Efetuado com Sucesso")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_login)

        supportActionBar?.hide()
        iniciarComponentes()


        btEntrar.setOnClickListener { v ->
            val email = editEmail.text.toString()
            val senha = editSenha.text.toString()

            if (email.isEmpty() || senha.isEmpty()) {
                val snackbar = Snackbar.make(v, mensagens[0], Snackbar.LENGTH_SHORT)
                snackbar.setBackgroundTint(Color.WHITE)
                snackbar.setTextColor(Color.BLACK)
                snackbar.show()
            } else {
                autenticarUsuario(v)
            }
        }
    }

    private fun autenticarUsuario(view: View) {
        val email = editEmail.text.toString()
        val senha = editSenha.text.toString()

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    Handler().postDelayed({
                        navegarTelaPrincipal()
                    }, 1000)
                } else {
                    val erro = try {
                        throw task.exception!!
                    } catch (e: Exception) {
                        "Erro ao logar usuário"
                    }

                    val snackbar = Snackbar.make(view, erro, Snackbar.LENGTH_SHORT)
                    snackbar.setBackgroundTint(Color.WHITE)
                    snackbar.setTextColor(Color.BLACK)
                    snackbar.show()
                }
            }
    }

    private fun navegarTelaPrincipal() {
        val intent = Intent(this, TelaPrincipal::class.java)
        startActivity(intent)
        finish()
    }

    private fun iniciarComponentes() {
        editEmail = findViewById(R.id.emailFields)
        editSenha = findViewById(R.id.passwordFields)
        btEntrar = findViewById(R.id.btnLogin)
    }

    override fun onStart() {
        super.onStart()
        
        val usuarioAtual = FirebaseAuth.getInstance().currentUser
        
        if (usuarioAtual != null) {
            navegarTelaPrincipal()
        }
    }
}

//    class FormLogin : AppCompatActivity() {
//
//override fun onCreate(savedInstanceState: Bundle?) {
//    super.onCreate(savedInstanceState)
//    binding = Activity FormLoginBinding.inflate(LayoutInflater)
//    setContentView(binding.root)
//
//        private lateinit var binding: ActivityFormLoginBinding
//        private val auth = FirebaseAuth.getInstance()
//
//        binding.btEntrar.setOnClickListener { view ->
//            val email = binding.editEmail.text.toString()
//            val senha = binding.editSenha.text.toString()
//
//            if (email.isEmpty() || senha.isEmpty()) {
//                val snackbar = Snackbar.make (view, "Preencha todos os campos!", Snackbar.LENGTH_SHORT)
//                snackbar.setBackgroundTint (Color.RED)
//                snackbar.show()
//            }else{
//                auth.signInWithEmail And Password (email, senha).addOnComplete Listener { autenticacao ->
//                    if (autenticacao.isSuccessful){
//                        navegarTelaPrincipal()
//                    }
//
//                }.addOnFailureListener {
//                    val snackbar = Snackbar.make(view, "Erro ao fazer o login do usuário!", Snackbar.LENGTH_SHORT)
//                    snackbar.setBackground Tint (Color.RED)
//                    snackbar.show()
//                }
//            }
//        }
//        binding.txtTelaCadastro.setOnClickListener {
//            val intent Intent(this, FormCadastro::class.java)
//            startActivity(intente)
//        }
//    }