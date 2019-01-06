package com.thinco.previemos

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_inicio.*
import kotlinx.android.synthetic.main.content_inicio.*
import kotlinx.android.synthetic.main.toolbar_with_buttons.*
import java.util.*
import java.util.Random

class InicioActivity : AppCompatActivity() {

    val previas: ArrayList<String> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)
        setSupportActionBar(toolbarWithButtons)

        BtnProfileIC.setOnClickListener { armarDialogDePerfil() }

        //Agregamos previas de prueba
        addPrevias()

        rv_previas_list.layoutManager = GridLayoutManager(this, 2)
        rv_previas_list.adapter = PreviasAdapter(previas, this)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Ir a crear previas", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

    }

    /**
     * Esta funcion es para llamar al perfil del usuario.
     * TODO traspasar esta funcionalidad a una clase aparte para no repetir codigo. ahora en cada actividad donde se pueda llamar al perfil del usuario vamos a tener que repetir este bloque de codigo
     *
     */
    private fun armarDialogDePerfil() {
        val dialog = AlertDialog.Builder(this)
        val view = layoutInflater.inflate(R.layout.profile_dialog, null)

        val btnClose = view.findViewById<View>(R.id.BtnCloseProfileDialog) as ImageView
        val btnLogOut = view.findViewById<View>(R.id.BtnLogOut) as Button

        val tvProfileName = view.findViewById<View>(R.id.tvUserName) as TextView
        tvProfileName.text = "Nombre de prueba"

        dialog.setView(view)
        dialog.setCancelable(false)
        val dialogShow = dialog.create()
        dialogShow.show()

        btnClose.setOnClickListener {
            Toast.makeText(this, "Cerrando el dialog", Toast.LENGTH_SHORT).show()
            dialogShow.dismiss()
        }

        btnLogOut.setOnClickListener {
            Toast.makeText(this, "Cerrando sesion", Toast.LENGTH_SHORT).show()
            dialogShow.dismiss()
        }
    }

    private fun addPrevias() {

        for (i in Random().nextInt(20) downTo 4 step 1)
            previas.add("Tu hermana en " + i)
    }

}
