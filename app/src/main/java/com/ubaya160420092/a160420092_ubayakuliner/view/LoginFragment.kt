package com.ubaya160420092.a160420092_ubayakuliner.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.android.material.textfield.TextInputEditText
import com.ubaya160420092.a160420092_ubayakuliner.R
import com.ubaya160420092.a160420092_ubayakuliner.model.user
import com.ubaya160420092.a160420092_ubayakuliner.viewmodel.LoginViewModel
import com.ubaya160420092.a160420092_ubayakuliner.viewmodel.SharedViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnLogin=view.findViewById<Button>(R.id.btnLogin)
        val btnRegis=view.findViewById<Button>(R.id.btnRegister)
        var txtUsername=view.findViewById<TextInputEditText>(R.id.txtUsername)
        var txtPassword=view.findViewById<TextInputEditText>(R.id.txtPassword)

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        btnLogin.setOnClickListener{
            loginViewModel.fetch(txtUsername.text.toString(), txtPassword.text.toString())
            loginViewModel.userLD.observe(viewLifecycleOwner) { user ->
                if (user.isNotEmpty()) {

                    val viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
                    viewModel.username = user[0].username


                    val action = LoginFragmentDirections.actionRestoListFragment()
                    Navigation.findNavController(it).navigate(action)
                }
                else{
                    Toast.makeText(requireContext(), "Username atau Password Salah", Toast.LENGTH_SHORT).show()
                }
            }

        }

        btnRegis.setOnClickListener{
            val action=LoginFragmentDirections.actionRegisterFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }
}