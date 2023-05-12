package com.ubaya160420092.a160420092_ubayakuliner.viewmodel

import android.app.Application
import android.util.Log
import android.util.MalformedJsonException
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya160420092.a160420092_ubayakuliner.model.user
import java.net.MalformedURLException

class LoginViewModel(application: Application): AndroidViewModel(application) {
    val userLD= MutableLiveData<List<user>>()
    val TAG = "benTag"
    private var queue: RequestQueue? = null

    fun fetch(username:String, password:String) {

        queue= Volley.newRequestQueue(getApplication())
        val url="https://my-json-server.typicode.com/benhardtitathiono/UbayaKuliner/user?username=$username&password=$password"

        val stringRequest= StringRequest(
            Request.Method.GET, url,{
                //val sType=object : TypeToken<Student>(){}.type
                val result= Gson().fromJson<ArrayList<user>>(it, object : TypeToken<ArrayList<user>>(){}.type)
                userLD.value=result.toList()

                Log.d("showvoley", result.toString())
                Log.d("showvoley", username)
                Log.d("showvoley", password)
            },
            {
                try {
                    throw it
                } catch (e: MalformedURLException){
                    Log.e("showvoley", "Invalid URL: $url", e)
                } catch (e: Exception){
                    Log.d("showvoley", e.toString())
                }
            }
        )

        stringRequest.tag=TAG
        queue?.add(stringRequest)
    }

    fun fetch(username:String) {

        queue= Volley.newRequestQueue(getApplication())
        val url="https://my-json-server.typicode.com/benhardtitathiono/UbayaKuliner/user?username=$username"

        val stringRequest= StringRequest(
            Request.Method.GET, url,{
                //val sType=object : TypeToken<Student>(){}.type
                val result= Gson().fromJson<ArrayList<user>>(it, object : TypeToken<ArrayList<user>>(){}.type)
                userLD.value=result.toList()

                Log.d("showvoley", result.toString())
                Log.d("showvoley", username)
            },
            {
                try {
                    throw it
                } catch (e: MalformedURLException){
                    Log.e("showvoley", "Invalid URL: $url", e)
                } catch (e: Exception){
                    Log.d("showvoley", e.toString())
                }
            }
        )

        stringRequest.tag=TAG
        queue?.add(stringRequest)
    }
}