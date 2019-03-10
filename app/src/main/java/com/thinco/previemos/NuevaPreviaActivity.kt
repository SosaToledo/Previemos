package com.thinco.previemos

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.ColorFilter
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.IntegerRes
import android.support.annotation.RequiresApi
import android.support.design.widget.FloatingActionButton
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Switch
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_nueva_previa.*
import java.lang.NumberFormatException
import java.text.SimpleDateFormat
import java.util.*

class NuevaPreviaActivity  : AppCompatActivity() {

    var set1 = mutableSetOf<String>("bebidas","gaseosas","musica","vasos")

    lateinit var edNombre:EditText
    lateinit var edUbicacion:EditText
    lateinit var swPrivacidad:Switch

    private fun agregarFotoPrevia() {
        Toast.makeText(this,"Presionado boton agregar foto", Toast.LENGTH_SHORT)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nueva_previa)

        //Desde aca vamos a manejar todos los botones
        val clickListener = View.OnClickListener { view ->
            //Todo borrar estos logs
            when(view.id){
                R.id.FABaddPhoto-> Log.d("FRANK", "Presionado boton foto previa")

                R.id.FABCreatePrevia-> CrearPrevia()

                R.id.imgViewGaseosas->alternarIcono(imgViewGaseosas, "gaseosas")
                R.id.imgViewBebidas->alternarIcono(imgViewBebidas, "bebidas")
                R.id.imgViewParlantes->alternarIcono(imgViewParlantes, "musica")
                R.id.imgViewVasos->alternarIcono(imgViewVasos, "vasos")
            }
        }

        val fotoPrevia = findViewById<ImageView>(R.id.ivPreviaPhoto)
        edNombre = findViewById<EditText>(R.id.ETPreviaName)
        edUbicacion = findViewById<EditText>(R.id.ETPreviaLocation)
        swPrivacidad = findViewById<Switch>(R.id.swPrivacidad)

        val fabFotoPrevia = findViewById<FloatingActionButton>(R.id.FABaddPhoto)
        val fabCreatePrevia = findViewById<FloatingActionButton>(R.id.FABCreatePrevia)

        val icVasos = findViewById<ImageView>(R.id.imgViewVasos)
        val icMusica = findViewById<ImageView>(R.id.imgViewParlantes)
        val icBebidas = findViewById<ImageView>(R.id.imgViewBebidas)
        val icGaseosas = findViewById<ImageView>(R.id.imgViewGaseosas)

        icVasos.setOnClickListener(clickListener)
        icMusica.setOnClickListener(clickListener)
        icBebidas.setOnClickListener(clickListener)
        icGaseosas.setOnClickListener(clickListener)

        fabFotoPrevia.setOnClickListener(clickListener)
        fabCreatePrevia.setOnClickListener (clickListener)
    }

    @SuppressLint("SimpleDateFormat")
    private fun CrearPrevia() {
        //Date().time.toInt()
        try {
            val sdf = SimpleDateFormat("yyyyMMddHHmmss")
            val previa:Previas= Previas(
                    //Date().time.toInt(),
                    sdf.format(Date()).toLong(),
                    !swPrivacidad.isChecked,
                    edNombre.text.toString(),
                    edUbicacion.text.toString(),
                    set1.toList())

            Log.d("FRANK","los datos son ${previa.toString()}")
            val pdao :previasDAO=previasDAO()
            pdao.cargarPrevia(previa)

        }catch (e:NumberFormatException){
            Log.d("FRANK","Error al parsear los datos")

        }
    }

    private fun alternarIcono(imagenParaAlternar: ImageView, elemento: String) {
        //imagenParaAlternar.alpha=imagenParaAlternar.alpha*-1f
        var alpha:Float = imagenParaAlternar.alpha.toFloat()
        when (alpha){
            1f -> {
                imagenParaAlternar.alpha=0.25f
                set1.remove(elemento)
            }
            0.25f -> {
                imagenParaAlternar.alpha=1f
                set1.add(elemento)
            }
        }

    }


}
