/*
 * Copyright (c) 2021.
 * Julio Cezar Riffel<julioriffel@gmail.com>
 */

package com.agropackingsolutions.leitor.util

import android.content.Context
import android.media.AudioManager
import android.media.RingtoneManager
import android.media.ToneGenerator
import android.widget.Toast
import com.julioriffel.snippets_android_kotlin.R


fun unrecognizedCode(context: Context, callbackClear: () -> Unit = {}) {
    Toast
        .makeText(
            context,
            context.getString(R.string.unrecognized_code),
            Toast.LENGTH_SHORT
        )
        .show()

    callbackClear()
}

/*
 * Para que seja possível lançar um sinal sonoro logo
 * após a leitura de algum código de barra.
 * */
fun notificationDefault(context: Context) {
    try {
        val notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val ringtone = RingtoneManager.getRingtone(context.applicationContext, notification)
        ringtone.play()
    } catch (e: Exception) {
    }
}

fun notificationBeep() {
    val toneG = ToneGenerator(AudioManager.STREAM_ALARM, 100)
    toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200)
}