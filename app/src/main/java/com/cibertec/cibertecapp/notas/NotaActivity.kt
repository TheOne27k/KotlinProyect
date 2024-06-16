package com.cibertec.cibertecapp.notas

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.cibertecapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class NotaActivity:AppCompatActivity() {
    private lateinit var viewModel: NotaViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nota)

        viewModel = ViewModelProvider(this).get(NotaViewModel::class.java)

        val recyclerNotas = findViewById<RecyclerView>(R.id.recyclerNotas)
        val floatingRegister = findViewById<FloatingActionButton>(R.id.floatinRegister)
        floatingRegister.setOnClickListener {
            registerAndUpdateNote()
        }
        val adapter = NotaAdapter()
        recyclerNotas.adapter = adapter
        recyclerNotas.layoutManager = LinearLayoutManager(this)
        viewModel.getNotas().observe(this) {notas ->
            if(notas.isNotEmpty()){
                notas?.let {
                    adapter.setNotas(it)
                }
            }
        }
    }
    private fun registerAndUpdateNote() {
        val mDialogView = LayoutInflater.from(this).inflate(R.layout.dialog_nota, null)

        val titleAlertNote = "Registrar nota"

        val mBuilder = AlertDialog.Builder(this)
            .setView(mDialogView)
            .setTitle(titleAlertNote)

        val mAlertDialog = mBuilder.show()

        val buttonCreate  = mDialogView.findViewById<Button>(R.id.btnCreate)
        val editTextTitleCreate  = mDialogView.findViewById<EditText> (R.id.edtTitleNote)
        val editTextDescriptionCreate  = mDialogView.findViewById<EditText>(R.id.edtDescriptionNote)

        buttonCreate.setOnClickListener {

            mAlertDialog.dismiss()

            val titleNote = editTextTitleCreate.text.toString()
            val descriptionNote = editTextDescriptionCreate.text.toString()
            val currentDateTime = LocalDateTime.now().formatChangeNote()

            val nota = Nota(currentDateTime, titleNote,
                descriptionNote)
            viewModel.insertNotas(nota)

        }

    }

    fun LocalDateTime.formatChangeNote() : String
            = this.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))
}