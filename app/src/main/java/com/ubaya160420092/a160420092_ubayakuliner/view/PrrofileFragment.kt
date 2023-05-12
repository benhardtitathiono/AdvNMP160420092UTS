package com.ubaya160420092.a160420092_ubayakuliner.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText

import com.ubaya160420092.a160420092_ubayakuliner.R
import com.ubaya160420092.a160420092_ubayakuliner.util.loadImage
import com.ubaya160420092.a160420092_ubayakuliner.viewmodel.LoginViewModel
import com.ubaya160420092.a160420092_ubayakuliner.viewmodel.SharedViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [PrrofileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PrrofileFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_prrofile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        loginViewModel.fetch(viewModel.username.toString())

        loginViewModel.userLD.observe(viewLifecycleOwner) { user ->
            val txtNama=view.findViewById<TextInputEditText>(R.id.txtName)
            val txtEmail=view.findViewById<TextInputEditText>(R.id.txtEmail)
            val txtPhoneNum=view.findViewById<TextInputEditText>(R.id.txtPhone)
            var imageView = view.findViewById<ImageView>(R.id.imageViewMenu)
            var progressBar = view.findViewById<ProgressBar>(R.id.progressBar)

            txtNama.setText(user[0].username)
            txtEmail.setText(user[0].email)
            txtPhoneNum.setText(user[0].nohp)
            imageView.loadImage(user[0].foto, progressBar)
        }
    }
}