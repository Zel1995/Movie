package com.example.movies.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.movies.R
import com.example.movies.di.App
import com.example.movies.di.MainSubcomponent
import com.example.movies.di.modules.MainActivityModule
import com.example.movies.ui.main.router.MainRouter
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    var mainSubcomponent: MainSubcomponent? = null

    @Inject
    lateinit var router: MainRouter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        mainSubcomponent =
            (application as App).appComponent.mainSubcomponent().create(MainActivityModule(this))
        mainSubcomponent?.inject(this)
        if (savedInstanceState == null) {
            router.openMoviesListFragment()
        }
        findViewById<BottomNavigationView>(R.id.bottom_nav_view).setOnItemSelectedListener {item->
            when(item.itemId){
                R.id.item_movies ->{router.openMoviesListFragment()}
                R.id.item_favorite ->{}
                R.id.item_history ->{}
            }
            true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mainSubcomponent = null
    }
}