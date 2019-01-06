package com.thinco.previemos

import android.content.Intent
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
import com.facebook.login.widget.ProfilePictureView
import com.google.firebase.auth.FirebaseAuth

import kotlinx.android.synthetic.main.activity_inicio.*
import kotlinx.android.synthetic.main.content_inicio.*
import kotlinx.android.synthetic.main.toolbar_with_buttons.*
import java.util.*
import java.util.Random
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Debug
import android.util.Log
import com.facebook.*
import de.hdodenhof.circleimageview.CircleImageView
import org.json.JSONObject
import java.net.URL
import java.util.logging.Logger


class InicioActivity : AppCompatActivity() {

    var previas: ArrayList<String> = ArrayList()


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
            val intent = Intent(applicationContext, NuevaPreviaActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "Creando nueva previa", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Esta funcion es para llamar al perfil del usuario.
     * TODO traspasar esta funcionalidad a una clase aparte para no repetir codigo. ahora en cada actividad donde se pueda llamar al perfil del usuario vamos a tener que repetir este bloque de codigo
     */
    private fun armarDialogDePerfil() {
        var name: String = ""
        var fbid: String = ""

        var dialog = AlertDialog.Builder(this)
        var view = layoutInflater.inflate(R.layout.profile_dialog, null)

        val btnClose = view.findViewById<View>(R.id.BtnCloseProfileDialog) as ImageView
        val btnLogOut = view.findViewById<View>(R.id.BtnLogOut) as Button

        var tvProfileName = view.findViewById<View>(R.id.tvUserName) as TextView
        var imgProfile = view.findViewById<ProfilePictureView>(R.id.facebookProfilePic)

        var request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken()) { `object`, response ->
            try {
                //here is the data that you want
                Log.d("FBLOGIN_JSON_RES", `object`.toString())

                if (`object`.has("id")) {
                    fbid = `object`.getString("id")
                    name = `object`.getString("name")
                    imgProfile.profileId=fbid
                    tvProfileName.text = name

                } else {
                    Log.e("FBLOGIN_FAILD", `object`.toString())
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        val parameters = Bundle()
        parameters.putString("fields", "name,email,id,picture.type(large)")
        request.parameters = parameters
        request.executeAsync()



        Log.d("TAGGGG", name + "Nombre  + id:" + fbid)

        dialog.setView(view)
        dialog.setCancelable(false)
        val dialogShow = dialog.create()
        dialogShow.getWindow().setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        dialogShow.show()

        btnClose.setOnClickListener {
            dialogShow.dismiss()
        }

        btnLogOut.setOnClickListener {
            dialogShow.dismiss()
        }
    }

    private fun addPrevias() {

        for (i in Random().nextInt(20) downTo 4 step 1)
            previas.add("Tu hermana en " + i)
    }

}
