package com.moviles.supletoriomoviles

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.util.Log
import android.widget.Toast
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.tapadoo.alerter.Alerter
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_crear_aplicacion.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


class CrearAplicacionActivity : AppCompatActivity() {

    var tipo = false
    var idS = 0

    var pathActualFoto = ""
    var respuestaBarcode = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_aplicacion)

        btn_foto.setOnClickListener {
            this.tomarFoto()
        }

        val type = intent.getStringExtra("tipo")
        val aplicacionRecivida = intent.getParcelableExtra<Aplicacion>("Aplicacion")
        idS = aplicacionRecivida.soId
        if (type == "Edit"){
            txt_id_ap.setText(aplicacionRecivida.idAP.toString())
            txt_peso_ap.setText(aplicacionRecivida.pesoGigas.toString())
            txt_versiones_ap.setText(aplicacionRecivida.versiones.toString())
            txt_nombres_ap.setText(aplicacionRecivida.nombres.toString())
            txt_costo_ap.setText(aplicacionRecivida.costo.toString())
            txt_lanzamiento_ap.setText(aplicacionRecivida.fechaLanzamiento.toString())
            txt_url_ap.setText(aplicacionRecivida.urlDescarga.toString())

            tipo = true

        }

        btn_cancelar_ap.setOnClickListener {
            this.irAMain()
        }
        btn_guardar_ap.setOnClickListener {
            this.guardarDatos()
        }

    }

    fun guardarDatos(){
        if (txt_id_ap.text.toString().isEmpty()||
            txt_peso_ap.text.toString().isEmpty()||
            txt_versiones_ap.text.toString().isEmpty()||
            txt_nombres_ap.text.toString().isEmpty()||
            txt_costo_ap.text.toString().isEmpty()||
            txt_lanzamiento_ap.text.toString().isEmpty()||
            txt_url_ap.text.toString().isEmpty()){
            Alerter.create(this).setTitle("Campos Vacios")
                .setText("Completa la informacion de todos los campos")
                .setBackgroundColorRes(R.color.error_color_material_dark)
                .enableSwipeToDismiss()
                .show()
        }else{
            var id = txt_id_ap.text.toString().toInt()
            var pesoGigas = txt_peso_ap.text.toString().toDouble()
            var versiones = txt_versiones_ap.text.toString().toInt()
            var nombres =  txt_nombres_ap.text.toString()
            var costo = txt_costo_ap.text.toString().toDouble()
            var fechaLanzamiento = txt_lanzamiento_ap.text.toString()
            var urlDescarga =  txt_url_ap.text.toString()
            var soId = idS
            var aplicacion  = Aplicacion(id,pesoGigas,versiones,nombres,urlDescarga,fechaLanzamiento,costo,soId)
            if (tipo == true){

                DatabaseAPP.editarSO(aplicacion)
            }else{

                DatabaseAPP.insertarSO(aplicacion)
                Log.i("mensaje",aplicacion.toString())
            }
            Toasty.success(this, "Datos registrados", Toast.LENGTH_LONG, true).show()
            val intent = Intent(this,MenuPrincipalActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }
    fun irAMain(){
        val intent = Intent(this, ListaAPPActivity::class.java)
        startActivity(intent)
    }

    fun tomarFoto(){
        val archivoImgen = crearArchivo("JPEG", Environment.DIRECTORY_PICTURES,".jpg")
        pathActualFoto = archivoImgen.absolutePath
        enviarIntentFoto(archivoImgen)
    }

    private fun crearArchivo(prefijo: String, directorio: String, extension: String): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = prefijo + timeStamp + "_"
        val storageDir = getExternalFilesDir(directorio)

        return File.createTempFile(imageFileName,extension,storageDir)
    }
    private fun enviarIntentFoto(archivoFile: File) {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val photoURI: Uri = FileProvider.getUriForFile(this, "com.moviles.supletoriomoviles",archivoFile)
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,photoURI)
        if (takePictureIntent.resolveActivity(packageManager)!= null){
            startActivityForResult(takePictureIntent,TOMAR_FOTO_REQUEST)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode){
            TOMAR_FOTO_REQUEST->{
                if (resultCode == Activity.RESULT_OK){

                    obtenerInfoCodigoBarras(obtenerBitmapDeArchivo(pathActualFoto))
                }
            }
        }
    }


    fun obtenerBitmapDeArchivo(path: String): Bitmap {
        val archivoImagen = File(path)
        return BitmapFactory.decodeFile(archivoImagen.getAbsolutePath())
    }

    fun obtenerInfoCodigoBarras(bitmap: Bitmap) {
        val image = FirebaseVisionImage.fromBitmap(bitmap)
        val detector = FirebaseVision.getInstance()
            .visionBarcodeDetector
        Log.i("mensaje", "------- Entro a detectar")
        val result = detector.detectInImage(image)
            .addOnSuccessListener { barCodes ->
                Log.i("mensaje", "------- tamano del barcode ${barCodes.size}")
                respuestaBarcode.add("Ejemplo")
                for (barcode in barCodes) {
                    val bounds = barcode.getBoundingBox()
                    val corners = barcode.getCornerPoints()

                    val rawValue = barcode.getRawValue()

                    Log.i("mensaje", "------- $bounds")
                    Log.i("mensaje", "------- $corners")
                    Log.i("mensaje", "------- $rawValue")

                    respuestaBarcode.add(rawValue.toString())
                }
                txt_id_ap.setText(respuestaBarcode[1])
            }
            .addOnFailureListener {
                Log.i("mensaje", "------- No reconocio nada")
            }
    }
    companion object {
        val TOMAR_FOTO_REQUEST = 1
    }
}
