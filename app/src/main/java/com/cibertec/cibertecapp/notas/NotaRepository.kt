package com.cibertec.cibertecapp.notas

import android.app.Application
import androidx.lifecycle.LiveData
import com.cibertec.cibertecapp.database.CibertecDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class NotaRepository(application: Application) {
    // aqui se hace la conexion con la base de datos
    private val  notaDao = CibertecDatabase.getInstance(application).notaDAO()
    //aqui se obtienen las notas
    //es un contenedor de datos observables o sea que se actualiza automaticamente cuando cambia la base de datos
    fun getNotas() : LiveData<List<Nota>>{
        return notaDao.list()
    }
    //aqui se insertan las notas
    //debe tener el withContext para que se ejecute en un hilo secundario y no genere problemas de concurrencia o sea que la aplicacion se cuelgue
    // es declarado como suspend para que pueda ser suspendido y no genere problemas de concurrencia
    // osea se puede suspender en base al ciclo de vida de la aplicacion
    suspend fun insert(nota: Nota){
        withContext(Dispatchers.IO){
            notaDao.insert(nota)
        }
    }
}