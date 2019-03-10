package com.thinco.previemos

import android.util.Log
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import java.util.*


class previasDAO() {
    //TODO instanciar cada vez que se hagan consultas con previas.
    //TODO cambiar la implementacion por un metodo estatico
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("previas")


    fun cargarPrevia(previa: Previas){
        val id: String = previa.id.toString()
        if (previa.publico){
            myRef.child("publicas").child(id).setValue(previa)
        }else{
            myRef.child("privadas").child(id).setValue(previa)
        }
    }

}
