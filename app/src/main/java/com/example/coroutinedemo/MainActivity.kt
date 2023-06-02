package com.example.coroutinedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.coroutinedemo.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var count = 0
    private val RESULT_1 = "Result"
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            //IO,Main,Default
            CoroutineScope(Dispatchers.IO).launch {

                AddNumber()
            }

        }

        binding.textView.setText(" ${count} ")

    }

    private suspend fun AddNumber(){
        ThreadLog("Addition Successful")

        AddNos()
        delay(1000)


        Thread.sleep(1000)

    }

    private  suspend fun  AddNos(){

        ThreadLog("Added Numbers")

        CoroutineScope(Dispatchers.Main).launch {
            binding.textView.setText("${count++}")
            showToast("Added Numbers")
        }

    }
    private fun ThreadLog(methodName: String){
        val MSG ="Kotlin"
        Log.d(MSG,"debug: ${methodName}: ${Thread.currentThread().name}")
    }



    private fun showToast(text:String){
        Toast.makeText(applicationContext,text,Toast.LENGTH_SHORT)
    }
}