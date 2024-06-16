package com.cibertec.cibertecapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cibertec.cibertecapp.notas.Nota
import com.cibertec.cibertecapp.notas.NotaDAO

//en el arreglo se agregan todas las entidades que se van a utilizar
//siempre el version debe ser incrementado de uno en uno en caso de cambios en la estructura de la base de datos sino se perderan los datos
@Database(entities = [Nota::class], version = 1)
abstract class CibertecDatabase: RoomDatabase() {
    abstract fun notaDAO(): NotaDAO

    //lo que va dentro de un companion objet es estatico en este caso el nombre de la base de datos
    // y la instancia y tambien declarado como volatile que quiere decir que ya no se va a ejecutar en la memoria cache
    // sino en la memoria principal para evitar problemas de concurrencia que quiere decir que dos o mas hilos de ejecucion esten accediendo a la misma variable
    //el getInstance es un metodo estatico que se encarga de crear la instancia de la base de datos
    //el buildDatabase es un metodo privado que se encarga de construir la base de datos
    companion object {
        const val DATABASE_NAME = "cibertec_database"
        @Volatile
        private var INSTANCE: CibertecDatabase? = null
        fun getInstance(context: Context):CibertecDatabase{
            return INSTANCE ?: synchronized(this){
                INSTANCE ?: buildDatabase(context).also {
                    INSTANCE = it
                }
            }
        }
        private fun buildDatabase(context: Context):CibertecDatabase{
            return Room.databaseBuilder(
                context.applicationContext,
                CibertecDatabase::class.java,
                DATABASE_NAME)
                .build()
        }
    }
}