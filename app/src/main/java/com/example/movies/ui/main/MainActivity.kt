package com.example.movies.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.movies.R
import com.example.movies.di.App
import com.example.movies.ui.main.router.MainRouter
import com.example.movies.ui.main.router.RouterHolder

class MainActivity : AppCompatActivity(), RouterHolder {
    override val router: MainRouter = MainRouter(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as App).appComponent.inject(this)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            router.openMoviesListFragment()
        }
    }
}