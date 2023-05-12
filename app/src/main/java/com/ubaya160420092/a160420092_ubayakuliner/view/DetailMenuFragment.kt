package com.ubaya160420092.a160420092_ubayakuliner.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.ubaya160420092.a160420092_ubayakuliner.R
import com.ubaya160420092.a160420092_ubayakuliner.util.loadImage
import com.ubaya160420092.a160420092_ubayakuliner.viewmodel.*


/**
 * A simple [Fragment] subclass.
 * Use the [DetailMenuFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailMenuFragment : Fragment() {

    private lateinit var detailMenuViewModel: DetailMenuViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var restoName=""
        var menuName=""

        arguments?.let{
            val resto=DetailMenuFragmentArgs.fromBundle(requireArguments()).resto
            restoName=resto.toString()
            val menu=DetailMenuFragmentArgs.fromBundle(requireArguments()).menu
            menuName=menu.toString()
        }

        detailMenuViewModel = ViewModelProvider(this).get(DetailMenuViewModel::class.java)
        detailMenuViewModel.fetch(menuName, restoName)

        detailMenuViewModel.menuLD.observe(viewLifecycleOwner) { menu ->
            val txtNama=view.findViewById<TextView>(R.id.txtNamaMenu)
            val txtHarga=view.findViewById<TextView>(R.id.txtHargaMenu)
            var imageView = view.findViewById<ImageView>(R.id.imageViewMenu)
            var progressBar = view.findViewById<ProgressBar>(R.id.progressBar)

            txtNama.setText(menu[0].nama)
            txtHarga.setText(menu[0].harga)
            imageView.loadImage(menu[0].foto, progressBar)
        }
    }

}