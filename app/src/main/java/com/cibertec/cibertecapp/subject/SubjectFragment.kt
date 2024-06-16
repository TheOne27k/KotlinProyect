package com.cibertec.cibertecapp.subject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.cibertecapp.R
import com.cibertec.cibertecapp.cursos.Curso
import com.cibertec.cibertecapp.cursos.CursosAdapter
import com.cibertec.cibertecapp.menu.MenuDrawerAction

class SubjectFragment: Fragment() {
    lateinit var  interfaceMenu: MenuDrawerAction
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_subject, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerCursos = view.findViewById<RecyclerView>(R.id.recyclerCursos)
        val listCursos = listOf<Curso>(
            Curso("Curso de Android", R.drawable.logo_android),
            Curso("Curso de iOS", R.drawable.logo_apple),
            Curso("Curso de Java", R.drawable.logo_java),
            Curso("Curso de OWASP", R.drawable.logo_owasp)
        )
        val toolbarNews = view.findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbarNews)
        toolbarNews.setNavigationOnClickListener{
            interfaceMenu.openMenu()
        }

        val adapter = CursosAdapter(listCursos)
        recyclerCursos.adapter = adapter
        recyclerCursos.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }
    companion object {
        fun newInstance(): SubjectFragment = SubjectFragment()
    }
}