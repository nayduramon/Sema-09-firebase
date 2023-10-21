package com.example.sema_09_firebase

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.sema_09_firebase.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

class RegisterActivity2 : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register2)


        val txtFullName: EditText = findViewById(R.id.txtFullName)
        val txtCountry: EditText = findViewById(R.id.txtCountry)
        val txtEmail: EditText = findViewById(R.id.txtEmailRegister)
        val txtPassword: EditText = findViewById(R.id.txtPasswordRegister)
        val btnSave: Button = findViewById(R.id.btnSaveRegister)
        val auth = FirebaseAuth.getInstance()
        val db = FirebaseFirestore.getInstance()
        val collection = db.collection("users")

        btnSave.setOnClickListener{
            val correo = txtEmail.text.toString()
            val clave = txtPassword.text.toString()
            val nombrecompleto = txtFullName.text.toString()
            val pais = txtCountry.text.toString()

            auth.createUserWithEmailAndPassword(correo, clave)
                .addOnCompleteListener(this){task->
                    if (task.isSuccessful){
                        //Se registro en Firebase auth y debera registrarse en Firestore
                        val user: FirebaseUser? = auth.currentUser
                        val uid = user?.uid



                    }
                }
        }
    }
}