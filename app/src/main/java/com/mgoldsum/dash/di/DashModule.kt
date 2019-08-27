package com.mgoldsum.dash.di

import androidx.lifecycle.ViewModelProviders
import com.mgoldsum.dash.DashFragment
import com.mgoldsum.dash.DashRepository
import com.mgoldsum.dash.DashViewModel
import com.mgoldsum.dash.DashViewModelFactory
import com.mgoldsum.dash.network.RestaurantService
import dagger.Module
import dagger.Provides

@Module
class DashModule {

    @Provides
    fun providesDashRepository(restaurantService: RestaurantService): DashRepository {
        return DashRepository(restaurantService)
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