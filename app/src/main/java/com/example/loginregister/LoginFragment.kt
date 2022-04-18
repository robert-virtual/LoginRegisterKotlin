package com.example.loginregister

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.loginregister.databinding.FragmentLoginBinding
// ktor
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import java.lang.reflect.Parameter

// ktor
@Serializable
data class User(
    val id:Int? = null,
    val name:String? = null,
    val email:String,
    val password:String? = null
)

class LoginFragment : Fragment() {

    private val mainScope = MainScope()
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val client = HttpClient(CIO){
        install(ContentNegotiation){
            json()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_login, container, false)
        _binding = FragmentLoginBinding.inflate(inflater,container,false)

        val view = binding.root
        binding.btnEntrar.setOnClickListener {
            mainScope.launch {
                kotlin.runCatching {
                    sendData()
                }.onSuccess {
                   findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                }.onFailure {

                    binding.textError.text = "Ups hubo un error al enviar los datos vuelva a intentar: ${it.localizedMessage}"
                    binding.textError.visibility = View.VISIBLE

                }
            }

        }

        binding.linkCrearCuenta.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_regsiterFragment)
        }
       testRequet()
        return view
    }
    private fun testRequet(){
        mainScope.launch {
            kotlin.runCatching {
                getData()
            }.onSuccess {

                binding.title.text = "Uber!!"
            }.onFailure {

                binding.textError.text = "Ups hubo un error al enviar los datos vuelva a intentar: ${it.localizedMessage}"
                binding.textError.visibility = View.VISIBLE

            }
        }
    }
    private suspend fun getData(){
        client.get("http://192.168.0.13:3000")
    }
    private suspend fun sendData(){
        client.post("http://192.168.0.13:3000/login"){
            contentType(ContentType.Application.Json)
            setBody(
                User(
                    email = binding.etEmail.text.toString(),
                    password = binding.etPassword.text.toString()
                )
            )
        }
    }

}