package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.model.Weather
import com.example.myapplication.network.ApiError
import com.example.myapplication.network.ApiHelpers
import com.example.myapplication.network.ApiRequestCallback
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiHelpers = ApiHelpers(this)

        apiHelpers.getWeatherByNameAsync("london",
            object : ApiRequestCallback<Weather>() {
                override fun onSuccess(result: Weather?) {
                    super.onSuccess(result)
                    runOnUiThread(object : Runnable {
                        override fun run() { // region Get data
                        }
                    })
                }

                override fun onError(error: ApiError) {
                    super.onError(error)
                    Log.d(
                       MainActivity::class.java.canonicalName,
                        "onError() called with: error.code  = [" + error.code
                            .toString() + " & error.message" + error.message+ "]"
                    )
                }
            }
        )
    }
}
