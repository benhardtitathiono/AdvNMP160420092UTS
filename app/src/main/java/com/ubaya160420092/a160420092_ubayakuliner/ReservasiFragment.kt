package com.ubaya160420092.a160420092_ubayakuliner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputEditText
import com.ubaya160420092.a160420092_ubayakuliner.view.DetailKulinerFragmentDirections


/**
 * A simple [Fragment] subclass.
 * Use the [ReservasiFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ReservasiFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reservasi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnReservasi=view.findViewById<Button>(R.id.btnReservasi)
        val txtJumlahTamu=view.findViewById<TextInputEditText>(R.id.txtJumTamu)

        btnReservasi.setOnClickListener {
            Toast.makeText(requireContext(), "Reservasi berhasil, Reservasi untuk total ${txtJumlahTamu.text.toString()} orang tamu", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }
    }
}