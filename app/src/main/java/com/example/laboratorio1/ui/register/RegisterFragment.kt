package com.example.laboratorio1.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.util.PatternsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.laboratorio1.databinding.FragmentRegisterBinding
import java.util.regex.Pattern

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(RegisterViewModel::class.java)

        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.btnContinue.setOnClickListener {
            validate()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun validate() {
        val result = arrayOf(validaMail(), validaPsw(), validaNombre())
        if (false in result) {
            return
        }
        Toast.makeText(MainActivity@ context, "Todo correcto", Toast.LENGTH_LONG).show()
    }

    private fun validaNombre(): Boolean {
        val nombre = binding.itName.editText?.text.toString()
        return if (nombre.isEmpty()) {
            binding.itName.error = "Este campo no puede estar vacio"
            false
        } else {
            binding.itName.error = null
            true
        }
    }

    private fun validaMail(): Boolean {
        val email = binding.itMail.editText?.text.toString()
        return if (email.isEmpty()) {
            binding.itMail.error = "Este campo no puede estar vacio"
            false
        } else if (!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.itMail.error = "Ingrese un correo valido"
            false
        } else {
            binding.itMail.error = null
            true
        }
    }

    private fun validaPsw(): Boolean {
        val pwd = binding.itPwd.editText?.text.toString()
        val passwordRegex = Pattern.compile(
            "^" + //simbolo de inicio
                    "(?=.*[0-9])" + //al menos un digito
                    "(?=.*[a-z])" + //al menos una letra minuscula
                    "(?=.*[A-Z])" + // al menos una mayuscula
                    "(?=.*[@#$%^&+=?!])" + // al menos un caracter especial
                    "(?=\\S+$)" + // no espacios en blanco
                    ".{6,}" + //longitud minima
                    "$" // simbolo de cierre
        )
        return if (pwd.isEmpty()) {
            binding.itPwd.error = "Este campo no puede estar vacio"
            false
        } else if (!passwordRegex.matcher(pwd).matches()) {
            binding.itPwd.error =
                "Ingrese una contraseña con minimo 6 caracteres, 1 mayuscula, 1 minuscula, 1 caracter especial, 1 numero y sin espacios en blanco"
            false
        } else {
            binding.itPwd.error = null
            true
        }
    }
}