package com.ubaya160420092.a160420092_ubayakuliner.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya160420092.a160420092_ubayakuliner.R

import com.ubaya160420092.a160420092_ubayakuliner.model.resto;
import com.ubaya160420092.a160420092_ubayakuliner.util.loadImage

import java.util.ArrayList;

class RestoListAdapter (val restoList:ArrayList<resto>): RecyclerView.Adapter<RestoListAdapter.RestoViewHolder>() {

    class RestoViewHolder(var view:View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.resto_list_item, parent, false)

        return RestoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return restoList.size
    }

    override fun onBindViewHolder(holder: RestoListAdapter.RestoViewHolder, position: Int) {
        val btnDetail=holder.view.findViewById<Button>(R.id.btnDetail)
        val btnReview=holder.view.findViewById<Button>(R.id.btnReviewResto)
        val imageView=holder.view.findViewById<ImageView>(R.id.imageView)
        val txtNama=holder.view.findViewById<TextView>(R.id.txtNamaResto)
        val txtAlamat=holder.view.findViewById<TextView>(R.id.txtHarga)
        val progressBar=holder.view.findViewById<ProgressBar>(R.id.progressBar)

        txtNama.setText(restoList[position].nama)
        txtAlamat.setText(restoList[position].alamat)
        imageView.loadImage(restoList[position].logo.toString(), progressBar)

        btnDetail.setOnClickListener {
            val action=RestoListFragmentDirections.actionDetailKulinerFragment(restoList[position].nama.toString())
            Navigation.findNavController(it).navigate(action)
        }
        btnReview.setOnClickListener {
            val action=RestoListFragmentDirections.actionReviewListFragment(restoList[position].nama.toString())
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun updateRestoList(newRestoList: ArrayList<resto>) {
        restoList.clear()
        restoList.addAll(newRestoList)
        notifyDataSetChanged()
    }
}
