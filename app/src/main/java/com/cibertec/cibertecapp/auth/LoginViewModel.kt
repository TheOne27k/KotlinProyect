package com.cibertec.cibertecapp.auth

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel: ViewModel() {
    private lateinit var auth : FirebaseAuth
    val userLoginStatus = MutableLiveData<Boolean>()
    val userLoginStatus2 = MutableLiveData<String>()
    fun verifyLogin(email: String, password: String){
        if(email.isEmpty() && password.isEmpty()) {
            userLoginStatus.value = false
            userLoginStatus2.value = "Ingrese sus datos"
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            userLoginStatus.value = false
            userLoginStatus2.value = "Ingrese un correo válido"
        } else if (password.length < 5) {
            userLoginStatus.value = false
            userLoginStatus2.value = "Ingrese una contraseña mayor a 5 caracteres"
        } else {
            loginFirebase(email, password)
        }

    }
    private fun loginFirebase(email: String, password: String) {
        auth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                userLoginStatus.value = it.isSuccessful
                // esto de arriba es como poner un if(it.isSuccessful) { userLoginStatus.value = true } else { userLoginStatus.value = false }
            }
    }
}