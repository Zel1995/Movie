package com.example.movies.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.movies.R
import com.example.movies.databinding.MainActivityBinding
import com.example.movies.di.App
import com.example.movies.di.MainSubcomponent
import com.example.movies.di.modules.MainActivityModule
import com.example.movies.ui.main.router.MainRouter
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    var mainSubcomponent: MainSubcomponent? = null
    lateinit var viewBinding:MainActivityBinding
    @Inject
    lateinit var router: MainRouter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = MainActivityBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        mainSubcomponent =
            (application as App).appComponent.mainSubcomponent().create(MainActivityModule(this))
        mainSubcomponent?.inject(this)
        if (savedInstanceState == null) {
            router.openMoviesListFragment()
        }
        findViewById<BottomNavigationView>(R.id.bottom_nav_view).setOnItemSelectedListener {item->
            when(item.itemId){
                R.id.item_movies ->{router.openMoviesListFragment()}
                R.id.item_favorite ->{router.openFavoriteFragment()}
                R.id.item_actors ->{router.openActorsFragment()}
            }
            true
        }
    }
    fun setBadgeNumber(count:Int){
        viewBinding.bottomNavView.getOrCreateBadge(R.id.item_favorite).number = count
    }


    override fun onDestroy() {
        super.onDestroy()
        mainSubcomponent = null
    }
}