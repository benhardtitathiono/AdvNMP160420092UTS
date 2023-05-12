package com.ubaya160420092.a160420092_ubayakuliner.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ubaya160420092.a160420092_ubayakuliner.R
import com.ubaya160420092.a160420092_ubayakuliner.viewmodel.DetailKulinerVIewModel

/**
 * A simple [Fragment] subclass.
 * Use the [DetailKulinerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailKulinerFragment : Fragment() {

    private lateinit var viewModel: DetailKulinerVIewModel
    private val kulinerListAdapter = DetailKulinerAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_kuliner, container, false)
    }

    fun observeViewModel() {
        viewModel.menuLD.observe(viewLifecycleOwner, Observer {
            kulinerListAdapter.updateKulinerList(it)
        })

        viewModel.menuLoadErrorLD.observe(viewLifecycleOwner, Observer {
            val txtError = view?.findViewById<TextView>(R.id.txtError)
            if(it == true) {
                txtError?.visibility = View.VISIBLE
            } else {
                txtError?.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            val recView = view?.findViewById<RecyclerView>(R.id.recViewMenu)
            val progressLoad = view?.findViewById<ProgressBar>(R.id.progressLoad)
            if(it == true) {
                recView?.visibility = View.GONE
                progressLoad?.visibility = View.VISIBLE
            } else {
                recView?.visibility = View.VISIBLE
                progressLoad?.visibility = View.GONE
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var restoName=""
        val txtNama=view.findViewById<TextView>(R.id.txtNamaResto)

        arguments?.let{
            val resto=ReviewListFragmentArgs.fromBundle(requireArguments()).resto
            restoName=resto.toString()
        }

        txtNama.setText(restoName)
        viewModel = ViewModelProvider(this).get(DetailKulinerVIewModel::class.java)
        viewModel.refresh(restoName)

        val recView=view.findViewById<RecyclerView>(R.id.recViewMenu)
        recView.layoutManager = LinearLayoutManager(context)
        recView.adapter = kulinerListAdapter

        val refreshLayout=view.findViewById<SwipeRefreshLayout>(R.id.refreshLayout)
        val txtError = view.findViewById<TextView>(R.id.txtError)
        val progressLoad = view.findViewById<ProgressBar>(R.id.progressLoad)
        val btnReservasi= view.findViewById<Button>(R.id.btnReserve)

        btnReservasi.setOnClickListener {
            val action=DetailKulinerFragmentDirections.actionReservassiFragment()
            Navigation.findNavController(it).navigate(action)
        }

        refreshLayout.setOnRefreshListener {
            recView.visibility=View.GONE
            txtError.visibility=View.GONE
            progressLoad.visibility=View.VISIBLE
            viewModel.refresh(restoName)
            refreshLayout.isRefreshing=false
        }

        observeViewModel()
    }
}