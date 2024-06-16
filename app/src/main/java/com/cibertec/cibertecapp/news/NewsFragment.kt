package com.cibertec.cibertecapp.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.cibertec.cibertecapp.R
import com.cibertec.cibertecapp.menu.MenuDrawerAction

class NewsFragment: Fragment() {

    lateinit var  interfaceMenu: MenuDrawerAction
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbarNews = view.findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbarNews)
        toolbarNews.setNavigationOnClickListener{
            interfaceMenu.openMenu()
        }
        toolbarNews.inflateMenu(R.menu.menu_toolbar)
        toolbarNews.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.itemCompartir -> {
                    Toast.makeText(context,"Compartir", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.itemActualizar -> {
                    true
                }
                else -> false
            }
        }
    }
    companion object {
        fun newInstance(): NewsFragment = NewsFragment()
    }
}