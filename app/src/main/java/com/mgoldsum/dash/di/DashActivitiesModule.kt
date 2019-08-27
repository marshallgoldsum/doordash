package com.mgoldsum.dash.di

import com.mgoldsum.dash.DashActivity
import com.mgoldsum.dash.DashFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DashActivitiesModule {

    @ContributesAndroidInjector
    abstract fun bindFeedbackActivity(): DashActivity

    @ContributesAndroidInjector(modules = [DashModule::class])
    abstract fun provideFeedbackFragment(): DashFragment
}
