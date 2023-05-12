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
import com.ubaya160420092.a160420092_ubayakuliner.model.resto

class DetailKulinerVIewModel (application: Application): AndroidViewModel(application) {
    val menuLD = MutableLiveData<ArrayList<menu>>()
    val menuLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    val TAG = "benTag"
    private var queue: RequestQueue? = null


    fun refresh(resto:String) {
        loadingLD.value=true
        menuLoadErrorLD.value=false

        queue= Volley.newRequestQueue(getApplication())
        val url="https://my-json-server.typicode.com/benhardtitathiono/UbayaKuliner/$resto"

        val stringRequest= StringRequest(
            Request.Method.GET, url,{
                val sType=object : TypeToken<ArrayList<menu>>(){}.type
                val result= Gson().fromJson<ArrayList<menu>>(it, sType)
                menuLD.value=result
                loadingLD.value=false

                Log.d("showvoley", result.toString())
            },
            {
                Log.d("showvoley", it.toString())
                menuLoadErrorLD.value=true
                loadingLD.value=false
            }
        )

        stringRequest.tag=TAG
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}