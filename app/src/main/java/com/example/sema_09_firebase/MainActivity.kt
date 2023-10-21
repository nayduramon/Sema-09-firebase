package com.example.sema_09_firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val db = FirebaseFirestore.getInstance()
        val tvCurso: TextView = findViewById(R.id.tvCurso)
        val tvNota: TextView = findViewById(R.id.tvNota)
        db.collection("courses")
            .addSnapshotListener{ snapshost, e ->

                if (e!= null){
                    Log.w("Firebase", "listen:error", e)
                    return@addSnapshotListener
                }
                for (dc in snapshost!!.documentChanges){
                    when(dc.type){
                        DocumentChange.Type.ADDED ->{
                            Log.d("Firebase", "Data" + dc.document.data)
                            tvCurso.text=dc.document.data["description"].toString()
                            tvNota.text=dc.document.data["score"].toString()
                        }
                        DocumentChange.Type.MODIFIED ->{
                            tvCurso.text=dc.document.data["description"].toString()
                            tvNota.text=dc.document.data["score"].toString()
                        }
                        DocumentChange.Type.REMOVED -> Log.d("Firebase", "Removed Data" + dc.document.data)
                    }
                }

            }
    }
}