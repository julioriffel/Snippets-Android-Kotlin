/*
 * Copyright (c) 2021. Julio Cezar Riffel<julioriffel@gmail.com>
 */

package com.julioriffel.snippets_android_kotlin.ui

import android.media.RingtoneManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.agropackingsolutions.leitor.util.*
import com.google.zxing.Result
import com.julioriffel.snippets_android_kotlin.R
import com.julioriffel.snippets_android_kotlin.databinding.ActivityLerCodigoBarrasBinding
import me.dm7.barcodescanner.zxing.ZXingScannerView

class LerCodigoBarrasActivity : AppCompatActivity(), ZXingScannerView.ResultHandler {
    val REQUEST_CODE_CAMERA = 182 /* Inteiro aleatório */
    private lateinit var binding: ActivityLerCodigoBarrasBinding
    var msg: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLerCodigoBarrasBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


    override fun onResume() {
        super.onResume()
        /*
         * Registrando a atividade para que ela possa
         * trabalhar os resultados de scan. Seguindo a
         * documentação, o código entra no onResume().
         * */
        binding.zXingScanner.setResultHandler(this)

        restartCameraIfInactive()
    }

    private fun restartCameraIfInactive() {
        if (!binding.zXingScanner.isCameraStarted()) {
            startCamera()
        }
    }


    private fun startCamera() {
        if (!binding.zXingScanner.isFlashSupported(this)) {
            binding.ibFlashlight.visibility = View.GONE
        }

        binding.zXingScanner.startCameraForAllDevices(this)
    }


    /* *** Algoritmos de interpretação de código de barras *** */
    override fun handleResult(result: Result?) {
        /*
         * Padrão Cláusula de Guarda - Caso o resultado seja
         * null, limpa a tela, se houver um último dado lido,
         * apresente uma mensagem e finaliza o processamento
         * do método handleResult().
         * */
        if (result == null) {
            unrecognizedCode(this, { clearContent() })
            return
        }

        processBarcodeResult(
            result.text,
            result.barcodeFormat.name
        )
    }

    private fun processBarcodeResult(text: String, barcodeFormatName: String) {


        /* Modificando interface do usuário. */
        binding.tvContent.text = text
        var msg = "${barcodeFormatName}:${text}\n" + msg

        binding.tvResumo.text = msg
        binding.tvBarCodeType.text = "${getString(R.string.barcode_format)}$barcodeFormatName"

        try {
            val notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val r = RingtoneManager.getRingtone(applicationContext, notification)
            r.play()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        binding.zXingScanner.resumeCameraPreview(this)
    }


    fun clearContent(view: View? = null) {
        binding.tvContent.text = getString(R.string.nothing_read)

    }


    fun flashLight(view: View? = null) {
        /*
         * Utilizando a propriedade tag de Button para salvar o
         * valor atual do status de luz de flash.
         * */
        val value = if (binding.ibFlashlight.tag == null)
            true
        else
            !(binding.ibFlashlight.tag as Boolean)
        binding.ibFlashlight.tag = value /* Sempre o inverso do valor de entrada. */

        if (value) {
            binding.zXingScanner.enableFlash(this, true)
            binding.ibFlashlight.setImageResource(R.drawable.ic_flash_on_baseline_24)
        } else {
            binding.zXingScanner.enableFlash(this, false)
            binding.ibFlashlight.setImageResource(R.drawable.ic_flash_off_baseline_24)
        }
    }

    private fun turnOffFlashlight() {
        binding.ibFlashlight.tag = true
        flashLight()
    }
}