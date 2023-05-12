package com.ubaya160420092.a160420092_ubayakuliner.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.ubaya160420092.a160420092_ubayakuliner.R
import com.ubaya160420092.a160420092_ubayakuliner.viewmodel.ReviewListViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

/**
 * A simple [Fragment] subclass.
 * Use the [ReviewListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ReviewListFragment : Fragment() {

    private lateinit var viewModel: ReviewListViewModel
    private val reviewListAdapter = ReviewListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_review_list, container, false)
    }

    fun observeViewModel() {
        viewModel.reviewLD.observe(viewLifecycleOwner, Observer {
            reviewListAdapter.updateReviewList(it)
        })

        viewModel.reviewLoadErrorLD.observe(viewLifecycleOwner, Observer {
            val txtError = view?.findViewById<TextView>(R.id.txtError)
            if(it == true) {
                txtError?.visibility = View.VISIBLE
            } else {
                txtError?.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            val recView = view?.findViewById<RecyclerView>(R.id.recViewMenu)
            if(it == true) {
                recView?.visibility = View.GONE
            } else {
                recView?.visibility = View.VISIBLE
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var restoName=""

        arguments?.let{
            val resto=ReviewListFragmentArgs.fromBundle(requireArguments()).resto
            restoName=resto.toString()
        }

        viewModel = ViewModelProvider(this).get(ReviewListViewModel::class.java)
        viewModel.refresh(restoName)

        val recView=view.findViewById<RecyclerView>(R.id.recViewMenu)
        recView.layoutManager = LinearLayoutManager(context)
        recView.adapter = reviewListAdapter

        val refreshLayout=view.findViewById<SwipeRefreshLayout>(R.id.refreshLayout)

        refreshLayout.setOnRefreshListener {
            recView.visibility=View.GONE
            viewModel.refresh(restoName)
            refreshLayout.isRefreshing=false
        }

        observeViewModel()
    }
}