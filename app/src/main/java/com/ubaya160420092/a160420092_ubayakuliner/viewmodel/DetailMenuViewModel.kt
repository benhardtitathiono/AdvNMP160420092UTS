package com.ubaya160420092.a160420092_ubayakuliner.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya160420092.a160420092_ubayakuliner.model.menu
import java.net.MalformedURLException
import kotlin.math.log

class DetailMenuViewModel (application: Application): AndroidViewModel(application) {
    val menuLD= MutableLiveData<List<menu>>()
    val TAG = "benTag"
    private var queue: RequestQueue? = null

    fun fetch(nama:String, resto:String) {

        queue= Volley.newRequestQueue(getApplication())
        Log.d("showvoley", nama.toString())
        Log.d("showvoley", resto.toString())
        val url="https://my-json-server.typicode.com/benhardtitathiono/UbayaKuliner/$resto?nama=$nama"

        val stringRequest= StringRequest(
            Request.Method.GET, url,{
                //val sType=object : TypeToken<Student>(){}.type
                val result= Gson().fromJson<ArrayList<menu>>(it, object : TypeToken<ArrayList<menu>>(){}.type)
                Log.d("showvoley", result.toString())
                menuLD.value=result.toList()

                Log.d("showvoley", result.toString())
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