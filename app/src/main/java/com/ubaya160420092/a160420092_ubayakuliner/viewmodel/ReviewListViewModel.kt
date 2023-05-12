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
import com.ubaya160420092.a160420092_ubayakuliner.model.resto
import com.ubaya160420092.a160420092_ubayakuliner.model.review

class ReviewListViewModel (application: Application): AndroidViewModel(application) {
    val reviewLD = MutableLiveData<ArrayList<review>>()
    val reviewLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    val TAG = "benTag"
    private var queue: RequestQueue? = null


    fun refresh(namaResto:String) {
        loadingLD.value=true
        reviewLoadErrorLD.value=false

        queue= Volley.newRequestQueue(getApplication())
        val url="https://my-json-server.typicode.com/benhardtitathiono/UbayaKuliner/review?resto=$namaResto"

        val stringRequest= StringRequest(
            Request.Method.GET, url,{
                val sType=object : TypeToken<ArrayList<review>>(){}.type
                val result= Gson().fromJson<ArrayList<review>>(it, sType)
                reviewLD.value=result
                loadingLD.value=false

                Log.d("showvoley", result.toString())
            },
            {
                Log.d("showvoley", it.toString())
                reviewLoadErrorLD.value=true
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