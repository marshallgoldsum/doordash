package com.mgoldsum.dash.di

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProviders
import com.mgoldsum.dash.*
import com.mgoldsum.dash.network.RestaurantService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import dagger.Binds



@Module
class DashModule {

    @Provides
    fun providesDashApplication():DashApplication {
        return DashApplication.getInstance()
    }

    @Provides
    fun providesContext(dashApplication: DashApplication):Context {
        return dashApplication.applicationContext
    }

    @Provides
    fun providesDashRepository(restaurantService: RestaurantService, context: Context): DashRepository {
        return DashRepository(restaurantService, context)
    }

    @Provides
    fun provideDashViewModelFactory(dashRepository: DashRepository): DashViewModelFactory {
        return DashViewModelFactory(dashRepository)
    }

    @Provides
    fun provideDashViewModelForFragment(
        dashFragment: DashFragment,
        dashViewModelFactory: DashViewModelFactory
    ): DashViewModel {
        return ViewModelProviders.of(dashFragment, dashViewModelFactory).get(DashViewModel::class.java)
    }
}