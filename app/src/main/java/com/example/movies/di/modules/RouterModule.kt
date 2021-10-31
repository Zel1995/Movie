package com.example.movies.di.modules

import androidx.fragment.app.FragmentManager
import com.example.movies.ui.main.router.MainRouter
import dagger.Module
import dagger.Provides

@Module
class RouterModule {
    @Provides
    fun providesRouter(fragmentManager: FragmentManager):MainRouter = MainRouter(fragmentManager)
}