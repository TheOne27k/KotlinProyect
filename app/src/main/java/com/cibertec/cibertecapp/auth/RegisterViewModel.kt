package com.cibertec.cibertecapp.auth

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class RegisterViewModel:ViewModel() {
    private lateinit var auth: FirebaseAuth

    val userRegisterStatus = MutableLiveData<Boolean>()

    fun verifyRegister(email: String, password: String) {
        if(email.isEmpty() && password.isEmpty()) {
            userRegisterStatus.value = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            userRegisterStatus.value = false
        } else if (password.length < 5) {
            userRegisterStatus.value = false
        } else {
            registerFirebase(email, password)
        }
    }
    private fun registerFirebase(email: String, password: String) {
        auth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                userRegisterStatus.value = it.isSuccessful
            }
    }
}