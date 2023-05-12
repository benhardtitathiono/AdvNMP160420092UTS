package com.ubaya160420092.a160420092_ubayakuliner.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ubaya160420092.a160420092_ubayakuliner.R
import com.ubaya160420092.a160420092_ubayakuliner.model.review
import java.util.ArrayList

class ReviewListAdapter(val reviewList: ArrayList<review>): RecyclerView.Adapter<ReviewListAdapter.ReviewViewHolder>() {

    class ReviewViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.resto_list_item, parent, false)

        return ReviewViewHolder(view)
    }

    override fun getItemCount(): Int {
        return reviewList.size
    }

    override fun onBindViewHolder(holder: ReviewListAdapter.ReviewViewHolder, position: Int) {
        val txtNama=holder.view.findViewById<TextView>(R.id.txtNamaResto)
        val txtReview=holder.view.findViewById<TextView>(R.id.txtHarga)

        txtNama.setText(reviewList[position].username)
        txtReview.setText(reviewList[position].review)
    }

    fun updateReviewList(newReviewList: ArrayList<review>) {
        reviewList.clear()
        reviewList.addAll(newReviewList)
    }
}