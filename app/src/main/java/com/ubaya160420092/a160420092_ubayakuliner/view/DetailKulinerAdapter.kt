package com.ubaya160420092.a160420092_ubayakuliner.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya160420092.a160420092_ubayakuliner.R
import com.ubaya160420092.a160420092_ubayakuliner.model.menu
import java.util.ArrayList

class DetailKulinerAdapter (val menuList: ArrayList<menu>): RecyclerView.Adapter<DetailKulinerAdapter.KulinerViewHolder>() {

    class KulinerViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KulinerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.menu_list_item, parent, false)

        return KulinerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return menuList.size
    }

    override fun onBindViewHolder(holder: DetailKulinerAdapter.KulinerViewHolder, position: Int) {
        val btnDetail=holder.view.findViewById<Button>(R.id.btnDetailMenu)
        val txtNama=holder.view.findViewById<TextView>(R.id.textNamaMenu)
        var namaMenu= menuList[position].nama.toString()
        var namResto=menuList[position].resto.toString()

        txtNama.setText(menuList[position].nama)

        btnDetail.setOnClickListener {
            val action=DetailKulinerFragmentDirections.actionDetailMenu(namaMenu, namResto)
            Log.d("showvoley", "nama menu $namaMenu, $namResto")
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun updateKulinerList(newKulinerList: ArrayList<menu>) {
        menuList.clear()
        menuList.addAll(newKulinerList)
        notifyDataSetChanged()
    }
}