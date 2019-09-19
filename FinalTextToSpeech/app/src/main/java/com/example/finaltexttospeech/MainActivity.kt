package com.example.finaltexttospeech

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(),TextToSpeech.OnInitListener {

    private var tts: TextToSpeech? = null

    private var homeWelcomeMessageText: TextView? = null
    private var homeInstructionUpText: TextView? = null
    private var homeInstructionDownText: TextView? = null
    private var homeInstructionLeftText: TextView? = null
    private var homeInstructionRightText: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tts = TextToSpeech(this, this)

        homeWelcomeMessageText = this.homeWelcomeMessageTV
        homeInstructionUpText = this.homeInstructionUpTV
        homeInstructionDownText = this.homeInstructionDownTV
        homeInstructionLeftText = this.homeInstructionLeftTV
        homeInstructionRightText = this.homeInstructionRightTV

    }

    override fun onInit(status: Int) {

        if (status == TextToSpeech.SUCCESS) {
            // set US English as language for tts
            val result = tts!!.setLanguage(Locale.US)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS","The Language specified is not supported!")
            } else {
                Log.i("TTS", "Language Supported.")
            }
            Log.i("TTS", "Initialization success.")

        } else {
            Log.e("TTS", "Initilization Failed!")
        }

        val homeWelcomeMessageString = homeWelcomeMessageText!!.text.toString()
        val homeInstructionUpString = homeInstructionUpText!!.text.toString()
        val homeInstructionDownString = homeInstructionDownText!!.text.toString()
        val homeInstructionLeftString = homeInstructionLeftText!!.text.toString()
        val homeInstructionRightString = homeInstructionRightText!!.text.toString()

        val finalText = homeWelcomeMessageString + homeInstructionUpString + homeInstructionDownString +
                homeInstructionLeftString + homeInstructionRightString

        tts!!.speak(finalText, TextToSpeech.QUEUE_FLUSH, null,"")

    }

    public override fun onDestroy() {
        // Shutdown TTS
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }

}